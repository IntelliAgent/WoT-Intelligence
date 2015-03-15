package ca.unknown.replaydecoder.decompression;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;

public class ReplayDecompressor {

    private final FileInputStream cryptedFile;

    public ReplayDecompressor(FileInputStream fis) {
        this.cryptedFile = fis;
    }

    public byte[] unzip() {

        try {
            return ZlibCompression.decompressData(cryptedFile);
        } catch (IOException | DataFormatException ignored) {


        }
        return null;
    }
}
