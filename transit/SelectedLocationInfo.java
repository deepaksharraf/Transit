package com.example.deepak.transit;

public class SelectedLocationInfo {
    private String  Shift;
    private String  Stage;
    private String  DeviceId;
    private String  Route;
    private String  masterIp;
    private String  afcsIp;
    private String  apn;
    private String  port;
    private String  pto;

    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String DeviceId) {
        this.DeviceId = DeviceId;
    }

    public String getStage() {
        return Stage;
    }

    public void setStage(String Stage) {
        this.Stage = Stage;
    }


    public String getShift() {
        return Shift;
    }

    public void setShift(String Shift) {
        this.Shift = Shift;
    }


    public String getRoute() {
        return Route;
    }

    public void setRoute(String Route) {
        this.Route = Route;
    }


    public String getMasterIP() {
        return masterIp;
    }

    public void setMasterIP(String masterIp) {
        this.masterIp = masterIp;
    }


    public String getAFCSIP() {
        return afcsIp;
    }

    public void setAFCSIP(String afcsIp) {
        this.afcsIp = afcsIp;
    }


    public String getAPN() {
        return apn;
    }

    public void setAPN(String apn) {
        this.apn = apn;
    }


    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.masterIp = port;
    }


    public String getPTO() {
        return pto;
    }

    public void setPTO(String pto) {
        this.masterIp = pto;
    }
}
