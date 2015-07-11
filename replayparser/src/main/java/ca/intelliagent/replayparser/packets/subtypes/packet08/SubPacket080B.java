package ca.intelliagent.replayparser.packets.subtypes.packet08;

import ca.intelliagent.replayparser.packets.Packet08;
import ca.intelliagent.replayparser.packets.PacketType;
import ca.intelliagent.replayparser.packets.RawPacket;
import com.google.gson.annotations.Expose;

import java.nio.ByteBuffer;

public class SubPacket080B extends Packet08 {
    @Expose
    private int source;
    @Expose
    private int target;

    public SubPacket080B(PacketType type, int length, float clock, ByteBuffer payload) {
        super(type, length, clock, payload);
    }

    public SubPacket080B(RawPacket rawPacket) {
        super(rawPacket);
    }

    @Override
    protected void parse(ByteBuffer payload) {
        source = payload.getInt(4);
        target = payload.getInt();
    }
}
