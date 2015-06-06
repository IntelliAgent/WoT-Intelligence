package ca.unknown.replayparser.packets;


public class RawPacket {
  private static final int NO_SUBTYPE = -1;

  private int type;

  private int subtype = NO_SUBTYPE;

  private int payloadLength;

  private float clock;

  public RawPacket(int type, int subtype, int payloadLength, int clock){
    this.type = type;
    this.subtype = subtype;
    this.payloadLength = payloadLength;
    this.clock = clock;
  }

  public RawPacket(int type, int payloadLength, float clock){
    this.type = type;
    this.payloadLength = payloadLength;
    this.clock = clock;
  }

  public int getType() {
    return type;
  }

  public int getSubtype() {
    return subtype;
  }

  public int getPayloadLength() {
    return payloadLength;
  }

  public float getClock() {
    return clock;
  }

  public boolean hasSubtype(){
    return subtype == NO_SUBTYPE;
  }
}
