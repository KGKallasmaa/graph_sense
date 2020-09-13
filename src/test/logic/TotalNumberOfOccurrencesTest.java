package logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.unitartu.graphSense.entity.EventOccurrence;
import com.unitartu.graphSense.entity.GraphData;
import com.unitartu.graphSense.logic.TotalNumberOfOccurrences;
import org.junit.jupiter.api.Test;

import java.util.*;

class TotalNumberOfOccurrencesTest {

    @Test
    public void simpleCaseIsHandledCorrectly(){
        List<String> args = new ArrayList<>();

        GraphData graphObj1 = new GraphData.GraphDataBuilder("123", "test-1")
                .withArgs(args)
                .build();

        GraphData graphObj2 = new GraphData.GraphDataBuilder("123", "test-2")
                .withArgs(args)
                .build();

        GraphData graphObj3 = new GraphData.GraphDataBuilder("124", "test-1")
                .withArgs(args)
                .build();

        GraphData graphObj4 = new GraphData.GraphDataBuilder("124", "test-3")
                .withArgs(args)
                .build();

        List<GraphData> dataFromFiles = Arrays.asList(graphObj1,graphObj2,graphObj3,graphObj4);

        TotalNumberOfOccurrences totalNumberOfOccurrences = new TotalNumberOfOccurrences();
        Map<EventOccurrence,Integer> results = totalNumberOfOccurrences.calculateTotalNumberOfOccurrences(dataFromFiles);

        assertEquals(2,results.keySet().size());

        EventOccurrence eventOccurrence1 = new EventOccurrence.EventOccurrenceBuilder("test-1","test-2").build();
        EventOccurrence eventOccurrence2 = new EventOccurrence.EventOccurrenceBuilder("test-1","test-3").build();
        assertTrue(Set.copyOf(Arrays.asList(eventOccurrence1,eventOccurrence2)).equals(results.keySet()));

        List<Integer> expectedResults = Arrays.asList(1,1);
        List<Integer> calculatedResults = new ArrayList<>();
        calculatedResults.addAll(results.values());
        assertTrue(expectedResults.equals(calculatedResults));
    }

    @Test
    public void valuesAreConcatenated(){
        List<String> args = new ArrayList<>();

        GraphData graphObj1 = new GraphData.GraphDataBuilder("123", "test-1")
                .withArgs(args)
                .build();

        GraphData graphObj2 = new GraphData.GraphDataBuilder("123", "test-2")
                .withArgs(args)
                .build();

        GraphData graphObj3 = new GraphData.GraphDataBuilder("123", "test-1")
                .withArgs(args)
                .build();

        GraphData graphObj4 = new GraphData.GraphDataBuilder("123", "test-2")
                .withArgs(args)
                .build();

        GraphData graphObj5 = new GraphData.GraphDataBuilder("124", "test-1")
                .withArgs(args)
                .build();

        GraphData graphObj6 = new GraphData.GraphDataBuilder("124", "test-3")
                .withArgs(args)
                .build();

        List<GraphData> dataFromFiles = Arrays.asList(graphObj1,graphObj2,graphObj3,graphObj4,graphObj5, graphObj6);

        TotalNumberOfOccurrences totalNumberOfOccurrences = new TotalNumberOfOccurrences();
        Map<EventOccurrence,Integer> results = totalNumberOfOccurrences.calculateTotalNumberOfOccurrences(dataFromFiles);


        assertEquals(3,results.keySet().size());

        EventOccurrence eventOccurrence1 = new EventOccurrence.EventOccurrenceBuilder("test-1","test-2").build();
        EventOccurrence eventOccurrence2 = new EventOccurrence.EventOccurrenceBuilder("test-2","test-1").build();
        EventOccurrence eventOccurrence3 = new EventOccurrence.EventOccurrenceBuilder("test-1","test-3").build();
        assertTrue(Set.copyOf(Arrays.asList(eventOccurrence1,eventOccurrence2,eventOccurrence3)).equals(results.keySet()));

        List<Integer> sortedExpectedResults = Arrays.asList(1,1,2);
        List<Integer> calculatedResults = new ArrayList<>();
        calculatedResults.addAll(results.values());
        Collections.sort(calculatedResults);
        assertTrue(sortedExpectedResults.equals(calculatedResults));
    }
}
