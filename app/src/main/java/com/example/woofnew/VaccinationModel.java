package com.example.woofnew;

import java.util.Date;

public class VaccinationModel {

    VaccinationModel(){

    }

    String Vacc_num,Vacc_name;
    String Vacc_date;

    public VaccinationModel(String vacc_num, String vacc_name, String vacc_date) {
        Vacc_num = vacc_num;
        Vacc_name = vacc_name;
        Vacc_date = vacc_date;
    }

    public String getVacc_num() {
        return Vacc_num;
    }

    public void setVacc_num(String vacc_num) {
        Vacc_num = vacc_num;
    }

    public String getVacc_name() {
        return Vacc_name;
    }

    public void setVacc_name(String vacc_name) {
        Vacc_name = vacc_name;
    }

    public String getVacc_date() {
        return Vacc_date;
    }

    public void setVacc_date(String vacc_date) {
        Vacc_date = vacc_date;
    }
}
