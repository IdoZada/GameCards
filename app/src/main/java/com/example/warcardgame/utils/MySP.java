package com.example.warcardgame.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.warcardgame.objects.WinnerPlayer;
import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;

public class MySP {
    public static final String KEY_WINNER = "KEY_WINNER";

    /**
     * Define the max size of the high score table
     */
    private final int MAX_HIGH_SCORE_LENGTH = 10;


    private ArrayList<WinnerPlayer> list;
    private SharedPreferences prefs;

    public MySP(Context context){
        prefs = context.getSharedPreferences("MY_SP",Context.MODE_PRIVATE);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void add(WinnerPlayer gameUser, Comparator<WinnerPlayer> comparator) {
        list = readDataFromStorage();
        if (!list.contains(gameUser)) {
            if (list.size() < MAX_HIGH_SCORE_LENGTH) {
                list.add(gameUser);
                list.sort(comparator);
                writeDataToStorage(list);
            } else if (gameUser.getScore() > list.get(list.size() - 1).getScore()) {
                list.set(list.size() - 1, gameUser);
                list.sort(comparator);
                writeDataToStorage(list);
            }
        } else {
            int idx = list.indexOf(gameUser);
            WinnerPlayer currentUser = list.get(idx);
            if (currentUser.getScore() < gameUser.getScore()) {
                list.set(idx, gameUser);
                list.sort(comparator);
                writeDataToStorage(list);
            }
        }
    }


    private void writeDataToStorage(ArrayList<WinnerPlayer> list) {
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString("task_list", json);
        editor.apply();
    }

    public ArrayList<WinnerPlayer> readDataFromStorage() {
        Gson gson = new Gson();
        String json = prefs.getString("task_list", null);
        Type type = new TypeToken<ArrayList<WinnerPlayer>>() {
        }.getType();
        if ((list = gson.fromJson(json, type)) == null)
            list = new ArrayList<>();
        return list;
    }

    public void clearSharedPreferences() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
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





    public void removeKey(String key){
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(key).apply();
    }
}

