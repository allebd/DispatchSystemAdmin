package com.allebd.dispatchsystemadmin.data.models;

/**
 * Created by Digz on 03/04/2017.
 */

public class Ambulance {

    private String id;
    private String hospitalId;
    private String driverName;
    private String name;

    public Ambulance() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
