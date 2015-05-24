package ca.unknown.replayparser.packets;

import java.nio.ByteBuffer;

public class Packet12 extends Packet {
    public Packet12(PacketType type, int length, float clock, ByteBuffer buffer) {
        super(type, length, clock, buffer);
    }

    @Override
    public void toReadableFormat() {

    }
}
