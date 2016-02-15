package uk.co.rbinesconsulting.babylonhealthtest.data.network;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import uk.co.rbinesconsulting.babylonhealthtest.data.entity.Person;

public class PeopleApi {

    private PeopleService service;

    public PeopleApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fast-gorge.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(PeopleService.class);
    }

    public Person[] getContacts() {
        Call<Person[]> result = service.getContacts();
        Response<Person[]> response;

        try {
            response = result.execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}