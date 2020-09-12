# Graph sense

This repository holds the code that is required to transform a .CSV file to different data structures.

## Data

This program is designed to handle large data volumes, but the rows should have the following structure:

```csv
Case ID,Activity,LoanGoal,ApplicationType,RequestedAmount,case:variant,Action,org:resource,lifecycle:transition,time:timestamp,EventID,EventOrigin,MonthlyCost,Selected,FirstWithdrawalAmount,Accepted,CreditScore,NumberOfTerms,OfferedAmount,OfferID
Application_652823628,A_Create Application,Existing loan takeover,New credit,20000.0,2,Created,User_1,complete,01/01/2016 09:51:15,Application_652823628,Application,,,,,,,,
Application_652823628,A_Submitted,Existing loan takeover,New credit,20000.0,2,statechange,User_1,complete,01/01/2016 09:51:15,ApplState_1582051990,Application,,,,,,,,
Application_652823628,W_Handle leads,Existing loan takeover,New credit,20000.0,2,Created,User_1,schedule,01/01/2016 09:51:15,Workitem_1298499574,Workflow,,,,,,,,
Application_652823628,W_Handle leads,Existing loan takeover,New credit,20000.0,2,Deleted,User_1,withdraw,01/01/2016 09:52:36,Workitem_1673366067,Workflow,,,,,,,,
Application_652823628,W_Complete application,Existing loan takeover,New credit,20000.0,2,Created,User_1,schedule,01/01/2016 09:52:36,Workitem_1493664571,Workflow,,,,,,,,
```

The first row in the CSV file lists all of the columns. The rest of the rows are data values.
Every row needs to have at least 2 values:
1. CaseID, which is a unique ID that allows diffrent events to be grouped
2. Activity, which is a name of the activity we want to measure





## Classes

In this project design the following two classes where used to

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

## Example usecases

This section lists some of the use cases that that the program can be used for. For the examples <a href="https://www.dropbox.com/s/lg3f2kfflrmzsnc/BPI%20Challenge%202017.csv?dl=0">this</a> dataset was used.

### 1. Total Number of occurrences

This function measures how many times different eventOccurrence were present over the dataset.

```java
public class TotalNumberOfOccurrences {
 public Map<EventOccurrence, Integer> calculateTotalNumberOfOccurrences(List<GraphData> dataFromFile)
}
```

Output (top5):

```csv
key:EventOccurrence(startEventName=O_Created, endEventName=O_Sent (mail and online)) count:35541
key:EventOccurrence(startEventName=O_Create Offer, endEventName=O_Created) count:35494
key:EventOccurrence(startEventName=A_Accepted, endEventName=O_Create Offer) count:29798
key:EventOccurrence(startEventName=A_Validating, endEventName=O_Returned) count:20620
key:EventOccurrence(startEventName=A_Concept, endEventName=A_Accepted) count:9146
```

### 2. Number of flows this event was present

This function returns the name of the event and in how many distinct cases this event was present.


```java
public class TotalNumberOfOccurrences {
 public Map<EventOccurrence, Integer> calculateNumberOfDistinctCases(List<GraphData> dataFromFile)
}
```

Output (top5):
```csv
key:EventOccurrence(startEventName=O_Created, endEventName=O_Sent (mail and online)) count:30348
key:EventOccurrence(startEventName=A_Accepted, endEventName=O_Create Offer) count:29798
key:EventOccurrence(startEventName=O_Create Offer, endEventName=O_Created) count:26811
key:EventOccurrence(startEventName=A_Validating, endEventName=O_Returned) count:20354
key:EventOccurrence(startEventName=A_Concept, endEventName=A_Accepted) count:9146
```

### 3. Max number of times an event was present during a case

This function returns the name of the event and the maximum times this event was present in a given event


```java
public class MaxMinPerCase {
 public Map<EventOccurrence, Integer> calculateNumberOfTimesPerCase("max", List<GraphData> dataFromFile)
}
```

Output (top5):
```csv
key:EventOccurrence(startEventName=O_Create Offer, endEventName=O_Created) count:10
key:EventOccurrence(startEventName=O_Created, endEventName=O_Create Offer) count:8
key:EventOccurrence(startEventName=O_Created, endEventName=O_Sent (mail and online)) count:7
key:EventOccurrence(startEventName=A_Validating, endEventName=W_Validate application) count:5
key:EventOccurrence(startEventName=O_Created, endEventName=O_Sent (online only)) count:4
```

### 4. Min number of times an event was present during a case

This function returns the name of the event and the minimum times this event was present in a given event


```java
public class MaxMinPerCase {
 public Map<EventOccurrence, Integer> calculateNumberOfTimesPerCase("min", List<GraphData> dataFromFile)
}
```

Output (top5):
```csv
key:EventOccurrence(startEventName=O_Create Offer, endEventName=O_Created) count:10
key:EventOccurrence(startEventName=A_Create Application, endEventName=A_Submitted) count:1
key:EventOccurrence(startEventName=A_Validating, endEventName=O_Create Offer) count:1
key:EventOccurrence(startEventName=A_Denied, endEventName=O_Refused) count:1
key:EventOccurrence(startEventName=A_Validating, endEventName=O_Returned) count:1
key:EventOccurrence(startEventName=O_Sent (mail and online), endEventName=O_Sent (mail and online)) count:1
```