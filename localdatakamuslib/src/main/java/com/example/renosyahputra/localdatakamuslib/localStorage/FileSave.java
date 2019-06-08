package com.example.renosyahputra.localdatakamuslib.localStorage;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileSave {

    public static final String fileKamus = "kamus.json";
    public static final String folderFile = "kamusData";

    private Context context;
    private String fileName = fileKamus;


    public FileSave(Context context) {
        this.context = context;
    }

    public Boolean save(String json){
        Boolean save = false;
        try {

            File folder = new File(Environment.getExternalStorageDirectory(),folderFile);
            if (!folder.exists()){
                folder.mkdir();
            }

            File fileKamus = new File(folder,fileName);
            FileWriter fileWriter = new FileWriter(fileKamus);
            fileWriter.write(json);
            fileWriter.flush();
            fileWriter.close();

            save = true;

        }catch (Exception ignored){

        }
        return save;
    }

    public String read(){

        String json = "";
        try {

            File folder = new File(Environment.getExternalStorageDirectory(),folderFile);
            File fileKamus = new File(folder,fileName);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileKamus));

            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                json += line;
            }
            bufferedReader.close();


        }catch (Exception ignored){

        }
        return json;
    }

}
