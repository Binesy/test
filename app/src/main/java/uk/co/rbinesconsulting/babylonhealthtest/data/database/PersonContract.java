package uk.co.rbinesconsulting.babylonhealthtest.data.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import uk.co.rbinesconsulting.babylonhealthtest.data.entity.Person;

public class PersonContract implements BaseColumns {

    public static final String TABLE_NAME = "person";

    private static final String FIRST_NAME = "first_name";
    private static final String SURNAME = "surname";
    private static final String ADDRESS = "address";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String EMAIL = "email";
    private static final String CREATED_AT = "created_at";
    private static final String UPDATED_AT = "updated_at";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" //
            + _ID + " INTEGER PRIMARY KEY, " //
            + FIRST_NAME + " TEXT, " //
            + SURNAME + " TEXT, " //
            + ADDRESS + " TEXT, " //
            + PHONE_NUMBER + " TEXT, " //
            + EMAIL + " TEXT, " //
            + CREATED_AT + " TEXT, " //
            + UPDATED_AT + " TEXT)";

    public static Person getPerson(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID));

        String firstName = cursor.getString(cursor.getColumnIndexOrThrow(FIRST_NAME));
        String surname = cursor.getString(cursor.getColumnIndexOrThrow(SURNAME));
        String address = cursor.getString(cursor.getColumnIndexOrThrow(ADDRESS));
        String phoneNumber = cursor.getString(cursor.getColumnIndexOrThrow(PHONE_NUMBER));
        String email = cursor.getString(cursor.getColumnIndexOrThrow(EMAIL));
        String createdAt = cursor.getString(cursor.getColumnIndexOrThrow(CREATED_AT));
        String updatedAt = cursor.getString(cursor.getColumnIndexOrThrow(UPDATED_AT));

        return new Person(firstName, surname, address, phoneNumber, email, id, createdAt, updatedAt);
    }

    public static ContentValues getContentValues(Person person) {
        ContentValues values = new ContentValues();

        values.put(_ID, person.getId());
        values.put(FIRST_NAME, person.getFirstName());
        values.put(SURNAME, person.getSurname());
        values.put(ADDRESS, person.getAddress());
        values.put(PHONE_NUMBER, person.getPhoneNumber());
        values.put(EMAIL, person.getEmail());
        values.put(CREATED_AT, person.getCreatedAt());
        values.put(UPDATED_AT, person.getUpdatedAt());

        return values;
    }
}