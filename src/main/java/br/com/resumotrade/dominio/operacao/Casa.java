package br.com.resumotrade.dominio.operacao;

public enum Casa {
	BETFAIR,
	BET365,
	BET188,
	BETMAIS;

	public static Casa buscarPorCasa(String casa) {
		if (casa.equals("BETFAIR"))
			return BETFAIR;
		return BET365;
	}
}
