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
    private static final int POS_NUMBER_JSON_BLOCKS = 4;
    private static final int POS_SIZE_FIRST_JSON = 8;
    private static final int OFFSET_SECOND_JSON = 4;
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

    public int getNumberOfJSONBlocks() {
        try {
            randomAccessFile.seek(POS_NUMBER_JSON_BLOCKS);
            return ByteSwapper.swap(randomAccessFile.readInt());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getFirstJSONBlockSize() {
        try {
            randomAccessFile.seek(POS_SIZE_FIRST_JSON);
            return ByteSwapper.swap(randomAccessFile.readInt());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getSecondJSONBlockSize() {
        try {
            int positionSecondJson =
                POS_NUMBER_JSON_BLOCKS + POS_SIZE_FIRST_JSON + getFirstJSONBlockSize();
            randomAccessFile.seek(positionSecondJson);
            return ByteSwapper.swap(randomAccessFile.readInt());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getSecondJson(int jsonSize) {
        byte[] json = ByteBuffer.allocate(jsonSize).array();
        try {
            randomAccessFile.seek(
                POS_NUMBER_JSON_BLOCKS + POS_SIZE_FIRST_JSON + getFirstJSONBlockSize()
                    + OFFSET_SECOND_JSON);
            randomAccessFile.read(json, 0, jsonSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getReadableJsonData(json);
    }

    public int getCryptedSizePart() {
        int positionCryptedPart = POS_NUMBER_JSON_BLOCKS + POS_SIZE_FIRST_JSON + POS_SIZE_FIRST_JSON + getFirstJSONBlockSize() + getSecondJSONBlockSize();
        try {
            randomAccessFile.seek(positionCryptedPart);
            return ByteSwapper.swap(randomAccessFile.readInt());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public byte[] getCryptedPart(int cryptedSize) {
        int positionCryptedPart = POS_NUMBER_JSON_BLOCKS + POS_NUMBER_JSON_BLOCKS + POS_SIZE_FIRST_JSON + POS_SIZE_FIRST_JSON + getFirstJSONBlockSize() + getSecondJSONBlockSize();
        byte[] crypted = ByteBuffer.allocate(cryptedSize).array();
        try {
            randomAccessFile.seek(positionCryptedPart);
            randomAccessFile.read(crypted, 0, cryptedSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return crypted;
    }

    public String getFirstJson(int jsonSize) {
        byte[] json = ByteBuffer.allocate(jsonSize).array();
        try {
            randomAccessFile.seek(POS_SIZE_FIRST_JSON + OFFSET_SECOND_JSON);
            randomAccessFile.read(json, 0, jsonSize);
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
