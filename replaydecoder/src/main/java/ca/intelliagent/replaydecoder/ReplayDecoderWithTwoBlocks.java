package ca.intelliagent.replaydecoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Path;

public class ReplayDecoderWithTwoBlocks extends ReplayDecoder {

    public ReplayDecoderWithTwoBlocks(ReplayFileReader fileReader, Path outputDirectory) {
        super(fileReader, outputDirectory);
    }

    public ReplayDecoderWithTwoBlocks(ReplayFileReader fileReader) {
        super(fileReader);
    }

    public ByteBuffer decodeToOutputDirectory() {
        String replayExtracted =
                replayFileReader.getReplayName().substring(0, replayFileReader.getReplayName().indexOf(".wotreplay"));



        String JSON = outputDirectory + replayExtracted + ".json";
        File file = new File(JSON);
        try {
            FileOutputStream jsonData = new FileOutputStream(file);
            jsonData.write(replayFileReader.getFirstBlockAsReadableJson().getBytes());
            jsonData.write(replayFileReader.getSecondBlockAsReadableJson().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.decodeToOutputDirectory();

    }
}



