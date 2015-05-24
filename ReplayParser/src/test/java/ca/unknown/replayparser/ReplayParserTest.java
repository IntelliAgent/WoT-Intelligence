package ca.unknown.replayparser;

import org.junit.Before;
import org.junit.Test;

import java.nio.ByteBuffer;


public class ReplayParserTest {

    private static ByteBuffer buffer;
    private ReplayParser replayParser;

    @Before
    public void setUp() {
        buffer = ByteBuffer.wrap(new byte[36]);
        replayParser = new ReplayParser(buffer);

    }

    @Test
    public void test() {
        replayParser.parsePackets();
    }
}