package com.avanxo.cursoandroid3a;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.ListAdapter;

import com.avanxo.cursoandroid3a.db.ConversionContract.ResultadoConversion;
import com.avanxo.cursoandroid3a.db.ConversionesDbHelper;
import com.avanxo.cursoandroid3b.R;

/*
 * Esta Activity se encarga de mostrar los resultados de conversiones anteriores
 * se cargan todos los resultados guardados en la base de datos y se muestran en una lista
 * 
 * Esta actividad hereda de ListActivity que es simplemente una Activity que tiene como layout una lista (ListView)
 * 
 * 
 * Normalmente lo recomendado para cargar datos de una base de datos o de un ContentProvider es un Loader, 
 * pero para mostrar el proceso completo lo hacemos accedieno directamente a la base de datos 
 */

public class ResultadosAnterioresActivity extends ListActivity {

	private Cursor mResultados;
	private ConversionesDbHelper mDbHelper;
	private ListAdapter mCursorAdapter;

	// Primero construimos un array con todas las columnas que queremos
	// tener en los resultados de nuestra query
	private static final String[] COLUMNAS = { ResultadoConversion._ID,
			ResultadoConversion.NOMBRE_COLUMNA_FECHA_HORA,
			ResultadoConversion.NOMBRE_COLUMNA_TEXTO };

	// Indicamos como queremos ordenar los campos
	private static final String ORDENAR_POR = ResultadoConversion.NOMBRE_COLUMNA_FECHA_HORA
			+ " ASC";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// hacer el query para obtener todos los resultados

		mDbHelper = new ConversionesDbHelper(this);

	}

	@Override
	public void onResume() {
		super.onResume();
		SQLiteDatabase db = mDbHelper.getWritableDatabase();
		mResultados = db.query(ResultadoConversion.NOMBRE_TABLA, COLUMNAS, // Las
																			// columnas
																			// a
																			// obtener
				null, // Las columnas para el "WHERE"
				null, // Los valores para el "WHERE"
				null, // No agrupar las filas
				null, // no filtrar por grupos de filas
				ORDENAR_POR); // El orden

		// Creamos un adapter para mostrar cada una de las filas de resultados

		// Para hacer esto definimos que columnas vamos a mostrar

		String[] columnasAmostrar = { ResultadoConversion.NOMBRE_COLUMNA_TEXTO };
		
		mCursorAdapter = new SimpleCursorAdapter(this,
				R.layout.registro_resultado, mResultados, columnasAmostrar,
				new int[] { R.id.textViewResultado }, 0);

		// Configuramos la lista para que muestre los contenidos del adapter
		getListView().setAdapter(mCursorAdapter);
	}

	@Override
	public void onPause() {
		super.onPause();
		mCursorAdapter = null;
		getListView().setAdapter(null);
		if (mResultados != null) {
			mResultados.close();

		}
	}
}
