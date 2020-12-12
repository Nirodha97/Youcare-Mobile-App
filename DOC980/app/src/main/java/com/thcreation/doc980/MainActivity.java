package com.thcreation.doc980;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;

    AutoCompleteTextView actvSpeciality, actvDoctor, actvHospital;
    Map<String, String> specialities = new HashMap<>();
    Map<String, String> doctors = new HashMap<>();
    Map<String, String> hospitals = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);

        actvSpeciality = findViewById(R.id.speciality);
        actvDoctor = findViewById(R.id.doctor);
        actvHospital = findViewById(R.id.hospital);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadSpecialities();
        loadDoctors();
        loadHospital();
    }

    public void loadSpecialities(){

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://beezzserver.com/slthadi/project01/speciality/";


// Request a string response from the provided URL.
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Display the first 500 characters of the response string.
//                        System.out.println("Responce = "+response.toString());                                    //check the data coming from jasone
//                        Toast.makeText(MainActivity.this,"Response="+response, Toast.LENGTH_SHORT).show();

                        setSpecialities(response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error = "+error.getMessage());
//                Toast.makeText(MainActivity.this,"Error="+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


// Add the request to the RequestQueue.
        queue.add(jsonArrayRequest);
    }

    public void setSpecialities(JSONArray response){

        List<String> list = new ArrayList<>();
        for(int i=0; i<response.length(); i++){

            try {
                JSONObject obj = response.getJSONObject(i);
                list.add(obj.getString("name"));
                specialities.put(obj.getString("name"),obj.getString("id"));

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        int layout = android.R.layout.simple_list_item_1;
        ArrayAdapter adapter = new ArrayAdapter(this,layout,list);
        actvSpeciality.setAdapter(adapter);
    }

    public void loadDoctors(){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://beezzserver.com/slthadi/project01/doctor/";


// Request a string response from the provided URL.
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Display the first 500 characters of the response string.
//                        System.out.println("Responce = "+response.toString());                                    //check the data coming from jasone
//                        Toast.makeText(MainActivity.this,"Response="+response, Toast.LENGTH_SHORT).show();

                        setDoctors(response);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Error = "+error.getMessage());
//                Toast.makeText(MainActivity.this,"Error="+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


// Add the request to the RequestQueue.
        queue.add(jsonArrayRequest);
    }

    public void setDoctors(JSONArray response){

        List<String> list = new ArrayList<>();
        for(int i=0; i<response.length(); i++){

            try {
                JSONObject obj = response.getJSONObject(i);
                list.add(obj.getString("name"));
                doctors.put(obj.getString("name"),obj.getString("id"));

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        int layout = android.R.layout.simple_list_item_1;
        ArrayAdapter adapter = new ArrayAdapter(this,layout,list);
        actvDoctor.setAdapter(adapter);
    }

    public void loadHospital(){

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://beezzserver.com/slthadi/project01/hospital/";


// Request a string response from the provided URL.
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Display the first 500 characters of the response string.
//                        System.out.println("Responce = "+response.toString());                                    //check the data coming from jasone
//                        Toast.makeText(MainActivity.this,"Response="+response, Toast.LENGTH_SHORT).show();

                        setHospital(response);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Error = "+error.getMessage());
//                Toast.makeText(MainActivity.this,"Error="+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


// Add the request to the RequestQueue.
        queue.add(jsonArrayRequest);
    }

    public void setHospital(JSONArray response){

        List<String> list = new ArrayList<>();
        for(int i=0; i<response.length(); i++){

            try {
                JSONObject obj = response.getJSONObject(i);
                list.add(obj.getString("name"));
                hospitals.put(obj.getString("name"),obj.getString("id"));

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        int layout = android.R.layout.simple_list_item_1;
        ArrayAdapter adapter = new ArrayAdapter(this,layout,list);
        actvHospital.setAdapter(adapter);
    }

    public void search(View v){
        String specialityName = actvSpeciality.getText().toString();
        String doctorName = actvDoctor.getText().toString();
        String hospitalName = actvHospital.getText().toString();

        String sid = specialities.get(specialityName);
        String did = doctors.get(doctorName);
        String hid = hospitals.get(hospitalName);

        if(sid==null){
            sid="0";}
        if(did==null){
            did="0";}
        if(hid==null){
            hid="0";}



        Intent intent = new Intent(this,SessionListActivity.class);
        intent.putExtra("SID",sid);
        intent.putExtra("DID",did);
        intent.putExtra("HID",hid);
        startActivity(intent);

    }

    public void reset(View v){

    }

    public void ClickMenu(View view) {
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view){
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickHome(View view){
        redirectActivity(this,HomeActivity.class);
    }

    public void ClickMeetDoctor(View view){
        redirectActivity(this,MainActivity.class);
    }

    public void findPharmacy(View view){
        redirectActivity(this,FindPharmacyActivity.class);
    }

    public void ClickTips(View view){ redirectActivity(this,HelthTipsActivity.class); }

    public void ClickSupport(View view){ redirectActivity(this,ChatActivity.class); }


    public static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity,aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
}