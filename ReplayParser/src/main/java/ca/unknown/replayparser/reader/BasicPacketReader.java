package ca.unknown.replayparser.reader;

import ca.unknown.common.swapper.ByteSwapper;

import java.nio.ByteBuffer;

public class BasicPacketReader implements PacketReader {

    private ByteBuffer replayData;

    public BasicPacketReader(ByteBuffer replayData) {
        this.replayData = replayData;
    }

    @Override
    public ByteBuffer getRawData() {
        return replayData;
    }

    @Override
    public int readType() {
        return readInt();
    }

    @Override
    public int readSubType() {
        return readInt();
    }

    @Override
    public int readLength() {
        return readInt();
    }

    @Override
    public float readClock() {
        return replayData.getFloat();
    }

    @Override
    public ByteBuffer readPayload(int length) {
        byte[] dst = new byte[length];
        replayData.get(dst);
        return ByteBuffer.wrap(dst);
    }

    private int readInt() {
        return ByteSwapper.swap(replayData.getInt());
    }

    @Override
    public boolean hasRemaining() {
        return replayData.hasRemaining();
    }
}
