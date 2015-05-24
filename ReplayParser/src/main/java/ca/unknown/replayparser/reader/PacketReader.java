package ca.unknown.replayparser.reader;

import java.nio.ByteBuffer;

public interface PacketReader {
    int readType();

    int readSubType();

    int readLength();

    float readClock();

    boolean hasRemaining();

    ByteBuffer readPayload(int length);

    ByteBuffer getRawData();
}
