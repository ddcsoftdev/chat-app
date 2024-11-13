package com.chatapp.infraestructure.encryption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class EncryptionUtil {
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private final SecretKey secretKey;

    public EncryptionUtil(@Value("${app.encryption.secret-key}") String base64Key) {
        byte[] decodedKey = Base64.getDecoder().decode(base64Key);
        this.secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

    public String encrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);

        byte[] iv = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

        byte[] encryptedBytes = cipher.doFinal(data.getBytes());

        // Prepend IV to encrypted data
        byte[] encryptedWithIv = new byte[iv.length + encryptedBytes.length];
        System.arraycopy(iv, 0, encryptedWithIv, 0, iv.length);
        System.arraycopy(encryptedBytes, 0, encryptedWithIv, iv.length, encryptedBytes.length);

        return Base64.getEncoder().encodeToString(encryptedWithIv);
    }

    public String decrypt(String encryptedData) throws Exception {
        byte[] encryptedWithIv = Base64.getDecoder().decode(encryptedData);

        // Extract IV from encrypted data
        byte[] iv = new byte[16];
        System.arraycopy(encryptedWithIv, 0, iv, 0, iv.length);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Extract actual encrypted message
        byte[] encryptedBytes = new byte[encryptedWithIv.length - iv.length];
        System.arraycopy(encryptedWithIv, iv.length, encryptedBytes, 0, encryptedBytes.length);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }
}
