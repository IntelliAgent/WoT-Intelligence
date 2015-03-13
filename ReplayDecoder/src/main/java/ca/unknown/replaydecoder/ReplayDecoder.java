package ca.unknown.replaydecoder;

import ca.unknown.replaydecoder.decompression.ReplayDecompressor;
import ca.unknown.replaydecoder.decryption.ReplayDecrypter;

import java.io.File;
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
                int numberOfJSONBlocks = replayFileReader.getNumberOfJSONBlocks();
                System.out.println("numberOfJSONBlocks: " + numberOfJSONBlocks);
                int firstBlockSize = replayFileReader.getFirstJSONBlockSize();
                int secondBlockSize = replayFileReader.getSecondJSONBlockSize();
                System.out.println("Second block size: " + secondBlockSize);
                int cryptedSizePart = replayFileReader.getCryptedPartSize();
                System.out.println("Crypted sized part : " + cryptedSizePart);
                byte[] compressedCrypted = replayFileReader.getCryptedBlock(cryptedSizePart);
                ReplayDecrypter replayDecrypter = new ReplayDecrypter(compressedCrypted);
                byte[] compressedData = replayDecrypter.decrypt();

                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(
                        "E:\\replays\\" + replay.getName().substring(0, replay.getName().indexOf(".wotreplay"))
                            + " - Decrypted.dat");
                    fos.write(compressedData);
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ReplayDecompressor replayDecompressor = new ReplayDecompressor(compressedData);
                byte[] decompressedData = replayDecompressor.unzip();

                try {
                    fos = new FileOutputStream(
                        "E:\\replays" + replay.getName().substring(0, replay.getName().indexOf(".wotreplay"))
                            + " - Decompressed.dat");

                    fos.write(decompressedData);
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
