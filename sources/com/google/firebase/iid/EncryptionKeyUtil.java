package com.google.firebase.iid;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.ECGenParameterSpec;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes2.dex */
public class EncryptionKeyUtil {
    public static KeyPair generateEcP256KeyPair() {
        return getEcKeyGen().generateKeyPair();
    }

    public static KeyPair generateRSA2048KeyPair() throws NoSuchAlgorithmException {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e9) {
            throw new AssertionError(e9);
        }
    }

    private static KeyPairGenerator getEcKeyGen() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
            try {
                try {
                    keyPairGenerator.initialize(new ECGenParameterSpec("prime256v1"));
                    return keyPairGenerator;
                } catch (InvalidAlgorithmParameterException unused) {
                    keyPairGenerator.initialize(new ECGenParameterSpec("secp256r1"));
                    return keyPairGenerator;
                }
            } catch (InvalidAlgorithmParameterException unused2) {
                throw new RuntimeException("Unable to find the NIST P-256 curve");
            }
        } catch (NoSuchAlgorithmException e9) {
            throw new RuntimeException(e9);
        }
    }

    public static boolean isEcP256Supported() {
        try {
            getEcKeyGen();
            return false;
        } catch (Exception unused) {
            return true;
        }
    }

    public static SecretKey parseAESKey(byte[] bArr) {
        return new SecretKeySpec(bArr, "AES");
    }
}
