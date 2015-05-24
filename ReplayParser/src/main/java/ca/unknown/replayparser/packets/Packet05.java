package ca.unknown.replayparser.packets;

import java.nio.ByteBuffer;

public class Packet05 extends Packet {

    public Packet05(PacketType type, int length, float clock, ByteBuffer buffer) {
        super(type, length, clock, buffer);
    }

    @Override
    public void toReadableFormat() {

    }
}