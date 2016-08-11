package br.com.resumotrade.dominio.mercado;

public enum Esporte {
	FUTEBOL, 
	BASQUETE;

	public static Esporte buscarEsporte(String esporte) {
		if (esporte.equals("FUTEBOL")){
			return FUTEBOL;
		}
		return BASQUETE;
	}
}
