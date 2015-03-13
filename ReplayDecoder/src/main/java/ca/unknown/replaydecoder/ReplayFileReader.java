package ca.unknown.replaydecoder;

import ca.unknown.replaydecoder.swapper.ByteSwapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

public class ReplayFileReader {

    private static final String MAGIC_NUMBER = "12323411";
    private static final int POS_NUMBER_OF_BLOCKS = 4;
    private static final int POS_FIRST_BLOCK_SIZE = 8;
    private static final int POS_SECOND_BLOCK_SIZE = 4;
    private static final int OFFSET_CRYPTED_SIZE = 4;
    private RandomAccessFile randomAccessFile;

    public ReplayFileReader(File file) {
        try {
            randomAccessFile = new RandomAccessFile(file, "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean validateMagicNumber() {
        try {
            randomAccessFile.seek(0);
            int magicNumberReadFromFile = randomAccessFile.readInt();
            return String.format("%04X", magicNumberReadFromFile).equals(MAGIC_NUMBER);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getNumberOfBlocks() {
        try {
            randomAccessFile.seek(POS_NUMBER_OF_BLOCKS);
            return randomAccessFile.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getFirstBlockSize() {
        try {
            randomAccessFile.seek(POS_FIRST_BLOCK_SIZE);
            return ByteSwapper.swap(randomAccessFile.readInt());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getSecondBlockSize() {
        try {
            int posSecondBlock = POS_NUMBER_OF_BLOCKS + POS_FIRST_BLOCK_SIZE + getFirstBlockSize();
            randomAccessFile.seek(posSecondBlock);
            return ByteSwapper.swap(randomAccessFile.readInt());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getSecondBlock(int blockSize) {
        byte[] json = ByteBuffer.allocate(blockSize).array();
        try {
            randomAccessFile.seek(
                POS_NUMBER_OF_BLOCKS + POS_NUMBER_OF_BLOCKS + getFirstBlockSize() + POS_SECOND_BLOCK_SIZE);
            randomAccessFile.read(json, 0, blockSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getReadableJsonData(json);
    }

    public int getCryptedPartSize() {
        int positionCryptedPart =
            POS_NUMBER_OF_BLOCKS + POS_FIRST_BLOCK_SIZE + getFirstBlockSize() + POS_SECOND_BLOCK_SIZE
                + getSecondBlockSize() + OFFSET_CRYPTED_SIZE;
        try {
            randomAccessFile.seek(positionCryptedPart);
            return ByteSwapper.swap(randomAccessFile.readInt());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public byte[] getCryptedBlock(int cryptedSize) {
        int positionCryptedPart =
            POS_NUMBER_OF_BLOCKS + POS_FIRST_BLOCK_SIZE + getFirstBlockSize() + POS_SECOND_BLOCK_SIZE
                + getSecondBlockSize() + OFFSET_CRYPTED_SIZE + 4;
        System.out.println(positionCryptedPart);
        byte[] crypted = ByteBuffer.allocate(cryptedSize).array();
        try {
            randomAccessFile.seek(positionCryptedPart);
            randomAccessFile.read(crypted, 0, cryptedSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return crypted;
    }

    public String getFirstBlock(int blockSize) {
        byte[] json = ByteBuffer.allocate(blockSize).array();
        try {
            randomAccessFile.seek(POS_FIRST_BLOCK_SIZE + POS_SECOND_BLOCK_SIZE);
            randomAccessFile.read(json, 0, blockSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getReadableJsonData(json);
    }

    private String getReadableJsonData(byte[] json) {
        StringBuilder buffer = new StringBuilder(json.length);
        for (byte bit : json) {
            buffer.append((char) bit);
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(buffer.toString());
        return gson.toJson(je);
    }
}
