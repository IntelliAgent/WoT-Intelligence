package ca.unknown.replayparser.packets;

import java.nio.ByteBuffer;

public class Packet1e extends Packet {
    public Packet1e(PacketType type, int length, float clock, ByteBuffer buffer) {
        super(type, length, clock, buffer);
    }

    @Override
    public void toReadableFormat() {

    }
}