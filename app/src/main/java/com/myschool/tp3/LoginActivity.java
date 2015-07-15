package com.myschool.tp3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.myschool.tp3.interfaces.CallBackCheckUser;
import com.myschool.tp3.interfaces.CallbackAddUser;
import com.myschool.tp3.service.ServicesAddUser;
import com.myschool.tp3.service.ServicesCheckUser;


public class LoginActivity extends ActionBarActivity implements CallBackCheckUser {

    SharedPreferences sharedpreferences =null;
    private final static String ACTIVE_USER = "ACTIVE_USER";
    SharedPreferences.Editor editor=null;
    private EditText ui_edit_nom = null;
    private EditText ui_edit_mail = null;
    private EditText ui_edit_mdp = null;
    private Button ui_btn_valide = null;
    private User user=null;

    private String mName = "";
    private String mMail = "";
    private String mMdp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ui_edit_mail = (EditText) findViewById(R.id.ui_edit_mail);
        ui_edit_mdp = (EditText) findViewById(R.id.ui_edit_mdp);
        ui_btn_valide = (Button) findViewById(R.id.ui_btn_valide);

        user=new User();

        ui_edit_mail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mMail = s.toString();
                user.setEmail(mMail);
            }
        });
        ui_edit_mdp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mMdp = s.toString();
                user.setMdp(mMdp);
            }
        });


        ui_btn_valide.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {

                                                 if (mMdp.equals("")) {

                                                     Toast.makeText(getApplicationContext(), "Votre mot de passe est null",
                                                             Toast.LENGTH_LONG).show();
                                                 } else if (mMail.equals("")) {
                                                     Toast.makeText(getApplicationContext(), "Votre email est null",
                                                             Toast.LENGTH_LONG).show();
                                                 } else {

                                                     if (user != null) {

                                                         ServicesCheckUser.getInstance().checkUser(LoginActivity.this, user);
                                                     }

                                                 }


                                             }
                                         }

        );


    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_form, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onUserConnected(String response) {
        Log.v("TAG", "response :" + response);
        SharedPreferences userSettings = getSharedPreferences(mMail, 0);
        SharedPreferences.Editor editor = userSettings.edit();
        editor.putString("name", mName);
        editor.putString("token", response);

        SharedPreferences userLoginPrefs = getSharedPreferences(ACTIVE_USER, 0);
        SharedPreferences.Editor editor2 = userLoginPrefs.edit();
        editor2.putString("email", mMail);


        Intent intent = new Intent(LoginActivity.this, ConnexionActivity.class);
        startActivity(intent);

        editor.commit();
        editor2.commit();


        Toast.makeText(getApplicationContext(), response,
                Toast.LENGTH_LONG).show();




    }

    @Override
    public void onNetworkFailed(String response) {
        Toast.makeText(getApplicationContext(), response,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMdpFailed(String response) {
        Toast.makeText(getApplicationContext(), response,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUserExist(String response) {
        Toast.makeText(getApplicationContext(), response,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onServerFailed(String response) {
        Toast.makeText(getApplicationContext(), response,
                Toast.LENGTH_LONG).show();
    }

}