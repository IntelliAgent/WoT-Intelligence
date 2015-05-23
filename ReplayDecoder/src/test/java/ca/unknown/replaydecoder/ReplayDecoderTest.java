package ca.unknown.replaydecoder;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ReplayDecoderTest {

  private static String resourceDirectory;

  private static final String fileName = "20150521131209 - T95E2FreeReroll - province-usa-t1_cunningham - 5231679620627844.wotreplay";

  private ReplayDecoder decoder;

  private ReplayFileReader reader;

  @BeforeClass
  public static void setupClass() {
    resourceDirectory = System.getProperty("user.dir") + "/src/test/resources/";
    System.out.println(resourceDirectory);
  }

  @Before
  public void setUp() {
    reader = new ReplayFileReader(new File(resourceDirectory + fileName));
    reader.init();
    reader.validateMagicNumber();

    int numberOfJsonBlock = reader.getNumberOfBlocks();

    ReplayDecoder replayDecoder = getReplayDecoder(numberOfJsonBlock, reader);
  }

  @Test
  public void test() {
    if (decoder != null)
      decoder.decode();
  }

  private static ReplayDecoder getReplayDecoder(int numberOfJsonBlock, ReplayFileReader replayFileReader) {
    ReplayDecoder replayDecoder = null;
    if (numberOfJsonBlock == 1) {
      replayDecoder = new ReplayDecoderWithOneBlock(replayFileReader);
    } else if (numberOfJsonBlock == 2) {
      replayDecoder = new ReplayDecoderWithTwoBlocks(replayFileReader);
    }
    return replayDecoder;
  }

}
