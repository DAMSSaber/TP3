package com.myschool.tp3.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.net.Uri;
import android.util.Log;
import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Cache.Entry;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.myschool.tp3.User;
import com.myschool.tp3.interfaces.CallbackAddUser;
import com.myschool.tp3.utils.AppController;
import com.myschool.tp3.utils.Const;

public class ServicesAddUser {

	public static final String TAG = ServicesAddUser.class.getSimpleName();
	private String tag_json_arry = "tag_json_arry";
	private static ServicesAddUser mInstance = null;

	private JSONObject item = null;

	public static ServicesAddUser getInstance() {
		if (mInstance == null) {
			mInstance = new ServicesAddUser();
		}

		return mInstance;
	}


	public void addUser(final CallbackAddUser delegate,final User user) {

		StringRequest req = new StringRequest(Request.Method.POST, Const.URL_ADD_USER, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				delegate.onAddUser(response);
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				delegate.onAddUserFailed(error.toString());
			}
		}){
			@Override
			protected Map<String,String> getParams(){
				Map<String,String> params = new HashMap<String, String>();
				params.put("name",user.getName());
				params.put("password",user.getMdp());
				params.put("email",user.getEmail());


				return params;
			}

			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				Map<String,String> params = new HashMap<String, String>();
				params.put("Content-Type","application/x-www-form-urlencoded");
				return params;
			}
		};

		AppController.getInstance().addToRequestQueue(req, tag_json_arry);

	}

}
