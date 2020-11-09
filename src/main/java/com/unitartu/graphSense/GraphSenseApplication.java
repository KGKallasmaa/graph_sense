package com.unitartu.graphSense;


import com.unitartu.graphSense.config.SparkConfig;
import com.unitartu.graphSense.logic.MyFileReader;
import org.apache.spark.sql.SparkSession;


public class GraphSenseApplication {
    final static String fileName = "/Users/Gustav/Documents/GitHub/graph_sense/src/main/resources/initial_file.csv";
    final static SparkConfig sparkConfig = new SparkConfig();
    final static MyFileReader fileReader = new MyFileReader();
    final static String sparkMaster = "spark://localhost:7077";


    public static void main(String[] args) {
        System.setProperty("hadoop.home.dir", "/");
        /*
        SparkConf conf = new SparkConf()
                .setAppName("Graph sense app")
                .setMaster("local[*]");

        JavaSparkContext sparkContext = new JavaSparkContext(conf);

        final JavaRDD<GraphData> dataFromFile = fileReader.readFileForSpark(sparkConfig.sc(),fileName);

        TotalNumberOfOccurrences service = new TotalNumberOfOccurrences();
        var result = service.SparkCalculateTotalNumberOfOccurrences(sparkContext,dataFromFile);

        System.out.println(result);
 */


        SparkSession sparkSession = new SparkSession.Builder()
                .appName("Graph Sense cluster")
                .master(sparkMaster)
                .getOrCreate();


        //     Dataset<Row> data = new MySpark().readFile(sparkSession, fileName);
        sparkSession.stop();


        /*

        MaxMinPerCase maxMinPerCase = new MaxMinPerCase();
        maxMinPerCase.calculateNumberOfTimesPerCase(data);

         */


    }
}
