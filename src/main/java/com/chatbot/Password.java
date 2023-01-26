package com.chatbot;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class Password {
    private static SecureRandom random = new SecureRandom();

    //Gets a random byte array for salt
    public static byte[] getSalt() {
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
    //Generates the hash with password and salt
    public static byte[] hash(String password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 256); //65536
        SecretKeyFactory factory;
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Hashin error: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }
    //Checks if inputted password is correct
    public static boolean isCorrectPassword(String password, byte[] salt, byte[] expectedHash) {
        byte[] passwordHash = hash(password, salt);
        if(passwordHash.length != expectedHash.length) return false;
        for(int i = 0; i < passwordHash.length; i++) {
            if(passwordHash[i] != expectedHash[i]) return false;
        }
        return true;
    }
}