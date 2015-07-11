package ca.intelliagent.replaydecoder;

import ca.intelliagent.replaydecoder.decompression.ReplayDecompressor;
import ca.intelliagent.replaydecoder.decompression.ZlibCompression;
import ca.intelliagent.replaydecoder.decryption.ReplayDecrypter;
import ca.intelliagent.replaydecoder.exception.CannotDecodeReplayException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.DataFormatException;

public abstract class ReplayDecoder {

    protected final ReplayFileReader replayFileReader;
    protected Path outputDirectory;

    protected ReplayDecoder(ReplayFileReader replayFileReader, Path outputDirectory) {
        this.replayFileReader = replayFileReader;
        this.outputDirectory = outputDirectory;
    }

    protected ReplayDecoder(ReplayFileReader replayFileReader) {
        this.replayFileReader = replayFileReader;
    }

    public ByteBuffer decode() {
        final byte[] compressedCrypted = replayFileReader.getCryptedBlock();
        final ReplayDecrypter replayDecrypter = new ReplayDecrypter(compressedCrypted);
        ByteBuffer decrypted = replayDecrypter.decrypt();
        try {
            return ByteBuffer.wrap(ZlibCompression.decompressData(decrypted.array()));
        } catch (DataFormatException e) {
            throw new CannotDecodeReplayException("Cannot decode replay", e);
        }
    }

    //TODO The output directory should be given here
    //TODO Refactor this method so it uses the decode method
    public ByteBuffer decodeToOutputDirectory() {
        byte[] compressedCrypted = replayFileReader.getCryptedBlock();

        String replayExtracted =
                replayFileReader.getReplayName().substring(0, replayFileReader.getReplayName().indexOf(".wotreplay"));

        StringBuilder sb = new StringBuilder();
        String decompressed = sb.append(outputDirectory).append(replayExtracted).append(" - Decompressed.dat").toString();
        ReplayDecrypter replayDecrypter = new ReplayDecrypter(compressedCrypted);

        sb = new StringBuilder();
        String decryptedFile = sb.append(outputDirectory).append(replayExtracted).append(" - Decrypted.dat").toString();
        FileOutputStream decompressFile;
        FileInputStream fis;
        FileOutputStream fos;

        try {
            fos = new FileOutputStream(decryptedFile);

            System.out.println("Decrypting : " + replayExtracted);
            ByteBuffer decrypted = replayDecrypter.decrypt();


            fis = new FileInputStream(decryptedFile);
            decompressFile = new FileOutputStream(decompressed);
            ReplayDecompressor replayDecompressor = new ReplayDecompressor(decrypted.array());

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
