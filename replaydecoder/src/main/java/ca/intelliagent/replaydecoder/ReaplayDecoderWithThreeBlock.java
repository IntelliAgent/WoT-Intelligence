package ca.intelliagent.replaydecoder;

import java.nio.file.Path;

public class ReaplayDecoderWithThreeBlock extends ReplayDecoder {
    protected ReaplayDecoderWithThreeBlock(ReplayFileReader replayFileReader, Path outputDirectory) {
        super(replayFileReader, outputDirectory);
    }

    protected ReaplayDecoderWithThreeBlock(ReplayFileReader replayFileReader) {
        super(replayFileReader);
    }
}
