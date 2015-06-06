package ca.unknown.replayparser.packets;

import java.nio.ByteBuffer;

public class Packet22 extends Packet {
    public Packet22(PacketType type, int length, float clock, ByteBuffer buffer) {
        super(type, length, clock, buffer);
    }

    public Packet22(RawPacket rawPacket) {
        super(rawPacket);
    }

    @Override
    public void toReadableFormat() {

    }

    @Override
    protected void parse(ByteBuffer payload) {

    }
}
