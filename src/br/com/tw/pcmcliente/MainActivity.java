package br.com.tw.pcmcliente;

import org.json.JSONArray;
import org.json.JSONObject;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import br.com.tw.adapter.DbAdapter;
import br.com.tw.model.ExecutaActions;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * @author Weder Cabral Mendes
 * Classe utilizada para criar e controlar a tela inicial.
 */
public class MainActivity extends Activity {
	@SuppressWarnings("static-access")
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		RequestQueue queue = Volley.newRequestQueue(this);
		String url = "http://192.168.3.97:8080/WebServicePCM/itens/listarTodos";
		JsonObjectRequest jsObjRequest = new JsonObjectRequest
		(Request.Method.GET, url, null, new Response.Listener<JSONObject>() 
			{	/**
				 * 
				 * @param response(JSONObject)
				 */
				@Override								
				public void onResponse(JSONObject response) 
				{
					getNomeItem(response);
					findViewById(R.id.progressBarMain).setVisibility(View.GONE);
				}
			}, new Response.ErrorListener() 
			{
				@Override
				public void onErrorResponse(VolleyError error) {
	
				}
			}
		);
		queue.add(jsObjRequest);	
		
		DbAdapter db = new DbAdapter(getApplicationContext());
		db.open();
		String consulta = null;	
		Cursor cursor = db.consultarTodosItens();
		db.criarItem(5, "Espetinho");
		if(db.DATABASE_VERSION > 6){
			db.criarItem(6, "DATABASE");
		}
		if(cursor!= null){
			while(cursor.moveToNext()){
				consulta = cursor.getString(cursor.getColumnIndex(DbAdapter.COLUNA_DESCRICAO_ITENS));
				Log.w("CONSULTA BANCO:","Mostrando Itens: "+consulta);
				
			}			
		}else Log.w("CONSULTA BANCO:","Não retornou nada");
		
		db.close();
		cursor.close();
	}	

	/**
	 * Método utilizado para pegar apenas o nomes do itens do JSONArray e adcionar ao banco de dados de ITENS.
	 * @param json
	 */
	private void getNomeItem(JSONObject json)
	{    DbAdapter db = new DbAdapter(getApplicationContext());
		db.open();		
		int id = 0;
		try
		{
	    	JSONArray itens = json.getJSONArray("itens");
	        for(int i=0;i<itens.length();i++)
	        {
	        	Log.i("ITEM",itens.getJSONObject(i).getString("nomeItem"));  
	        	//db.criarItem(id, itens.getJSONObject(i).getString("nomeItem"));
	        	id++;
	        }
		}catch(Exception e)
		{
			e.printStackTrace();
    	}
		Cursor cursor = db.consultarTodosItens();	
		String consulta;
		if(cursor!= null){
			while(cursor.moveToNext()){
				consulta = cursor.getString(cursor.getColumnIndex(DbAdapter.COLUNA_DESCRICAO_ITENS));
				Log.w("CONSULTA BANCO:","Mostrando Itens: "+consulta);
			}			
		}else Log.w("CONSULTA BANCO:","Não retornou nada");
		
		db.close();
		cursor.close();
	}
	/**
	 * Método responsávl por controlar o botão "Cardápio", ao clicar nesse botão 
	 * a activity de itens abrirá.
	 * @param v(View)
	 */
	public void btnCardapio(View v)
	{
		Intent intent = new Intent(this, ItensActivity.class);
		startActivity(intent);
	}
	
	/**
	 * Método resposável por criar os menus do ActionBar, os itens que irá aparecer nesse menu
	 * estão definitos no arquivo R.menu.main.
	 * @param menu (Menu)
	 * @return retorna o menu criado com nossos itens(ACTIONS).
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}	

	/**
	 * Método responsável por controlar as ações dos itens(ACTIONS) do Menu.
	 * Quando o usuário clicar em um dos itens(ACTIONS), ele dispara uma ação. Que irá depender
	 * do item clicado.
	 * @param item (MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		
		switch(item.getItemId())
		{
		case R.id.action_valorTotal:
			new ExecutaActions(MainActivity.this).openValorTotal();
			return true;
		case R.id.action_chamarGarcom:
			new ExecutaActions(MainActivity.this).chamarGarcom();	
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
