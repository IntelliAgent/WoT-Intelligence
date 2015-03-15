package ca.unknown.replaydecoder.decompression;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/**
 * Example program to demonstrate how to use zlib compression with
 * Java.
 * Inspired by http://stackoverflow.com/q/6173920/600500.
 */
public class ZlibCompression {

    private static final Logger LOG = Logger.getLogger(String.valueOf(ZlibCompression.class));

    public static byte[] decompressData(FileInputStream fileInputStream) throws IOException, DataFormatException {
        // Decompress the bytes
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        try {
            for (int readNum; (readNum = fileInputStream.read(buf)) != -1; ) {
                bos.write(buf, 0, readNum);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
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
