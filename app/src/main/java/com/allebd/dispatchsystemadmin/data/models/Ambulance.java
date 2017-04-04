package com.allebd.dispatchsystemadmin.data.models;

/**
 * Created by Digz on 03/04/2017.
 */

public class Ambulance {

    private String id;
    private String hospitalId;
    private String driverName;
    private String description;
    private int size;
    private boolean isAssigned;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isAssigned() {
        return isAssigned;
    }

    public void setAssigned(boolean assigned) {
        isAssigned = assigned;
    }

    public String getAssignedStatus(){
        if (isAssigned)return "Assigned";
        return "Unassigned";
    }
}
