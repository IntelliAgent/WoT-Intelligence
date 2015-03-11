package ca.unknown.replaydecoder;

import ca.unknown.replaydecoder.compression.ZlibCompression;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ReplayDecompressor {


    private final byte[] compressedData;

    public ReplayDecompressor(byte[] compressedData) {
        this.compressedData = compressedData;
    }

    public String unzip() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ZlibCompression.decompressFile(compressedData, byteArrayOutputStream.toByteArray());

            System.out.println(byteArrayOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
