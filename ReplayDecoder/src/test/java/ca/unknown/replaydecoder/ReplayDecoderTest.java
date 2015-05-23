package ca.unknown.replaydecoder;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReplayDecoderTest {

    private static final String fileName = "20150521131209 - T95E2FreeReroll - province-usa-t1_cunningham - 5231679620627844.wotreplay";
    private static String resourceDirectory;
    private static Path outputDirectory;
    private ReplayDecoder decoder;
    private ReplayFileReader reader;

    @BeforeClass
    public static void setupClass() {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        URL url = contextClassLoader.getResource(fileName);
        resourceDirectory = url.getFile();
        outputDirectory = Paths.get(String.valueOf(contextClassLoader.getResource("/output")));


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
    public void setUp() {
        reader = new ReplayFileReader(new File(resourceDirectory));
        reader.init();
        reader.validateMagicNumber();

        int numberOfJsonBlock = reader.getNumberOfBlocks();

        decoder = getReplayDecoder(numberOfJsonBlock, reader);
    }

    @Test
    public void test() {
        if (decoder != null)
            decoder.decode();
    }

}
