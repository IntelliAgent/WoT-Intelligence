package ca.unknown.replaydecoder.decryption;

import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.SecureRandom;

import static org.junit.Assert.assertArrayEquals;

public class ReplayDecryterTest {

    private ReplayDecrypter replayDecrypter;

    @Test
    public void whenCryptingAndDecryptingTheOriginAndResultOfDecryptingShouldBeTheSame() throws Throwable

    {
        SecureRandom random = new SecureRandom();
        byte[] KEY =
            {(byte) 0xDE, (byte) 0x72, (byte) 0xBE, (byte) 0xA0, (byte) 0xDE, (byte) 0x04, (byte) 0xBE, (byte) 0xB1,
                (byte) 0xDE, (byte) 0xFE, (byte) 0xBE, (byte) 0xEF, (byte) 0xDE, (byte) 0xAD, (byte) 0xBE, (byte) 0xEF};
        SecretKeySpec KS = new SecretKeySpec(KEY, "Blowfish");

        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.ENCRYPT_MODE, KS);
        String randomData = new BigInteger(512, random).toString().substring(0, 64);

        byte[] encrypted = cipher.doFinal(randomData.getBytes());
        replayDecrypter = new ReplayDecrypter(encrypted);

        assertArrayEquals(replayDecrypter.decrypt(), randomData.getBytes());
    }
}
