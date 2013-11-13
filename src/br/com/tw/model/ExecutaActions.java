package br.com.tw.model;

import android.content.Context;

public class ExecutaActions {
	private Context contexto;
	public ExecutaActions(Context contexto){
		this.contexto = contexto;
	}
	
	public void openValorTotal()
	{
		 new ExibirMensagem("Valor Total", "Aqui vai o valor total!", contexto);
	}
	public void chamarGarcom(){
		 new ExibirMensagem("Chamar Garçom", "Opção chamar garçom!", contexto);
	}	
}
