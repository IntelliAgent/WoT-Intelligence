package ca.unknown.replaydecoder;

import ca.unknown.replaydecoder.decompression.ReplayDecompressor;
import ca.unknown.replaydecoder.decryption.ReplayDecrypter;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public abstract class ReplayDecoder {

    protected final ReplayFileReader replayFileReader;

    public ReplayDecoder(ReplayFileReader replayFileReader) {
        this.replayFileReader = replayFileReader;
    }


    protected void decode() {
        byte[] compressedCrypted = replayFileReader.getCryptedBlock();
        String replayExtracted =
            replayFileReader.getReplayName().substring(0, replayFileReader.getReplayName().indexOf(".wotreplay"));

        String decompressed = "C:\\replays\\" + replayExtracted + " - Decompressed.dat";
        ReplayDecrypter replayDecrypter = new ReplayDecrypter(compressedCrypted);

        String decryptedFile = "C:\\replays\\" + replayExtracted + " - Decrypted.dat";
        FileOutputStream decompressFile = null;
        FileInputStream fis = null;

        try {
            FileOutputStream fos = new FileOutputStream(decryptedFile);
            System.out.println("Decrypting : " + replayExtracted);
            replayDecrypter.decrypt(fos);


            fis = new FileInputStream(decryptedFile);
            decompressFile = new FileOutputStream(decompressed);
            ReplayDecompressor replayDecompressor = new ReplayDecompressor(fis);

            System.out.println("Decompressing : " + replayExtracted);
            byte[] decompressedData = replayDecompressor.unzip();
            decompressFile.write(decompressedData);
            fis.close();
            Files.delete(Paths.get(decryptedFile));

        } catch (NoSuchPaddingException | BadPaddingException | NoSuchAlgorithmException | IllegalBlockSizeException | IOException | InvalidKeyException e) {
            System.err.println(e.getMessage());
        }
    }
}
