package ca.unknown.replayparser.packets;

import java.nio.ByteBuffer;

public class Packet16 extends Packet {
    public Packet16(PacketType type, int length, float clock, ByteBuffer buffer) {
        super(type, length, clock, buffer);
    }

    @Override
    public void toReadableFormat() {

    }
}
