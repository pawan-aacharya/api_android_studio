package com.example.firstapiexample;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity {
    ArrayList<UserList> list=new ArrayList<UserList>();
    Button btnLoad,btnSend;
    RecyclerView myList;
    RecyclerView.LayoutManager LayoutManager;
    MyAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout_activity);
        btnLoad=findViewById(R.id.btnLoad);
        btnSend=findViewById(R.id.btnSend);
        myList=findViewById(R.id.myList);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadData();
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData();
            }
        });
    }
    public void sendApiData(){
        RequestQueue queue=Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(Request.Method.POST, "http://apitest.lunarit.com.np/api/apiuserlist/addusers", new Response.Listener<String>() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this,response.toString(),Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
            }
        }){
            protected HashMap<String, String> getParams() throws AuthFailureError{
                HashMap<String, String> params=new HashMap<String, String>();
                params.put("userId","0");
                params.put("userName","Pawan");
                params.put("userPassword","pawan123");
                params.put("userRole","Member");
                return params;
            }
        };
        queue.add(request);
    }
    public void sendData(){
       sendApiData();
    }
    public void LoadApiData(){
       list.clear();
        //list.add(new UserList(1,"Ram","12345","admin"));
        RequestQueue queue= Volley.newRequestQueue(this);
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, "http://apitest.lunarit.com.np/api/apiuserlist/getusers", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i=0;i<response.length();i++){
                    try {
                        JSONObject obj=response.getJSONObject(i);
                        UserList user=new UserList();
                        user.userId=obj.getInt("userId");
                        user.userName=obj.getString("userName");
                        user.userPassword=obj.getString("userPassword");
                        user.userRole=obj.getString("userRole");
                        list.add(user);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
               adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ApiError",error.getMessage());
            }
        });
        queue.add(request);
    }
    public void LoadData(){
        adapter=new MyAdapter(this,list);
        LayoutManager=new LinearLayoutManager(this);
        myList.setLayoutManager(LayoutManager);
        myList.setAdapter(adapter);
        LoadApiData();
    }
}
