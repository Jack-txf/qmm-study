package com.feng.chat.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class TokenSecretUtil {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";
    private static final String SECRET_KEY = "txfChat";
    // 生成一个基于SECRET_KEY的SecretKey对象（这里简化了密钥生成过程，仅用于示例）
    private static SecretKey getSecretKey() {
        return new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);
    }
    // 加密uid并返回加密后的字符串
    public static String enCodeToken(Long uid, String token) throws Exception {
        SecretKey secretKey = getSecretKey();
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // 将uid转换为字节数组并加密
        byte[] uidBytes = Long.toString(uid).getBytes(StandardCharsets.UTF_8);
        byte[] encryptedUidBytes = cipher.doFinal(uidBytes);

        // 将加密后的uid转换为Base64字符串
        String encryptedUid = Base64.getEncoder().encodeToString(encryptedUidBytes);

        // 拼接加密后的uid和token
        return encryptedUid + "-" + token;
    }

    // 解密并还原uid
    public static Long deCodeToken(String encodedToken) throws Exception {
        String[] parts = encodedToken.split("-");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid encoded token format");
        }

        String encryptedUid = parts[0];
        String token = parts[1]; // 可以验证token，但这里不做处理

        SecretKey secretKey = getSecretKey();
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        // 将加密后的uid从Base64字符串转换回字节数组
        byte[] encryptedUidBytes = Base64.getDecoder().decode(encryptedUid);

        // 解密uid字节数组
        byte[] decryptedUidBytes = cipher.doFinal(encryptedUidBytes);

        // 将解密后的字节数组转换为Long
        return Long.parseLong(new String(decryptedUidBytes, StandardCharsets.UTF_8));
    }

}
