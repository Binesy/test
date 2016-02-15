package uk.co.rbinesconsulting.babylonhealthtest.data.network;

import retrofit2.Call;
import retrofit2.http.GET;
import uk.co.rbinesconsulting.babylonhealthtest.data.entity.Person;

public interface PeopleService {

    @GET("contacts")
    Call<Person[]> getContacts();
}