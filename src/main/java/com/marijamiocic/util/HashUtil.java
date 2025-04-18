package com.marijamiocic.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utility class for hashing and checking passwords.
 */
public class HashUtil {

    /**
     * Hashes a plaintext password using SHA-256.
     * @param password The plaintext password to hash.
     * @return The hashed password as a hexadecimal string.
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    /**
     * Compares a plaintext password to a previously hashed password.
     * @param plainPassword The plaintext password to check.
     * @param hashedPassword The hashed password to compare against.
     * @return true if the password matches, false otherwise.
     */
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return hashPassword(plainPassword).equals(hashedPassword);
    }
}
