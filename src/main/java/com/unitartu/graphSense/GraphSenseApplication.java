package com.unitartu.graphSense;

import com.unitartu.graphSense.entity.EventOccurrence;
import com.unitartu.graphSense.entity.GraphData;
import com.unitartu.graphSense.logic.MaxMinPerCase;
import com.unitartu.graphSense.logic.MyFileReader;
import com.unitartu.graphSense.logic.NumberOfDistinctCases;
import com.unitartu.graphSense.util.MapUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class GraphSenseApplication {
    final static String fileName = "src/main/java/com/unitartu/graphSense/data/initial_file.csv";

    public static void main(String[] args) throws Exception {
        MyFileReader fileReader = new MyFileReader();
        List<GraphData> dataFromFile = fileReader.readFile(fileName);
        MapUtil mapUtil = new MapUtil();

        // Statistic 1: total nr of occurrences
/*

		TotalNumberOfOccurrences totalNumberOfOccurrences = new TotalNumberOfOccurrences();
		Map<EventOccurrence,Integer>  occurrences = totalNumberOfOccurrences.calculateTotalNumberOfOccurrences(dataFromFile);
		occurrences =  mapUtil.sortByValue(occurrences);

		for(EventOccurrence key : occurrences.keySet()){
			System.out.println("key:"+key.toString()+" count:"+occurrences.get(key));
		}

		 */


        // Statistic 2: number of distinct cases

/*
        NumberOfDistinctCases numberOfDistinctCases = new NumberOfDistinctCases();
        Map<EventOccurrence, Integer> occurrences = numberOfDistinctCases.calculateNumberOfDistinctCases(dataFromFile);
        occurrences = mapUtil.sortByValue(occurrences);

        for (EventOccurrence key : occurrences.keySet()) {
            System.out.println("key:" + key.toString() + " count:" + occurrences.get(key));
        }

 */

        // Statistic 3,4: max/min occurrences per case
        MaxMinPerCase maxMinPerCase = new MaxMinPerCase();
        Map<EventOccurrence,Integer>  occurrences = maxMinPerCase.calculateNumberOfTimesPerCase("min",dataFromFile);
        occurrences =  mapUtil.sortByValue(occurrences);

        for(EventOccurrence key : occurrences.keySet()){
            System.out.println("key:"+key.toString()+" count:"+occurrences.get(key));
        }

        /*
         */
    }
}
