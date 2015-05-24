package ca.unknown.replayparser;

import ca.unknown.replayparser.packets.Packet;
import ca.unknown.replayparser.packets.PacketFactory;
import ca.unknown.replayparser.packets.PacketType;
import ca.unknown.common.swapper.ByteSwapper;

import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;

public class ReplayParser {
    private final PacketFactory packetFactory;

    private List<Packet> packets;

    private PacketReader packetReader;

    public ReplayParser(PacketReader packetReader) {
        this.packetReader = packetReader;
        packets = new LinkedList<>();
        packetFactory = new PacketFactory();
    }

    public void parsePackets() {
        int type;
        int length;
        float clock;

        ByteBuffer packetRawData;

        while (packetReader.hasRemaining()) {

            type = packetReader.getType();
            length = packetReader.getLength();
            clock = packetReader.getClock();

            packetRawData = packetReader.getRawPacketData(length);

            packets.add(packetFactory.createPacket(PacketType.fromInt(type), length, clock, packetRawData));
        }
    }

    public List<Packet> getPackets() {
        return packets;
    }
}
