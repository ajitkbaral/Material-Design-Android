package com.pagodalabs.materialdesign.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
		//constant
		private static final String DATABASE_NAME = "zyami.db";
		public static final String COLUMN_KEY_ID = "_id";//used in all table
		public static final String COLUMN_CATEGORY_ID = "category_id";//used in category and professionals tables
		private static final int DATABASE_VERSION = 27;
		
		
		//professionals
		public static final String TABLE_NAME_PROFESSIONALS = "professionals";
		public static final String COLUMN_PROFESSIONAL_ID = "professional_id";
		public static final String COLUMN_FIRST_NAME = "first_name";
		public static final String COLUMN_LAST_NAME = "last_name";
		public static final String COLUMN_EMAIL = "email";
		public static final String COLUMN_PHONE = "phone";
		public static final String COLUMN_DESCRIPTION = "description";
		public static final String COLUMN_ACTIVATION = "activation"; 
		public static final String CREATE_DATABASE_TABLE_PROFESSIONALS = "CREATE TABLE "
				+TABLE_NAME_PROFESSIONALS+"( "+COLUMN_KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
				+COLUMN_PROFESSIONAL_ID+" INTEGER, "+COLUMN_CATEGORY_ID+" INTEGER, "+COLUMN_FIRST_NAME+" VARCHAR(255), "+COLUMN_LAST_NAME+" VARCHAR(255), "
				+COLUMN_EMAIL+" VARCHAR(255), "+COLUMN_PHONE+" VARCHAR(255), "+COLUMN_DESCRIPTION+" TEXT, "+COLUMN_ACTIVATION+" INT);";
		private static final String DROP_DATABASE_TABLE_PROFESSIONALS = "DROP TABLE IF EXISTS "
						+ TABLE_NAME_PROFESSIONALS;
		
		//professionalsJson
		public static final String TABLE_NAME_JSON_PROFESSIONALS = "json_professionals";
		public static final String COLUMN_PROFESSIONALS_JSON = "professionals_json";
		private static final String CREATE_DATABASE_TABLE_JSON_PROFESSIONALS = "CREATE TABLE "+TABLE_NAME_JSON_PROFESSIONALS+"("+COLUMN_KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
				+COLUMN_CATEGORY_ID+ " INTEGER, "+COLUMN_PROFESSIONALS_JSON+" TEXT);"; 
		private static final String DROP_DATABASE_TABLE_JSON_PROFESSIONALS = "DROP TABLE IF EXISTS "
				+ TABLE_NAME_JSON_PROFESSIONALS;
		
		
		//favorites professional
		public static final String TABLE_NAME_PROFESSIONALS_FAVORITES = "professional_favorites";
		public static final String CREATE_DATABASE_TABLE_PROFESSIONAL_FAVORITES = "CREATE TABLE "
				+TABLE_NAME_PROFESSIONALS_FAVORITES+"( "+COLUMN_KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
				+COLUMN_PROFESSIONAL_ID+" INTEGER, "+COLUMN_CATEGORY_ID+" INTEGER, "+COLUMN_FIRST_NAME+" VARCHAR(255), "+COLUMN_LAST_NAME+" VARCHAR(255), "
				+COLUMN_EMAIL+" VARCHAR(255), "+COLUMN_PHONE+" VARCHAR(255), "+COLUMN_DESCRIPTION+" TEXT, "+COLUMN_ACTIVATION+" INT);";
		private static final String DROP_DATABASE_TABLE_PROFESSIONALS_FAVORITES = "DROP TABLE IF EXISTS "
				+ TABLE_NAME_PROFESSIONALS_FAVORITES;
		
		
		//users
		public static final String TABLE_NAME_USERS = "users";
		public static final String COLUMN_USER_USER_ID = "user_id";
		public static final String COLUMN_USER_USERNAME = "username";
		public static final String COLUMN_USER_PASSWORD = "password";
		public static final String COLUMN_USER_TOKEN = "token";
		public static final String COLUMN_USER_TOKEN_BEGIN = "token_begin";
		public static final String COLUMN_USER_TOKEN_END = "token_end";
		public static final String CREATE_DATABASE_TABLE_USERS = "CREATE TABLE "
				+TABLE_NAME_USERS+"( "+COLUMN_KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +COLUMN_USER_USER_ID+" INTEGER, "
				+COLUMN_USER_USERNAME+" VARCHAR(255), "+COLUMN_USER_PASSWORD+" VARCHAR(255), "+COLUMN_USER_TOKEN+" VARCHAR(255), "+COLUMN_USER_TOKEN_BEGIN
				+" VARCHAR(255), "+COLUMN_USER_TOKEN_END+" VARCHAR(255));";
		
		private static final String DROP_DATABASE_TABLE_USERS = "DROP TABLE IF EXISTS "
				+ TABLE_NAME_USERS;
		
		//user_professional
		public static final String TABLE_NAME_USER_PROFESSIONAL = "user_professional";
		public static final String CREATE_DATABASE_TABLE_USER_PROFESSIONAL = "CREATE TABLE "
				+TABLE_NAME_USER_PROFESSIONAL+"( "+COLUMN_KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
				+COLUMN_PROFESSIONAL_ID+" INTEGER, "+COLUMN_CATEGORY_ID+" INTEGER, "+COLUMN_FIRST_NAME+" VARCHAR(255), "+COLUMN_LAST_NAME+" VARCHAR(255), "
				+COLUMN_EMAIL+" VARCHAR(255), "+COLUMN_PHONE+" VARCHAR(255), "+COLUMN_DESCRIPTION+" TEXT, "+COLUMN_ACTIVATION+" INT);";
		private static final String DROP_DATABASE_TABLE_USER_PROFESSIONAL = "DROP TABLE IF EXISTS "
						+ TABLE_NAME_USER_PROFESSIONAL;
		
		//jobsLiveFeed
		public static final String TABLE_NAME_JSON_JOBS_LIVE_FEED = "jobs_live_feed";
		public static final String COLUMN_LIVE_FEED_JSON = "live_feed_json";
		private static final String CREATE_DATABASE_TABLE_JSON_JOB_LIVE_FEED = "CREATE TABLE "+TABLE_NAME_JSON_JOBS_LIVE_FEED+"("+COLUMN_KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
				+COLUMN_LIVE_FEED_JSON+" TEXT);"; 
		private static final String DROP_DATABASE_TABLE_JSON_JOBS_LIVE_FEED = "DROP TABLE IF EXISTS "
				+ TABLE_NAME_JSON_JOBS_LIVE_FEED;
		
		//myJobsPage
		public static final String TABLE_NAME_JSON_JOBS_MY_POSTS = "jobs_my_posts";
		public static final String COLUMN_MY_POSTS_JSON = "my_post_json";
		private static final String CREATE_DATABASE_TABLE_JSON_JOB_MY_POSTS = "CREATE TABLE "+TABLE_NAME_JSON_JOBS_MY_POSTS+"("+COLUMN_KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
				+COLUMN_MY_POSTS_JSON+" TEXT);"; 
		private static final String DROP_DATABASE_TABLE_JSON_JOBS_MY_POSTS = "DROP TABLE IF EXISTS "
				+ TABLE_NAME_JSON_JOBS_MY_POSTS;
		
		
		
		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(CREATE_DATABASE_TABLE_PROFESSIONALS);
			db.execSQL(CREATE_DATABASE_TABLE_JSON_PROFESSIONALS);
			db.execSQL(CREATE_DATABASE_TABLE_PROFESSIONAL_FAVORITES);
			db.execSQL(CREATE_DATABASE_TABLE_USERS);
			db.execSQL(CREATE_DATABASE_TABLE_USER_PROFESSIONAL);
			db.execSQL(CREATE_DATABASE_TABLE_JSON_JOB_LIVE_FEED);
			db.execSQL(CREATE_DATABASE_TABLE_JSON_JOB_MY_POSTS);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL(DROP_DATABASE_TABLE_PROFESSIONALS);
			db.execSQL(DROP_DATABASE_TABLE_JSON_PROFESSIONALS);
			db.execSQL(DROP_DATABASE_TABLE_PROFESSIONALS_FAVORITES);
			db.execSQL(DROP_DATABASE_TABLE_USERS);
			db.execSQL(DROP_DATABASE_TABLE_USER_PROFESSIONAL);
			db.execSQL(DROP_DATABASE_TABLE_JSON_JOBS_LIVE_FEED);
			db.execSQL(DROP_DATABASE_TABLE_JSON_JOBS_MY_POSTS);
			onCreate(db);
		}

	

}
