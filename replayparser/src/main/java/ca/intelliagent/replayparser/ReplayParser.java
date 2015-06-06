package ca.intelliagent.replayparser;

import ca.intelliagent.replayparser.packets.*;
import ca.intelliagent.replayparser.reader.PacketReader;

import java.util.*;
import java.util.stream.Stream;

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
        Set<Integer> playerIDs = new HashSet();
        while (packetReader.hasNext()) {

            rawPacket = packetReader.next();

            if (rawPacket.isValid()) {
                Packet packet = packetFactory.createPacket(rawPacket);
                int playerID = packet.getPlayerID();
                if (playerID > 0) {
                    playerIDs.add(playerID);
                }
                packets.add(packet);
            }
        }
        Stream<Integer> sorted = playerIDs.stream().sorted();

        sorted.forEach(System.out::println);
    }

    public List<Packet> getPackets() {
        return packets;
    }
}
