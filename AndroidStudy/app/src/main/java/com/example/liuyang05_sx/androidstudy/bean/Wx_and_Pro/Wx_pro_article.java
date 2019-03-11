package com.example.liuyang05_sx.androidstudy.bean.Wx_and_Pro;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wx_pro_article {

@SerializedName("children")
@Expose
private List<Object> children = null;
@SerializedName("courseId")
@Expose
private Integer courseId;
@SerializedName("id")
@Expose
private Integer id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("order")
@Expose
private Integer order;
@SerializedName("parentChapterId")
@Expose
private Integer parentChapterId;
@SerializedName("userControlSetTop")
@Expose
private Boolean userControlSetTop;
@SerializedName("visible")
@Expose
private Integer visible;

public List<Object> getChildren() {
return children;
}

public void setChildren(List<Object> children) {
this.children = children;
}

public Integer getCourseId() {
return courseId;
}

public void setCourseId(Integer courseId) {
this.courseId = courseId;
}

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
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

public Integer getParentChapterId() {
return parentChapterId;
}

public void setParentChapterId(Integer parentChapterId) {
this.parentChapterId = parentChapterId;
}

public Boolean getUserControlSetTop() {
return userControlSetTop;
}

public void setUserControlSetTop(Boolean userControlSetTop) {
this.userControlSetTop = userControlSetTop;
}

public Integer getVisible() {
return visible;
}

public void setVisible(Integer visible) {
this.visible = visible;
}

}