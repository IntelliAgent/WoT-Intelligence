package ca.unknown.replayparser;

import ca.unknown.replayparser.packets.Packet;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static junit.framework.Assert.assertFalse;

public class ReplayParserTest {

    private static ByteBuffer buffer;
    private ReplayParser replayParser;

    @BeforeClass
    public static void setUpClass() {
        buffer = getReplayRawData();
    }

    @Before
    public void setUp() {
        replayParser = new ReplayParser(buffer);
    }

    @After
    public void tearDown() {
        buffer.reset();
    }

    @Test
    public void test() {
        replayParser.parsePackets();
        List<Packet> packets = replayParser.getPackets();
        assertFalse(packets.isEmpty());
    }

    private static ByteBuffer getReplayRawData() {
        byte[] data = null;
        try {
            Path path = Paths.get(System.getProperty("user.dir") + "/src/test/resources/ca.unknown.replayparser/decompressedData.dat");
            data = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ByteBuffer.wrap(data);
    }
}
