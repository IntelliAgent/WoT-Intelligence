package ca.intelliagent.replayparser;

import ca.intelliagent.replayparser.packets.*;
import ca.intelliagent.replayparser.reader.BasicPacketReader;
import ca.intelliagent.replayparser.reader.PacketReader;

import java.nio.ByteBuffer;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import static java.lang.StrictMath.pow;
import static java.lang.StrictMath.sqrt;
import static java.util.stream.Collectors.toList;

public class ReplayParser {
    private final PacketFactory packetFactory = new PacketFactory();

    private final List<Packet> packets = new LinkedList<>();

    private final PacketReader packetReader;

    public ReplayParser(PacketReader packetReader) {
        this.packetReader = packetReader;
    }

    public ReplayParser(ByteBuffer replayData){
        this.packetReader = new BasicPacketReader(replayData);
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

        Map<Integer, List<Packet>> maps = packets.stream()
                .filter(p -> p instanceof Packet0a)
                .collect(Collectors.groupingBy(Packet::getPlayerID, toList()));

        for (Entry<Integer, List<Packet>> integerListEntry : maps.entrySet()) {
            List<Packet> packetList = integerListEntry.getValue();
            for (int i = 0; i < packetList.size() - 10; i += 10) {
                Packet packet1 = packetList.get(i);
                Packet packet2 = packetList.get(i + 10);
                float posX = packet1.getPosX();
                float posY = packet1.getPosY();
                float posZ = packet1.getPosZ();

                float posX1 = packet2.getPosX();
                float posY1 = packet2.getPosY();
                float posZ1 = packet2.getPosZ();
                double sqrt = sqrt(pow(posX - posX1, 2) + pow(posY - posY1, 2) + pow(posZ - posZ1, 2));
                if (sqrt != 0) {
                    System.out.println(
                            "PlayerID : " + integerListEntry.getKey() +
                                    "\n Distance : " + sqrt +
                                    "\n Clock 1: " + packet1.getClock() +
                                    "\n Clock 2: " + packet2.getClock());
                    System.out.println("------");
                }
            }
        }
    }

    public List<Packet> getPackets() {
        return packets;
    }
}
