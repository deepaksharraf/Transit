package com.example.deepak.transit;

public class SelectBusStop {

    private String  Route;
    private String  StopId;
    private String  StopName;
    private String  SequenceNumber;

    public String getStopId() {
        return StopId;
    }

    public void setStopId(String StopId) {
        this.StopId = StopId;
    }

    public String getStopName() {
        return StopName;
    }

    public void setStopName(String StopName) {
        this.StopName = StopName;
    }


    public String getSequenceNumber() {
        return SequenceNumber;
    }

    public void setSequenceNumber(String SequenceNumber) {
        this.SequenceNumber = SequenceNumber;
    }


    public String getRoute() {
        return Route;
    }

    public void setRoute(String Route) {
        this.Route = Route;
    }
}
