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

        if (listOfReplays != null) {
            decodeReplays(listOfReplays);
        }
    }

    private static void decodeReplays(File[] listOfReplays) {
        for (File replay : listOfReplays) {
            if (isExtensionReplayValid(replay)) {

                ReplayFileReader replayFileReader = new ReplayFileReader(replay);
                replayFileReader.init();
                replayFileReader.validateMagicNumber();

                int numberOfJsonBlock = replayFileReader.getNumberOfBlocks();

                ReplayDecoder replayDecoder = getReplayDecoder(numberOfJsonBlock, replayFileReader);

                if (replayDecoder != null) {
                    replayDecoder.decode();
                }
            }
        }
    }

    private static boolean isExtensionReplayValid(File replay) {
        return replay.isFile() && replay.getName().endsWith(".wotreplay");
    }

    private static ReplayDecoder getReplayDecoder(int numberOfJsonBlock, ReplayFileReader replayFileReader) {
        ReplayDecoder replayDecoder = null;
        if (numberOfJsonBlock == 1) {
            replayDecoder = new ReplayDecoderWithOneBlock(replayFileReader);
        } else if (numberOfJsonBlock == 2) {
            replayDecoder = new ReplayDecoderWithTwoBlocks(replayFileReader);
        }
        return replayDecoder;
    }
}
