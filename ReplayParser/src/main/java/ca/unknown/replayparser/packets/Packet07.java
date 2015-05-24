package ca.unknown.replayparser.packets;

import java.nio.ByteBuffer;

public class Packet07 extends Packet {
    public Packet07(PacketType type, int length, float clock, ByteBuffer buffer) {
        super(type, length, clock, buffer);
    }

    @Override
    public void toReadableFormat() {

    }
}