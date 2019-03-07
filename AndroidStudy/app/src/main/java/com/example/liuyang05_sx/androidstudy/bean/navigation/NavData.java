package com.example.liuyang05_sx.androidstudy.bean.navigation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.bind.ArrayTypeAdapter;

import java.util.ArrayList;
import java.util.List;

public class NavData {


    @SerializedName("cid")
    @Expose
    private int cid;
    @SerializedName("name")
    @Expose
    private int name;
    @SerializedName("articles")
    @Expose
    private List<Articles> list= new ArrayList<>();

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public List<Articles> getList() {
        return list;
    }

    public void setList(List<Articles> list) {
        this.list = list;
    }
}
