package com.unitartu.graphSense.entity;


import lombok.EqualsAndHashCode;
import lombok.ToString;


@ToString
@EqualsAndHashCode
public class EventOccurrence {
    private final String startEventName;
    private final String endEventName;

    private EventOccurrence(EventOccurrenceBuilder builder) {
        this.startEventName = builder.startEventName;
        this.endEventName = builder.endEventName;
    }

    public static class EventOccurrenceBuilder {
        private final String startEventName;
        private final String endEventName;

        public EventOccurrenceBuilder(String startEventName, String endEventName) {
            this.startEventName = startEventName;
            this.endEventName = endEventName;
        }

        public EventOccurrence build() {
            return new EventOccurrence(this);
        }
    }
}
