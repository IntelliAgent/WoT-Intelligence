package ca.unknown.replaydecoder.decompression;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/**
 * Inspired by http://stackoverflow.com/q/6173920/600500.
 */
public class ZlibCompression {

    public static byte[] decompressData(FileInputStream fileInputStream) throws IOException, DataFormatException {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];

        for (int readNum; (readNum = fileInputStream.read(buf)) != -1; ) {
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

}
