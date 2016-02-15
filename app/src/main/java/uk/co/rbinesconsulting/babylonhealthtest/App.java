package uk.co.rbinesconsulting.babylonhealthtest;

import android.app.Application;

import uk.co.rbinesconsulting.babylonhealthtest.data.network.PeopleApi;

public class App extends Application {

    private PeopleApi api;

    @Override
    public void onCreate() {
        super.onCreate();
        api = new PeopleApi();
    }

    public PeopleApi getApi() {
        return api;
    }
}