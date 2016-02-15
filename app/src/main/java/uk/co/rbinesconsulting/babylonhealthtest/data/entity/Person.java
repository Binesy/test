package uk.co.rbinesconsulting.babylonhealthtest.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Person implements Parcelable {

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("surname")
    private String surname;

    @SerializedName("address")
    private String address;

    @SerializedName("phone_number")
    private String phoneNumber;

    @SerializedName("email")
    private String email;

    @SerializedName("id")
    private int id;

    @SerializedName("createdAt")
    private String createdAt;

    @SerializedName("updatedAt")
    private String updatedAt;

    public Person() {
    }

    public Person(String firstName, String surname, String address, String phoneNumber, String email, int id, String createdAt, String updatedAt) {
        this.firstName = firstName;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    protected Person(Parcel in) {
        firstName = in.readString();
        surname = in.readString();
        address = in.readString();
        phoneNumber = in.readString();
        email = in.readString();
        id = in.readInt();
        createdAt = in.readString();
        updatedAt = in.readString();
    }

    public String getAvatarUrl() {
        return "http://api.adorable.io/avatars/285/" + email + ".png";
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(surname);
        dest.writeString(address);
        dest.writeString(phoneNumber);
        dest.writeString(email);
        dest.writeInt(id);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}