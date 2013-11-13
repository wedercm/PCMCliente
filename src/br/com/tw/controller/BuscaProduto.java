package br.com.tw.controller;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import br.com.tw.model.ExibirMensagem;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

public class BuscaProduto {
	private Context contexto;
	private String url;
	private RequestQueue queue;
	
	public BuscaProduto(String url, RequestQueue queue, Context contexto){
		this.url = url;
		Log.i("URL", url);
		this.queue = queue;
		this.contexto = contexto;
		getRequeste();
		
	}	
	public void getRequeste()
	{
		JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
	
			@Override
			public void onResponse(JSONObject response) {
				Log.i("TESTE", response.toString());
				parseJSON(response);
				
			}
		}, new Response.ErrorListener() {
	
			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
	
			}
		});
	
		queue.add(jsObjRequest);
	}
	private void parseJSON(JSONObject json){
    try{
    	JSONArray value = json.getJSONArray("produtoNome");            
        for(int i=0;i<value.length();i++){

                JSONObject item = value.getJSONObject(i);
                new ExibirMensagem("TESTE", item.optString("nomeProduto"), contexto);                                   
        }
    }
    catch(Exception e){
        e.printStackTrace();
    }


}
	

}
