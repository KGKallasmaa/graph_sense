package com.unitartu.graphSense.entity;



import lombok.ToString;

import java.util.List;


@ToString
public class GraphData {
    private final String id;
    private final String name;

    private GraphData(GraphDataBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        List<String> args = builder.args;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public static class GraphDataBuilder
    {
        private final String id;
        private final String name;
        private List<String> args;

        public GraphDataBuilder(String id,String name) {
            this.id = id;
            this.name = name;
        }
        public GraphDataBuilder withArgs(List<String> args) {
            this.args = args;
            return this;
        }

        public GraphData build() {
            GraphData graphData =  new GraphData(this);
            validateGraphDataObject(graphData);
            return graphData;
        }
        private void validateGraphDataObject(GraphData graphData) {
            if (graphData.id.isEmpty()){
                throw new Error("GraphData id can't be empty");
            }
            if (graphData.name.isEmpty()){
                throw new Error("GraphData name can't be empty");
            }
        }
    }
}
