package ca.intelliagent.replayparser.packets;

import java.nio.ByteBuffer;

public class Packet26 extends Packet {

    public Packet26(PacketType type, int length, float clock, ByteBuffer buffer) {
        super(type, length, clock, buffer);
    }

    public Packet26(RawPacket rawPacket) {
        super(rawPacket);
    }

    @Override
    public void toReadableFormat() {

    }
}
