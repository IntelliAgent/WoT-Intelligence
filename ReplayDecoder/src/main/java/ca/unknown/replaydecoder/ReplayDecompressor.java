package ca.unknown.replaydecoder;

import ca.unknown.replaydecoder.compression.ZlibCompression;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ReplayDecompressor {


    private final byte[] compressedData;

    public ReplayDecompressor(byte[] compressedData) {
        this.compressedData = compressedData;
    }

    public String unzip() {

        try {
            byte[] decompressedData = ZlibCompression.decompressFile(compressedData);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decompressedData);
            System.out.println(byteArrayInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
