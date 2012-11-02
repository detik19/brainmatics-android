package com.brainmatics.notepad.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class DataHelper {
	private static final String DATABASE_NAME = "notepad.db";
	private static final int DATABASE_VERSION = 2;
	private static final String TABLE_NAME = "notes";
	private Context context;
	private SQLiteDatabase db;
	private SQLiteStatement insertStmt;

	private static final String INSERT = "insert into "
		+ TABLE_NAME + "(title, content) values (?,?)";

	public DataHelper(Context context) {
		this.context = context;
		OpenHelper openHelper = new OpenHelper(this.context);
		this.db = openHelper.getWritableDatabase();
		this.insertStmt = this.db.compileStatement(INSERT);
	}
	/**\
	 * Cara pertama query
	 * @param title
	 * @param content
	 * @return
	 */
	public long insert(String title, String content) {
		this.insertStmt.bindString(1, title);
		this.insertStmt.bindString(2, content);
		return this.insertStmt.executeInsert();
	}
	/**
	 * Cara kedua Que
	 * @param title
	 * @param content
	 * @return
	 */
	public long insert2(String title, String content) {
		ContentValues cv = new ContentValues();
		cv.put("title", title);
		cv.put("content", content);
		return db.insert(TABLE_NAME, null, cv);
	}
	
	public Cursor getAll(){
		//SELECT id, name FROM table1 WHERE title = "a" ORDER BY title LIMIT 10
	//	return db.query(TABLE_NAME, new String[]{"id","name"}, "title = 'a'", 
		//		null, null, "title", "10");
		
		return db.query(TABLE_NAME, null, null, null, null, null, null);
		//SELECT * FROM table1
		//return db.rawQuery("SELECT id, name FROM table1 WHERE name = 'a' ORDER BY name LIMIT 10", null);
		
	}
	public Cursor getById(int id){
		return db.query(TABLE_NAME, null, "_id="+id, null, null, null, null);
	}

	public void close(){
		db.close();
	}
	
	public int deleteById(int id){
		return db.delete(TABLE_NAME, "WHERE _id ="+id, null);
	}
	
	public int updateById(int id, String title, String content){
		ContentValues cv = new ContentValues();
		cv.put("title", title);
		cv.put("content", content);
		return db.update(TABLE_NAME, cv, "_id = "+id, null);
	}
	
	
	
	/**
	 * initialize database
	 * @author Student 5
	 *
	 */
	private static class OpenHelper extends SQLiteOpenHelper { 
		OpenHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE " + TABLE_NAME + 
					"(_id INTEGER PRIMARY KEY, title TEXT, content TEXT)");
		}
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w("Example", "Upgrading database, this will drop tables and recreate.");
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
			onCreate(db);
		}   
	}
	
}
