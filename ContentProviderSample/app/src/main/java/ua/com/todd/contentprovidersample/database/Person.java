package ua.com.todd.contentprovidersample.database;

import android.content.ContentValues;
import android.database.Cursor;

public class Person {


	public static final String TABLE_NAME = "Person";
	public static final String COL_ID = "_id";
	public static final String COL_FIRSTNAME = "firstname";
	public static final String COL_LASTNAME = "lastname";
	public static final String COL_BIO = "bio";


	public static final String[] FIELDS = { COL_ID, COL_FIRSTNAME, COL_LASTNAME,
		COL_BIO };
	

	public static final String CREATE_TABLE =
			"CREATE TABLE " + TABLE_NAME + "(" 
			+ COL_ID + " INTEGER PRIMARY KEY,"
			+ COL_FIRSTNAME + " TEXT NOT NULL DEFAULT '',"
			+ COL_LASTNAME + " TEXT NOT NULL DEFAULT '',"
			+ COL_BIO + " TEXT NOT NULL DEFAULT ''"
			+ ")";
	

	public long id = -1;
	public String firstname = "";
	public String lastname = "";
	public String bio = "";


	public Person() {
	}


	public Person(final Cursor cursor) {
		this.id = cursor.getLong(0);
		this.firstname = cursor.getString(1);
		this.lastname = cursor.getString(2);
		this.bio = cursor.getString(3);
	}



	public ContentValues getContent() {
		final ContentValues values = new ContentValues();
		values.put(COL_FIRSTNAME, firstname);
		values.put(COL_LASTNAME, lastname);
		values.put(COL_BIO, bio);

		return values;
	}
}
