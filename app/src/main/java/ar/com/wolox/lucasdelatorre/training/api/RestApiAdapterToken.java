package ar.com.wolox.lucasdelatorre.training.api;

import ar.com.wolox.lucasdelatorre.training.Config;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

public class RestApiAdapterToken {

    public static RestAdapter getAdapter(final String token) {

        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader(Config.PARSE_APP_HEADER, Config.PARSE_APP_ID);
                request.addHeader(Config.PARSE_REST_API_HEADER, Config.PARSE_REST_API_KEY);
                request.addHeader(Config.PARSE_TOKEN_HEADER, token);
            }
        };

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Config.URL)
                .setRequestInterceptor(requestInterceptor)
                .build();

        return restAdapter;
    }
}
