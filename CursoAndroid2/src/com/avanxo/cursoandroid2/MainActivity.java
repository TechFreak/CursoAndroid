package com.avanxo.cursoandroid2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final float LIBRAS_POR_KILO = 2.2046f;

	private EditText editTextValorKilos;
	private EditText editTextValorLibras;
	private Button buttonConvertirALibras;
	private Button buttonConvertirAKilos;
	private TextView textViewResultado;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Obtener referencias a todos los componentes gráficos que necesitamos
		editTextValorKilos = (EditText) findViewById(R.id.editTextValorKilos);
		editTextValorLibras = (EditText) findViewById(R.id.editTextValorLibras);
		buttonConvertirALibras = (Button) findViewById(R.id.buttonConvertirALibras);
		buttonConvertirAKilos = (Button) findViewById(R.id.buttonConvertirAKilos);
		textViewResultado = (TextView) findViewById(R.id.textViewResultado);
		
		if(savedInstanceState!=null){
			textViewResultado.setText(savedInstanceState.getString("resultado"));
		}

		// definir que hacer cuando el usuario hace click en los botones
		buttonConvertirAKilos.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// Limpio el valor presente en la casilla de kilos
				editTextValorKilos.setText("");

				// Obtengo el valor que el usuario ingreso como un string
				String librasString = editTextValorLibras.getText().toString();

				// convierto el valor a un float y realizo la conversión
				float librasFloat = Float.parseFloat(librasString);
				float kilosFloat = librasFloat / LIBRAS_POR_KILO;
				textViewResultado.setText(getString(
						R.string.resultado_libras_a_kilos, librasFloat,
						kilosFloat));

			}
		});

		buttonConvertirALibras.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// Limpio el valor presente en la casilla de libras
				editTextValorLibras.setText("");

				// Obtengo el valor que el usuario ingreso como un string
				String kilosString = editTextValorKilos.getText().toString();

				// convierto el valor a un float y realizo la conversión
				float kilosFloat = Float.parseFloat(kilosString);
				float librasFloat = kilosFloat * LIBRAS_POR_KILO;
				textViewResultado.setText(getString(
						R.string.resultado_kilos_a_libras, kilosFloat,
						librasFloat));

			}
		});
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString("resultado", textViewResultado.getText().toString());
		
	}

}
