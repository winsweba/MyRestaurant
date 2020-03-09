package tk.winsweb.myrestaurant.Model;

import java.util.List;

public class UpdateUserModel {
    private boolean IsSuccess;
    private String message;

    public boolean isSuccess() {
        return IsSuccess;
    }

    public void setSuccess(boolean success) {
        IsSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
