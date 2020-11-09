package com.unitartu.graphSense.entity;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@ToString
@EqualsAndHashCode
@Getter
public class EventOccurrence {
    private final GraphData startEvent;
    private final GraphData endEvent;

    public EventOccurrence(EventOccurrenceBuilder builder) {
        this.startEvent = builder.startEvent;
        this.endEvent = builder.endEvent;
    }




    public static class EventOccurrenceBuilder {
        private final GraphData startEvent;
        private final GraphData endEvent;

        public EventOccurrenceBuilder(GraphData startEvent, GraphData endEvent) {
            this.startEvent = startEvent;
            this.endEvent = endEvent;
        }

        public EventOccurrence build() {
            return new EventOccurrence(this);
        }
    }
}
