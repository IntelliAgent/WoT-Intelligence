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
        return ByteSwapper.swap(replayData.getInt());
    }

    @Override
    public int readLength() {
        return ByteSwapper.swap(replayData.getInt());
    }

    @Override
    public float readClock() {
        return replayData.getFloat();
    }

    @Override
    public boolean hasRemaining() {
        return replayData.hasRemaining();
    }

    @Override
    public ByteBuffer readPayload(int length) {
        return replayData.get(new byte[length]);
    }
}
