package com.avanxo.cursoandroid3c;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * 
 * En este ejemplo aprenderemos a usar listviews, Loaders y ContentProviders
 * mostrando una lista de los contactos del telefono
 * 
 * Para poder hacer esto debemos primero añadir el permiso para leer contactos
 * al AndroidManifest.xml
 * 
 * 
 */

public class MainActivity extends FragmentActivity implements
		LoaderCallbacks<Cursor> {

	private ListView listViewContactos;
	private ListAdapter mAdapter;

	// Las columnas disponibles para el ContentProvider de Contactos esta en una
	// clase llamada ContactsContract
	private static final String[] COLUMNAS = {
			ContactsContract.CommonDataKinds.Phone._ID,
			ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
			ContactsContract.CommonDataKinds.Phone.NUMBER };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// inicializo la referencia a la lista
		listViewContactos = (ListView) findViewById(R.id.listViewContactos);

		// con esta llamada le decimos al loader manager que estamos listos para
		// cargar los datos

		// el loader manager automaitcamente crea un nuevo hilo para acceder a
		// la base de datos sin bloquear el hilo de la interfaz gráfica
		getSupportLoaderManager().initLoader(0, null, this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle arguments) {
		// En este metodo indicamos que datos queremos cargar

		// El content uri indica cual ContentProvider vamos a usa
		Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

		CursorLoader loader = new CursorLoader(this, uri, // Indica el content
															// provider a usar
				COLUMNAS, // las columnas que quermos consultar
				null, // La clausula WHERE
				null, // los argumentos de la clausula WHERE
				null); // EL orden
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

		// Creo el adapter con los nuevos datos
		mAdapter = new SimpleCursorAdapter(this, R.layout.registro_contacto,
				cursor, new String[] {
						ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
						ContactsContract.CommonDataKinds.Phone.NUMBER },
				new int[] { R.id.textViewNombre, R.id.textViewTelefono }, 0);

		listViewContactos.setAdapter(mAdapter);

	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {

	}

}
