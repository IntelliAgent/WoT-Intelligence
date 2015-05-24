package ca.unknown.replayparser;

import ca.unknown.replayparser.packets.Packet;
import ca.unknown.replayparser.packets.PacketFactory;
import ca.unknown.replayparser.packets.PacketType;
import ca.unknown.replayparser.reader.PacketReader;

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

            length = packetReader.readLength();
            type = packetReader.readType();
            clock = packetReader.readClock();

            packetRawData = packetReader.readPayload(length);

            if (PacketType.fromInt(type) != null && length > 0 && packetRawData != null)
                packets.add(packetFactory.createPacket(PacketType.fromInt(type), length, clock, packetRawData));
        }

        packets.forEach(System.out::println);
    }

    public List<Packet> getPackets() {
        return packets;
    }
}
