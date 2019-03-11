package com.example.renosyahputra.localdatakamuslib.queryKata;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.renosyahputra.localdatakamuslib.localStorage.SerializableSave;
import com.example.renosyahputra.localdatakamuslib.model.KosakKataModel;
import com.example.renosyahputra.localdatakamuslib.model.ListKosakKataModel;

import java.util.ArrayList;

import static com.example.renosyahputra.localdatakamuslib.model.ListError.error_not_found;

public class QueryKata {

    public static String KosakKataFileName = "KosakKataFileName.data";
    public static String Inggris = "Inggris Ke Indonesia";
    public static String Indonesia = "Indonesia Ke Inggris";

    private static QueryKata _instance;
    private Context context;
    private KosakKataModel kosakKataDicari = new KosakKataModel();

    private String findBy = Indonesia;
    private OnQueryKataListener listener;
    private ArrayList<String> errors = new ArrayList<>();

    public static QueryKata newInstance(){
        _instance = new QueryKata();
        return  _instance;
    }

    public QueryKata setFindBy(String findBy) {
        this.findBy = findBy;
        return _instance;
    }

    public QueryKata setContext(Context context) {
        this.context = context;
        return  _instance;
    }

    public QueryKata setKosakKataDicari(KosakKataModel kosakKataDicari) {
        this.kosakKataDicari = kosakKataDicari;
        return  _instance;
    }

    public QueryKata setOnQueryKataListener(OnQueryKataListener listener) {
        this.listener = listener;
        return _instance;
    }

    private QueryKata() {
        super();
    }

    public void cari(){

        if (checkAllVariableIsNull()){
            return;
        }

        errors.clear();
        SerializableSave serializableSave = new SerializableSave(this.context,KosakKataFileName);
        if (serializableSave.load() == null){
            errors.add(error_not_found);
            listener.onErrorLogs(errors);
            return;
        }
        ArrayList<KosakKataModel> kosakKataModelsKetemu = new ArrayList<>();
        ListKosakKataModel listKosakKataModel = serializableSave.load();

        for (KosakKataModel kosakKataModelDariStorage : listKosakKataModel.kosakKataModels) {
            if (!isInList(kosakKataModelsKetemu,kosakKataModelDariStorage.BahasaIndonesia) && findBy.equals(Indonesia) && isSimmilar(kosakKataModelDariStorage.BahasaIndonesia,kosakKataDicari.BahasaIndonesia)) {
                kosakKataModelsKetemu.add(new KosakKataModel(kosakKataModelDariStorage.BahasaInggris,kosakKataModelDariStorage.keteranganBahasaInggris, kosakKataModelDariStorage.BahasaIndonesia,kosakKataModelDariStorage.keteranganBahasaIndonesia));
            } else if (!isInList(kosakKataModelsKetemu,kosakKataModelDariStorage.BahasaInggris) && findBy.equals(Inggris) && isSimmilar(kosakKataModelDariStorage.BahasaInggris,kosakKataDicari.BahasaInggris)) {
                kosakKataModelsKetemu.add(new KosakKataModel(kosakKataModelDariStorage.BahasaInggris,kosakKataModelDariStorage.keteranganBahasaInggris, kosakKataModelDariStorage.BahasaIndonesia,kosakKataModelDariStorage.keteranganBahasaIndonesia));
            }
        }

        listener.onListKataFound(kosakKataModelsKetemu);

    }

    private Boolean isSimmilar(String text_1, String text_2){
        return (text_1.matches("(?i).*" + text_2 + "(.*)") || text_2.matches("(?i).*" + text_1 + "(.*)"));
    }

    private Boolean isInList(ArrayList<KosakKataModel> listString,String s){
        boolean found = false;

        for (KosakKataModel d : listString){
            if (d.BahasaIndonesia.equals(s) || d.BahasaInggris.equals(s)){
                found = true;
                break;
            }
        }

        return found;
    }

    private Boolean checkAllVariableIsNull(){
        return (context == null || listener == null || kosakKataDicari == null || errors == null);
    }

    public interface OnQueryKataListener {
        void onListKataFound(@NonNull ArrayList<KosakKataModel> kosakKataModels);
        void onErrorLogs(@NonNull ArrayList<String> errors);
    }
}
