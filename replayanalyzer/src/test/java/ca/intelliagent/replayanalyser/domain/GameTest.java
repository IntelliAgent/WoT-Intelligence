package ca.intelliagent.replayanalyser.domain;

import ca.intelliagent.replayanalyser.domain.game.Game;
import ca.intelliagent.replaydecoder.ReplayDecoder;
import ca.intelliagent.replaydecoder.ReplayFileReader;
import ca.intelliagent.replayparser.ReplayParser;
import junit.framework.TestCase;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class GameTest extends TestCase {
  private static final String FILENAME = "/testReplay.wotreplay";

  private File replayFile;

  private ReplayFileReader reader;

  @Before
  public void setUp() {
    replayFile = new File(GameTest.class.getResource(FILENAME).getPath());

    reader = new ReplayFileReader(replayFile);
    try {
      reader.init();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testDummyGameCreation(){
    Game game = new Game(null, new JSONObject(reader.getFirstBlockAsReadableJson()),
            new JSONArray(reader.getSecondBlockAsReadableJson()));

    System.out.println(game.getGameMode());
  }
}