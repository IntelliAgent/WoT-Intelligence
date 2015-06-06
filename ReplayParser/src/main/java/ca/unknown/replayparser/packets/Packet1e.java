package ca.unknown.replayparser.packets;

import java.nio.ByteBuffer;

public class Packet1e extends Packet {
    public Packet1e(PacketType type, int length, float clock, ByteBuffer buffer) {
        super(type, length, clock, buffer);
    }

    public Packet1e(RawPacket rawPacket) {
        super(rawPacket);
    }

    @Override
    public void toReadableFormat() {

    }

    @Override
    protected void parse(ByteBuffer payload) {

    }
}