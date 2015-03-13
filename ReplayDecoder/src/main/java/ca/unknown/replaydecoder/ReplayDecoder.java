package ca.unknown.replaydecoder;

import ca.unknown.replaydecoder.decompression.ReplayDecompressor;
import ca.unknown.replaydecoder.decryption.ReplayDecrypter;
import ca.unknown.replaydecoder.swapper.ByteSwapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class ReplayDecoder {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input replay path:");
        String filename = scanner.nextLine();
        File folder = new File(filename);
        File[] listOfReplays = folder.listFiles();

        assert listOfReplays != null;

        for (File replay : listOfReplays) {
            if (replay.isFile() && replay.getName().endsWith(".wotreplay")) {
                ReplayFileReader replayFileReader = new ReplayFileReader(replay);
                boolean goodMagicNumber = replayFileReader.validateMagicNumber();
                if (!goodMagicNumber) {
                    break;
                }
                int numberOfJSONBlocks = replayFileReader.getNumberOfBlocks();
                System.out.println(numberOfJSONBlocks);
                int firstBlockSize = replayFileReader.getFirstBlockSize();
                int secondBlockSize = replayFileReader.getSecondBlockSize();
                System.out.println("Second block size: " + secondBlockSize);
                int cryptedSizePart = replayFileReader.getCryptedPartSize();
                System.out.println("Crypted sized part : " + cryptedSizePart);
                byte[] compressedCrypted = replayFileReader.getCryptedBlock(cryptedSizePart);
                ReplayDecrypter replayDecrypter = new ReplayDecrypter(compressedCrypted);
                byte[] compressedData = replayDecrypter.decrypt();

                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream("C:\\Data\\DecryptedData.txt");

                    fos.write(compressedData);
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ReplayDecompressor replayDecompressor = new ReplayDecompressor(compressedData);
                String decompressedData = replayDecompressor.unzip();

                try {
                    fos = new FileOutputStream("C:\\Data\\DecompressedData.txt");

                    fos.write(decompressedData.getBytes());
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
