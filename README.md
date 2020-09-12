# Graph sense

This repository holds the code that is required to transform a .CSV file to different data structures.


## Classes

```java
 class GraphData {
    private final String id;
    private final String name;
    private final List<String> args;


    public boolean hasAnArgument(String argument);
    public String getArgumentValue(String argument);
    public boolean thisEventWasBefore(GraphData o);
}
```
The GraphData class maps the data present in the file to a Java object. Every event needs to have a name and an Id,
arguments (args) are optional. 


```java
public class EventOccurrence {
    private final String startEventName;
    private final String endEventName;
}
```
The EventOccurrence class is used to top record different types of events, where the startEvent is an event that is
followed by the end event in a single class.  

## Functions

### 1. Total Number of occurrences

```java
public class TotalNumberOfOccurrences {
 public HashMap<EventOccurrence, Integer> calculateTotalNumberOfOccurrences(List<GraphData> dataFromFile)
}
```
This function measures how many times different eventOccurrence were present over the dataset.
The result is not sorted by popularity 