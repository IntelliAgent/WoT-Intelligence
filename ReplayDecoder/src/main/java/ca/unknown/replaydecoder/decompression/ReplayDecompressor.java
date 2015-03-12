package ca.unknown.replaydecoder.decompression;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ReplayDecompressor {


    private final byte[] compressedData;

    public ReplayDecompressor(byte[] compressedData) {
        this.compressedData = compressedData;
    }

    public String unzip() {

        try {
            String decompressedData = ZlibCompression.decompressFile(compressedData);
            return decompressedData;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
