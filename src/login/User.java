package login;

import java.security.SecureRandom;
import java.util.Random;
import java.security.MessageDigest;
/**
 * Created by C on 4/1/2017.
 */
public class User {
    private String password;
    private String username;
    private byte[] salt;
    private static final Random RANDOM = new SecureRandom();
    private static final DBConnect conn = DBConnect.getInstance();
    private String hash;

    public User(String username, String password) {
        this.username   =   username;
        this.password   =   password;
        this.salt       =   genSalt();
        try {
            this.hash   =   PassToHash();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getUsername() {
        return this.username;
    }

    public User(String username, String password, String salt) {
        this.username   =   username;
        this.password   =   password;
        try {
            this.hash   =   PassToHash(new StringBuilder(salt).reverse().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public byte[] getSalt() {
        return this.salt;
    }

    public String getHash() {
        return this.hash;
    }

    private byte[] genSalt() {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);

        return salt;
    }

    private String PassToHash() throws Exception { //pentru generare de hash (signup)
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update((this.password+this.salt).getBytes());
        byte s[] = m.digest();
        String result = "";
        for (int i = 0; i < s.length; i++) {
            result += Integer.toHexString((0x000000ff & s[i]) | 0xffffff00).substring(6);
        }
        return result;
    }
    private String PassToHash(String verifySalt) throws Exception { //pentru verificare credentiale
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update((this.password+verifySalt).getBytes());
        byte s[] = m.digest();
        String result = "";
        for (int i = 0; i < s.length; i++) {
            result += Integer.toHexString((0x000000ff & s[i]) | 0xffffff00).substring(6);
        }
        return result;
    }
}
