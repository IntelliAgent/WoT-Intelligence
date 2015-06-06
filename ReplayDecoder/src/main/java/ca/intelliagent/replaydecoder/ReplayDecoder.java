package ca.intelliagent.replaydecoder;

import ca.intelliagent.replaydecoder.decompression.ReplayDecompressor;
import ca.intelliagent.replaydecoder.decryption.ReplayDecrypter;
import ca.intelliagent.replaydecoder.exception.CannotDecodeReplayException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.file.*;

public abstract class ReplayDecoder {

    protected final ReplayFileReader replayFileReader;
    protected final Path defaultDirectory;

    public ReplayDecoder(ReplayFileReader replayFileReader, Path outputDirectory) {
        this.replayFileReader = replayFileReader;
        defaultDirectory = outputDirectory;
    }


    protected ByteBuffer decode() {
        byte[] compressedCrypted = replayFileReader.getCryptedBlock();
        String replayExtracted =
                replayFileReader.getReplayName().substring(0, replayFileReader.getReplayName().indexOf(".wotreplay"));

        String decompressed = defaultDirectory + replayExtracted + " - Decompressed.dat";
        ReplayDecrypter replayDecrypter = new ReplayDecrypter(compressedCrypted);

        String decryptedFile = defaultDirectory + replayExtracted + " - Decrypted.dat";
        FileOutputStream decompressFile = null;
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(decryptedFile);

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

            return ByteBuffer.wrap(decompressedData);

        } catch (Exception e) {
            throw new CannotDecodeReplayException("Cannot decode replay", e);
        }
    }
}
