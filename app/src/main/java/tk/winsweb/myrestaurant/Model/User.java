package tk.winsweb.myrestaurant.Model;

public class User {
    private String fbid, userPone, name, address;

    public User(String fbid, String userPone, String name, String address) {
        this.fbid = fbid;
        this.userPone = userPone;
        this.name = name;
        this.address = address;
    }

    public String getFbid() {
        return fbid;
    }

    public void setFbid(String fbid) {
        this.fbid = fbid;
    }

    public String getUserPone() {
        return userPone;
    }

    public void setUserPone(String userPone) {
        this.userPone = userPone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
