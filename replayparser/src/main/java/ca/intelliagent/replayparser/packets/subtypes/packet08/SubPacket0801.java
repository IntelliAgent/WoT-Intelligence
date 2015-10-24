package ca.intelliagent.replayparser.packets.subtypes.packet08;

import ca.intelliagent.replayparser.packets.Packet08;
import ca.intelliagent.replayparser.packets.PacketType;
import ca.intelliagent.replayparser.packets.RawPacket;
import com.google.gson.annotations.Expose;

import java.nio.ByteBuffer;

public class SubPacket0801 extends Packet08 {

    private short health;
    private int source;

    public SubPacket0801(PacketType type, int length, float clock, ByteBuffer payload) {
        super(type, length, clock, payload);
    }

    public SubPacket0801(RawPacket rawPacket) {
        super(rawPacket);
    }

    @Override
    protected void parse(ByteBuffer payload) {
        source = payload.getInt(4);
        health = payload.getShort();
    }

    @Override
    public void toReadableFormat() {

    }
}
