package ca.unknown.replayparser;

import ca.unknown.replayparser.packets.RawPacket;
import ca.unknown.replayparser.reader.BasicPacketReader;
import ca.unknown.replayparser.reader.PacketReader;
import org.junit.*;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.*;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

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
