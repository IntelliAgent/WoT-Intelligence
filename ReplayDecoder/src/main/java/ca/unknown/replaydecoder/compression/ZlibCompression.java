package ca.unknown.replaydecoder.compression;

import ca.unknown.replaydecoder.swapper.ByteSwapper;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
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

    /**
     * Decompresses a zlib compressed file.
     */
    public static byte[] decompressFile(byte[] compressed) throws IOException {

        Path path = Paths.get("C:\\Data\\Python.txt");

        Inflater inflater = new Inflater();
        byte[] compressedFile = Files.readAllBytes(path);
        inflater.setInput(compressedFile);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(compressedFile.length);
        byte[] buffer = new byte[10*1024*1024];
        try {
            while (!inflater.finished()) {
                int count = 0;

                count = inflater.inflate(compressedFile);

                outputStream.write(buffer, 0, count);
            }
        } catch (DataFormatException e) {
            e.printStackTrace();
        }
        outputStream.close();
        byte[] output = outputStream.toByteArray();

        LOG.log(Level.FINE, "Original: " + compressed.length);
        LOG.log(Level.FINE, "Compressed: " + output.length);
        return output;
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
