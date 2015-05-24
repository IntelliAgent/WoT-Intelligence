package ca.unknown.replayparser;

import ca.unknown.replayparser.reader.BasicPacketReader;
import ca.unknown.replayparser.reader.PacketReader;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

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
        assertTrue(packetReader.getRawData().limit() > 0);
    }

    @Test
    public void lengthAndRawDataSizeShouldBeTheSameSize() {
        int length = packetReader.readLength();
        int type = packetReader.readType();
        float clock = packetReader.readClock();
        ByteBuffer payload = packetReader.readPayload(length);

        assertEquals(length, payload.array().length);
    }

    @Test
    public void test() {
        List<Integer> type = new LinkedList<>();
        List<Integer> length = new LinkedList<>();
        List<Float> clock = new LinkedList<>();

        while (packetReader.hasRemaining()) {
            length.add(packetReader.readLength());
            type.add(packetReader.readType());
            clock.add(packetReader.readClock());
            packetReader.readPayload(length.get(length.size() - 1));
        }

assertEquals(type.size(), length.size());
        assertTrue(!type.isEmpty());
    }
}
