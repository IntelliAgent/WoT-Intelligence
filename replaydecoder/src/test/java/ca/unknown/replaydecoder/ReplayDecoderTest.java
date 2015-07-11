package ca.intelliagent.replaydecoder;

import org.junit.*;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReplayDecoderTest {

    private static final String fileName = "replayFile.wotreplay";

    @ClassRule
    public static TemporaryFolder testFolder = new TemporaryFolder();

    private static String resourceDirectory;
    private static Path outputDirectory;
    private ReplayDecoder decoder;
    private ReplayFileReader reader;

    @BeforeClass
    public static void setupClass() {
        File tempFolder = null;
        try {
            tempFolder = testFolder.newFolder("output");
        } catch (IOException e) {
            e.printStackTrace();
        }

        resourceDirectory = System.getProperty("user.dir") + "/src/test/resources/";
        outputDirectory = Paths.get(tempFolder.getPath());


        System.out.println(resourceDirectory);
        System.out.println(outputDirectory);
    }

    private static ReplayDecoder getReplayDecoder(int numberOfJsonBlock, ReplayFileReader replayFileReader) {
        ReplayDecoder replayDecoder = null;
        if (numberOfJsonBlock == 1) {
            replayDecoder = new ReplayDecoderWithOneBlock(replayFileReader, outputDirectory);
        } else if (numberOfJsonBlock == 2) {
            replayDecoder = new ReplayDecoderWithTwoBlocks(replayFileReader, outputDirectory);
        }
        return replayDecoder;
    }

    @Before
    public void setUp() throws Exception {
        reader = new ReplayFileReader(new File(resourceDirectory + fileName));
        reader.init();
        reader.validateMagicNumber();

        int numberOfJsonBlock = reader.getNumberOfBlocks();

        decoder = getReplayDecoder(numberOfJsonBlock, reader);
    }

    @Test
    public void test() {
        if (decoder != null)
            decoder.decodeToOutputDirectory();
    }

}
