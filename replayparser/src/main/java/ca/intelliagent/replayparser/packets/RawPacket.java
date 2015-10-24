package ca.intelliagent.replayparser.packets;


import ca.intelliagent.common.swapper.ByteSwapper;
import ca.intelliagent.replayparser.packets.subtypes.SubPacketType;

import java.nio.ByteBuffer;

import static ca.intelliagent.replayparser.packets.subtypes.SubPacketType.fromInt;

public class RawPacket {

    private final PacketType type;
    private final int payloadLength;
    private final float clock;
    private final ByteBuffer payload;
    private SubPacketType subtype;

    public RawPacket(int type, int subtype, int payloadLength, float clock, ByteBuffer payload) {
        this.type = PacketType.fromInt(type);
        this.subtype = fromInt(subtype);
        this.payloadLength = payloadLength;
        this.clock = clock;
        this.payload = payload;
    }

    public RawPacket(int type, int payloadLength, float clock, ByteBuffer payload) {
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

    public ByteBuffer getPayload() {
        return payload;
    }

    public boolean hasSubtype() {
        return subtype != null;
    }

    public SubPacketType getSubType() {
        return hasSubtype() ? subtype : fetchSubtype();
    }

    public boolean isValid() {
        return type != null;
    }

    private SubPacketType fetchSubtype() {
        int subtypeNumber = ByteSwapper.swap(payload.getInt(4));
        payload.rewind();
        subtype = fromInt(subtypeNumber);
        return subtype;
    }

    @Override
    public String toString() {
        String s = "Type : " + type
                + " Length : " + payloadLength
                + " Clock : " + clock;
        return s;
    }
}
