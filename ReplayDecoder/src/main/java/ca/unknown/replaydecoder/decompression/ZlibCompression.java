package ca.unknown.replaydecoder.decompression;

import java.io.*;
import java.util.zip.InflaterInputStream;

/**
 * Example program to demonstrate how to use zlib compression with
 * Java.
 * Inspired by http://stackoverflow.com/q/6173920/600500.
 */
public class ZlibCompression {

    /**
     * Decompresses a zlib compressed file.
     */
    public static void decompressFile(byte[] compressed, byte[] raw) throws IOException {

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(compressed);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        InputStream in = new InflaterInputStream(byteArrayInputStream);
        shovelInToOut(in, byteArrayOutputStream);
        in.close();
        byteArrayOutputStream.close();
    }

    /**
     * Shovels all data from an input stream to an output stream.
     */
    private static void shovelInToOut(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1000];
        int len;
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
    }
}
