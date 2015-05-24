package ca.unknown.replayparser.packets;

import java.nio.ByteBuffer;

public abstract class Packet {

    private int length;
    private PacketType type;
    private float clock;
    private ByteBuffer buffer;


    public Packet(PacketType type, int length, float clock, ByteBuffer buffer) {
        this.type = type;
        this.length = length;
        this.clock = clock;
        this.buffer = buffer;
    }

    public abstract void toReadableFormat();

    @Override
    public String toString() {
        return "";
    }
}
