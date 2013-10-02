package com.avanxo.cursoandroid3a.db;

import com.avanxo.cursoandroid3a.db.ConversionContract.ResultadoConversion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConversionesDbHelper extends SQLiteOpenHelper {

	public static final int VERSION_DB = 1;
	public static final String NOMBRE_DB = "conversiones.db";

	private static final String TIPO_TEXTO = " TEXT";
	private static final String TIPO_NUMERO = " INTEGER";
	private static final String SEP_COMA = ",";
	private static final String SQL_CREAR_TABLA = "CREATE TABLE "
			+ ResultadoConversion.NOMBRE_TABLA + " ("  
			+ ResultadoConversion._ID	+ " INTEGER PRIMARY KEY," 
			+ ResultadoConversion.NOMBRE_COLUMNA_FECHA_HORA	+ TIPO_NUMERO + SEP_COMA 
			+ ResultadoConversion.NOMBRE_COLUMNA_TEXTO + TIPO_TEXTO 
			+ " ) ";
	
	private static final String SQL_BORRAR_TABLA =  "DROP TABLE IF EXISTS " + ResultadoConversion.NOMBRE_TABLA;

	public ConversionesDbHelper(Context context) {
		super(context, NOMBRE_DB, null, VERSION_DB);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREAR_TABLA);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(SQL_BORRAR_TABLA);
		onCreate(db);
	}

}
