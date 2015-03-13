package ca.unknown.replaydecoder.decompression;

import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.zip.DataFormatException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotEquals;

public class ZlibCompressionTest {

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();


    @Test
    public void whenCompressingBytesShouldDecompressWithOriginBytes() throws IOException, DataFormatException {
        byte[] source = new byte[] {1};
        byte[] result = ZlibCompression.compressFile(source);
        assertNotEquals(source, result);
        byte[] output = ZlibCompression.decompressData(result);
        byte[] truncateOutput = Arrays.copyOfRange(output, 0, source.length);
        assertArrayEquals(truncateOutput, source);
    }

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
