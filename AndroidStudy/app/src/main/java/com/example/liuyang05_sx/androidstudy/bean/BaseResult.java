package com.example.liuyang05_sx.androidstudy.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseResult<T> {
    @SerializedName("data")
    @Expose
    private T data;
    @SerializedName("errorCode")
    @Expose
    private Integer errorCode;
    @SerializedName("errorMsg")
    @Expose
    private String errorMsg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data =data;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
