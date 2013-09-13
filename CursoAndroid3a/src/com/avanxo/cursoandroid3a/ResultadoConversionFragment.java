package com.avanxo.cursoandroid3a;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResultadoConversionFragment extends Fragment {

	public static final String CONVERSION_RESULT_TAG = "conversion_result_tag";

	private TextView textViewResultado;
	private TextView textViewResultadoAnterior;
	private Button buttonExportar;

	public static ResultadoConversionFragment newInstance(String result) {
		ResultadoConversionFragment f = new ResultadoConversionFragment();
		Bundle args = new Bundle();
		args.putString(CONVERSION_RESULT_TAG, result);
		f.setArguments(args);
		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_resultado_conversion,
				container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		// inicializo la referencia al textview
		textViewResultado = (TextView) getView().findViewById(
				R.id.textViewResultado);
		textViewResultadoAnterior = (TextView) getView().findViewById(
				R.id.textViewResultadoAnterior);

		// Pongo el texto del resultado, obteniendolo de los argumentos del
		// Fragment
		textViewResultado.setText(getArguments().getString(
				CONVERSION_RESULT_TAG));

		// Miro si hay algun resultado guardado
		Activity activity = getActivity();
		SharedPreferences preferencias = activity
				.getPreferences(Context.MODE_PRIVATE);
		String ultimoResultado = preferencias.getString("ultimo_resultado", "");
		textViewResultadoAnterior.setText(ultimoResultado);

		buttonExportar = (Button) getView().findViewById(R.id.buttonExportar);

		buttonExportar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (isExternalStorageWritable()) {
					// Crear la carpeta
					File carpeta = new File(
							Environment
									.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
							"conversiones");
					carpeta.mkdirs();

					// crear el archivo
					File archivo = new File(carpeta, "conversion.txt");
					String ruta = archivo.getAbsolutePath();
					try {
						FileOutputStream f = new FileOutputStream(archivo);
						PrintWriter pw = new PrintWriter(f);

						// obtener resultado
						String resultado = getArguments().getString(
								CONVERSION_RESULT_TAG);
						pw.println(resultado);
						pw.println("\n");
						pw.flush();
						pw.close();
						f.close();

						Toast.makeText(
								getActivity(),
								"El archivo fue almacenado correctamente en "
										+ ruta, Toast.LENGTH_LONG).show();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

				} else {
					Toast.makeText(getActivity(),
							"No esta disponible el almacenamiento externo",
							Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	/* Checks if external storage is available for read and write */
	public boolean isExternalStorageWritable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			return true;
		}
		return false;
	}
}
