package ca.intelliagent.replaydecoder;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReplayDecoderFactory {
  /**
   * Factory method to get the appropriate replay decoder as a function of the number of json block.
   * @param reader The replay reader for the given file
   * @param outputDirectory The output directory of the replay decoder
   * @return A replay decoder compatible with the given number of json block
   */
  public static ReplayDecoder getReplayDecoder(final ReplayFileReader reader, final Path outputDirectory)
          throws IllegalArgumentException{
    int numberOfBlocks = reader.getNumberOfBlocks();

    if (numberOfBlocks == 1)
      return new ReplayDecoderWithOneBlock(reader, outputDirectory);
    else if (numberOfBlocks == 2)
      return new ReplayDecoderWithTwoBlocks(reader, outputDirectory);
    else if (numberOfBlocks == 3)
      throw new IllegalArgumentException("Number of json block must be 1 or 2, was " + numberOfBlocks);
    else
      throw new IllegalArgumentException("Number of json block must be 1 or 2, was " + numberOfBlocks);
  }

  public static ReplayDecoder getReplayDecoder(final ReplayFileReader reader, final String outputDirectory){
    try{
      final Path outputDirectoryPath = Paths.get(outputDirectory);
      return ReplayDecoderFactory.getReplayDecoder(reader,outputDirectoryPath);
    }catch(IllegalArgumentException e){
      throw e;
    }
  }

  public static ReplayDecoder getReplayDecoder(final ReplayFileReader reader)
          throws IllegalArgumentException{
    int numberOfBlocks = reader.getNumberOfBlocks();

    if (numberOfBlocks == 1)
      return new ReplayDecoderWithOneBlock(reader);
    else if (numberOfBlocks == 2)
      return new ReplayDecoderWithTwoBlocks(reader);
    else if (numberOfBlocks == 3)
      throw new IllegalArgumentException("Number of json block must be 1 or 2, was " + numberOfBlocks);
    else
      throw new IllegalArgumentException("Number of json block must be 1 or 2, was " + numberOfBlocks);
  }
}
