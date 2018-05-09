package edu.niu.cs.z1805839.photoarts1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by KIRAN on 11/24/2017.
 */

 public class CartDatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASENAME = "userDB";
    private static final int DBVERSION = 4;
    private static final String TBLCART = "cart";
    private static final String TBLUSER = "user";
    private static final String PRODUCTID = "productid";
    private static final String USERID = "userid";
    private static final String ITEMNAME = "itemname";
    private static final String ITEMNUMBER = "itemnumber";
    private static final String FRAME = "frame";
    private static final String SIZE = "size";
    private static final String QUANTITY = "quantity";
    private static final String PRICE = "price";
    private static final String TAG = "database1";



    public CartDatabaseManager(Context ctx) {
        super(ctx,DATABASENAME,null,DBVERSION);
    }

   /* @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "create table " +
                TBLCART + " (" + PRODUCTID;
        sqlCreate += " integer primary key autoincrement, " + ITEMNUMBER;
        sqlCreate += " number, " + ITEMNAME;
        sqlCreate += " text, " + FRAME;
        sqlCreate += " text, " + SIZE;
        sqlCreate += " text, " + QUANTITY;
        sqlCreate += " number, " + PRICE;
        sqlCreate += " number, " + USERID;
        sqlCreate += " integer )";
        //put in a log statement to show the sqlCreate string
        Log.d(TAG,sqlCreate);
        db.execSQL(sqlCreate);
    }
*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "create table " +
                TBLCART + " (" + PRODUCTID;
        sqlCreate += " integer primary key autoincrement, " + ITEMNUMBER;
        sqlCreate += " text, " + ITEMNAME;
        sqlCreate += " text, " + FRAME;
        sqlCreate += " text, " + SIZE;
        sqlCreate += " text, " + QUANTITY;
        sqlCreate += " number, " + PRICE;
        sqlCreate += " number, " + USERID + " real )";
       // sqlCreate += " integer, " + "FOREIGN KEY (" + USERID + ") REFERENCES " + TBLUSER + "(userid) " + ")";
        //put in a log statement to show the sqlCreate string
        //Toast.makeText(, "", Toast.LENGTH_SHORT).show();
        Log.d(TAG,sqlCreate);
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TBLCART);
        onCreate(db);
    }

    public void insert(Cart c) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " + TBLCART;
        sqlInsert += " values( null, '" + c.getItemnumber();
        sqlInsert += "','" + c.getItemname();
        sqlInsert += "','" + c.getFrame();
        sqlInsert += "','" + c.getSize();
        sqlInsert += "','" + c.getQuantity();
        sqlInsert += "','" + c.getPrice();
        sqlInsert += "','" + c.getUserid() + "')";
        db.execSQL(sqlInsert);
        db.close();
    }

    public ArrayList<Cart> selectAll() {
        String sqlQuery = "select * from " + TBLCART;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(sqlQuery,null);
        ArrayList<Cart> cartData = new ArrayList<>();
        while (c.moveToNext()) {

              Cart currentData = new Cart(Integer.parseInt(c.getString(0)),c.getString(1),c.getString(2),c.getString(3),c.getString(4),Integer.parseInt(c.getString(5)),Double.parseDouble(c.getString(6)),Integer.parseInt(c.getString(7)));
            //Cart currentData = new Cart(Integer.parseInt(c.getString(0)),Integer.parseInt(c.getString(1)),Integer.parseInt(c.getString(2)),c.getString(3),c.getString(4),c.getString(5),c.getString(6),Double.parseDouble(c.getString(7)));
            Log.d("cart","hi");

            cartData.add(currentData);
        }
        db.close();


        Log.d("cart",cartData.toString());
        return cartData;
    }

    public void deleteById(int id) {
        String sqlDelete = "delete from " + TBLCART + " where " + PRODUCTID + " = " + id;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sqlDelete);
        db.close();
    }

    public void deleteByUserId(int id) {
        String sqlDelete = "delete from " + TBLCART + " where " + USERID + " = " + id;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sqlDelete);
        db.close();
    }


}
