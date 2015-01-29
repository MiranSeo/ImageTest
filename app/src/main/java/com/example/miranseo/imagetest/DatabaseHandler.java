package com.example.miranseo.imagetest;

/**
 * Created by Miran Seo on 15. 1. 21.
 */

        import java.util.ArrayList;
        import java.util.List;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "contactsManager";

    // Contacts table name
    private static final String TABLE_CONTACTS = "contacts";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String PHOTO = "PHOTO";
    public static ImageAdapter imageAdapter;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
//        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + " " +  "("
//                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PHOTO + " TEXT" + ')';
        db.execSQL("CREATE TABLE " + TABLE_CONTACTS + " " +  "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + PHOTO + " TEXT " + ");");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    void addImages(String url) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PHOTO, url); // Contact photo

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);

        db.close(); // Closing database connection
    }

    // Getting single contact
    Images getImages(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                        PHOTO }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Images images = new Images(cursor.getString(1));
        // return contact
        return images;
    }
    public ArrayList<Images> getList() {
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        ArrayList<Images> imageList = new ArrayList<Images>();

        while(cursor.moveToNext()) {
            int no = cursor.getInt(0);
            String photo = cursor.getString(1);

            Images item = new Images(photo);
            imageList.add(item);
        }

        return imageList;
    }

    public List<Images> getAllImagesList() {
        List<Images> imagesList = new ArrayList<Images>();


        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        Log.i("***Count**", ""+cursor.getCount());
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {

            Images images = new Images();
            images.setPhoto(cursor.getString(1));

            String photo = cursor.getString(1);

            //imageAdapter.mThumbIds.add(images);

            // Adding contact to list
            imagesList.add(images);

        } while (cursor.moveToNext());


        // return contact list
        return imagesList;
    }

    // Getting All Contacts
  //  public List<Images> getAllImages() {
    public String getAllImages() {

        String photo = null;
        String photo_url = null;
        int num = 0;

        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        Log.i("***Count**", ""+cursor.getCount());
        // looping through all rows and adding to list
        while (cursor.moveToNext()) {

                num = cursor.getInt(0);
                photo = cursor.getString(1);
                //imageAdapter.mThumbIds.add(photo);

            }
                photo_url = photo;

        // return contact list
       // imageAdapter.mThumbIds.add(photo);
        return photo_url;
    }

    // Updating single contact
//    public int updateImages(Images images) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(PHOTO, images.getPhoto());
//
//        // updating row
//        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
//                new String[] { String.valueOf(images.getID()) });
//    }

//    // Deleting single contact
//    public void deleteImages(Images images) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
//                new String[] { String.valueOf(images.getID()) });
//        db.close();
//    }


    // Getting contacts Count
    public int getImagesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}