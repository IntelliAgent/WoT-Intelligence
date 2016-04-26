package ca.intelliagent.replayanalyser.domain.game;

import ca.intelliagent.replaydecoder.ReplayDecoderFactory;
import ca.intelliagent.replaydecoder.ReplayDecoderWithTwoBlocks;
import ca.intelliagent.replaydecoder.ReplayFileReader;
import ca.intelliagent.replayparser.ReplayParser;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.util.LinkedList;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameFactoryTest extends TestCase {
    private static final String FILENAME = "/testReplay.wotreplay";
    private GameFactory factory;
    @Mock
    private ReplayDecoderFactory replayDecoderFactory;
    @Mock
    private ReplayFileReader replayFileReader;
    @Mock
    private ReplayParser replayParser;

    private File replayFile;

    @Before
    public void setUp() {
        replayFile = new File(GameFactoryTest.class.getResource(FILENAME).getPath());
        when(replayFileReader.getNumberOfBlocks()).thenReturn(2);
        when(replayFileReader.getFirstBlockAsReadableJson()).thenReturn("{}");
        when(replayFileReader.getSecondBlockAsReadableJson()).thenReturn("[]");
        when(replayDecoderFactory.getReplayDecoder(replayFileReader)).thenReturn(new ReplayDecoderWithTwoBlocks(replayFileReader));
        when(replayParser.getPackets()).thenReturn(new LinkedList<>());

        factory = new GameFactory(replayDecoderFactory, replayFileReader, replayParser);
    }
}