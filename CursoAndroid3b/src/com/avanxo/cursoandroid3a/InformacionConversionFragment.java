package com.avanxo.cursoandroid3a;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.avanxo.cursoandroid3b.R;

public class InformacionConversionFragment extends Fragment {

	private static final float LIBRAS_POR_KILO = 2.2046f;

	private EditText editTextValorKilos;
	private EditText editTextValorLibras;
	private Button buttonConvertirALibras;
	private Button buttonConvertirAKilos;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_informacion_conversion,
				container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		// Obtener referencias a todos los componentes gráficos que necesitamos
		editTextValorKilos = (EditText) getView().findViewById(
				R.id.editTextValorKilos);
		editTextValorLibras = (EditText) getView().findViewById(
				R.id.editTextValorLibras);
		buttonConvertirALibras = (Button) getView().findViewById(
				R.id.buttonConvertirALibras);
		buttonConvertirAKilos = (Button) getView().findViewById(
				R.id.buttonConvertirAKilos);

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

				((MainActivity) getActivity()).updateResult(getString(
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
				((MainActivity) getActivity()).updateResult(getString(
						R.string.resultado_kilos_a_libras, kilosFloat,
						librasFloat));

			}
		});
	}

}
