package ca.unknown.replaydecoder.decryption;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class ReplayDecrypter {

    private static final int BLOCK_SIZE = 8;
    private static final byte[] KEY =
        {(byte) 0xDE, (byte) 0x72, (byte) 0xBE, (byte) 0xA0, (byte) 0xDE, (byte) 0x04, (byte) 0xBE, (byte) 0xB1,
            (byte) 0xDE, (byte) 0xFE, (byte) 0xBE, (byte) 0xEF, (byte) 0xDE, (byte) 0xAD, (byte) 0xBE, (byte) 0xEF};

    private final byte[] bytes;

    public ReplayDecrypter(byte[] bytes) {
        this.bytes = bytes;
    }

    private static void decryptBlowfish(byte[] to_decrypt, FileOutputStream replayDecrypted) {
        try {
            SecretKeySpec key = new SecretKeySpec(KEY, "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, key);

            int padding_size = BLOCK_SIZE - (to_decrypt.length % BLOCK_SIZE);
            byte[] paddedToDecrypted = Arrays.copyOfRange(to_decrypt, 0, to_decrypt.length + padding_size);


            byte[] decrypted = cipher.update(to_decrypt, 0, 8);
            byte[] previous = decrypted;

            replayDecrypted.write(decrypted);

            for (int i = 8; i < paddedToDecrypted.length - 8; i += BLOCK_SIZE) {
                byte[] toDecrypt = Arrays.copyOfRange(to_decrypt, i, i + BLOCK_SIZE);
                byte[] decrypt = cipher.update(toDecrypt);
                previous = xorArrays(previous, decrypt);
                replayDecrypted.write(previous, 0, 8);
            }
            byte[] toDecrypt = Arrays.copyOfRange(to_decrypt, paddedToDecrypted.length - 8, paddedToDecrypted.length);
            byte[] decrypt = cipher.doFinal(toDecrypt);
            previous = xorArrays(previous, decrypt);
            replayDecrypted.write(previous, 0, 8);
            replayDecrypted.close();

        } catch (NoSuchPaddingException | NoSuchAlgorithmException | BadPaddingException | IOException | IllegalBlockSizeException | InvalidKeyException e) {
            System.err.println(e.getMessage());
        }



    }

    /**
     * Computes array-wise XOR.
     *
     * @param a the first array.
     * @param b the second array.
     * @return the XOR-ed array.
     */
    public static byte[] xorArrays(byte[] a, byte[] b) {
        byte[] xor = new byte[a.length];

        for (int i = 0; i < a.length; i++) {
            xor[i] = (byte) (a[i] ^ b[i]);
        }

        return xor;
    }

    public void decrypt(FileOutputStream replayDecrypted)
        throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, BadPaddingException,
        IllegalBlockSizeException, InvalidKeyException {
        decryptBlowfish(bytes, replayDecrypted);
    }


}
