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
    }

    public List<Packet> getPackets() {
        return packets;
    }
}
