package com.avanxo.cursoandroid2;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	public void updateResult(String result) {
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.frameLayoutContenedor,
						ResultadoConversionFragment.newInstance(result))
				.commit();
	}

}
