package ca.unknown.replayparser;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.io.File;

public class ReplayParserTest {

    private static ByteBuffer buffer;
    private ReplayParser replayParser;

    @BeforeClass
    public void setUpClass(){
        //buffer = getReplayRawData();
        buffer = ByteBuffer.wrap(new byte[36]);
    }

    @Before
    public void setUp() {
        replayParser = new ReplayParser(buffer);
    }

    @After
    public void tearDown(){
        buffer.reset();
    }

    @Test
    public void test() {
        replayParser.parsePackets();
    }

    private ByteBuffer getReplayRawData(){
        File f = new File(System.getProperty("user.dir") + "/src/test/resources/decompressedData.dat");
        
        return null;
    }
}
