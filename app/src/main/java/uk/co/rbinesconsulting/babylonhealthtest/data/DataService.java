package uk.co.rbinesconsulting.babylonhealthtest.data;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.greenrobot.eventbus.EventBus;

import uk.co.rbinesconsulting.babylonhealthtest.App;
import uk.co.rbinesconsulting.babylonhealthtest.data.database.PeopleDatabase;
import uk.co.rbinesconsulting.babylonhealthtest.data.entity.Person;
import uk.co.rbinesconsulting.babylonhealthtest.data.network.PeopleApi;

public class DataService extends IntentService {

    private static final String GET_CONTACTS = "GET_CONTACTS";
    private PeopleDatabase database;

    public DataService() {
        super("Data Service");
        database = new PeopleDatabase(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent.getAction().equals(GET_CONTACTS)) {
            boolean hasConnection = hasConnection();

            // Get local cached data
            Person[] people = database.getPeople();

            if (people != null) {
                EventBus.getDefault().post(people);
            }

            // Get from network only if there is a connection
            if (hasConnection) {
                App app = (App) getApplication();
                PeopleApi api = app.getApi();
                Person[] contacts = api.getContacts();

                EventBus.getDefault().post(contacts);

                // Add people to database
                database.addPeople(contacts);
            }
        }
    }

    public static Intent getPeopleIntent(Context context) {
        Intent intent = new Intent(context, DataService.class);
        intent.setAction(GET_CONTACTS);
        return intent;
    }

    private boolean hasConnection() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}