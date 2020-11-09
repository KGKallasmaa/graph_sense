package com.unitartu.graphSense.spark;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class MySpark {


    public Dataset<Row> readFile(final SparkSession spark, final String filename) {
        Dataset<Row> csv =  spark.read()
                .format("csv")
                .option("header", "true")
                .load(filename);
        csv.show();
        return csv;
    }
}
