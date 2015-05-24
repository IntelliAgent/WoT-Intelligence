package ca.unknown.replayparser;

import ca.unknown.common.swapper.ByteSwapper;

import java.nio.ByteBuffer;

public class PacketReader {

    private ByteBuffer replayData;

    public PacketReader(ByteBuffer replayData){
        this.replayData = replayData;
    }

    public int getType() {
        return ByteSwapper.swap(replayData.getInt());
    }

    public int getLength() {
        return ByteSwapper.swap(replayData.getInt());
    }

    public float getClock() {
        return replayData.getFloat();
    }

    public boolean hasRemaining(){
        return replayData.hasRemaining();
    }

    public ByteBuffer getRawPacketData(int length) {
        return replayData.get(new byte[length]);
    }
}
