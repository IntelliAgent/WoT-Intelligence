package ca.unknown.replaydecoder;

import ca.unknown.replaydecoder.exception.*;
import ca.unknown.replaydecoder.swapper.ByteSwapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class ReplayFileReader {

    private static final String MAGIC_NUMBER = "12323411";
    private final File file;
    private int numberOfBlocks;
    private Map<Integer, Integer> dataBlockSize = new HashMap<>();
    private Map<Integer, Integer> dataBlockPosition = new HashMap<>();
    private RandomAccessFile randomAccessFile;

    public ReplayFileReader(File file) {
        this.file = file;
    }

    public int getNumberOfBlocks() {
        return numberOfBlocks;
    }

    public void init() {
        int startPointer = 8;
        int blockNumber = 1;
        try {
            randomAccessFile = new RandomAccessFile(file, "r");
            randomAccessFile.seek(4);

            numberOfBlocks = ByteSwapper.swap(randomAccessFile.readInt());
            int tmpNumberOfBlocks = numberOfBlocks;
            System.out.println("Number of blocks : " + numberOfBlocks);
            if (numberOfBlocks == 0) {
                return;
            }

            while (tmpNumberOfBlocks >= 1) {
                randomAccessFile.seek(startPointer);
                dataBlockSize.put(blockNumber, ByteSwapper.swap(randomAccessFile.readInt()));
                dataBlockPosition.put(blockNumber, startPointer + 4);
                startPointer = dataBlockPosition.get(blockNumber) + dataBlockSize.get(blockNumber);
                tmpNumberOfBlocks--;
                blockNumber++;
            }

        } catch (IOException e) {
            throw new CannotInitializeReplayDecoderException("Cannot initialize replay decoder", e);
        }
    }

    public boolean validateMagicNumber() {
        try {
            randomAccessFile.seek(0);
            int magicNumberReadFromFile = randomAccessFile.readInt();
            return String.format("%04X", magicNumberReadFromFile).equals(MAGIC_NUMBER);
        } catch (IOException e) {
            throw new CannotValidateMagicNumber("Cannot validate magic number", e);
        }
    }

    public String getFirstBlock() {
        byte[] json = ByteBuffer.allocate(getBlockSize(1)).array();
        try {
            randomAccessFile.seek(getBlockPosition(1));
            randomAccessFile.read(json, 0, getBlockSize(1));
        } catch (IOException e) {
            throw new CannotReadFirstBlockException("Cannot read first block", e);
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
            throw new CannotReadSecondBlockException("Cannot read second block", e);
        }
        return getReadableJsonData(json);
    }

    public int getCryptedPartSize() {
        int positionCryptedPart = dataBlockPosition.get(numberOfBlocks) + getBlockSize(numberOfBlocks);
        try {
            randomAccessFile.seek(positionCryptedPart);
            return ByteSwapper.swap(randomAccessFile.readInt());
        } catch (IOException e) {
            throw new CannotGetCryptedPartSizeException("Cannot get crypted part size", e);
        }
    }

    public byte[] getCryptedBlock() {
        int positionCryptedPart = getBlockPosition(numberOfBlocks) + getBlockSize(numberOfBlocks) + 8;
        int cryptedSize = getCryptedPartSize();

        byte[] crypted = ByteBuffer.allocate(cryptedSize).array();
        try {
            randomAccessFile.seek(positionCryptedPart);
            randomAccessFile.read(crypted, 0, cryptedSize);
        } catch (IOException e) {
            throw new CannotGetCryptedBlockException("Cannot get crypted block", e);
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

    public String getReplayName() {
        return file.getName();
    }
}
