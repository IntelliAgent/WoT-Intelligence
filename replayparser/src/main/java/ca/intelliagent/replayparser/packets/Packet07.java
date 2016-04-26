package ca.intelliagent.replayparser.packets;

import java.nio.ByteBuffer;

/**
 *  @see <a href="http://wiki.vbaddict.net/pages/Packet_0x07">Packet 07</a>
 */
public class Packet07 extends Packet {

    public Packet07(PacketType type, int length, float clock, ByteBuffer buffer) {
        super(type, length, clock, buffer);
    }

    public Packet07(RawPacket rawPacket) {
        super(rawPacket);
    }

    @Override
    public void toReadableFormat() {

    }
}