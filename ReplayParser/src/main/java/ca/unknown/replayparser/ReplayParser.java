package ca.unknown.replayparser;

import ca.unknown.replaydecoder.swapper.ByteSwapper;
import ca.unknown.replayparser.packets.Packet;
import ca.unknown.replayparser.packets.PacketFactory;
import ca.unknown.replayparser.packets.PacketType;

import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;

public class ReplayParser {
    private static final int SIZE_OF_INT = 4;

    private final PacketFactory packetFactory;

    private ByteBuffer replayPackets;

    private List<Packet> packets;

    public ReplayParser(ByteBuffer decodedReplay) {
        replayPackets = decodedReplay;
        packets = new LinkedList<>();
        packetFactory = new PacketFactory();
    }

    public void parsePackets() {
        int type;
        int length;
        float clock;

        ByteBuffer packetRawData;

        while (replayPackets.hasRemaining()) {

            type = getType();
            length = getLength();
            clock = getClock();

            packetRawData = getRawPacketData(length);

            packets.add(packetFactory.createPacket(PacketType.fromInt(type), length, clock, packetRawData));
        }
    }

    public List<Packet> getPackets() {
        return packets;
    }

    private int getType() {
        return ByteSwapper.swap(replayPackets.getInt());
    }

    private int getLength() {
        return ByteSwapper.swap(replayPackets.getInt());
    }

    private float getClock() {
        return replayPackets.getFloat();
    }

    private ByteBuffer getRawPacketData(int length) {
        return replayPackets.get(new byte[length]);
    }


}
