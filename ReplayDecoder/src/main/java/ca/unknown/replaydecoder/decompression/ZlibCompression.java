package ca.unknown.replaydecoder.decompression;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.Inflater;

/**
 * Example program to demonstrate how to use zlib compression with
 * Java.
 * Inspired by http://stackoverflow.com/q/6173920/600500.
 */
public class ZlibCompression {

    private static final Logger LOG = Logger.getLogger(String.valueOf(ZlibCompression.class));

    /**
     * Decompresses a zlib compressed file.
     */
    public static String decompressFile(byte[] compressed) throws IOException {
        byte[] output = new byte[compressed.length * 10];

        try {

            // Decompress the bytes
            Inflater decompresser = new Inflater(true);
            decompresser.setInput(compressed, 0, compressed.length);
            byte[] result = new byte[100];
            int resultLength = decompresser.inflate(output);
            decompresser.end();

            // Decode the bytes into a String
            return new String(result, 0, resultLength, "UTF-8");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        LOG.log(Level.FINE, "Original: " + compressed.length);
        LOG.log(Level.FINE, "Compressed: " + output.length);
        return null;
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
