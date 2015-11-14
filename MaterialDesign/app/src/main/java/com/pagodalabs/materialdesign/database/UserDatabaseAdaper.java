package com.pagodalabs.materialdesign.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pagodalabs.materialdesign.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserDatabaseAdaper{
	DatabaseHelper databaseHelper;
	
	public UserDatabaseAdaper(Context context) {
		databaseHelper = new DatabaseHelper(context);
	}

	public long insert(User user) {
		// TODO Auto-generated method stub
		long insert = 0;
		if(getById(user.getUserId())==null){
		SQLiteDatabase sqliteDb = databaseHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.COLUMN_USER_USER_ID, user.getUserId());
		values.put(DatabaseHelper.COLUMN_USER_USERNAME, user.getUsername());
		values.put(DatabaseHelper.COLUMN_USER_PASSWORD, user.getPassword());
		values.put(DatabaseHelper.COLUMN_USER_TOKEN, user.getToken());
		values.put(DatabaseHelper.COLUMN_USER_TOKEN_BEGIN, user.getTokenBegin());
		values.put(DatabaseHelper.COLUMN_USER_TOKEN_BEGIN, user.getTokenEnd());
		insert = sqliteDb.insert(DatabaseHelper.TABLE_NAME_USERS, null, values);
		}
		return insert;
	}

	public int update(User user) {
		// TODO Auto-generated method stub
		SQLiteDatabase sqliteDb = databaseHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.COLUMN_USER_USER_ID, user.getUserId());
		values.put(DatabaseHelper.COLUMN_USER_USERNAME, user.getUsername());
		values.put(DatabaseHelper.COLUMN_USER_PASSWORD, user.getPassword());
		values.put(DatabaseHelper.COLUMN_USER_TOKEN, user.getToken());
		values.put(DatabaseHelper.COLUMN_USER_TOKEN_BEGIN, user.getTokenBegin());
		values.put(DatabaseHelper.COLUMN_USER_TOKEN_BEGIN, user.getTokenEnd());
		String[] whereArgs = {user.getUserId().toString()};
		return sqliteDb.update(DatabaseHelper.TABLE_NAME_USERS, values, DatabaseHelper.COLUMN_KEY_ID+" = "+1, null);
	}


	public User getById(int userId) {
		// TODO Auto-generated method stub
		User user = null;
		SQLiteDatabase sqliteDb = databaseHelper.getWritableDatabase();
		String[] columns = {DatabaseHelper.COLUMN_USER_USER_ID, DatabaseHelper.COLUMN_USER_USERNAME, DatabaseHelper.COLUMN_USER_PASSWORD, 
							DatabaseHelper.COLUMN_USER_TOKEN, DatabaseHelper.COLUMN_USER_TOKEN_BEGIN, DatabaseHelper.COLUMN_USER_TOKEN_END};
		Cursor cursor = sqliteDb.query(DatabaseHelper.TABLE_NAME_USERS, columns, DatabaseHelper.COLUMN_USER_USER_ID+" = " + userId, null, null, null, null);
		while(cursor.moveToNext()){
			user = initUser(cursor);
		}
		return user;
	}


	public List<User> getAll() {
		List<User> userList = new ArrayList<User>();
		SQLiteDatabase sqliteDb = databaseHelper.getWritableDatabase();
		String[] columns = {DatabaseHelper.COLUMN_USER_USER_ID, DatabaseHelper.COLUMN_USER_USERNAME, DatabaseHelper.COLUMN_USER_PASSWORD, 
							DatabaseHelper.COLUMN_USER_TOKEN, DatabaseHelper.COLUMN_USER_TOKEN_BEGIN, DatabaseHelper.COLUMN_USER_TOKEN_END};
		Cursor cursor = sqliteDb.query(DatabaseHelper.TABLE_NAME_USERS, columns, null, null, null, null, null);
		while(cursor.moveToNext()){
			User user = initUser(cursor);
			userList.add(user);
		}
		return userList;
	}
	
	
	private User initUser(Cursor cursor){
		User user = new User();
		user.setUserId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_USER_ID)));
		user.setUsername(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_USERNAME)));
		user.setPassword(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_PASSWORD)));
		user.setToken(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_TOKEN)));
		user.setTokenBegin(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_TOKEN_BEGIN)));
		user.setTokenEnd(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_TOKEN_END)));
		
		return user;
	}
	
}
