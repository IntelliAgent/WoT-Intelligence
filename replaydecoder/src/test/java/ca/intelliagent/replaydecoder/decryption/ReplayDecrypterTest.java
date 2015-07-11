package ca.intelliagent.replaydecoder.decryption;

import ca.intelliagent.replaydecoder.ReplayFileReader;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class ReplayDecrypterTest {

    private final String FILENAME = "/replayFile.wotreplay";

    private File replayFile;

    private ReplayFileReader reader;

    @Before
    public void setUp() throws Exception {
        replayFile = new File(ReplayDecrypterTest.class.getResource(FILENAME).getPath());
        reader = new ReplayFileReader(replayFile);
        reader.init();
    }

    @Test
    public void testDecryptWithArrayAndFileOutputStreamShouldGiveSameThing() throws IOException {
        File tempsFile = new File("temp");
        FileOutputStream fos = new FileOutputStream(tempsFile);

        ReplayDecrypter decrypter = new ReplayDecrypter(reader.getCryptedBlock());
        byte[] withoutFos = decrypter.decryptToByteArray(reader.getCryptedBlock());
        decrypter.decrypt(fos);

        FileInputStream fis = new FileInputStream(tempsFile);

        for (int i = 0; i < 4; i++) {
            assertEquals(fis.read(), withoutFos[i]);
        }
    }

}