package ca.intelliagent.replayparser.packets;

import java.nio.ByteBuffer;

/**
 *  @see <a href="http://wiki.vbaddict.net/pages/Packet_0x08">Packet 08</a>
 */
public class Packet08 extends Packet {

    public Packet08(PacketType type, int length, float clock, ByteBuffer buffer) {
        super(type, length, clock, buffer);
    }

    public Packet08(RawPacket rawPacket) {
        super(rawPacket);
    }

    @Override
    public void toReadableFormat() {

    }
}
