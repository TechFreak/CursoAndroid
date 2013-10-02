package com.avanxo.cursoandroid3a;

import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.widget.Toast;

import com.avanxo.cursoandroid3a.db.ConversionContract.ResultadoConversion;
import com.avanxo.cursoandroid3a.db.ConversionesDbHelper;
import com.avanxo.cursoandroid3b.R;

public class MainActivity extends FragmentActivity {

	private String mResultadoActual;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	public void updateResult(String resultado) {

		// Guardo el resultado actual como el resultado anterior en el archivo
		// de preferencias

		// Obtengo una referencia a las preferencias
		if (!TextUtils.isEmpty(mResultadoActual)) {
			SharedPreferences preferencias = getPreferences(Context.MODE_PRIVATE);
			Editor editor = preferencias.edit();
			editor.putString("ultimo_resultado", mResultadoActual);
			editor.commit();
		}

		// Actualizo el valor de resultado actual
		mResultadoActual = resultado;

		// Obtengo una referencia al fragment manager
		FragmentManager fragmentManager = getSupportFragmentManager();

		// Inicio una nueva transaccion

		FragmentTransaction transaccion = fragmentManager.beginTransaction();

		// creo un nuevo fragmento resultado
		Fragment fragmentoResultado = ResultadoConversionFragment
				.newInstance(resultado);

		// Encolo la accion de reemplazar o añadir el nuevo fragmento
		transaccion.replace(R.id.frameLayoutContenedor, fragmentoResultado);

		// ejecuto la transaccion
		transaccion.commit();

		// Escribir resultado en una base de datos
		guardarResultado();

	}

	private void guardarResultado() {
		if (!TextUtils.isEmpty(mResultadoActual)) {
			// Lo ideal es hacer esta operación en otro hilo para evitar que se
			// bloquee la interfaz de usuario,
			// por ahora para efectos demostrativos la haremos en el hilo
			// principal
			ConversionesDbHelper dbHelper = new ConversionesDbHelper(this);
			SQLiteDatabase db = dbHelper.getWritableDatabase();

			// creamos un objecto content values con los datos que queremos
			// ingresar

			ContentValues valores = new ContentValues();
			// El resultado de la conveersión
			valores.put(ResultadoConversion.NOMBRE_COLUMNA_TEXTO,
					mResultadoActual);
			// La fecha actual
			Date ahora = new Date();
			long ahoraMilisegundos = ahora.getTime();
			valores.put(ResultadoConversion.NOMBRE_COLUMNA_FECHA_HORA,
					ahoraMilisegundos);

			long idInternoNuevoResultado = db.insert(
					ResultadoConversion.NOMBRE_TABLA, null, valores);

			dbHelper.close();
			// creemos una notificación para verificar que el resultado fue
			// guardado
			Toast.makeText(
					this,
					"El resultado fue guardado exitosamente id:"
							+ idInternoNuevoResultado, Toast.LENGTH_LONG)
					.show();

		}

	}

	public void mostrarActividadResultados() {

		// Las actividades deben ser añadidas SIEMPRE al archivo
		// AndroidManifest.xml para poder lanzarlas
		// para lanzar una nueva actividad primero creamos un intent
		Intent intentActividadResultados = new Intent(this,
				ResultadosAnterioresActivity.class);

		// Llamamos startActivity para que el sistema busque la actidida
		// indicada y la lanze
		startActivity(intentActividadResultados);
	}
}
