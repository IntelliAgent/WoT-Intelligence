package ca.unknown.replayparser.packets;

import java.nio.ByteBuffer;

public class Packet03 extends Packet {


    public Packet03(PacketType type, int length, float clock, ByteBuffer buffer) {
        super(type, length, clock, buffer);
    }

    @Override
    public void toReadableFormat() {

    }
}
