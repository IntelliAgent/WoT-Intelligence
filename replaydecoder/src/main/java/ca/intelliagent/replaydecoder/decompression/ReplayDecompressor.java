package ca.intelliagent.replaydecoder.decompression;

import java.util.zip.DataFormatException;

public class ReplayDecompressor {

    private final byte[] cryptedFile;

    public ReplayDecompressor(byte[] fis) {
        cryptedFile = fis;
    }

    public byte[] unzip() {
        try {
            return ZlibCompression.decompressData(cryptedFile);
        } catch (DataFormatException ignored) {
            return new byte[0];
        }
    }
}
