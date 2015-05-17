package ca.unknown.replaydecoder;

import java.io.File;
import java.util.Scanner;

public class Main {

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
                replayFileReader.init();
                int numberOfJsonBlock = replayFileReader.getNumberOfBlocks();
                System.out.println(numberOfJsonBlock);
                boolean goodMagicNumber = replayFileReader.validateMagicNumber();
                if (!goodMagicNumber) {
                    System.err.println("Bad replays");
                    break;
                }

                if (numberOfJsonBlock == 1) {
                    ReplayDecoderWithOneBlock replayDecoderWithOneBlock =
                            new ReplayDecoderWithOneBlock(replayFileReader);
                    replayDecoderWithOneBlock.decode();
                } else if (numberOfJsonBlock == 2) {
                    ReplayDecoderWithTwoBlocks replayDecoderWithTwoBlocks =
                            new ReplayDecoderWithTwoBlocks(replayFileReader);
                    replayDecoderWithTwoBlocks.decode();
                }


            }
        }
    }
}
