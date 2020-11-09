package com.unitartu.graphSense.logic;

import com.unitartu.graphSense.entity.EventOccurrence;
import com.unitartu.graphSense.entity.GraphData;

import java.util.*;
import java.util.stream.Collectors;


public class MaxMinPerCase {
    /*
    public Map<EventOccurrence, Integer> calculateNumberOfTimesPerCase(String mode, List<GraphData> dataFromFile) {

        Map<String, List<GraphData>> id_events = dataFromFile.parallelStream()
                .filter(e -> e.getId() != null && e.getName() != null)
                .collect(Collectors.groupingBy(GraphData::getId));

        HashMap<EventOccurrence, List<String>> occurringEvent_Cases = new HashMap<>();


        for (Map.Entry<String, List<GraphData>> entry : id_events.entrySet()) {
            List<GraphData> events = entry.getValue();
            for (int i = 1; i < events.size() - 1; i++) {
                GraphData event1 = events.get(i - 1);
                GraphData event2 = events.get(i);
                boolean bothEventsHaveCompleteAndFirstEventWasBeforeSecond = event1.hasAnArgument("complete") && event2.hasAnArgument("complete") && event1.thisEventWasBefore(event2,events);
                if (bothEventsHaveCompleteAndFirstEventWasBeforeSecond) {
                    EventOccurrence eventOccurrence = new EventOccurrence.EventOccurrenceBuilder(event1.getName(), event2.getName()).build();

                    List<String> occuredCases = occurringEvent_Cases.containsKey(eventOccurrence) ? occurringEvent_Cases.get(eventOccurrence) : new ArrayList<>();
                    occuredCases.add(entry.getKey());
                    occurringEvent_Cases.put(eventOccurrence, occuredCases);
                }
            }
        }

        Map<EventOccurrence, Integer> occurringEvent_Count = new HashMap<>();

        for (Map.Entry<EventOccurrence, List<String>> entry : occurringEvent_Cases.entrySet()) {
            Optional<Map.Entry<String, Long>> targetValue = Optional.empty();

            if (mode.equals("min")) {
                targetValue = entry.getValue().stream()
                        .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                        .entrySet()
                        .stream()
                        .min(Map.Entry.comparingByValue());
            } else if (mode.equals("max")) {
                targetValue = entry.getValue().stream()
                        .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                        .entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue());
            }
            int count = 0;
            if (targetValue.isPresent()) {
                count = Math.toIntExact(targetValue.get().getValue());
            }
            occurringEvent_Count.put(entry.getKey(), count);
        }
        return occurringEvent_Count;
    }
     */
}