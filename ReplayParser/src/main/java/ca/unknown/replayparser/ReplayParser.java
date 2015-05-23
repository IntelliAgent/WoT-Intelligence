package ca.unknown.replayparser;

import ca.unknown.replayparser.packets.Packet;
import ca.unknown.replayparser.packets.PacketFactory;
import ca.unknown.replaydecoder.swapper.ByteSwapper;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.ArrayList;

public class ReplayParser {
    private static final int MIN_PACKET_SIZE = 12;

    private static final int SIZE_OF_INT = 4;

    private final PacketFactory packetFactory;

    private ByteBuffer replayPackets;

    private List<Packet> packets;

    public ReplayParser(ByteBuffer decodedReplay) {
        replayPackets = decodedReplay;
        packets = new ArrayList<>(); //Ineffective as fuck, no size allocation
        packetFactory = new PacketFactory();
    }

    public void parsePackets() {
        int     type;
        int     length;
        float   clock;

        ByteBuffer packetRawData;

        while(replayPackets.hasRemaining()){
            type    = getType(replayPackets);
            length  = getLength(replayPackets);
            clock   = getClock(replayPackets);

            packetRawData = getRawPacketData(length);

            packets.add(packetFactory.createPacket(type,length,clock,packetRawData));

            replayPackets.position(replayPackets.position() + length);
        }
    }

    private int getType(ByteBuffer buffer) {
        return ByteSwapper.swap(replayPackets.getInt(replayPackets.position()));
    }

    private int getLength(ByteBuffer buffer) {
        return ByteSwapper.swap(replayPackets.getInt(replayPackets.position() + SIZE_OF_INT));
    }

    private float getClock(ByteBuffer buffer) {
        return replayPackets.getFloat(replayPackets.position() + 2*SIZE_OF_INT);
    }

    private ByteBuffer getRawPacketData(int length){
        return replayPackets.get(
            new byte[length],
            replayPackets.position(),
            length);
    }

}
