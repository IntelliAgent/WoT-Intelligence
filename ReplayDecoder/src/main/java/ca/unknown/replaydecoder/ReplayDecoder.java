package ca.unknown.replaydecoder;

import java.io.BufferedReader;
import java.io.File;
import java.util.Scanner;

public class ReplayDecoder {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input replay path:");
        String filename = scanner.nextLine();
        File folder = new File(filename);
        File[] listOfReplays = folder.listFiles();

        assert listOfReplays != null;
        BufferedReader encrypted = null;
        StringBuilder sb = new StringBuilder();

        for (File replay : listOfReplays) {
            if (replay.isFile() && replay.getName().endsWith(".wotreplay")) {
                ReplayFileReader replayFileReader = new ReplayFileReader(replay);
                boolean goodMagicNumber = replayFileReader.validateMagicNumber();
                int numberOfJSONBlocks = replayFileReader.getNumberOfJSONBlocks();
                int firstBlockSize = replayFileReader.getFirstJSONBlockSize();
                System.out.println(replayFileReader.getFirstJson(firstBlockSize));

            }
        }
    }

}
