package ca.unknown.replayparser.packets;

import java.nio.ByteBuffer;

public class Packet0801 extends Packet08 {

    public Packet0801(PacketType type, int length, float clock, ByteBuffer byteBuffer) {
        super(type, length, clock, byteBuffer);
    }

    public Packet0801(RawPacket rawPacket) {
        super(rawPacket);
    }

    @Override
    public void toReadableFormat() {

    }
}
