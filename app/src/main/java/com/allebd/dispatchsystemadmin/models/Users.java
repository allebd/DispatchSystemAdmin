package com.allebd.dispatchsystemadmin.models;

/**
 * Created by CSISPC on 10/03/2017.
 */

public class Users {
    private Long id;
    private Double latitude;
    private Double longitude;
    private String username;
    private String password;
    private String telephone;
    private String dob;
    private String gender;
    private String bloodGroup;

    public Users(Long id, Double latitude, Double longitude, String username, String password, String telephone, String dob, String gender, String bloodGroup) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.username = username;
        this.password = password;
        this.telephone = telephone;
        this.dob = dob;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
    }

    public Users(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }
}
