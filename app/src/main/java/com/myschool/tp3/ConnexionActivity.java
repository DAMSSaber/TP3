package com.myschool.tp3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class ConnexionActivity extends ActionBarActivity {
    private final static String ACTIVE_USER = "ACTIVE_USER";
    private String mName=null;
    private String mMail=null;

    private TextView ui_tx_nom=null;


    private Button ui_btn_clear=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        initWithSharedPreferences();
        setContentView(R.layout.activity_connexion);

        ui_tx_nom=(TextView)findViewById(R.id.ui_tx_nom);
        ui_btn_clear=(Button)findViewById(R.id.ui_btn_clear);
        ui_btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deconnexion();

            }
        });

        ui_tx_nom.setText("Bienvenue "+mMail );
    }


    private void deconnexion() {
        getSharedPreferences(ACTIVE_USER, 0).edit().clear().commit();
        getSharedPreferences(mMail, 0).edit().clear().commit();
                Intent intent=new Intent(ConnexionActivity.this,HomeActivity.class);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_connexion, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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



    private void initWithSharedPreferences() {
        SharedPreferences userCurrentLogin = getSharedPreferences(ACTIVE_USER, 0);

        if (userCurrentLogin != null) {
            String email = userCurrentLogin.getString("email", null);
            if (email != null) {
                SharedPreferences userDatas = getSharedPreferences(email, 0);
                mName = userDatas.getString("name", "");
                mMail = email;
            }
        }



        // mUserName = "";

    }
}