package ca.intelliagent.replayparser.packets;

import java.nio.ByteBuffer;

public class PacketFF extends Packet {
    public PacketFF(PacketType type, int length, float clock, ByteBuffer buffer) {
        super(type, length, clock, buffer);
    }

    public PacketFF(RawPacket rawPacket) {
        super(rawPacket);
    }

    @Override
    public void toReadableFormat() {

    }
}
