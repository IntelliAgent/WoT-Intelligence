package ca.unknown.replayparser.reader;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;


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
        ByteBuffer wrap = ByteBuffer.wrap(dst);
        return wrap.order(ByteOrder.LITTLE_ENDIAN);
    }

    private int readInt() {
        return replayData.getInt();
    }

    @Override
    public boolean hasRemaining() {
        return replayData.hasRemaining();
    }
}
