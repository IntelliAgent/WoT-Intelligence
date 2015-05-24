package ca.unknown.replayparser.packets;

import java.nio.ByteBuffer;

public class Packet0801 extends Packet08 {

    public Packet0801(PacketType type, int length, float clock, ByteBuffer byteBuffer) {
        super(type, length, clock, byteBuffer);
    }

    @Override
    public void toReadableFormat() {

    }
}
