package ca.intelliagent.replayparser.packets;

import java.nio.ByteBuffer;

public class Packet05 extends Packet {

    public Packet05(PacketType type, int length, float clock, ByteBuffer payload) {
        super(type, length, clock, payload);
    }

    public Packet05(RawPacket rawPacket) {
        super(rawPacket);
    }

    @Override
    public void toReadableFormat() {

    }
}