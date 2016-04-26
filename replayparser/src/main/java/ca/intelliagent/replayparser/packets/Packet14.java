package ca.intelliagent.replayparser.packets;

import java.nio.ByteBuffer;

/**
 *  @see <a href="http://wiki.vbaddict.net/pages/Packet_0x14">Packet 14</a>
 */
public class Packet14 extends Packet {

    public Packet14(PacketType type, int length, float clock, ByteBuffer buffer) {
        super(type, length, clock, buffer);
    }

    public Packet14(RawPacket rawPacket) {
        super(rawPacket);
    }

    @Override
    public void toReadableFormat() {

    }
}
