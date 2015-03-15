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
import java.util.HashMap;
import java.util.Map;

public class ReplayFileReader {

    private static final String MAGIC_NUMBER = "12323411";
    private int numberOfBlocks;
    private Map<Integer, Integer> dataBlockSize = new HashMap<>();
    private Map<Integer, Integer> dataBlockPosition = new HashMap<>();
    private RandomAccessFile randomAccessFile;

    public ReplayFileReader(File file) {
        try {
            randomAccessFile = new RandomAccessFile(file, "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void init() throws IOException {
        int startPointer = 8;
        int blockNumber = 1;
        randomAccessFile.seek(4);
        numberOfBlocks = ByteSwapper.swap(randomAccessFile.readInt());

        System.out.println("Number of blocks : " + numberOfBlocks);
        if (numberOfBlocks == 0) {
            return;
        }

        while (numberOfBlocks >= 1) {
            randomAccessFile.seek(startPointer);
            dataBlockSize.put(blockNumber, ByteSwapper.swap(randomAccessFile.readInt()));
            dataBlockPosition.put(blockNumber, startPointer + 4);
            startPointer = dataBlockPosition.get(blockNumber) + dataBlockSize.get(blockNumber);
            numberOfBlocks--;
            blockNumber++;
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

    public String getFirstBlock() {
        byte[] json = ByteBuffer.allocate(getBlockSize(1)).array();
        try {
            randomAccessFile.seek(getBlockPosition(1));
            randomAccessFile.read(json, 0, getBlockSize(1));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getReadableJsonData(json);
    }

    public String getSecondBlock() {
        Integer blockSize = getBlockSize(2);
        byte[] json = ByteBuffer.allocate(blockSize).array();

        try {
            randomAccessFile.seek(getBlockPosition(2));
            randomAccessFile.read(json, 0, blockSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getReadableJsonData(json);
    }

    public int getCryptedPartSize() {
        int positionCryptedPart = dataBlockPosition.get(2) + getBlockSize(2);
        System.out.println(positionCryptedPart);
        try {
            randomAccessFile.seek(positionCryptedPart);
            return ByteSwapper.swap(randomAccessFile.readInt());
        } catch (IOException ignored) {

        }
        return 0;
    }

    public byte[] getCryptedBlock() {
        int positionCryptedPart = getBlockPosition(2) + getBlockSize(2) + 4;
        int cryptedSize = getCryptedPartSize();

        byte[] crypted = ByteBuffer.allocate(cryptedSize).array();
        try {
            randomAccessFile.seek(positionCryptedPart);
            randomAccessFile.read(crypted, 0, cryptedSize);
        } catch (IOException ignored) {

        }
        return crypted;
    }

    private Integer getBlockPosition(int position) {
        return dataBlockPosition.get(position);
    }

    private Integer getBlockSize(int blockNumber) {
        return dataBlockSize.get(blockNumber);
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
