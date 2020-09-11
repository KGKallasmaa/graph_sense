package com.unitartu.graphSense.logic;

import com.unitartu.graphSense.entity.EventOccurrence;
import com.unitartu.graphSense.entity.GraphData;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



public class TotalNumberOfOccurrences {


    public HashMap<EventOccurrence, Integer> calculateTotalNumberOfOccurrences(List<GraphData> dataFromFile) {
        Map<String, List<GraphData>> id_events = dataFromFile.stream()
                .filter(e -> e.getId() != null && e.getName() != null)
                .collect(Collectors.groupingBy(GraphData::getId));


        HashMap<EventOccurrence, Integer> occurringEventCount = new HashMap<>();

        for (Map.Entry<String, List<GraphData>> entry : id_events.entrySet()) {
            List<GraphData> events = entry.getValue();
            for (int i = 1; i < events.size() - 1; i++) {
                GraphData event1 = events.get(i - 1);
                GraphData event2 = events.get(i);

                boolean bothEventsHaveCompleteAndFirstEventWasBeforeSecond = event1.hasAnArgument("complete") && event2.hasAnArgument("complete") && event1.thisEventWasBefore(event2);

                if (bothEventsHaveCompleteAndFirstEventWasBeforeSecond) {
                    EventOccurrence eventOccurrence = new EventOccurrence.EventOccurrenceBuilder(event1.getName(), event2.getName()).build();

                    Integer newCount = 1;
                    if (occurringEventCount.containsKey(eventOccurrence)) {
                        Integer extraCount = occurringEventCount.get(eventOccurrence);
                        newCount += extraCount;
                    }
                    occurringEventCount.put(eventOccurrence, newCount);
                }
            }
        }

        return occurringEventCount;
    }
}
