package ca.unknown.replaydecoder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
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

        SecretKeySpec KS = new SecretKeySpec("SomeString".getBytes(), "Blowfish");
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("Blowfish");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }

        for (File replay : listOfReplays) {
            if (replay.isFile()) {
                try {
                    FileInputStream fis = new FileInputStream(replay);
                    InputStreamReader isr = new InputStreamReader(fis, "UTF8");
                    DataInputStream in = new DataInputStream(fis);

                    boolean goodMagicNumber = confirmMagicWoTNumber(in.readInt());
                    int blocks = getNumberOfBlocks(in.readShort());
                    System.out.println("Blocks : " + blocks);
                    int firstBlockSize = getFirstBlockSize(in.readUnsignedShort());
                    System.out.println("FirstBlockSize : " + firstBlockSize);
                    int firstBlockSize2 = getFirstBlockSize(in.readUnsignedShort());
                    int firstBlockSize3 = getFirstBlockSize(in.readUnsignedShort());
                    BufferedReader br = new BufferedReader(isr);

                    ByteBuffer allocate = ByteBuffer.allocate(firstBlockSize2);
                    if (goodMagicNumber) {

                        do {
                            // /home/maxime/Desktop/Replays
                            firstBlockSize2 += 255;
                            while (firstBlockSize2 != 0) {
                                firstBlockSize2--;
                                byte c = in.readByte();
                                sb.append((char) c);
                            }
                            Gson gson = new GsonBuilder().setPrettyPrinting().create();
                            JsonParser jp = new JsonParser();
                            JsonElement je = jp.parse(sb.toString());
                            String prettyJsonString = gson.toJson(je);
                            System.out.println(prettyJsonString);
                            break;
                        } while (in.read() != -1);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static int getFirstBlockSize(int i) {
        System.out.println(i);
        return i;
    }

    private static int getNumberOfBlocks(int i) {
        System.out.println(i);
        return i;
    }

    private static boolean confirmMagicWoTNumber(int i) {
        String magic_number = "12323411";
        return String.format("%04X", i).equals(magic_number);
    }
}
