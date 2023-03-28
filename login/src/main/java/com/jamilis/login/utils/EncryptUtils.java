package com.jamilis.login.utils;

import javax.crypto.*;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EncryptUtils {
    private static String ALGORITHM = "AES";
    private static Integer BLOCK_SIZE = 128;
    private static Cipher cipher;

    private static SecretKey generateKey (int n) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(n);
        return keyGenerator.generateKey();
    }

    public static String encrypt(String plainText) throws GeneralSecurityException {
        cipher = Cipher.getInstance(ALGORITHM);
        byte[] plainTextByte = plainText.getBytes();
        cipher.init(Cipher.ENCRYPT_MODE, generateKey(BLOCK_SIZE));
        byte[] encryptedByte = cipher.doFinal(plainTextByte);
        Base64.Encoder encoder = Base64.getEncoder();
        String encryptedText = encoder.encodeToString(encryptedByte);
        return encryptedText;
    }

    public static String decrypt(String encryptedText) throws GeneralSecurityException {
        cipher = Cipher.getInstance(ALGORITHM);
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] encryptedTextByte = decoder.decode(encryptedText);
        cipher.init(Cipher.DECRYPT_MODE, generateKey(BLOCK_SIZE));
        byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
        String decryptedText = new String(decryptedByte);
        return decryptedText;
    }
}
