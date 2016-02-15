package uk.co.rbinesconsulting.babylonhealthtest.data.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import uk.co.rbinesconsulting.babylonhealthtest.data.entity.Person;

public class PeopleDatabase {

    private PeopleDbHelper helper;

    public PeopleDatabase(Context context) {
        helper = new PeopleDbHelper(context);
    }

    public synchronized Person[] getPeople() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(PersonContract.TABLE_NAME, null, null, null, null, null, null);
        Person[] people = new Person[0];

        if (cursor.moveToFirst()) {
            people = new Person[cursor.getCount()];

            do {
                Person person = PersonContract.getPerson(cursor);
                people[cursor.getPosition()] = person;
            }
            while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return people;
    }

    public synchronized void addPeople(Person[] people) {
        SQLiteDatabase db = helper.getWritableDatabase();

        final int totalPeople = people.length;

        for (int i = 0; i < totalPeople; i++) {
            String where = PersonContract._ID + " = ?";
            String[] whereArgs = new String[]{String.valueOf(people[i].getId())};

            Cursor cursor = db.query(PersonContract.TABLE_NAME, new String[]{PersonContract._ID}, where, whereArgs, null, null, null);

            if (cursor.getCount() == 0) {
                db.insert(PersonContract.TABLE_NAME, null, PersonContract.getContentValues(people[i]));
            } else {
                db.update(PersonContract.TABLE_NAME, PersonContract.getContentValues(people[i]), where, whereArgs);
            }
        }

        db.close();
    }

    static class PeopleDbHelper extends SQLiteOpenHelper {
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "database.db";

        public PeopleDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(PersonContract.CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}