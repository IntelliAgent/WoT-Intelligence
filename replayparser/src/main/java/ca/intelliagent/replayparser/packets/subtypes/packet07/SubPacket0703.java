package ca.intelliagent.replayparser.packets.subtypes.packet07;

import ca.intelliagent.replayparser.packets.Packet07;
import ca.intelliagent.replayparser.packets.PacketType;
import ca.intelliagent.replayparser.packets.RawPacket;

import java.nio.ByteBuffer;

/**
 * @see <a href="http://wiki.vbaddict.net/pages/Packet_0x00">Packet 00</a>
 */
public class SubPacket0703 extends Packet07 {

    private short health;

    public SubPacket0703(PacketType type, int length, float clock, ByteBuffer payload) {
        super(type, length, clock, payload);
    }

    public SubPacket0703(RawPacket rawPacket) {
        super(rawPacket);
    }

    @Override
    protected void parse(ByteBuffer payload) {
        health = payload.getShort();
    }

    @Override
    public void toReadableFormat() {

    }

    @Override
    public short getHealth() {
        return health;
    }
}
