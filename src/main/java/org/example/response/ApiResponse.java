package org.example.response;

public class ApiResponse<T> {

    boolean success;
    T data;
    String error;

    public ApiResponse(){}

    public ApiResponse(T data){
        this.success = true;
        this.data = data;
    }

    public ApiResponse(T data, boolean success, String error) {
        this.data = data;
        this.success = success;
        this.error = error;
    }

    public ApiResponse(String error){
        this.success = false;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
