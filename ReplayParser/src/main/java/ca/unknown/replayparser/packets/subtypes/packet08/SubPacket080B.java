package ca.unknown.replayparser.packets.subtypes.packet08;

import ca.unknown.replayparser.packets.Packet08;
import ca.unknown.replayparser.packets.PacketType;
import ca.unknown.replayparser.packets.RawPacket;

import java.nio.ByteBuffer;

public class SubPacket080B extends Packet08 {

    private int source;
    private int target;

    public SubPacket080B(PacketType type, int length, float clock, ByteBuffer payload) {
        super(type, length, clock, payload);
    }

    public SubPacket080B(RawPacket rawPacket) {
        super(rawPacket);
    }

    @Override
    protected void parse(ByteBuffer payload) {
        source = payload.getInt(4);
        target = payload.getInt();
    }
}
