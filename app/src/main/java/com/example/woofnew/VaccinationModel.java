package com.example.woofnew;

import java.util.Date;

public class VaccinationModel {

    VaccinationModel(){

    }

    String vaccnum,vaccname;
    Date vaccdate;

    public VaccinationModel(String vaccnum, String vaccname, Date vaccdate) {
        this.vaccnum = vaccnum;
        this.vaccname = vaccname;
        this.vaccdate = vaccdate;
    }

    public String getVaccnum() {
        return vaccnum;
    }

    public void setVaccnum(String vaccnum) {
        this.vaccnum = vaccnum;
    }

    public String getVaccname() {
        return vaccname;
    }

    public void setVaccname(String vaccname) {
        this.vaccname = vaccname;
    }

    public Date getVaccdate() {
        return vaccdate;
    }

    public void setVaccdate(Date vaccdate) {
        this.vaccdate = vaccdate;
    }
}
