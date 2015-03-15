package ca.unknown.replaydecoder;

import ca.unknown.replaydecoder.decompression.ReplayDecompressor;
import ca.unknown.replaydecoder.decryption.ReplayDecrypter;

import java.io.File;
import java.io.FileInputStream;
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
                byte[] compressedCrypted = replayFileReader.getCryptedBlock();

                ReplayDecrypter replayDecrypter = new ReplayDecrypter(compressedCrypted);

                String replayExtracted = replay.getName().substring(0, replay.getName().indexOf(".wotreplay"));
                String decryptedFile = "C:\\replays\\" + replayExtracted + " - Decrypted.dat";
                String JSON = "C:\\replays\\" + replayExtracted + ".json";
                File file = new File(JSON);
                try {
                    FileOutputStream jsonData = new FileOutputStream(file);
                    jsonData.write(replayFileReader.getFirstBlock().getBytes());
                    jsonData.write(replayFileReader.getSecondBlock().getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                file = new File(decryptedFile);


                String decompressed = "C:\\replays\\" + replayExtracted + " - Decompressed.dat";
                file = new File(decompressed);
                FileOutputStream decompressFile = null;
                FileInputStream fis = null;

                try {
                    FileOutputStream fos = new FileOutputStream(decryptedFile);
                    System.out.println("Decrypting : " + replayExtracted);
                    replayDecrypter.decrypt(fos);

                    fis = new FileInputStream(decryptedFile);
                    decompressFile = new FileOutputStream(decompressed);
                } catch (Exception e) {
                    System.err.println("Error at decrypting : " + replayExtracted);
                    System.err.println(e.getMessage());
                    continue;
                }

                try {
                    ReplayDecompressor replayDecompressor = new ReplayDecompressor(fis, decompressFile);
                    System.out.println("Decompressing : " + replayExtracted);
                    byte[] decompressedData = replayDecompressor.unzip();

                    decompressFile.write(decompressedData);
                    decompressFile.close();
                } catch (Exception e) {
                    System.err.println("Error at decompressing : " + replayExtracted);
                    System.err.println(e.getMessage());
                } finally {
                    try {
                        decompressFile.close();
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


}
