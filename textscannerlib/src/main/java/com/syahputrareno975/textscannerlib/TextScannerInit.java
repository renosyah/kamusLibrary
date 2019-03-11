package com.syahputrareno975.textscannerlib;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;

public class TextScannerInit {

    private static TextScannerInit _instance;
    private Context context;
    private OnTextScannerInitListener listener;

    public static TextScannerInit newInstance() {
        _instance = new TextScannerInit();
        return  _instance;
    }

    public TextScannerInit setContext(Context context) {
        this.context = context;
        return  _instance;
    }

    public TextScannerInit setOnTextScannerInitListener(OnTextScannerInitListener listener) {
        this.listener = listener;
        return  _instance;
    }

    public void scanText(){

        if (checkVariableIsNull()){
            return;
        }


        FragmentDialogTextScanner dialogTextScanner = new FragmentDialogTextScanner();
        dialogTextScanner.setListener(listener);

        try {

            Activity activity = (Activity) this.context;
            dialogTextScanner.show(activity.getFragmentManager(), "dialog");


        }catch (ClassCastException e){
            e.printStackTrace();
        }


    }

    private TextScannerInit() {
        super();
    }

    private Boolean checkVariableIsNull(){
        return (context == null || listener == null);
    }

    public interface OnTextScannerInitListener {
        void onGetListString(@NonNull ArrayList<String> texts);
        void onScannerFinish();
    }
}
