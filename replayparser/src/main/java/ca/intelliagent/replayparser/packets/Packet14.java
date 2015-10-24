package ca.intelliagent.replayparser.packets;

import java.nio.ByteBuffer;

public class Packet14 extends Packet {

    public Packet14(PacketType type, int length, float clock, ByteBuffer buffer) {
        super(type, length, clock, buffer);
    }

    public Packet14(RawPacket rawPacket) {
        super(rawPacket);
    }

    @Override
    public void toReadableFormat() {

    }
}
