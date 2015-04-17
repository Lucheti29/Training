package ar.com.wolox.lucasdelatorre.training.api;

import ar.com.wolox.lucasdelatorre.training.Utils;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

public class RestApiAdapter {

    public static RestAdapter getAdapter() {
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader(Utils.PARSE_APP_HEADER, Utils.PARSE_APP_ID);
                request.addHeader(Utils.PARSE_REST_API_HEADER, Utils.PARSE_REST_API_KEY);
            }
        };

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(Utils.URL)
                .setRequestInterceptor(requestInterceptor)
                .build();

        return restAdapter;
    }

}