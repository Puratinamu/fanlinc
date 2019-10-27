package ca.utoronto.utm.mcs.projectcloudinfantry.token;

import org.apache.tomcat.util.codec.binary.Base64;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Utility methods to parse and create {@link java.security.Key} instances.
 */
public final class KeyUtil {

    private static final String PUBLIC_KEY_HEADER = "-----BEGIN PUBLIC KEY-----";
    private static final String PUBLIC_KEY_FOOTER = "-----END PUBLIC KEY-----";
    private static final String PRIVATE_KEY_HEADER = "-----BEGIN PRIVATE KEY-----";
    private static final String PRIVATE_KEY_FOOTER = "-----END PRIVATE KEY-----";

    private KeyUtil() {}

    /**
     * Creates {@link RSAPrivateKey} key.
     *
     * @param privateKey string containing private key
     * @return {@code RSAPrivateKey} key.
     */
    public static RSAPrivateKey parsePrivateKey(String privateKey) throws KeyUtilException {
        String privateKeyNoHeaders = privateKey.replace(PRIVATE_KEY_HEADER, "")
            .replace(PRIVATE_KEY_FOOTER, "").replace("\n", "");
        byte[] der = Base64.decodeBase64(privateKeyNoHeaders);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(der);

        try {
            return (RSAPrivateKey) createKeyFactory().generatePrivate(spec);
        } catch (InvalidKeySpecException ex) {
            throw new KeyUtilException("failed to parse RSA private key", ex);
        }
    }

    /**
     * Creates {@link RSAPublicKey} key.
     *
     * @param publicKey string containing private key
     * @return {@code RSAPublicKey} key.
     */
    public static RSAPublicKey parsePublicKey(String publicKey) throws KeyUtilException {
        String publicKeyNoHeaders = publicKey.replace(PUBLIC_KEY_HEADER, "")
            .replace(PUBLIC_KEY_FOOTER, "").replace("\n", "");
        byte[] der = Base64.decodeBase64(publicKeyNoHeaders);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(der);

        try {
            return (RSAPublicKey) createKeyFactory().generatePublic(spec);
        } catch (InvalidKeySpecException ex) {
            throw new KeyUtilException("failed to parse RSA public key", ex);
        }
    }

    private static KeyFactory createKeyFactory() throws KeyUtilException {
        try {
            return KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException ex) {
            throw new KeyUtilException("failed to create KeyFactory", ex);
        }
    }
}
