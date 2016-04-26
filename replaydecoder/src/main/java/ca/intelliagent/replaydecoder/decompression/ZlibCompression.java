package ca.intelliagent.replaydecoder.decompression;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public final class ZlibCompression {

    private static final Logger LOG = Logger.getLogger(String.valueOf(ZlibCompression.class));

    public static synchronized byte[] decompressData(byte[] data) throws DataFormatException, IOException {
        Inflater decompresser = new Inflater();
        decompresser.setInput(data);

        byte[] dataBytes = new byte[10 * 1024 * 1024];

        int resultLength = decompresser.inflate(dataBytes);
        dataBytes = Arrays.copyOf(dataBytes, resultLength);
        decompresser.end();

        return dataBytes;
    }

}
