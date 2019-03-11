package com.syahputrareno975.textscannerlib;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class TextScanner extends AppCompatActivity implements
        View.OnClickListener,
        SurfaceHolder.Callback,
        Detector.Processor<TextBlock> {

    private Context context;

    private CameraSource cameraSource;
    private SurfaceView surfaceViewCariKata;
    private TextRecognizer textRecognizer;

    private TextView textFound;
    private ImageView imageViewTerjemahkan;

    private ArrayList<String> listString = new ArrayList<>();

    private static int MY_PERMISSION_REQUEST_CAMERA = 223;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_scanner);
        initWidget();
    }

    private void initWidget(){

        context = this;

        surfaceViewCariKata = findViewById(R.id.surfaceViewCariKata);

        textFound = findViewById(R.id.textFound);

        textRecognizer = new TextRecognizer.Builder(context).build();
        textRecognizer.setProcessor(this);

        imageViewTerjemahkan = findViewById(R.id.ButtonTerjemahkan);
        imageViewTerjemahkan.setOnClickListener(this);

        requestCameraPermission();


    }
    private void requestCameraPermission() {

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions( new String[] {Manifest.permission.CAMERA}, MY_PERMISSION_REQUEST_CAMERA);
            }
        } else {
            initTextScanner();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        Intent i = getIntent();
        i.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
        startActivity(i);
        finish();

    }

    private void initTextScanner(){
        if (textRecognizer.isOperational()){
            cameraSource = new CameraSource.Builder(getApplicationContext(), textRecognizer)
                    .setFacing(CameraSource.CAMERA_FACING_BACK)
                    .setRequestedPreviewSize(1280, 1024)
                    .setRequestedFps(15.0f)
                    .setAutoFocusEnabled(true)
                    .build();

            surfaceViewCariKata.getHolder().addCallback(this);

        }
    }

    @Override
    public void onClick(View v) {
        if (v == imageViewTerjemahkan){

            ArrayList<String> listResult = new ArrayList<>();
            Collections.addAll(listResult, textFound.getText().toString().split("\n"));

            Intent resultIntent = new Intent();
            resultIntent.putExtra("string_list",listResult);
            setResult(Activity.RESULT_OK,resultIntent);
            finish();

        }
    }

    // -----

    private Boolean isStringInBuilder(ArrayList<String> stringList,String s){
        boolean found = false;

        for (String d : stringList){
            if (d.equals(s)){
                found = true;
                break;
            }
        }

        return found;
    }

    // -----

    @Override
    public void release() {

    }

    @Override
    public void receiveDetections(Detector.Detections<TextBlock> detections) {
        final SparseArray<TextBlock> items = detections.getDetectedItems();

        if (items.size() != 0) {
            for (int i = 0; i < items.size(); i++) {
                TextBlock item = items.valueAt(i);
                if (!isStringInBuilder(listString,item.getValue())) {
                    listString.add(item.getValue());
                }
            }
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String stringToShow = "";
                for (String s : listString) {
                   stringToShow += s + "\n";
                }
                textFound.setText(stringToShow);
                listString.clear();

            }
        });

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    // ------

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        try {

            cameraSource.start(surfaceViewCariKata.getHolder());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if(cameraSource != null){
            cameraSource.stop();
        }
    }

}
