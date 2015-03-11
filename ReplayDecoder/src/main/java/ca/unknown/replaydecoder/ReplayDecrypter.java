package ca.unknown.replaydecoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class ReplayDecrypter {

    private static final int BLOCK_SIZE = 8;
    private final byte[] KEY =
        {(byte) 0xDE, (byte) 0x72, (byte) 0xBE, (byte) 0xA0, (byte) 0xDE, (byte) 0x04, (byte) 0xBE,
            (byte) 0xB1, (byte) 0xDE, (byte) 0xFE, (byte) 0xBE, (byte) 0xEF, (byte) 0xDE,
            (byte) 0xAD, (byte) 0xBE, (byte) 0xEF};

    private final byte[] bytes;

    public ReplayDecrypter(byte[] bytes) {
        this.bytes = bytes;
    }

    public byte[] decrypt() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(KEY.length);
        byteBuffer.put(KEY);
        try {
            return decryptBlowfish(bytes, byteBuffer.array());
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] decryptBlowfish(byte[] to_decrypt, byte[] strkey)
        throws BadPaddingException {

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("C:\\Data\\BeforeDecryption.txt");
            fos.write(to_decrypt);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            SecretKeySpec key = new SecretKeySpec(strkey, "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, key);

            int padding_size = BLOCK_SIZE - (to_decrypt.length % BLOCK_SIZE);

            if (padding_size == 0 || padding_size == 8) {
                cipher.update(to_decrypt);
                return cipher.doFinal(to_decrypt);
            } else {
                int requiredSize = to_decrypt.length + padding_size;
                ByteBuffer byteBuffer = ByteBuffer.allocate(requiredSize);
                byteBuffer.put(to_decrypt);
                cipher.update(byteBuffer.array());
                return cipher.doFinal(byteBuffer.array());
            }
        } catch (IllegalBlockSizeException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
