package ca.intelliagent.replayparser.packets.subtypes.packet08;

import ca.intelliagent.replayparser.packets.Packet08;
import ca.intelliagent.replayparser.packets.PacketType;
import ca.intelliagent.replayparser.packets.RawPacket;

import java.nio.ByteBuffer;

/**
 * @see <a href="http://wiki.vbaddict.net/pages/Packet_0x08#Update_Type_0x17">Packet 08, Update type 17</a>
 */
public class SubPacket0817 extends Packet08 {

    private int target;

    public SubPacket0817(PacketType type, int length, float clock, ByteBuffer payload) {
        super(type, length, clock, payload);
    }

    public SubPacket0817(RawPacket rawPacket) {
        super(rawPacket);
    }

    @Override
    public int getTarget() {
        return target;
    }

    @Override
    protected void parse(ByteBuffer payload) {
        target = payload.getInt(4);
    }
}
