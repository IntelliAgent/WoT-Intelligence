package ca.unknown.replayparser.packets;


import ca.unknown.common.swapper.ByteSwapper;
import ca.unknown.replayparser.packets.subtypes.SubPacketType;

import java.nio.ByteBuffer;

import static ca.unknown.replayparser.packets.subtypes.SubPacketType.fromInt;

public class RawPacket {
  private PacketType type;

  private SubPacketType subtype;

  private int payloadLength;

  private float clock;

  private ByteBuffer payload;

  public RawPacket(int type, int subtype, int payloadLength, float clock, ByteBuffer payload){
    this.type = PacketType.fromInt(type);
    this.subtype = fromInt(subtype);
    this.payloadLength = payloadLength;
    this.clock = clock;
    this.payload = payload;
  }

  public RawPacket(int type, int payloadLength, float clock, ByteBuffer payload){
    this.type = PacketType.fromInt(type);
    this.payloadLength = payloadLength;
    this.clock = clock;
    this.payload = payload;
  }

  public PacketType getType() {
    return type;
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
    return subtype == null;
  }

  public SubPacketType getSubType() {
    fetchSubtype();
    return hasSubtype() ? subtype : fetchSubtype();
  }

  private SubPacketType fetchSubtype() {
    int subtypeNumber = ByteSwapper.swap(payload.getInt(4));
    payload.rewind();
    return fromInt(subtypeNumber);
  }
}
