package com.unitartu.graphSense.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GraphData {
    private final String id;
    private final String name;
    private final List<String> args;

    private GraphData(GraphDataBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.args = builder.args;
    }

    @Override
    public String toString() {
        return "Id: "+this.id+", Name:"+this.name+", Args:"+this.args;
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
                throw new Error("Graphdata id can't be empty");
            }
            if (graphData.name.isEmpty()){
                throw new Error("Graphdata name can't be empty");
            }
        }
    }
}
