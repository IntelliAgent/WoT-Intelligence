package ca.unknown.replayparser.packets;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class RawPacket {
  private static final int NO_SUBTYPE = -1;

  private int type;

  private int subtype = NO_SUBTYPE;

  private int payloadLength;

  private float clock;

  private ByteBuffer payload;

  public RawPacket(int type, int subtype, int payloadLength, int clock, ByteBuffer payload){
    this.type = type;
    this.subtype = subtype;
    this.payloadLength = payloadLength;
    this.clock = clock;
    this.payload = payload;
  }

  public RawPacket(int type, int payloadLength, float clock, ByteBuffer payload){
    this.type = type;
    this.payloadLength = payloadLength;
    this.clock = clock;
    this.payload = payload;
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

  public ByteBuffer getPayload(){
    return payload;
  }

  public boolean hasSubtype(){
    return subtype == NO_SUBTYPE;
  }
}
