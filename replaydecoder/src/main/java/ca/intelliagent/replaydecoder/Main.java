package ca.intelliagent.replaydecoder;

import ca.intelliagent.replayparser.ReplayParser;
import ca.intelliagent.replayparser.reader.BasicPacketReader;
import ca.intelliagent.replayparser.reader.PacketReader;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private ReplayDecoderFactory replayDecoderFactory = new ReplayDecoderFactory();

    public static void main(String[] args) {
        Main main = new Main();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Input replay path:");
        String filename = scanner.nextLine();
        System.out.print("Output data path:");
        String outputDirectory = scanner.nextLine();

//        String filename = "C:\\Games\\World_of_Tanks\\replays\\";
//        String outputDirectory = "D:\\Replays\\";

        Path outputDirectoryPath = Paths.get(outputDirectory);

        File folder = new File(filename);

        if (!Files.exists(outputDirectoryPath)) {
            try {
                Files.createDirectory(outputDirectoryPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File[] listOfReplays = folder.listFiles();
        List<File> files = Arrays.asList(listOfReplays);

        main.decodeReplays(files, outputDirectoryPath);
    }

    private static boolean isExtensionReplayValid(File replay) {
        return replay.isFile() && replay.getName().endsWith(".wotreplay");
    }

    private void decodeReplays(List<File> listOfReplays, Path outputDirectory) {
        listOfReplays.parallelStream().forEach(replay -> {
            if (isExtensionReplayValid(replay)) {
                ReplayFileReader replayFileReader = new ReplayFileReader(replay);
                try {
                    replayFileReader.init();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                replayFileReader.validateMagicNumber();

                ReplayDecoder replayDecoder = replayDecoderFactory.getReplayDecoder(replayFileReader, outputDirectory);
                ByteBuffer decodedReplay = null;

                if (replayDecoder != null) {
                    decodedReplay = replayDecoder.decode();
//                    try {
//                        FileUtils.writeByteArrayToFile(new File(outputDirectory.toString() + "/" + replayFileReader.getReplayName()),
//                                decodedReplay.array());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                }

//                PacketReader packetReader = new BasicPacketReader(decodedReplay);
//                ReplayParser replayParser = new ReplayParser(packetReader);
//                replayParser.parsePackets();
            }
        });
    }
}
