package com.pagodalabs.materialdesign.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ProfessionalJsonDatabaseAdapter {

	DatabaseHelper databaseHelper;

	public ProfessionalJsonDatabaseAdapter(Context context) {
		databaseHelper = new DatabaseHelper(context);
	}

	public long insert(String jsonContent, Integer categoryId) {
		SQLiteDatabase sqliteDb = databaseHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.COLUMN_CATEGORY_ID, categoryId);
		values.put(DatabaseHelper.COLUMN_PROFESSIONALS_JSON, jsonContent);
		
		return sqliteDb.insert(DatabaseHelper.TABLE_NAME_JSON_PROFESSIONALS, null, values);
	}
	
	public int update(String jsonContent, Integer categoryId){
		SQLiteDatabase sqliteDb = databaseHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.COLUMN_PROFESSIONALS_JSON, jsonContent);
		return sqliteDb.update(DatabaseHelper.TABLE_NAME_JSON_PROFESSIONALS, values, DatabaseHelper.COLUMN_CATEGORY_ID+" = "+ categoryId , null);
	}
	
	public int delete(Integer categoryId){
		SQLiteDatabase sqliteDb = databaseHelper.getWritableDatabase();
		return sqliteDb.delete(DatabaseHelper.TABLE_NAME_JSON_PROFESSIONALS, DatabaseHelper.COLUMN_CATEGORY_ID+" = "+categoryId, null);
	}
	
	public String getById(Integer categoryId){
		String jsonContent = "";
		SQLiteDatabase sqliteDb = databaseHelper.getWritableDatabase();
		String[] columns = {DatabaseHelper.COLUMN_PROFESSIONALS_JSON};
		Cursor cursor = sqliteDb.query(DatabaseHelper.TABLE_NAME_JSON_PROFESSIONALS, columns, DatabaseHelper.COLUMN_CATEGORY_ID+" = "+ categoryId, null, null, null, null);
		while(cursor.moveToNext()){
			jsonContent+=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PROFESSIONALS_JSON));
		}
		return jsonContent;
	}
	public String showAll(){
		String jsonContent = "";
		SQLiteDatabase sqliteDb = databaseHelper.getWritableDatabase();
		String[] columns = {DatabaseHelper.COLUMN_PROFESSIONALS_JSON};
		Cursor cursor = sqliteDb.query(DatabaseHelper.TABLE_NAME_JSON_PROFESSIONALS, columns, null, null, null, null, null);
		while(cursor.moveToNext()){
			jsonContent+=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PROFESSIONALS_JSON));
		}
		return jsonContent;
	}
	
	
	
}
