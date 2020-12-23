import java.util.ArrayList;

public class Userdata {
    private int userId;
    private String userName;
    private String userPwd;
    public Userdata(int userId,String userName,String userPwd){
        this.userId=userId;
        this.userName=userName;
        this.userPwd=userPwd;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    @Override
    public String toString() {
        return "Userdata{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                '}';
    }
}
