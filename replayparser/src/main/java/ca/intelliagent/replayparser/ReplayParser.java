package ca.intelliagent.replayparser;

import ca.intelliagent.replayparser.packets.Packet;
import ca.intelliagent.replayparser.packets.PacketFactory;
import ca.intelliagent.replayparser.packets.RawPacket;
import ca.intelliagent.replayparser.reader.BasicPacketReader;
import ca.intelliagent.replayparser.reader.PacketReader;

import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;

public class ReplayParser {
    private final PacketFactory packetFactory = new PacketFactory();

    private final List<Packet> packets = new LinkedList<>();

    private final PacketReader packetReader;

    public ReplayParser(PacketReader packetReader) {
        this.packetReader = packetReader;
    }

    public ReplayParser(ByteBuffer replayData) {
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
