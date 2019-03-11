package com.example.renosyahputra.localdatakamuslib.localStorage;

import android.content.Context;

import com.example.renosyahputra.localdatakamuslib.model.ListKosakKataModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializableSave {
    private Context context;
    private String filename;

    public SerializableSave(Context context, String filename) {
        this.context = context;
        this.filename = filename;
    }

    public Boolean save(Serializable d) {

        Boolean succes = false;
        try {
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(d);
            os.close();
            fos.close();

            succes = true;

        }catch (Exception e){
            e.printStackTrace();
        }
        return succes;
    }

    public Boolean delete(){
        File f = new File(context.getFilesDir(), filename);
        return f.delete();
    }

    public ListKosakKataModel load(){
        ListKosakKataModel listKosakKataModel = null;

        try {
            FileInputStream fis = context.openFileInput(filename);
            ObjectInputStream file = new ObjectInputStream(fis);
            listKosakKataModel = (ListKosakKataModel) file.readObject() ;
            file.close();
            fis.close();

        } catch (IOException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return listKosakKataModel;
    }

}
