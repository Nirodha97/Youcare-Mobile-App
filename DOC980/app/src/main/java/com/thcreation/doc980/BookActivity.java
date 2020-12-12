package com.thcreation.doc980;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class BookActivity extends AppCompatActivity implements Response.Listener<String>,Response.ErrorListener {

    String sessionID="";

    DrawerLayout drawerLayout;

    EditText etName,etnic,etmobile,etemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        Intent intent=getIntent();
        Bundle bundle = intent.getExtras();
        sessionID = bundle.getString("SID");

        etName = findViewById(R.id.name);
        etnic = findViewById(R.id.nic);
        etmobile = findViewById(R.id.mobile);
        etemail = findViewById(R.id.email);

        drawerLayout = findViewById(R.id.drawer_layout);
    }

    public void confirm(View view) {

        final String name=etName.getText().toString();
        final String nic=etnic.getText().toString();
        final String mobile=etmobile.getText().toString();
        final String email = etemail.getText().toString();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://beezzserver.com/slthadi/project01/appoinment/insert.php";
        System.out.println("url = "+url);
        StringRequest request = new StringRequest(Request.Method.POST,url,this,this){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String, String> params = new HashMap<>();
                params.put("name",name);
                params.put("email",email);
                params.put("nic",nic);
                params.put("mobile",mobile);
                params.put("session_id",sessionID);

                return params;
            }
        };
        queue.add(request);

    }

    @Override
    public void onResponse(String response) {
       // Toast.makeText(this, "success = "+response, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this,CompleteActivity.class);
        intent.putExtra("msg",response);
        startActivity(intent);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        error.printStackTrace();
        Toast.makeText(this, "error = "+error.getMessage(), Toast.LENGTH_SHORT).show();
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