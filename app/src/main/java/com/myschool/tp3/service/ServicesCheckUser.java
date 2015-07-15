package com.myschool.tp3.service;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.myschool.tp3.User;
import com.myschool.tp3.interfaces.CallBackCheckUser;
import com.myschool.tp3.interfaces.CallbackAddUser;
import com.myschool.tp3.utils.AppController;
import com.myschool.tp3.utils.Const;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ServicesCheckUser {

	public static final String TAG = ServicesCheckUser.class.getSimpleName();
	private String tag_json_arry = "tag_json_arry";
	private static ServicesCheckUser mInstance = null;

	private JSONObject item = null;

	public static ServicesCheckUser getInstance() {
		if (mInstance == null) {
			mInstance = new ServicesCheckUser();
		}

		return mInstance;
	}

	public void checkUser(final CallBackCheckUser delegate,final User user) {

		StringRequest req = new StringRequest(Request.Method.POST, Const.URL_CHECK_USER, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {

				try {
					JSONObject json = new JSONObject(response);

					if(json.has("message")){

						delegate.onUserExist(json.getString("message"));


					}else if(json.has("token")){
						delegate.onUserConnected(json.getString("token"));
					}else{

						delegate.onServerFailed(json.getString(response));
					}






				} catch (JSONException e) {
					e.printStackTrace();
				}



			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				delegate.onNetworkFailed(error.toString());
			}
		}){
			@Override
			protected Map<String,String> getParams(){
				Map<String,String> params = new HashMap<String, String>();
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
