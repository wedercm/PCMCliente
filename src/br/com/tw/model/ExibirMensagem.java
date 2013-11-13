package br.com.tw.model;

import android.app.AlertDialog;
import android.content.Context;

public class ExibirMensagem {
	
	public ExibirMensagem(String titulo, String texto, Context contexto){
		AlertDialog.Builder mensagem  = new AlertDialog.Builder(contexto);
		mensagem.setTitle(titulo);
		mensagem.setMessage(texto);
		mensagem.setNeutralButton("OK", null);
		mensagem.show();
		
	}

}
