package com.thcreation.doc980;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class CompleteActivity extends AppCompatActivity {

    ImageView qr;
    BitmapDrawable drawable;
    Bitmap bitmap;
    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);
        qr=(ImageView) findViewById(R.id.qr);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String msg = bundle.getString("msg");
        data=msg;
        Toast.makeText(this, "-"+msg, Toast.LENGTH_SHORT).show();
        TextView tv = findViewById(R.id.massage);
        //tv.setText(msg);
        tv.setText(msg.split("\"")[5]);     //not recomend

        generateQR();
    }


    public void generateQR()
    {
        QRGEncoder qrgEncoder = new QRGEncoder(data,null, QRGContents.Type.TEXT,1000);
        try {
            Bitmap qrBits= qrgEncoder.encodeAsBitmap();
            qr.setImageBitmap(qrBits);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}