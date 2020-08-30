package com.unitartu.graphSense;

import com.unitartu.graphSense.entity.EventOccurrence;
import com.unitartu.graphSense.entity.GraphData;
import com.unitartu.graphSense.logic.MyFileReader;
import com.unitartu.graphSense.logic.TotalNumberOfOccurrences;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.List;

@SpringBootApplication
public class GraphSenseApplication {
	final static String fileName = "src/main/java/com/unitartu/graphSense/data/initial_file.csv";

	public static void main(String[] args) {
		MyFileReader fileReader = new MyFileReader();
		List<GraphData> dataFromFile = fileReader.readFile(fileName);

		// Statistic 1: total nr of occurrences

		TotalNumberOfOccurrences totalNumberOfOccurrences = new TotalNumberOfOccurrences();
		HashMap<EventOccurrence,Integer>  occurrences = totalNumberOfOccurrences.calculateTotalNumberOfOccurrences(dataFromFile);

		for(EventOccurrence key : occurrences.keySet()){
			System.out.println("key:"+key.toString()+" count:"+occurrences.get(key));
		}
	}
}
