package ca.unknown.replaydecoder.decompression;

import java.io.IOException;
import java.util.zip.DataFormatException;

public class ReplayDecompressor {


    private final byte[] compressedData;

    public ReplayDecompressor(byte[] compressedData) {
        this.compressedData = compressedData;
    }

    public byte[] unzip() {

        try {
            return ZlibCompression.decompressData(compressedData);
        } catch (IOException | DataFormatException e) {
            e.printStackTrace();
        }
        return null;
    }
}
