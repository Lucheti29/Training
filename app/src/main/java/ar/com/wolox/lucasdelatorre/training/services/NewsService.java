package ar.com.wolox.lucasdelatorre.training.services;

import ar.com.wolox.lucasdelatorre.training.instances.NewsArrayInstance;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface NewsService {

    @GET("/1/classes/news")
    public void getNews(@Query("order") String order,
                        @Query("skip") int fromPos,
                        @Query("limit") int NumbOfNews,
                        Callback<NewsArrayInstance> newsArrayRequestCallback);

}
