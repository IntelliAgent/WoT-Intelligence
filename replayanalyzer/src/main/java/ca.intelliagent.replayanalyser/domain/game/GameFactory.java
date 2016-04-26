package ca.intelliagent.replayanalyser.domain.game;

import ca.intelliagent.replaydecoder.ReplayDecoderFactory;
import ca.intelliagent.replaydecoder.ReplayFileReader;
import ca.intelliagent.replayparser.ReplayParser;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class GameFactory {

    private ReplayParser parser;
    private ReplayDecoderFactory decoderFactory;
    private ReplayFileReader reader;

    public GameFactory() {
    }

    public GameFactory(ReplayDecoderFactory replayDecoderFactory, ReplayFileReader replayFileReader, ReplayParser replayParser) {
        decoderFactory = replayDecoderFactory;
        reader = replayFileReader;
        parser = replayParser;
    }

    public Game getGame() throws IOException, IllegalArgumentException {
        reader.init();
        decoderFactory.getReplayDecoder(reader);

        switch (reader.getNumberOfBlocks()) {
            case 1:
                return null;
            case 2:
                return new Game(parser.getPackets(), new JSONObject(reader.getFirstBlockAsReadableJson()), new JSONArray(reader.getSecondBlockAsReadableJson()));
            case 3:
                return null;
            default:
                throw new IllegalArgumentException("Replay must have between 1 to 3 blocks. Was " + reader.getNumberOfBlocks());
        }
    }
}
