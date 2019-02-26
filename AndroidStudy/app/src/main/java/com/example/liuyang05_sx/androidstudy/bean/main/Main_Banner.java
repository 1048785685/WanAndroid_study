package com.example.liuyang05_sx.androidstudy.bean.main;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Main_Banner {

@SerializedName("desc")
@Expose
private String desc;
@SerializedName("id")
@Expose
private Integer id;
@SerializedName("imagePath")
@Expose
private String imagePath;
@SerializedName("isVisible")
@Expose
private Integer isVisible;
@SerializedName("order")
@Expose
private Integer order;
@SerializedName("title")
@Expose
private String title;
@SerializedName("type")
@Expose
private Integer type;
@SerializedName("url")
@Expose
private String url;

public String getDesc() {
return desc;
}

public void setDesc(String desc) {
this.desc = desc;
}

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getImagePath() {
return imagePath;
}

public void setImagePath(String imagePath) {
this.imagePath = imagePath;
}

public Integer getIsVisible() {
return isVisible;
}

public void setIsVisible(Integer isVisible) {
this.isVisible = isVisible;
}

public Integer getOrder() {
return order;
}

public void setOrder(Integer order) {
this.order = order;
}

public String getTitle() {
return title;
}

public void setTitle(String title) {
this.title = title;
}

public Integer getType() {
return type;
}

public void setType(Integer type) {
this.type = type;
}

public String getUrl() {
return url;
}

public void setUrl(String url) {
this.url = url;
}

}