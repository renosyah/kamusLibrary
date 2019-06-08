package com.example.renosyahputra.localdatakamuslib.tambahKata;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import com.example.renosyahputra.localdatakamuslib.fragmentDialogRequestPermission.FragmentDialogRequestPermission;
import com.example.renosyahputra.localdatakamuslib.localStorage.FileSave;
import com.example.renosyahputra.localdatakamuslib.localStorage.SerializableSave;
import com.example.renosyahputra.localdatakamuslib.model.ListKosakKataModel;
import com.google.gson.Gson;

import java.util.ArrayList;

import static com.example.renosyahputra.localdatakamuslib.queryKata.QueryKata.KosakKataFileName;

public class TambahKata {

    private static TambahKata _instance;
    private Context context;
    private ListKosakKataModel listKosakKataModelDitambah = new ListKosakKataModel();
    private OnTambahKataListener listener;
    private OnRequestStoragePermissionListener onRequestStoragePermissionListener;
    private ArrayList<String> errors = new ArrayList<>();

    public static TambahKata newInstance(){
        _instance = new TambahKata();
        return _instance;
    }

    public TambahKata setContext(Context context) {
        this.context = context;
        return  _instance;
    }

    public TambahKata setListKosakKataModel(ListKosakKataModel listKosakKataModel) {
        this.listKosakKataModelDitambah = listKosakKataModel;
        return _instance;
    }

    public TambahKata setOnTambahKataListener(OnTambahKataListener listener) {
        this.listener = listener;
        return  _instance;
    }

    public TambahKata setOnRequestStoragePermissionListener(OnRequestStoragePermissionListener listener) {
        this.onRequestStoragePermissionListener = listener;
        return  _instance;
    }

    public void tambah(){

        if (checkAllVariableIsNull()){
            return;
        }

        requestPermission();

        errors.clear();
        SerializableSave serializableSave = new SerializableSave(this.context,KosakKataFileName);

        ListKosakKataModel listKosakKataModel = new ListKosakKataModel();
        listKosakKataModel.kosakKataModels.addAll(listKosakKataModelDitambah.kosakKataModels);

        if (serializableSave.save(listKosakKataModel)){
            listener.onBerhasilTambahKata(true);
        }

    }

    public void tambahKeFile(){

        if (checkAllVariableIsNull()){
            return;
        }

        requestPermission();

        errors.clear();
        FileSave save = new FileSave(this.context);
        ListKosakKataModel listKosakKataModel = new ListKosakKataModel();
        listKosakKataModel.kosakKataModels.addAll(listKosakKataModelDitambah.kosakKataModels);

        if (save.save(new Gson().toJson(listKosakKataModel))){
            listener.onBerhasilTambahKata(true);
        }
    }

    private void requestPermission(){

        if ((ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
                || ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)){


            FragmentDialogRequestPermission fragmentDialogRequestPermission = new FragmentDialogRequestPermission();
            fragmentDialogRequestPermission.setOnRequestPermissionListener(this.onRequestStoragePermissionListener);

            try {

                Activity activity = (Activity) this.context;
                fragmentDialogRequestPermission.show(activity.getFragmentManager(), "dialog");

            }catch (ClassCastException e){
                e.printStackTrace();
            }


            return;
        }
    }

    private TambahKata() {
        super();
    }

    private Boolean checkAllVariableIsNull(){
        return (context == null || listener == null || onRequestStoragePermissionListener == null || listKosakKataModelDitambah == null || errors == null);
    }

    public interface OnTambahKataListener{
        void onBerhasilTambahKata(@NonNull Boolean success);
        void onErrorLogs(@NonNull ArrayList<String> errors);
    }


    public interface OnRequestStoragePermissionListener {
        void onPermissionResult(@NonNull Boolean status);
        void onFinishRequestPermission();
    }
}
