package com.avanxo.cursoandroid3a.db;

import android.provider.BaseColumns;

public class ConversionContract {

	public ConversionContract() {
	}
	
	public static abstract class ResultadoConversion implements BaseColumns{
		public static final String NOMBRE_TABLA = "resultado";
		public static final String NOMBRE_COLUMNA_FECHA_HORA = "fechaHora";
		public static final String NOMBRE_COLUMNA_TEXTO = "texto";
	}
}
