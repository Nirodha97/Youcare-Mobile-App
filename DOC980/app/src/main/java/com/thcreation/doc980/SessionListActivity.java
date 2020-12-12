package com.thcreation.doc980;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SessionListActivity extends AppCompatActivity {

    String sid = "0";
    String did = "0";
    String hid = "0";

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_list);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        sid=bundle.getString("SID");
        did=bundle.getString("DID");
        hid=bundle.getString("HID");

        lv = findViewById(R.id.session_list);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadSession();
    }

    public void loadSession() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://beezzserver.com/slthadi/project01/session/index.php?did="+did+"&hid="+hid+"&sid="+sid+"";
        System.out.println("!!!!!!!!!!!!!!!url="+url);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                            setSession(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        queue.add(request);
    }

    public void  setSession(JSONArray response){

        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

        try {
            for(int i=0; i<response.length(); i++){

                JSONObject object = response.getJSONObject(i);

                HashMap<String, String> map = new HashMap<>();

                map.put("id", object.getString("id"));
                map.put("doctor_name", object.getString("doctor_name"));
                map.put("speciality_name", object.getString("speciality_name"));
                map.put("date_time", object.getString("date_time"));
                map.put("hospital_name", object.getString("hospital_name")+"-"+object.getString("hospital_place"));
                map.put("next", object.getString("next"));
                list.add(map);

            }

            int layout = R.layout.single_session_item;

            int[] views = {R.id.session_id,R.id.doctor_name,R.id.speciality,R.id.hospital,R.id.date,R.id.number};

            String[] colums = {"id","doctor_name","speciality_name","hospital_name","date_time","next"};

            SimpleAdapter adapter = new SimpleAdapter(this,list,layout,colums,views);
            lv.setAdapter(adapter);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void Book(View v){
        LinearLayout ll = (LinearLayout)v.getParent();
        TextView tv = ll.findViewById(R.id.session_id);


        String id = tv.getText().toString();
        Toast.makeText(this, "session id="+id, Toast.LENGTH_SHORT).show();

        Intent in = new Intent(this,BookActivity.class);
        in.putExtra("SID",id);
        startActivity(in);
    }
}