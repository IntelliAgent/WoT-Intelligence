package ca.unknown.replayparser.packets;

import java.nio.ByteBuffer;


public class Packet26 extends Packet {
    public Packet26(PacketType type, int length, float clock, ByteBuffer buffer) {
        super(type, length, clock, buffer);
    }

    @Override
    public void toReadableFormat() {

    }
}
