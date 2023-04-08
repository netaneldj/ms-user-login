package com.jamilis.login.utils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class EncryptUtils {
    private static final String ENCRYPT_ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final String SECRET_KEY_ALGORITHM = "AES";
    private static final String ENCODING = "UTF-8";
    private static final String SHA = "SHA-1";
    private static SecretKeySpec secretKeySpec;
    private static byte[] key;

    private static void setKey (final String myKey) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest sha = null;
        key = myKey.getBytes(ENCODING);
        sha = MessageDigest.getInstance(SHA);
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16);
        secretKeySpec = new SecretKeySpec(key, SECRET_KEY_ALGORITHM);
    }

    public static String encrypt(final String strToEncrypt, final String secret) throws GeneralSecurityException,
            UnsupportedEncodingException {
        setKey(secret);
        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        return Base64.getEncoder()
                .encodeToString(cipher.doFinal(strToEncrypt.getBytes(ENCODING)));
    }

    public static String decrypt(final String strToDecrypt, final String secret) throws GeneralSecurityException,
            UnsupportedEncodingException {
        setKey(secret);
        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        return new String(cipher.doFinal(Base64.getDecoder()
                .decode(strToDecrypt)));
    }
}
