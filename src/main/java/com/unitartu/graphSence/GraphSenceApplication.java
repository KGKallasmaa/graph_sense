package com.unitartu.graphSence;

import com.unitartu.graphSence.entity.GraphData;
import com.unitartu.graphSence.logic.MyFileReader;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class GraphSenceApplication {
	final static String fileName = "src/main/java/com/unitartu/graphSence/data/inital_file.csv";

	public static void main(String[] args) {
		MyFileReader fileReader = new MyFileReader();
		List<GraphData> dataFromFile = fileReader.readFile(fileName);
		for (GraphData result : dataFromFile) {
			System.out.println(result);
		}
	//	SpringApplication.run(GraphSenceApplication.class, args);
	}

}
