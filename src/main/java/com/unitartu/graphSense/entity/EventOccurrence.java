package com.unitartu.graphSense.entity;


import lombok.ToString;

import java.util.Objects;

@ToString
public class EventOccurrence {
    private final String startEventName;
    private final String endEventName;

    private EventOccurrence(EventOccurrenceBuilder builder) {
        this.startEventName = builder.startEventName;
        this.endEventName = builder.endEventName;
    }

    public static class EventOccurrenceBuilder
    {
        private final String startEventName;
        private final String endEventName;

        public EventOccurrenceBuilder(String startEventName,String endEventName) {
            this.startEventName = startEventName;
            this.endEventName = endEventName;
        }

        public EventOccurrence build() {
            EventOccurrence eventOccurrence =  new EventOccurrence(this);
            return eventOccurrence;
        }
    }



    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        EventOccurrence otherEventOccurrence = (EventOccurrence) o;
        return Objects.equals(this.startEventName, otherEventOccurrence.startEventName)
                && Objects.equals(this.endEventName, otherEventOccurrence.endEventName);
    }
    @Override
    public int hashCode() {
        return startEventName.hashCode() * endEventName.hashCode();
    }
}
