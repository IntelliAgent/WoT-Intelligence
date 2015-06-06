package ca.unknown.replayparser.reader;

import ca.unknown.replayparser.packets.RawPacket;

import java.nio.ByteBuffer;


public class BasicPacketReader implements PacketReader {

    private final ByteBuffer replayData;

    public BasicPacketReader(ByteBuffer replayData) {
        this.replayData = replayData;
    }

    @Override
    public ByteBuffer getRawData() {
        return replayData;
    }

    private int readType() {
        return readInt();
    }

    private int readSubType() {
        return readInt();
    }

    private int readLength() {
        return readInt();
    }

    private float readClock() {
        return replayData.getFloat();
    }

//    @Override
//    public ByteBuffer readPayload(int length) {
//        byte[] dst = new byte[length];
//        replayData.get(dst);
//        ByteBuffer wrap = ByteBuffer.wrap(dst);
//        return wrap.order(ByteOrder.LITTLE_ENDIAN);
//    }

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
        return new RawPacket(type, length, clock, replayData);
    }
}
