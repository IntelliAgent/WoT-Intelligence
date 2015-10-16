package ca.intelliagent.replaydecoder;

import ca.intelliagent.replaydecoder.decompression.ReplayDecompressor;
import ca.intelliagent.replaydecoder.decompression.ZlibCompression;
import ca.intelliagent.replaydecoder.decryption.ReplayDecrypter;
import ca.intelliagent.replaydecoder.exception.CannotDecodeReplayException;

import java.nio.ByteBuffer;
import java.nio.file.Path;
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
            throw new CannotDecodeReplayException("Cannot decode replay because of : " + e.getMessage(), e);
        }
    }

    //TODO: The output directory should be given here
    //TODO: Refactor this method so it uses the decode method
    public ByteBuffer decodeToOutputDirectory() {
        byte[] compressedCrypted = replayFileReader.getCryptedBlock();

        ReplayDecrypter replayDecrypter = new ReplayDecrypter(compressedCrypted);

        try {
            ByteBuffer decrypted = replayDecrypter.decrypt();

            ReplayDecompressor replayDecompressor = new ReplayDecompressor(decrypted.array());

            byte[] decompressedData = replayDecompressor.unzip();

            return ByteBuffer.wrap(decompressedData);

        } catch (Exception e) {
            throw new CannotDecodeReplayException("Cannot decode replay because of : " + e.getMessage(), e);
        }
    }
}
