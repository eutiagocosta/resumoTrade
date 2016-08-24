package br.com.resumotrade.dominio.mercado;

public enum Esporte {
	FUTEBOL, 
	BASQUETE,
	VOLEI,
	TENIS,
	LUTAS,
	CORRIDA_DE_CAVALOS,
	ESPORTES_A_MOTOR;

	public static Esporte buscarEsporte(String esporte) {
		switch (esporte.toUpperCase()) {
			case "FUTEBOL":
				return Esporte.FUTEBOL;
			case "BASQUETE":
				return Esporte.BASQUETE;
			case "VÔLEI":
				return Esporte.VOLEI;
			case "TÊNIS":
				return Esporte.TENIS;
			case "LUTAS":
				return Esporte.LUTAS;
			case "CORRIDAS DE CAVALOS":
				return Esporte.CORRIDA_DE_CAVALOS;
			case "ESPORTES A MOTOR":
				return Esporte.ESPORTES_A_MOTOR;
			default:
				throw new RuntimeException("ESPORTE INVÁLIDO");
			}
	}
}
