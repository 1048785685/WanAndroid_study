package com.example.liuyang05_sx.androidstudy.bean.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchDatum {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("link")
@Expose
private String link;
@SerializedName("name")
@Expose
private String name;
@SerializedName("order")
@Expose
private Integer order;
@SerializedName("visible")
@Expose
private Integer visible;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getLink() {
return link;
}

public void setLink(String link) {
this.link = link;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public Integer getOrder() {
return order;
}

public void setOrder(Integer order) {
this.order = order;
}

public Integer getVisible() {
return visible;
}

public void setVisible(Integer visible) {
this.visible = visible;
}

}