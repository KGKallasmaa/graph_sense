package com.unitartu.graphSense;

import com.unitartu.graphSense.entity.GraphData;
import com.unitartu.graphSense.logic.MyFileReader;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class GraphSenseApplication {
	final static String fileName = "src/main/java/com/unitartu/graphSense/data/initial_file.csv";

	public static void main(String[] args) {
		MyFileReader fileReader = new MyFileReader();
		List<GraphData> dataFromFile = fileReader.readFile(fileName);
		for (GraphData result : dataFromFile) {
			System.out.println(result);
		}
	//	SpringApplication.run(GraphSenceApplication.class, args);
	}

}
