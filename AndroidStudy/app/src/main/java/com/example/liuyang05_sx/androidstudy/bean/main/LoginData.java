package com.example.liuyang05_sx.androidstudy.bean.main;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginData {

    @SerializedName("chapterTops")
    @Expose
    private List<Object> chapterTops = null;
    @SerializedName("collectIds")
    @Expose
    private List<Object> collectIds = null;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("username")
    @Expose
    private String username;

    public List<Object> getChapterTops() {
        return chapterTops;
    }

    public void setChapterTops(List<Object> chapterTops) {
        this.chapterTops = chapterTops;
    }

    public List<Object> getCollectIds() {
        return collectIds;
    }

    public void setCollectIds(List<Object> collectIds) {
        this.collectIds = collectIds;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}