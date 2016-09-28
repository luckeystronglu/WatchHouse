package com.qf.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CityNameSQLiteOpenHelper extends SQLiteOpenHelper {
	private static final int VERSION = 0X001;
	private static final String SQL_NAME = "citylist.db";
	private static final String CREATE_TABLE_CITYNAME = "create table citynames(_id integer primary key,cityid,cityname,type)";


	public CityNameSQLiteOpenHelper(Context context) {
		super(context, SQL_NAME, null, VERSION);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_TABLE_CITYNAME);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
