package com.example.liuyang05_sx.androidstudy.bean.save;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShowSaveData_ {

@SerializedName("author")
@Expose
private String author;
@SerializedName("chapterId")
@Expose
private Integer chapterId;
@SerializedName("chapterName")
@Expose
private String chapterName;
@SerializedName("courseId")
@Expose
private Integer courseId;
@SerializedName("desc")
@Expose
private String desc;
@SerializedName("envelopePic")
@Expose
private String envelopePic;
@SerializedName("id")
@Expose
private Integer id;
@SerializedName("link")
@Expose
private String link;
@SerializedName("niceDate")
@Expose
private String niceDate;
@SerializedName("origin")
@Expose
private String origin;
@SerializedName("originId")
@Expose
private Integer originId;
@SerializedName("publishTime")
@Expose
private long publishTime;
@SerializedName("title")
@Expose
private String title;
@SerializedName("userId")
@Expose
private Integer userId;
@SerializedName("visible")
@Expose
private Integer visible;
@SerializedName("zan")
@Expose
private Integer zan;

public String getAuthor() {
return author;
}

public void setAuthor(String author) {
this.author = author;
}

public Integer getChapterId() {
return chapterId;
}

public void setChapterId(Integer chapterId) {
this.chapterId = chapterId;
}

public String getChapterName() {
return chapterName;
}

public void setChapterName(String chapterName) {
this.chapterName = chapterName;
}

public Integer getCourseId() {
return courseId;
}

public void setCourseId(Integer courseId) {
this.courseId = courseId;
}

public String getDesc() {
return desc;
}

public void setDesc(String desc) {
this.desc = desc;
}

public String getEnvelopePic() {
return envelopePic;
}

public void setEnvelopePic(String envelopePic) {
this.envelopePic = envelopePic;
}

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

public String getNiceDate() {
return niceDate;
}

public void setNiceDate(String niceDate) {
this.niceDate = niceDate;
}

public String getOrigin() {
return origin;
}

public void setOrigin(String origin) {
this.origin = origin;
}

public Integer getOriginId() {
return originId;
}

public void setOriginId(Integer originId) {
this.originId = originId;
}

public long getPublishTime() {
return publishTime;
}

public void setPublishTime(long publishTime) {
this.publishTime = publishTime;
}

public String getTitle() {
return title;
}

public void setTitle(String title) {
this.title = title;
}

public Integer getUserId() {
return userId;
}

public void setUserId(Integer userId) {
this.userId = userId;
}

public Integer getVisible() {
return visible;
}

public void setVisible(Integer visible) {
this.visible = visible;
}

public Integer getZan() {
return zan;
}

public void setZan(Integer zan) {
this.zan = zan;
}

}