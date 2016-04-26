package ca.intelliagent.replayparser.packets.subtypes.packet08;

import ca.intelliagent.replayparser.packets.Packet08;
import ca.intelliagent.replayparser.packets.PacketType;
import ca.intelliagent.replayparser.packets.RawPacket;

import java.nio.ByteBuffer;

/**
 * @see <a href="http://wiki.vbaddict.net/pages/Packet_0x08#Update_Type_0x05">Packet 08, Update type 05</a>
 */
public class SubPacket0805 extends Packet08 {

    private int source;

    public SubPacket0805(PacketType type, int length, float clock, ByteBuffer payload) {
        super(type, length, clock, payload);
    }

    public SubPacket0805(RawPacket rawPacket) {
        super(rawPacket);
    }

    @Override
    public int getSource() {
        return source;
    }

    @Override
    protected void parse(ByteBuffer payload) {
        source = payload.getInt(4);
    }
}
