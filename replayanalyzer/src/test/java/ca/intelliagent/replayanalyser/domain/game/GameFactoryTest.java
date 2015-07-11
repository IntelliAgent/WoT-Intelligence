package ca.intelliagent.replayanalyser.domain.game;

import junit.framework.TestCase;
import org.junit.Before;

import java.io.File;

public class GameFactoryTest extends TestCase {
  private static final String FILENAME = "/testReplay.wotreplay";

  private final GameFactory factory = new GameFactory();

  private File replayFile;

  @Before
  public void setUp() {
    replayFile = new File(GameFactoryTest.class.getResource(FILENAME).getPath());
  }

  public void testGetGame() throws Exception {
    Game game = factory.getGame(replayFile);

    assertEquals("ctf", game.getGameMode());
  }

  public void testSetReplay() throws Exception {

  }
}