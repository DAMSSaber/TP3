package com.myschool.tp3;

import org.json.JSONObject;

/**
 * Created by Saber on 15/07/2015.
 */
public class User {

    private  String name=null;
    private String email=null;
    private String mdp=null;


    public void initUser(JSONObject user) {

    }





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }




}
