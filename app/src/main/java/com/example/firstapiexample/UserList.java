package com.example.firstapiexample;

public class UserList {
    public int userId;
    public String userName;
    public String userPassword;
    public String userRole;
    public UserList(){
        userId=0;
        userName="";
        userPassword="";
        userRole="";
    }
    public UserList(int id,String name,String pass,String role){
        userId=id;
        userName=name;
        userPassword=pass;
        userRole=role;
    }
}
