package ca.unknown.replayparser.packets.subtypes.packet07;

import ca.unknown.replayparser.packets.Packet07;
import ca.unknown.replayparser.packets.PacketType;

import java.nio.ByteBuffer;

public class SubPacket0703 extends Packet07 {

    private int health;

    public SubPacket0703(PacketType type, int length, float clock, ByteBuffer payload) {
        super(type, length, clock, payload);

        health = payload.getInt(4);
    }

    @Override
    public void toReadableFormat(){

    }
}
