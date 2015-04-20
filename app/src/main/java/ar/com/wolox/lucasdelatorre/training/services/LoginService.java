package ar.com.wolox.lucasdelatorre.training.services;

import ar.com.wolox.lucasdelatorre.training.User;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface LoginService {

    final static String sUsername = "username";
    final static String sPassword = "password";

    @GET("/1/login")
    public void login(@Query(sUsername) String email,
                      @Query(sPassword) String password,
                      Callback<User> userCallback);

    @GET("/1/users/me")
    public void checkToken(Callback<User> userCallback);
}