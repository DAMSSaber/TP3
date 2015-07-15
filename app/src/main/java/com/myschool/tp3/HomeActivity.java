package com.myschool.tp3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class HomeActivity extends ActionBarActivity {

    private Button ui_btn_create=null;
    private final static String ACTIVE_USER = "ACTIVE_USER";
    private Button ui_btn_have=null;
    private String mName="";
    private String mMail="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_home);

        ui_btn_create= (Button)findViewById(R.id.ui_btn_create);
        ui_btn_have= (Button)findViewById(R.id.ui_btn_have);
        initWithSharedPreferences();

        if(mName.equals("")&&mMail.equals("")){
            ui_btn_create.setVisibility(View.VISIBLE);
            ui_btn_have.setVisibility(View.VISIBLE);
        }







        ui_btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(HomeActivity.this,FormActivity.class);
                startActivity(intent);
            }
        });


        ui_btn_have.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(HomeActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
                mMail = userDatas.getString("email", "");


            }
        }



        // mUserName = "";

    }

}