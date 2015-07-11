package ca.intelliagent.replayanalyser.domain.game;

import ca.intelliagent.replayparser.packets.Packet;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class Game {
  private final List<Packet> packets;

  private final String gameMode;

  private final JSONObject gameBegin;

  private final JSONArray gameEnd;

  public Game(List<Packet> packets, JSONObject gameBegin, JSONArray gameEnd) {
    this.packets = packets;
    this.gameMode = gameBegin.getString("gameplayID");
    this.gameBegin = gameBegin;
    this.gameEnd = gameEnd;
  }

  public List<Packet> getPackets() {
    return packets;
  }

  public String getGameMode() {
    return gameMode;
  }

  public JSONObject getGameBegin() {
    return gameBegin;
  }

  public JSONArray getGameEnd() {
    return gameEnd;
  }
}
