package ca.intelliagent.replayparser.packets;

import java.nio.ByteBuffer;

/**
 *  @see <a href="http://wiki.vbaddict.net/pages/Packet_0x03">Packet 03</a>
 */
public class Packet03 extends Packet {

    public Packet03(PacketType type, int length, float clock, ByteBuffer buffer) {
        super(type, length, clock, buffer);
    }

    public Packet03(RawPacket rawPacket) {
        super(rawPacket);
    }

    @Override
    public void toReadableFormat() {

    }
}
