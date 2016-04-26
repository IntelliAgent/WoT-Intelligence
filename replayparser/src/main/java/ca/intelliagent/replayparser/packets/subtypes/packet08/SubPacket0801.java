package ca.intelliagent.replayparser.packets.subtypes.packet08;

import ca.intelliagent.replayparser.packets.Packet08;
import ca.intelliagent.replayparser.packets.PacketType;
import ca.intelliagent.replayparser.packets.RawPacket;

import java.nio.ByteBuffer;

/**
 * @see <a href="http://wiki.vbaddict.net/pages/Packet_0x08#Update_Type_0x01">Packet 08, Update type 01</a>
 */
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
    public short getHealth() {
        return health;
    }

    @Override
    public int getSource() {
        return source;
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
