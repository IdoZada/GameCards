package com.example.warcardgame.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.warcardgame.objects.WinnerPlayer;
import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MySharedPreference {
    public static final String KEY_WINNER = "KEY_WINNER";
    private ArrayList<WinnerPlayer> list;



    private static MySharedPreference instance;
    private SharedPreferences prefs;

    public static MySharedPreference getInstance(){
        return instance;
    }
    private MySharedPreference(Context context){
        prefs = context.getSharedPreferences("MY_SP",Context.MODE_PRIVATE);
    }

    public static void init(Context context){
        if(instance == null){
            instance = new MySharedPreference(context);
        }
    }

    public void putString(String key,String value){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key,value);
        editor.apply();
    }

    public String getString(String key,String def){
        return prefs.getString(key, def);
    }

    public void putInt(String key, Integer value){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key,value);
        editor.apply();
    }

    public Integer getInt(String key,Integer def){
        return prefs.getInt(key, def);
    }

    public void putFloat(String key, Float value){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putFloat(key,value);
        editor.apply();
    }

    public Float getFloat(String key,Float def){
        return prefs.getFloat(key, def);
    }

    public void putObject(String key,Object obj){
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        editor.putString(KEY_WINNER,json);
        editor.apply();
    }

  /*  public ArrayList<E> getObject(String key, Object def){
        Gson gson = new Gson();
        String json = prefs.getString("MyObject", "");
        Type type = new TypeToken<def>() {
        }.getType();
        Object obj = gson.fromJson(json, type);
        return obj;
    }*/


    public void removeKey(String key){
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(key).apply();
    }
}
