package com.unitartu.graphSense.logic;

import com.unitartu.graphSense.entity.EventOccurrence;
import com.unitartu.graphSense.entity.GraphData;

import java.util.*;

public class TotalNumberOfOccurrences {
    // TODO: implement streams
    public HashMap<EventOccurrence,Integer> calculateTotalNumberOfOccurrences(List<GraphData> dataFromFile){

        HashMap<String,List<String>> id_events = new HashMap<>();

        for(GraphData g :dataFromFile){
            String startEventName = g.getName();
            String id = g.getId();

            List<String> currentValue  = new ArrayList<>();
            if (id_events.containsKey(id)){
                currentValue = id_events.get(id);
            }
            currentValue.add(startEventName);
            id_events.put(id,currentValue);
        }

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
