package com.pagodalabs.materialdesign.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pagodalabs.materialdesign.entities.Professional;

import java.util.ArrayList;
import java.util.List;

public class ProfessionalFavoriteDatabaseAdapter {
	DatabaseHelper databaseHelper;

	public ProfessionalFavoriteDatabaseAdapter(Context context) {
		databaseHelper = new DatabaseHelper(context);
	}

	public long insertProfessional(Professional professional) {
		long insert = 0;
		if(getProfessionalById(professional.getProfessionalId())==null){
		SQLiteDatabase sqliteDb = databaseHelper.getWritableDatabase();
		ContentValues values = professionalValues(professional);
		insert = sqliteDb.insert(DatabaseHelper.TABLE_NAME_PROFESSIONALS_FAVORITES, null, values);
		}
		return insert;
	}
	
	public int updateProfessional(Professional professional){
		SQLiteDatabase sqliteDb = databaseHelper.getWritableDatabase();
		ContentValues values = professionalValues(professional);
		String[] whereArgs = {professional.getProfessionalId().toString()};
		return sqliteDb.update(DatabaseHelper.TABLE_NAME_PROFESSIONALS_FAVORITES, values, DatabaseHelper.COLUMN_PROFESSIONAL_ID+" = "+professional.getProfessionalId(), whereArgs);
	}
	
	public Professional getProfessionalById(Integer professionalId){
		Professional professional = null;
		SQLiteDatabase sqliteDb = databaseHelper.getWritableDatabase();
		String[] columns = {DatabaseHelper.COLUMN_PROFESSIONAL_ID, DatabaseHelper.COLUMN_CATEGORY_ID, DatabaseHelper.COLUMN_FIRST_NAME, DatabaseHelper.COLUMN_LAST_NAME, 
							DatabaseHelper.COLUMN_EMAIL, DatabaseHelper.COLUMN_PHONE, DatabaseHelper.COLUMN_DESCRIPTION, DatabaseHelper.COLUMN_ACTIVATION};
		Cursor cursor = sqliteDb.query(DatabaseHelper.TABLE_NAME_PROFESSIONALS_FAVORITES, columns, DatabaseHelper.COLUMN_PROFESSIONAL_ID+" = " + professionalId, null, null, null, null);
		while(cursor.moveToNext()){
			professional = initProfessional(cursor);
		}
		return professional;
	}
	
	
	public int delete(Integer id){
		SQLiteDatabase sqliteDb = databaseHelper.getWritableDatabase();
		return sqliteDb.delete(DatabaseHelper.TABLE_NAME_PROFESSIONALS_FAVORITES, DatabaseHelper.COLUMN_PROFESSIONAL_ID+" = "+id, null);
	}
	
	public List<Professional> showAll(){
		List<Professional> professionalList = new ArrayList<Professional>();
		SQLiteDatabase sqliteDb = databaseHelper.getWritableDatabase();
		String[] columns = {DatabaseHelper.COLUMN_PROFESSIONAL_ID, DatabaseHelper.COLUMN_CATEGORY_ID, DatabaseHelper.COLUMN_FIRST_NAME, DatabaseHelper.COLUMN_LAST_NAME, 
							DatabaseHelper.COLUMN_EMAIL, DatabaseHelper.COLUMN_PHONE, DatabaseHelper.COLUMN_DESCRIPTION, DatabaseHelper.COLUMN_ACTIVATION};
		Cursor cursor = sqliteDb.query(DatabaseHelper.TABLE_NAME_PROFESSIONALS_FAVORITES, columns, null, null, null, null, null);
		while(cursor.moveToNext()){
			Professional professional = initProfessional(cursor);
			professionalList.add(professional);
		}
		return professionalList;
	}
	
	private Professional initProfessional(Cursor cursor){
		Professional professional = new Professional();
		professional.setProfessionalId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_PROFESSIONAL_ID)));
		professional.setCategoryId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_CATEGORY_ID)));
		professional.setFirstName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_FIRST_NAME)));
		professional.setLastName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_LAST_NAME)));
		professional.setEmail(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EMAIL)));
		professional.setPhone(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PHONE)));
		professional.setDescription(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DESCRIPTION)));
		professional.setActivation(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ACTIVATION)));
		return professional;
	}
	
	private ContentValues professionalValues(Professional professional){
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.COLUMN_PROFESSIONAL_ID, professional.getProfessionalId());
		values.put(DatabaseHelper.COLUMN_CATEGORY_ID, professional.getCategoryId());
		values.put(DatabaseHelper.COLUMN_FIRST_NAME,professional.getFirstName());
		values.put(DatabaseHelper.COLUMN_LAST_NAME, professional.getLastName());
		values.put(DatabaseHelper.COLUMN_EMAIL,professional.getEmail());
		values.put(DatabaseHelper.COLUMN_PHONE,professional.getPhone());
		values.put(DatabaseHelper.COLUMN_DESCRIPTION,professional.getDescription());
		values.put(DatabaseHelper.COLUMN_ACTIVATION,professional.getActivation());
		return values;
	}


}
