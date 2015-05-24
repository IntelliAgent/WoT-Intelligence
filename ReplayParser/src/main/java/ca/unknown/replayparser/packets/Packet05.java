package ca.unknown.replayparser.packets;

import java.nio.ByteBuffer;

public class Packet05 extends Packet {
    private final ByteBuffer buffer;

    public Packet05(PacketType type, int length, float clock, ByteBuffer buffer) {
        super(type, length, clock);
        this.buffer = buffer;
    }

    @Override
    public void toReadableFormat() {

    }
}