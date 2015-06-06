package ca.intelliagent.replayparser.reader;

import ca.intelliagent.replayparser.packets.RawPacket;

import java.nio.ByteBuffer;
import java.util.Iterator;

public interface PacketReader extends Iterator<RawPacket> {

    ByteBuffer getRawData();
}
