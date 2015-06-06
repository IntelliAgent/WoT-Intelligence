package ca.intelliagent.replayparser;

import ca.intelliagent.replayparser.reader.BasicPacketReader;
import ca.intelliagent.replayparser.reader.PacketReader;
import org.junit.*;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.*;

public class ReplayParserTest {

    private static ByteBuffer buffer;

    private ReplayParser replayParser;

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
    public void setup() {
        packetReader = new BasicPacketReader(buffer);
        replayParser = new ReplayParser(packetReader);
    }

    @Test
    public void test() {
        replayParser.parsePackets();
    }


}