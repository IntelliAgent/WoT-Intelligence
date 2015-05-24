package ca.unknown.replayparser.packets.subtypes.packet08;

import ca.unknown.replayparser.packets.Packet08;
import ca.unknown.replayparser.packets.PacketType;

import java.nio.ByteBuffer;

public class SubPacket0801 extends Packet08 {

    private short health;
    private int source;

    public SubPacket0801(PacketType type, int length, float clock, ByteBuffer payload) {
        super(type, length, clock, payload);

        source = payload.getInt(4);
        health = payload.getShort();
    }

    @Override
    public void toReadableFormat() {

    }
}
