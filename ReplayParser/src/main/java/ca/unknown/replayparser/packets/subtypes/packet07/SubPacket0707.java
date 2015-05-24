package ca.unknown.replayparser.packets.subtypes.packet07;

import ca.unknown.replayparser.packets.Packet07;
import ca.unknown.replayparser.packets.PacketType;

import java.nio.ByteBuffer;

public class SubPacket0707 extends Packet07 {

    private int destroyedTrackID;

    public SubPacket0707(PacketType type, int length, float clock, ByteBuffer payload) {
        super(type, length, clock, payload);

        destroyedTrackID = payload.getInt(4);
    }

    @Override
    public void toReadableFormat() {

    }
}
