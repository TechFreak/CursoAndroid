package com.avanxo.cursoandroid3;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ResultadoConversionFragment extends Fragment {

	public static final String CONVERSION_RESULT_TAG = "conversion_result_tag";

	private TextView textViewResultado;
	private TextView textViewResultadoAnterior;

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
		SharedPreferences preferencias = activity.getPreferences(Context.MODE_PRIVATE);
		String ultimoResultado = preferencias.getString("ultimo_resultado", "");
		textViewResultadoAnterior.setText(ultimoResultado);
	}
}
