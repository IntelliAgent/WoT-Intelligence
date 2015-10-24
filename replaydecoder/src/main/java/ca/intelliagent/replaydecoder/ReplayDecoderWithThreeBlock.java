package ca.intelliagent.replaydecoder;

import java.nio.file.Path;

public class ReplayDecoderWithThreeBlock extends ReplayDecoder {
    protected ReplayDecoderWithThreeBlock(ReplayFileReader replayFileReader, Path outputDirectory) {
        super(replayFileReader, outputDirectory);
    }

    protected ReplayDecoderWithThreeBlock(ReplayFileReader replayFileReader) {
        super(replayFileReader);
    }
}
