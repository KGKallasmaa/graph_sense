package com.unitartu.graphSense.logic;

import com.unitartu.graphSense.entity.GraphData;
import org.apache.spark.api.java.JavaRDD;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.spark.api.java.JavaSparkContext;


public class MyFileReader {
    //TODO: we should convert csv to parquet

    public List<GraphData> readFile(String fileName) {
        List<List<String>> rawRecords = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                rawRecords.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Remove column names
        rawRecords.remove(0);

        List<GraphData> graphData = new ArrayList<>();
        for (List<String> rawRecord : rawRecords) {
            List<String> args = new ArrayList<>();
            for (int j = 2; j < rawRecord.size(); j++) {
                args.add(rawRecord.get(j));
            }

            GraphData graphObj = new GraphData.GraphDataBuilder(rawRecord.get(0), rawRecord.get(1))
                    .withArgs(args)
                    .build();
            graphData.add(graphObj);
        }
        return graphData;
    }

    public JavaRDD<GraphData> readFileForSpark(JavaSparkContext sparkcontext, String fileName) {

        JavaRDD<String> input = sparkcontext.textFile(fileName);
        JavaRDD<String[]> parts = input.map(e->e.split(","));
        return parts.map(array->
                        new GraphData.GraphDataBuilder(array[0], array[1])
                                .withArgs(Arrays.asList(Arrays.copyOfRange(array,2,array.length-1)))
                                .build()
                );

    }
}
