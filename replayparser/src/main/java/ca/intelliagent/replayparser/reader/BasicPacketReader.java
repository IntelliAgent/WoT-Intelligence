package ca.intelliagent.replayparser.reader;

import ca.intelliagent.replayparser.packets.RawPacket;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;


public class BasicPacketReader implements PacketReader {

    private final ByteBuffer replayData;

    public BasicPacketReader(ByteBuffer replayData) {
        this.replayData = replayData;
        replayData.order(ByteOrder.LITTLE_ENDIAN);
    }

    @Override
    public ByteBuffer getRawData() {
        return replayData;
    }

    private int readType() {
        return readInt();
    }

    private int readLength() {
        return readInt();
    }

    private float readClock() {
        return replayData.getFloat();
    }

    private int readInt() {
        return replayData.getInt();
    }

    @Override
    public boolean hasNext() {
        return replayData.hasRemaining();
    }

    @Override
    public RawPacket next() {
        int length = readLength();
        int type = readType();
        float clock = readClock();
        byte[] dst = new byte[length];
        replayData.get(dst);
        return new RawPacket(type, length, clock, ByteBuffer.wrap(dst));
    }
}
