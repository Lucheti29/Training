package ar.com.wolox.lucasdelatorre.training.api;

import ar.com.wolox.lucasdelatorre.training.Utils;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

public class RestApiAdapterToken {

    public static RestAdapter getAdapter(final String token) {

        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader(Utils.PARSE_APP_HEADER, Utils.PARSE_APP_ID);
                request.addHeader(Utils.PARSE_REST_API_HEADER, Utils.PARSE_REST_API_KEY);
                request.addHeader(Utils.PARSE_TOKEN_HEADER, token);
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
