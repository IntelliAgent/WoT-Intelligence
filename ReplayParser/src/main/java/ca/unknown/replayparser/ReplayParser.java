package ca.unknown.replayparser;

import ca.unknown.replayparser.packets.*;
import ca.unknown.replayparser.reader.PacketReader;

import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;

public class ReplayParser {
    private final PacketFactory packetFactory;

    private final List<Packet> packets;

    private final PacketReader packetReader;

    public ReplayParser(PacketReader packetReader) {
        this.packetReader = packetReader;
        packets = new LinkedList<>();
        packetFactory = new PacketFactory();
    }

    public void parsePackets() {

        ByteBuffer packetRawData;
        RawPacket rawPacket;
        while (packetReader.hasNext()) {

            rawPacket = packetReader.next();

            Packet packet = packetFactory.createPacket(rawPacket);
            packets.add(packet);
        }

        packets.forEach(System.out::println);
    }

    public List<Packet> getPackets() {
        return packets;
    }
}
