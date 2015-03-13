package ca.unknown.replaydecoder.decompression;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;

public class ReplayDecompressor {

    private final FileOutputStream decompressedFile;
    private final FileInputStream cryptedFile;

    public ReplayDecompressor(FileInputStream fis, FileOutputStream decompressFile) {
        this.cryptedFile = fis;
        this.decompressedFile = decompressFile;
    }

    public byte[] unzip() {

        try {
            return ZlibCompression.decompressData(cryptedFile);
        } catch (IOException | DataFormatException ignored) {


        }
        return null;
    }
}
