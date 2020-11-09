package com.unitartu.graphSense.config;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {

    @Bean
    public SparkConf conf() {
        final String appName = "Graphsence app";
        final String masterUri = "local";
        return new SparkConf().setAppName(appName).setMaster(masterUri);
    }

    @Bean
    public JavaSparkContext sc() {
        return new JavaSparkContext(conf());
    }

}
