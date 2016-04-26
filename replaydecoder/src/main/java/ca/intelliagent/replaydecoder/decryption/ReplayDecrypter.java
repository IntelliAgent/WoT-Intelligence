package ca.intelliagent.replaydecoder.decryption;

import ca.intelliagent.replaydecoder.decryption.exception.CannotDecryptReplayException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class ReplayDecrypter {

    private static final int BLOCK_SIZE = 8;
    private static final byte[] KEY =
            {(byte) 0xDE, (byte) 0x72, (byte) 0xBE, (byte) 0xA0, (byte) 0xDE, (byte) 0x04, (byte) 0xBE, (byte) 0xB1,
                    (byte) 0xDE, (byte) 0xFE, (byte) 0xBE, (byte) 0xEF, (byte) 0xDE, (byte) 0xAD, (byte) 0xBE, (byte) 0xEF};

    private final byte[] bytes;

    public ReplayDecrypter(byte[] bytes) {
        this.bytes = bytes;
    }

    private static ByteBuffer decryptBlowfish(byte[] to_decrypt) {
        try {
            Cipher cipher = getCipher();

            int padding_size = BLOCK_SIZE - (to_decrypt.length % BLOCK_SIZE);
            byte[] paddedToDecrypted = Arrays.copyOfRange(to_decrypt, 0, to_decrypt.length + padding_size);

            byte[] previous = cipher.update(paddedToDecrypted, 0, BLOCK_SIZE);

            int paddedLength = paddedToDecrypted.length;

            ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream(paddedLength);
            byteOutputStream.write(previous);

            for (int i = 8; i < paddedLength; i += BLOCK_SIZE) {
                byte[] decrypt = cipher.update(paddedToDecrypted, i, BLOCK_SIZE);
                xorArrays(previous, decrypt);
                byteOutputStream.write(previous, 0, BLOCK_SIZE);
            }
            cipher.doFinal();

            return ByteBuffer.wrap(byteOutputStream.toByteArray());

        } catch (Exception e) {
            throw new CannotDecryptReplayException("Cannot decrypt replay exception", e);
        }
    }

    private static Cipher getCipher() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        SecretKeySpec key = new SecretKeySpec(KEY, "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher;
    }

    private static void xorArrays(byte[] a, byte[] b) {
        for (int i = 0; i < a.length; i++) {
            a[i] = (byte) (a[i] ^ b[i]);
        }
    }

    public ByteBuffer decrypt() {
        return decryptBlowfish(bytes);
    }
}
