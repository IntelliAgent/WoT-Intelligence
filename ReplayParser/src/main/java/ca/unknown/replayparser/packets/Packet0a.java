package ca.unknown.replayparser.packets;

import java.nio.ByteBuffer;

public class Packet0a extends Packet {
    public Packet0a(PacketType type, int length, float clock, ByteBuffer buffer) {
        super(type, length, clock, buffer);
    }

    @Override
    public void toReadableFormat() {

    }
}