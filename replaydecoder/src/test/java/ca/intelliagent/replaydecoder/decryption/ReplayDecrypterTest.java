package ca.intelliagent.replaydecoder.decryption;

import ca.intelliagent.replaydecoder.ReplayFileReader;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

public class ReplayDecrypterTest extends TestCase {

  private final String fileName =
          "/replayFile.wotreplay";

  private File replayFile;

  private ReplayFileReader reader;

  @Before
  public void setUp() throws IOException {
    final String filePath = ReplayDecrypterTest.class.getResource(fileName).toString();
    System.out.println("File path " + filePath);
    replayFile = new File(filePath);

    reader = new ReplayFileReader(replayFile);
    reader.init();
  }

  @Test
  public void testDecrypt() throws Exception {

  }

  @Test
  public void testDecryptToByteArray() throws Exception {

  }

  @Test
  public void testDecryptWithArrayAndFileOutputStreamShouldGiveSameThing() throws IOException {
    File tempsFile = new File("temp");
    FileOutputStream fos = new FileOutputStream(tempsFile);

    ReplayDecrypter decrypter = new ReplayDecrypter(reader.getCryptedBlock());
    byte[] withoutFos = decrypter.decryptToByteArray(reader.getCryptedBlock());
    decrypter.decrypt(fos);

    FileInputStream fis = new FileInputStream(tempsFile);

    for(int i = 0 ;i < 4; i++){
      System.out.println("From fis : " + fis.read());
      System.out.println("From other streamless decrypter " + withoutFos[i]);
    }
  }

}