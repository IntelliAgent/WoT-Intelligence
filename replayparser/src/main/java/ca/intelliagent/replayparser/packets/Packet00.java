package ca.intelliagent.replayparser.packets;

import java.nio.ByteBuffer;

/**
 *  @see <a href="http://wiki.vbaddict.net/pages/Packet_0x00">Packet 00</a>
 */
public class Packet00 extends Packet {

    public Packet00(PacketType type, int length, float clock, ByteBuffer payload) {
        super(type, length, clock, payload);
    }

    public Packet00(RawPacket rawPacket) {
        super(rawPacket);
    }

    @Override
    public void toReadableFormat() {

    }
}
