package ca.unknown.replaydecoder;

import ca.unknown.replayparser.ReplayParser;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input replay path:");
        String filename = scanner.nextLine();
        File folder = new File(filename);
        System.out.print("Output data path:");
        String outputDirectory = scanner.nextLine();

        Path outputDirectoryPath = Paths.get(outputDirectory);
        if (!Files.exists(outputDirectoryPath)) {
            try {
                Files.createDirectory(outputDirectoryPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File[] listOfReplays = folder.listFiles();

        if (listOfReplays != null) {
            decodeReplays(listOfReplays, outputDirectoryPath);
        }
    }

    private static void decodeReplays(File[] listOfReplays, Path outputDirectory) {
        for (File replay : listOfReplays) {
            if (isExtensionReplayValid(replay)) {

                ReplayFileReader replayFileReader = new ReplayFileReader(replay);
                replayFileReader.init();
                replayFileReader.validateMagicNumber();

                int numberOfJsonBlock = replayFileReader.getNumberOfBlocks();

                ReplayDecoder replayDecoder = getReplayDecoder(numberOfJsonBlock, replayFileReader, outputDirectory);
                ByteBuffer decodedReplay = null;
                if (replayDecoder != null) {
                    decodedReplay = replayDecoder.decode();
                }
                ReplayParser replayParser = new ReplayParser(decodedReplay);
            }
        }
    }

    private static boolean isExtensionReplayValid(File replay) {
        return replay.isFile() && replay.getName().endsWith(".wotreplay");
    }

    private static ReplayDecoder getReplayDecoder(int numberOfJsonBlock, ReplayFileReader replayFileReader, Path outputDirectory) {
        ReplayDecoder replayDecoder = null;
        if (numberOfJsonBlock == 1) {
            replayDecoder = new ReplayDecoderWithOneBlock(replayFileReader, outputDirectory);
        } else if (numberOfJsonBlock == 2) {
            replayDecoder = new ReplayDecoderWithTwoBlocks(replayFileReader, outputDirectory);
        }
        return replayDecoder;
    }
}
