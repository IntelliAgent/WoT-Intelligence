package ca.intelliagent.replayparser.packets.subtypes.packet07;

import ca.intelliagent.replayparser.packets.Packet07;
import ca.intelliagent.replayparser.packets.PacketType;
import ca.intelliagent.replayparser.packets.RawPacket;

import java.nio.ByteBuffer;

/**
 * @see <a href="http://wiki.vbaddict.net/pages/Packet_0x00">Packet 00</a>
 */
public class SubPacket0707 extends Packet07 {

    private int destroyedTrackID;

    public SubPacket0707(PacketType type, int length, float clock, ByteBuffer payload) {
        super(type, length, clock, payload);
    }

    public SubPacket0707(RawPacket rawPacket) {
        super(rawPacket);
    }

    public int getDestroyedTrackID() {
        return destroyedTrackID;
    }

    @Override
    protected void parse(ByteBuffer payload) {
        destroyedTrackID = payload.getInt(4);
    }

    @Override
    public void toReadableFormat() {

    }
}
