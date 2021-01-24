package com.fb.onedigit.util;

import com.fb.onedigit.exceptions.ApplicationException;
import com.fb.onedigit.messages.Messages;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;

@Component
public class RSAUtil {
    private static final int LENGTH = 2048;

    public static PublicKey decodePublicKey(String publicKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            var publicKeyDecoded = (RSAPublicKey) keyFactory.generatePublic(new X509EncodedKeySpec(Base64.decodeBase64(publicKey)));
            validatedRSAKey(publicKeyDecoded);
            return publicKeyDecoded;
        } catch (ApplicationException e) {
            throw new ApplicationException(Messages.INVALID_PUBLIC_KEY);
        } catch (Exception e) {
            throw new ApplicationException(Messages.DECODE_FAILED);
        }
    }

    private static void validatedRSAKey(RSAPublicKey publicKeyDecoded) {
        if(publicKeyDecoded.getModulus().bitLength() < LENGTH) {
            throw new ApplicationException(Messages.INVALID_PUBLIC_KEY);
        }
    }

    public static String crypter(String value, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return Base64.encodeBase64String(cipher.doFinal(value.getBytes()));
        } catch (Exception e) {
            throw new ApplicationException(Messages.CRYPTOGRAPHY_FAILED);
        }
    }
}
