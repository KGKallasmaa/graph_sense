package com.unitartu.graphSense.entity;



import lombok.Getter;
import lombok.ToString;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@ToString
@Getter
public class GraphData {
    private final String id;
    private final String name;
    private final List<String> args;

    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

    private GraphData(GraphDataBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.args = builder.args;
    }


    public boolean hasAnArgument(String argument){
        return args.contains(argument);
    }
    public String getArgumentValue(String argument){
       int argumentIndex = this.args.indexOf(argument);
       return this.args.get(argumentIndex+1);
    }
    public boolean thisEventWasBefore(GraphData o){
        try {
            Date completionTime1 = formatter.parse(this.getArgumentValue("complete"));
            Date completionTime2 = formatter.parse(o.getArgumentValue("complete"));
            return completionTime1.before(completionTime2);
        } catch (ParseException e) {
            System.out.println("Error: Coun't parse: "+o.getArgumentValue("complete"));
        }
        return false;
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
