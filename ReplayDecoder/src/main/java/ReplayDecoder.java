import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class ReplayDecoder {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input replay path:");
        String filename = scanner.nextLine();
        File folder = new File(filename);
        File[] listOfReplays = folder.listFiles();
        String magic_number = "12323411";
        assert listOfReplays != null;
        byte[] encrypted = new byte[0];
        for (File replay : listOfReplays) {
            if (replay.isFile()) {
                Path path = Paths.get(replay.getAbsolutePath());
                try {
                    encrypted = Files.readAllBytes(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        SecretKeySpec KS = new SecretKeySpec(magic_number.getBytes(), "Blowfish");
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, KS);
            byte[] decrypted = cipher.doFinal(encrypted);
            System.out.println(decrypted);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }
}
