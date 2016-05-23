package control;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import congnhanvienthong.entity.ThongTinModule;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "moduleManager";

	// Contacts table name
	private static final String TABLE_MODULE = "thongtinModule";

	// Contacts Table Columns names
	private static final String USER_NAME = "USER_NAME";
	private static final String CLASS_PATH1 = "CLASS_PATH1";
	private static final String CLASS_PATH2 = "CLASS_PATH2";
	private static final String CLASS_PATH3 = "CLASS_PATH3";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_MODULE_TABLE = "CREATE TABLE " + TABLE_MODULE + "("
				+ USER_NAME + " TEXT PRIMARY KEY," + CLASS_PATH1 + " TEXT,"
				+ CLASS_PATH2 + " TEXT," + CLASS_PATH3 + " TEXT)";
		db.execSQL(CREATE_MODULE_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_MODULE);

		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new contact
	public void addThongTinModule(ThongTinModule thongTinModule) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(USER_NAME, thongTinModule.getUserName());
		values.put(CLASS_PATH1, thongTinModule.getModuleClassPath_1());
		values.put(CLASS_PATH2, thongTinModule.getModuleClassPath_2());
		values.put(CLASS_PATH3, thongTinModule.getModuleClassPath_3());
		// Inserting Row
		db.insert(TABLE_MODULE, null, values);
		db.close(); // Closing database connection
	}

	// Getting single contact
	public ThongTinModule getThongTinModule(String username) {
		SQLiteDatabase db = this.getReadableDatabase();
		try {
			Cursor cursor = db.query(TABLE_MODULE, new String[] { USER_NAME,
					CLASS_PATH1, CLASS_PATH2,CLASS_PATH3 }, USER_NAME + "=?",
					new String[] { username }, null, null, null, null);
			if (cursor != null)
				cursor.moveToFirst();

			ThongTinModule thongTinModule = new ThongTinModule();
			thongTinModule.setUserName(cursor.getString(0));
			thongTinModule.setModuleClassPath_1(cursor.getString(1));
			thongTinModule.setModuleClassPath_2(cursor.getString(2));
			thongTinModule.setModuleClassPath_3(cursor.getString(3));
			// return contact
			return thongTinModule;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

		// TODO: handle exception

	}

	public List<ThongTinModule> getAllThongTinModules() {
		List<ThongTinModule> thongTinModulesList = new ArrayList<ThongTinModule>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_MODULE;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				ThongTinModule thongTinModule = new ThongTinModule();
				thongTinModule.setUserName(cursor.getString(0));
				thongTinModule.setModuleClassPath_1(cursor.getString(1));
				thongTinModule.setModuleClassPath_2(cursor.getString(2));
				thongTinModule.setModuleClassPath_3(cursor.getString(3));
				// Adding contact to list
				thongTinModulesList.add(thongTinModule);
				
			} while (cursor.moveToNext());
		}

		return thongTinModulesList;
	}

	public int updateContact(ThongTinModule thongTinModule) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(USER_NAME, thongTinModule.getUserName());
		values.put(CLASS_PATH1, thongTinModule.getModuleClassPath_1());
		values.put(CLASS_PATH2, thongTinModule.getModuleClassPath_2());
		values.put(CLASS_PATH3, thongTinModule.getModuleClassPath_3());

		// updating row
		return db.update(TABLE_MODULE, values, USER_NAME + " = ?",
				new String[] { thongTinModule.getUserName() });
	}

	public void deleteContact(ThongTinModule thongTinModule) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_MODULE, USER_NAME + " = ?",
				new String[] { thongTinModule.getUserName() });
		db.close();
	}

	public int getContactsCount() {
		String countQuery = "SELECT  * FROM " + TABLE_MODULE;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}

}