package com.thcreation.doc980;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

public class showPharmacyActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pharmacy);

        drawerLayout = findViewById(R.id.drawer_layout);

        dialog = new Dialog(this);
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

    public void findPharmacy(View view){
        recreate();
    }

    public void ClickMeetDoctor(View view){
        redirectActivity(this,MainActivity.class);
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

    public void search(View view) {
        Intent in = new Intent(this,MainActivity.class);
        startActivity(in);
    }

    public void ClickPharmacy(View view) {
        dialog.setContentView(R.layout.dialog_pharmacy);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void dialogClose(View view)
    {
        dialog.dismiss();
    }

    public void callNow(View view)
    {

    }

    public void emergency(View view)
    {

    }
}