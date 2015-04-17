package ar.com.wolox.lucasdelatorre.training.services;

import ar.com.wolox.lucasdelatorre.training.User;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface SignupService {

    @POST("/1/users")
    public void signup(@Body User user, Callback<User> userCallback);

}
