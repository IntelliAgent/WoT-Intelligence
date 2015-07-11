package ca.intelliagent.replaydecoder.decompression;

import java.util.Arrays;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/**
 * Inspired by http://stackoverflow.com/q/6173920/600500.
 */
public final class ZlibCompression {

    private static Inflater DECOMPRESSOR = new Inflater();
    private static byte[] dataBytes = new byte[10 * 1024 * 1024];

    public static byte[] decompressData(byte[] data) throws DataFormatException {
        DECOMPRESSOR.setInput(data);

        int resultLength = DECOMPRESSOR.inflate(dataBytes);
        dataBytes = Arrays.copyOf(dataBytes, resultLength);
        DECOMPRESSOR.end();

        return dataBytes;
    }

}
