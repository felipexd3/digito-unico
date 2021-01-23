package com.fb.onedigit.util;

import com.fb.onedigit.exceptions.ApplicationException;
import com.fb.onedigit.messages.Messages;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

@Component
public class RSAUtil {
    public static PublicKey decodePublicKey(String publicKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(new X509EncodedKeySpec(Base64.decodeBase64(publicKey)));
        } catch (Exception e) {
            throw new ApplicationException(Messages.DECODE_FAILED);
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
