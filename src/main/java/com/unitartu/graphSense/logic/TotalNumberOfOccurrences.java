package com.unitartu.graphSense.logic;

import com.unitartu.graphSense.entity.EventOccurrence;
import com.unitartu.graphSense.entity.GraphData;

import java.util.*;
import java.util.stream.Collectors;

public class TotalNumberOfOccurrences {

    public HashMap<EventOccurrence,Integer> calculateTotalNumberOfOccurrences(List<GraphData> dataFromFile){
        Map<String,List<String>> id_events = dataFromFile.stream()
                .filter(e->e.getId() != null && e.getName() != null)
                .collect(Collectors.groupingBy(GraphData::getId))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        x->x.getValue().stream().map(GraphData::getName).collect(Collectors.toList()))
                );


        // TODO: implement streams
        HashMap<EventOccurrence,Integer> occurringEvent_Count = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : id_events.entrySet()) {
            List<String> sequentialEvents = entry.getValue();
            for (int i = 0; i < sequentialEvents.size()-1; i++) {
                String startEvent = sequentialEvents.get(i);
                String endEvent = sequentialEvents.get(i+1);

                EventOccurrence eventOccurrence = new EventOccurrence.EventOccurrenceBuilder(startEvent,endEvent).build();

                Integer newCount = 1;
                if (occurringEvent_Count.containsKey(eventOccurrence)){
                    Integer extraCount = occurringEvent_Count.get(eventOccurrence);
                    newCount += extraCount;
                }
                occurringEvent_Count.put(eventOccurrence,newCount);
            }
        }
        return occurringEvent_Count;
    }
}
