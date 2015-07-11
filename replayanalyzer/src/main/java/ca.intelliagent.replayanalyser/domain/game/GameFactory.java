package ca.intelliagent.replayanalyser.domain.game;

import ca.intelliagent.replaydecoder.ReplayDecoder;
import ca.intelliagent.replaydecoder.ReplayDecoderFactory;
import ca.intelliagent.replaydecoder.ReplayFileReader;
import ca.intelliagent.replayparser.ReplayParser;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemException;

public class GameFactory {

    private ReplayDecoder decoder;

    private ReplayParser parser;

    private ReplayFileReader reader;

    public GameFactory() {
    }

    public GameFactory(File replayFile) {
        setReplay(replayFile);
    }

    public GameFactory(String replayPath) throws FileNotFoundException, FileSystemException {
        setReplay(replayPath);
    }

    public Game getGame() throws IOException, IllegalArgumentException {
        reader.init();

        decoder = ReplayDecoderFactory.getReplayDecoder(reader);

        parser = new ReplayParser(decoder.decode());

        switch (reader.getNumberOfBlocks()) {
            case 1:
                return null;
            case 2:
                return new Game(parser.getPackets(), new JSONObject(reader.getFirstBlockAsReadableJson()),
                        new JSONArray(reader.getSecondBlockAsReadableJson()));
            case 3:
                return null;
            default:
                throw new IllegalArgumentException("Replay must have between 1 to 3 blocks. Was " + reader.getNumberOfBlocks());
        }
    }

    public Game getGame(File replayFile) throws IOException, IllegalArgumentException {
        setReplay(replayFile);
        return getGame();
    }

    public Game getGame(String replayPath) throws IOException, IllegalArgumentException {
        setReplay(replayPath);
        return getGame();
    }

    public void setReplay(File replayFile) {
        init(replayFile);
    }

    public void setReplay(String replayPath) throws FileNotFoundException, FileSystemException {
        File replayFile = new File(replayPath);

        fileNotFoundOrFileNotReadable(replayPath, replayFile);

        init(replayFile);
    }

    private void init(File replayFile) {
        reader = new ReplayFileReader(replayFile);
    }

    private void fileNotFoundOrFileNotReadable(String replayPath, File replayFile) throws FileNotFoundException, FileSystemException {
        if (!replayFile.exists())
            throw new FileNotFoundException(replayPath);
        if (!replayFile.canRead())
            throw new FileSystemException(replayPath);
    }

}
