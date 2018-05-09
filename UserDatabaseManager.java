package edu.niu.cs.z1805839.photoarts1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by KIRAN on 11/17/2017.
 */

public class UserDatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASENAME = "userDB";
    private static final int DBVERSION = 4;
    private static final String TBLUSER = "user";
    private static final String USERID = "userid";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String STREET = "street";
    private static final String APARTMENT = "apartment";
    private static final String CITY = "city";
    private static final String STATE = "state";
    private static final String EMAIL = "email";
    private static final String ZIP = "zip";
    private static final String TAG = "database";

    public UserDatabaseManager(Context ctx) {
        super(ctx,DATABASENAME,null,DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "create table " +
                TBLUSER + " (" + USERID;
        sqlCreate += " integer primary key autoincrement, " + USERNAME;
        sqlCreate += " text, " + PASSWORD;
        sqlCreate += " text, " + STREET;
        sqlCreate += " text, " + APARTMENT;
        sqlCreate += " text, " + CITY;
        sqlCreate += " text, " + STATE;
        sqlCreate += " text, " + ZIP;
        sqlCreate += " number, " + EMAIL + " text )";
        //put in a log statement to show the sqlCreate string
        Log.d(TAG,sqlCreate);
        db.execSQL(sqlCreate);
    }

    public void updateById(int userId, String userName,String password,String street,String apartment,String city,String state,int zipcode,String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        String updateSQL = "update " + TBLUSER;
        updateSQL += " set " + USERNAME + " = '" + userName + "', ";
        updateSQL += PASSWORD + " = '" + password + "', ";
        updateSQL += STREET + " = '" + street + "', ";
        updateSQL += APARTMENT + " = '" + apartment + "', ";
        updateSQL += CITY + " = '" + city + "', ";
        updateSQL += STATE + " = '" + state + "', ";
        updateSQL += ZIP + " = '" + zipcode + "', ";
        updateSQL += EMAIL + " = '" + email + "'";
        updateSQL += " where " + USERID + " = " + userId;
        db.execSQL(updateSQL);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TBLUSER);
        onCreate(db);
    }

    public void insert(User u) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " + TBLUSER;
        sqlInsert += " values( null, '" + u.getUsername();
        sqlInsert += "','" + u.getPassword();
        sqlInsert += "','" + u.getStreet();
        sqlInsert += "','" + u.getApartment();
        sqlInsert += "','" + u.getCity();
        sqlInsert += "','" + u.getState();
        sqlInsert += "','" + u.getZip();
        sqlInsert += "','" + u.getEmail() + "')";
        db.execSQL(sqlInsert);
        db.close();
    }

    public ArrayList<User> selectAll() {
        String sqlQuery = "select * from " + TBLUSER;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(sqlQuery,null);
        ArrayList<User> userData = new ArrayList<>();
        while (c.moveToNext()) {
            User currentUser = new User(Integer.parseInt(c.getString(0)),c.getString(1),c.getString(2),c.getString(3),c.getString(4),
            c.getString(5),c.getString(6),Integer.parseInt(c.getString(7)),c.getString(8));
            userData.add(currentUser);
        }
        db.close();
        return userData;
    }

    public int selectIDByName(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlQuery = "select " + USERID + " from " + TBLUSER;
        sqlQuery += " where " + USERNAME + " = " + username;
        Cursor c = db.rawQuery(sqlQuery,null);
        Integer userid = 0;
        if(c.moveToFirst()) {
            userid = Integer.parseInt(c.getString(0));
        }
        db.close();
        return userid;
    }



}
