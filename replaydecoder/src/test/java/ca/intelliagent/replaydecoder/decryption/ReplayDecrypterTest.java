package ca.intelliagent.replaydecoder.decryption;

import ca.intelliagent.replaydecoder.ReplayFileReader;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

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
        ByteBuffer decrypt = decrypter.decrypt();

        for (int i = 0; i < 4; i++) {
            System.out.println(Byte.toUnsignedInt(decrypt.get()));

        }
    }

}