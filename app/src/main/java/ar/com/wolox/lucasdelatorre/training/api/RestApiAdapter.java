package ar.com.wolox.lucasdelatorre.training.api;

import ar.com.wolox.lucasdelatorre.training.Config;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

public class RestApiAdapter {

    public static RestAdapter getAdapter() {
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader(Config.PARSE_APP_HEADER, Config.PARSE_APP_ID);
                request.addHeader(Config.PARSE_REST_API_HEADER, Config.PARSE_REST_API_KEY);
            }
        };

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Config.URL)
                .setRequestInterceptor(requestInterceptor)
                .build();

        return restAdapter;
    }

}