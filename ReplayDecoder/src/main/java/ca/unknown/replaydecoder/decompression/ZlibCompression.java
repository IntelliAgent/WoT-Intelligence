package ca.unknown.replaydecoder.decompression;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * Example program to demonstrate how to use zlib compression with
 * Java.
 * Inspired by http://stackoverflow.com/q/6173920/600500.
 */
public class ZlibCompression {

    private static final Logger LOG = Logger.getLogger(String.valueOf(ZlibCompression.class));

    /**
     * Compresses a file with zlib compression.
     */
    public static byte[] compressFile(byte[] raw) throws IOException {
        try {
            Deflater df = new Deflater(Deflater.BEST_COMPRESSION);
            df.setInput(raw);
            ByteArrayOutputStream baos = new ByteArrayOutputStream(raw.length);
            df.finish();
            byte[] buff = new byte[1024];
            while (!df.finished()) {
                int count = df.deflate(buff);
                baos.write(buff, 0, count);
            }
            baos.close();
            byte[] output = baos.toByteArray();
            return output;
        } catch (IOException e) {
            return null;
        }
    }

    public static byte[] decompressData(byte[] input) throws IOException, DataFormatException {
        // Decompress the bytes
        Inflater decompresser = new Inflater();
        decompresser.setInput(input);
        byte[] dataBytes = new byte[input.length * 10];
        int resultLength = decompresser.inflate(dataBytes);
        decompresser.end();
        return dataBytes;
    }

    /**
     * Shovels all data from an input stream to an output stream.
     */
    private static void shovelInToOut(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[10 * 1024 * 1024];
        int len;
        while ((len = in.read()) > 0) {
            out.write(buffer, 0, len);
        }
    }
}
