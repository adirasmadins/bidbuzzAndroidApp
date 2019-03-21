package com.bidbuzz.utils;

public class AuthService {

    private static AuthService ourInstance = null;
    AuthUser user;

    public static AuthService getInstance() {
        return ourInstance != null ? ourInstance : new AuthService();
    }

    private AuthService () {
        user = new AuthUser();
    }

    public void setUser(AuthUser user) {
        this.user = user;
    }

    public AuthUser getUser() {
        return this.user;
    }

    public void login(String username, String password){

    }
    public boolean isAuthenticated(){
        if(user.getInstance().getToken().length()>0){
            return true;
        }
        return false;
    }
}
