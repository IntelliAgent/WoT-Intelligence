package ca.unknown.replaydecoder;

import java.io.File;
import java.util.Arrays;
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
                //int numberOfJSONBlocks = replayFileReader.getNumberOfJSONBlocks();
                int firstBlockSize = replayFileReader.getFirstJSONBlockSize();
                int secondBlockSize = replayFileReader.getSecondJSONBlockSize();
                int cryptedSizePart = replayFileReader.getCryptedSizePart();

                System.out.println(replayFileReader.getFirstJSONBlockSize());
                System.out.println(replayFileReader.getSecondJSONBlockSize());
                byte[] compressedCrypted = replayFileReader.getCryptedPart(cryptedSizePart);
                ReplayDecrypter replayDecrypter = new ReplayDecrypter(compressedCrypted);
                replayDecrypter.decrypt();

            }
        }
    }

}
