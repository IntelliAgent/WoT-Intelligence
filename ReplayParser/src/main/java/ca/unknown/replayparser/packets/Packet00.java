package ca.unknown.replayparser.packets;

import java.nio.ByteBuffer;

public class Packet00 extends Packet {
    public Packet00(PacketType type, int length, float clock, ByteBuffer payload) {
        super(type, length, clock, payload);
    }

    @Override
    public void toReadableFormat() {

    }
}
