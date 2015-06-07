package ca.intelliagent.replayparser;

import ca.intelliagent.replayparser.packets.*;
import ca.intelliagent.replayparser.reader.PacketReader;

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

        RawPacket rawPacket;
        while (packetReader.hasNext()) {

            rawPacket = packetReader.next();

            if (rawPacket.isValid()) {
                Packet packet = packetFactory.createPacket(rawPacket);
                packets.add(packet);
            }
        }

        packets.forEach(System.out::println);
    }

    public List<Packet> getPackets() {
        return packets;
    }
}
