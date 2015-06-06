package ca.unknown.replayparser.packets;

import java.nio.ByteBuffer;

public class Packet16 extends Packet {
    public Packet16(PacketType type, int length, float clock, ByteBuffer buffer) {
        super(type, length, clock, buffer);
    }

    public Packet16(RawPacket rawPacket) {
        super(rawPacket);
    }

    @Override
    public void toReadableFormat() {

    }
}
