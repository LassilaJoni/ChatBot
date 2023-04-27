package com.chatbot.models;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * Utility class for handling password hashing and verification.
 */
public class Password {
    private static SecureRandom random = new SecureRandom();

    /**
     * Generates a random byte array for use as a salt.
     *
     * @return A 16-byte salt
     */
    public static byte[] getSalt() {
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    /**
     * Generates a hash using the given password and salt.
     *
     * @param password The password to hash
     * @param salt     The salt to use
     * @return A byte array representing the hashed password
     */
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

    /**
     * Checks if the given password is correct by comparing its hash to the expected hash.
     *
     * @param password     The password to check
     * @param salt         The salt used for hashing
     * @param expectedHash The expected hash to compare against
     * @return True if the password is correct, false otherwise
     */
    public static boolean isCorrectPassword(String password, byte[] salt, byte[] expectedHash) {
        byte[] passwordHash = hash(password, salt);
        if (passwordHash.length != expectedHash.length) return false;
        for (int i = 0; i < passwordHash.length; i++) {
            if (passwordHash[i] != expectedHash[i]) return false;
        }
        return true;
    }
}