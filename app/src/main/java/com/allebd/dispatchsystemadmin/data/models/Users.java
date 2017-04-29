package com.allebd.dispatchsystemadmin.data.models;

import static android.R.attr.id;

/**
 * Created by CSISPC on 10/03/2017.
 */

public class Users {
    private String uid;
    private int id;
    private Double latitude;
    private Double longitude;
    private String username;
    private String password;
    private String name;
    private String address;
    private String number;
    private String ambulanceNumber;

    public Users(Double latitude, Double longitude, String username, String password, String name, String address, String number, String ambulanceNumber) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.username = username;
        this.password = password;
        this.number = number;
        this.address = address;
        this.ambulanceNumber = ambulanceNumber;
        this.name = name;
    }

    public Users(){

    }


    public void initEmptyUsers(){
        name = "";
        number = "";
        address = "";
        ambulanceNumber = "";
    }
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Long getId() {
        return (long) id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getAmbulanceNumber() {
        return ambulanceNumber;
    }

    public void setAmbulanceNumber(String ambulanceNumber) {
        this.ambulanceNumber = ambulanceNumber;
    }
}
