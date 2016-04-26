package ca.intelliagent.replaydecoder.decompression;

import java.io.IOException;
import java.util.zip.DataFormatException;

public class ReplayDecompressor {

    private final byte[] cryptedFile;

    public ReplayDecompressor(byte[] fis) {
        cryptedFile = fis;
    }

    public byte[] unzip() {
        try {
            return ZlibCompression.decompressData(cryptedFile);
        } catch (DataFormatException | IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }
}
