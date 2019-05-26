package com.example.renosyahputra.localdatakamuslib.tambahKata;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.renosyahputra.localdatakamuslib.localStorage.SerializableSave;
import com.example.renosyahputra.localdatakamuslib.model.ListKosakKataModel;

import java.util.ArrayList;

import static com.example.renosyahputra.localdatakamuslib.queryKata.QueryKata.KosakKataFileName;

public class TambahKata {

    private static TambahKata _instance;
    private Context context;
    private ListKosakKataModel listKosakKataModelDitambah = new ListKosakKataModel();
    private OnTambahKataListener listener;
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

    public void tambah(){

        if (checkAllVariableIsNull()){
            return;
        }

        errors.clear();
        SerializableSave serializableSave = new SerializableSave(this.context,KosakKataFileName);

        ListKosakKataModel listKosakKataModel = new ListKosakKataModel();
        listKosakKataModel.kosakKataModels.addAll(listKosakKataModelDitambah.kosakKataModels);

        if (serializableSave.save(listKosakKataModel)){
            listener.onBerhasilTambahKata(true);
        }

    }

    private TambahKata() {
        super();
    }

    private Boolean checkAllVariableIsNull(){
        return (context == null || listener == null || listKosakKataModelDitambah == null || errors == null);
    }

    public interface OnTambahKataListener{
        void onBerhasilTambahKata(@NonNull Boolean success);
        void onErrorLogs(@NonNull ArrayList<String> errors);
    }
}
