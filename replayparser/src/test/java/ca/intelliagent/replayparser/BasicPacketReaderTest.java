package ca.intelliagent.replayparser;

import ca.intelliagent.replayparser.packets.RawPacket;
import ca.intelliagent.replayparser.reader.BasicPacketReader;
import ca.intelliagent.replayparser.reader.PacketReader;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class BasicPacketReaderTest {

    private static ByteBuffer buffer;
    private PacketReader packetReader;

    @BeforeClass
    public static void setUpClass() {
        buffer = getReplayRawData();
    }

    private static ByteBuffer getReplayRawData() {
        byte[] data = null;
        try {
            Path path = Paths.get(System.getProperty("user.dir") + "/src/test/resources/decompressedData.wia");
            data = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ByteBuffer.wrap(data);
    }

    @Before
    public void setUp() {
        packetReader = new BasicPacketReader(buffer);
    }

    @After
    public void tearDown() {
        buffer.rewind();
    }

    @Test
    public void rawDataSizeShouldNotBeZero() {
        assertTrue("Should be greater than 0", packetReader.getRawData().limit() > 0);
    }

    @Test
    public void lengthAndRawDataSizeShouldBeTheSameSize() {
        RawPacket next = packetReader.next();
        int payloadLength = next.getPayloadLength();

        assertEquals("Should have equal length", payloadLength, next.getPayload().limit());
    }
}
