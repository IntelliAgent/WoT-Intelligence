package ca.intelliagent.replayparser.packets.subtypes.packet08;

import ca.intelliagent.replayparser.packets.Packet08;
import ca.intelliagent.replayparser.packets.PacketType;
import ca.intelliagent.replayparser.packets.RawPacket;

import java.nio.ByteBuffer;

/**
 * @see <a href="http://wiki.vbaddict.net/pages/Packet_0x08#Update_Type_0x0b">Packet 08, Update type 0b</a>
 */
public class SubPacket080B extends Packet08 {

    private int source;
    private int target;

    public SubPacket080B(PacketType type, int length, float clock, ByteBuffer payload) {
        super(type, length, clock, payload);
    }

    public SubPacket080B(RawPacket rawPacket) {
        super(rawPacket);
    }

    @Override
    public int getSource() {
        return source;
    }

    @Override
    public int getTarget() {
        return target;
    }

    @Override
    protected void parse(ByteBuffer payload) {
        source = payload.getInt(4);
        target = payload.getInt();
    }
}
