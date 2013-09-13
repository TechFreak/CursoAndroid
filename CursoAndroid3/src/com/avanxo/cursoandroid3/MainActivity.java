package com.avanxo.cursoandroid3;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

public class MainActivity extends FragmentActivity {

	private String mResultadoActual;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	public void updateResult(String resultado) {
		
		// Guardo el resultado actual como el resultado anterior en el archivo de preferencias
		
		// Obtengo una referencia a las preferencias
		if(!TextUtils.isEmpty(mResultadoActual)){
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
		Fragment fragmentoResultado = ResultadoConversionFragment.newInstance(resultado);
		
		// Encolo la accion de reemplazar o añadir el nuevo fragmento
		transaccion.replace(R.id.frameLayoutContenedor, fragmentoResultado);
		
		// ejecuto la transaccion
		transaccion.commit();

	}
}
