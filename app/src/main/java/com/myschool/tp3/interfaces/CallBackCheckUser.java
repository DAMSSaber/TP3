package com.myschool.tp3.interfaces;

/**
 * Created by Saber on 15/07/2015.
 */
public interface CallBackCheckUser {


    public void onUserConnected(String response);
    public void onNetworkFailed(String response);
    public void onMdpFailed(String response);
    public void onUserExist(String response);
    public void onServerFailed(String response);

}
