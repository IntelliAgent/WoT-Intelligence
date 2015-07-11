package ca.intelliagent.replaydecoder.decompression;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/**
 * Inspired by http://stackoverflow.com/q/6173920/600500.
 */
public final class ZlibCompression {

    public static byte[] decompressData(FileInputStream fileInputStream) throws IOException, DataFormatException {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];

        int readNum;
        while ((readNum = fileInputStream.read(buf)) != -1) {
            bos.write(buf, 0, readNum);
        }

        byte[] bytes = bos.toByteArray();

        Inflater decompresser = new Inflater();
        decompresser.setInput(bytes);

        byte[] dataBytes = new byte[10 * 1024 * 1024];

        int resultLength = decompresser.inflate(dataBytes);
        dataBytes = Arrays.copyOf(dataBytes, resultLength);
        decompresser.end();

        return dataBytes;
    }

    public static byte[] decompressData(byte[] data) throws DataFormatException{
        Inflater decompresser = new Inflater();
        decompresser.setInput(data);

        byte[] dataBytes = new byte[10 * 1024 * 1024];

        int resultLength = decompresser.inflate(dataBytes);
        dataBytes = Arrays.copyOf(dataBytes, resultLength);
        decompresser.end();

        return dataBytes;
    }

}
