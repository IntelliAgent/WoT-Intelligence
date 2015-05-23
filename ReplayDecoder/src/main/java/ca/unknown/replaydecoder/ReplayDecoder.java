package ca.unknown.replaydecoder;

import ca.unknown.replaydecoder.decompression.ReplayDecompressor;
import ca.unknown.replaydecoder.decryption.ReplayDecrypter;
import ca.unknown.replaydecoder.exception.CannotDecodeReplayException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class ReplayDecoder {

    protected final ReplayFileReader replayFileReader;
    private final Path defaultDirectory;

    public ReplayDecoder(ReplayFileReader replayFileReader, Path outputDirectory) {
        this.replayFileReader = replayFileReader;
        this.defaultDirectory = outputDirectory;
    }


    protected void decode() {
        byte[] compressedCrypted = replayFileReader.getCryptedBlock();
        String replayExtracted =
                replayFileReader.getReplayName().substring(0, replayFileReader.getReplayName().indexOf(".wotreplay"));

        String decompressed = defaultDirectory.toString() + replayExtracted + " - Decompressed.dat";
        ReplayDecrypter replayDecrypter = new ReplayDecrypter(compressedCrypted);

        String decryptedFile = defaultDirectory.toString() + replayExtracted + " - Decrypted.dat";
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

        } catch (Exception e) {
            throw new CannotDecodeReplayException("Cannot decode replay", e);
        }
    }
}
