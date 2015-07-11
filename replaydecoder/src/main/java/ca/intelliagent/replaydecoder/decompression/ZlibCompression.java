package ca.intelliagent.replaydecoder.decompression;

import java.io.*;
import java.util.Arrays;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/**
 * Inspired by http://stackoverflow.com/q/6173920/600500.
 */
public final class ZlibCompression {

    private static Inflater DECOMPRESSOR = new Inflater();
    private static byte[] dataBytes = new byte[10 * 1024 * 1024];

    public static byte[] decompressData(FileInputStream fileInputStream) throws IOException, DataFormatException {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];

        int readNum;
        while ((readNum = fileInputStream.read(buf)) != -1) {
            bos.write(buf, 0, readNum);
        }

        byte[] bytes = bos.toByteArray();

        DECOMPRESSOR.setInput(bytes);

        int resultLength = DECOMPRESSOR.inflate(dataBytes);
        dataBytes = Arrays.copyOf(dataBytes, resultLength);
        DECOMPRESSOR.end();

        return dataBytes;
    }

    public static byte[] decompressData(byte[] data) throws DataFormatException {
        DECOMPRESSOR.setInput(data);

        int resultLength = DECOMPRESSOR.inflate(dataBytes);
        dataBytes = Arrays.copyOf(dataBytes, resultLength);
        DECOMPRESSOR.end();

        return dataBytes;
    }

}
