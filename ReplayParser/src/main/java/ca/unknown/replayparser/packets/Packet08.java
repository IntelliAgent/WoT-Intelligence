package ca.unknown.replayparser.packets;

import java.nio.ByteBuffer;

public class Packet08 extends Packet {

    public Packet08(PacketType type, int length, float clock, ByteBuffer buffer) {
        super(type, length, clock, buffer);
    }

    public Packet08(RawPacket rawPacket) {
        super(rawPacket);
    }

    @Override
    public void toReadableFormat() {

    }

    @Override
    protected void parse(ByteBuffer payload) {

    }
}
