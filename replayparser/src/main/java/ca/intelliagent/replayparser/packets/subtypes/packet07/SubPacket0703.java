package ca.intelliagent.replayparser.packets.subtypes.packet07;

import ca.intelliagent.replayparser.packets.Packet07;
import ca.intelliagent.replayparser.packets.PacketType;
import ca.intelliagent.replayparser.packets.RawPacket;
import com.google.gson.annotations.Expose;

import java.nio.ByteBuffer;

public class SubPacket0703 extends Packet07 {
    @Expose
    private int health;

    public SubPacket0703(PacketType type, int length, float clock, ByteBuffer payload) {
        super(type, length, clock, payload);
    }

    public SubPacket0703(RawPacket rawPacket) {
        super(rawPacket);
    }

    @Override
    protected void parse(ByteBuffer payload) {
        health = payload.getInt(4);
    }

    @Override
    public void toReadableFormat() {

    }
}
