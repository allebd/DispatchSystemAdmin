package com.allebd.dispatchsystemadmin.data.models;


public class Hospital {
    private String name;
    private String address;
    private String number;
    private long ambulanceNumber;


    public Hospital(){

    }

    public static Hospital newHospital(){
        Hospital hospital = new Hospital();
        hospital.setName("");
        hospital.setNumber("");
        hospital.setAddress("");
        hospital.setAmbulanceNumber(0L);
        return hospital;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String telephone) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getAmbulanceNumber() {
        return ambulanceNumber;
    }

    public void setAmbulanceNumber(long ambulanceNumber) {
        this.ambulanceNumber = ambulanceNumber;
    }
}
