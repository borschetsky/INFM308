package com.example.infm308.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.infm308.models.Person;

import java.util.ArrayList;

public class SwapiDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "swapi_database.db";
    private static final int DATABASE_VERSION = 1;
    public static final String PERSONS_TABLE_NAME = "persons";

    public static final String UID = "_id";
    public static final String PERSONS_NAME = "name";
    public static final String PERSONS_GENDER = "gender";
    public static final String PERSONS_BIRTH_YEAR = "birth_year";
    public static final String PERSONS_AVATAR = "avatar";

    public SwapiDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_PERSONS_TABLE = "CREATE TABLE " +
                PERSONS_TABLE_NAME + "(" +
                UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PERSONS_NAME + " TEXT NOT NULL," +
                PERSONS_GENDER + " TEXT NOT NULL," +
                PERSONS_BIRTH_YEAR + " TEXT NOT NULL," +
                PERSONS_AVATAR + " TEXT);";

        db.execSQL(SQL_CREATE_PERSONS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PERSONS_TABLE_NAME );
        onCreate(db);
    }

    public void addPersonToFavorites (Person personDto){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(PERSONS_NAME, personDto.name);
        values.put(PERSONS_GENDER, personDto.gender);
        values.put(PERSONS_BIRTH_YEAR, personDto.birth_year);
        values.put(PERSONS_AVATAR, personDto.avatar);

        db.insert(PERSONS_TABLE_NAME, null, values);

    }

    public ArrayList<Person> getAllFavorites(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Person> result = new ArrayList<>();

        Cursor cursor = db.query(
                PERSONS_TABLE_NAME,
                new String[] { UID, PERSONS_NAME, PERSONS_GENDER, PERSONS_BIRTH_YEAR, PERSONS_AVATAR},
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(UID));
            String name = cursor.getString(cursor.getColumnIndex(PERSONS_NAME));
            String gender = cursor.getString(cursor.getColumnIndex(PERSONS_GENDER));
            String birthYear = cursor.getString(cursor.getColumnIndex(PERSONS_BIRTH_YEAR));
            String avatar = cursor.getString(cursor.getColumnIndex(PERSONS_AVATAR));
            Person currentPerson = new Person(id, gender, name, birthYear, avatar);
            result.add(currentPerson);
        }

        cursor.close();

        return  result;
    }

    public boolean removeFromFavorites ( int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String whereArgs[] = {String.valueOf(id)};
        return db.delete(PERSONS_TABLE_NAME, UID + " = ?", whereArgs) != 0;
    }

    public boolean isAdded (String name){;
        ArrayList<Person> persons = getAllFavorites();
        for (int i = 0; i < persons.size(); i++){
            if(persons.get(i).name.equals(name)){
                return true;
            }
        }

        return false;
    }
}
