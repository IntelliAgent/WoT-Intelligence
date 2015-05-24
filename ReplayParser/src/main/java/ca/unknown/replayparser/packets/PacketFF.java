package ca.unknown.replayparser.packets;

import java.nio.ByteBuffer;

public class PacketFF extends Packet {
    public PacketFF(PacketType type, int length, float clock, ByteBuffer buffer) {
        super(type, length, clock, buffer);
    }

    @Override
    public void toReadableFormat() {

    }
}
