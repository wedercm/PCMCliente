package br.com.tw.pcmcliente;

import org.json.JSONArray;
import org.json.JSONObject;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import br.com.tw.model.ExecutaActions;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		RequestQueue queue = Volley.newRequestQueue(this);
		String url = "http://192.168.3.97:8080/WebServicePCM/itens/listarTodos";
		JsonObjectRequest jsObjRequest = new JsonObjectRequest
		(Request.Method.GET, url, null, new Response.Listener<JSONObject>() 
			{

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
	}	

	private void getNomeItem(JSONObject json)
	{    
		try
		{
	    	JSONArray itens = json.getJSONArray("itens");
	        for(int i=0;i<itens.length();i++)
	        {
	        		Log.i("ITEM",itens.getJSONObject(i).getString("nomeItem"));        		
	        }
		}catch(Exception e)
		{
			e.printStackTrace();
    	}
	}
	
	
	
	public void btnCardapio(View v)
	{
		Intent intent = new Intent(this, ItensActivity.class);
		startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}	
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
