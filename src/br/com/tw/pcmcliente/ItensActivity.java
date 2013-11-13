package br.com.tw.pcmcliente;

import org.json.JSONArray;
import org.json.JSONObject;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import br.com.tw.adapter.AdapterListViewItem;
import br.com.tw.model.ExecutaActions;
import br.com.tw.model.ExibirMensagem;

public class ItensActivity extends Activity{

	ListView listViewItem;
	AdapterListViewItem adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item);
		
		listViewItem = (ListView) findViewById(R.id.listItem);
		String [] itens = new String[] {"Cerveja", "Doces", "Vinhos", "Frios", "Comida", "Caldos"};
		
		adapter = new AdapterListViewItem(this, itens);
		listViewItem.setAdapter(adapter);
		
		listViewItem.setOnItemClickListener(new OnItemClickListener()
		{	    	   	    	   
	    	   @Override    	   
				public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
	    	   	{
	    		   TextView nomeItem;
	    		   nomeItem = (TextView) view.findViewById(R.id.texteViewNomeItem);   		   
	    		   
	    		   
	    		  if(listViewItem.getItemAtPosition(position).toString().equals("0")){	    			  
	    			  Intent intent = new Intent(ItensActivity.this, ItemActivity.class);
		    		  startActivity(intent);	    				
	    		  }else
	    			  new ExibirMensagem("TESTE", nomeItem.getText().toString(), ItensActivity.this);
	    		  
	    		}	    	   
	    	   
		});
		
		RequestQueue queue = Volley.newRequestQueue(this);
		//String url = "http://pipes.yahooapis.com/pipes/pipe.run?_id=giWz8Vc33BG6rQEQo_NLYQ&_render=json";
		String url = "http://192.168.1.100/WebServicePCM/produtos/Comida";
		JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				Log.i("TESTE", response.toString());
				parseJSON(response);
				findViewById(R.id.progressBar1).setVisibility(View.GONE);
				Log.i("TESTE", "TESTE");
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
        	JSONObject value = json.getJSONObject("");
            JSONArray items = value.getJSONArray("nomeProduto");
            for(int i=0;i<items.length();i++){

                    JSONObject item = items.getJSONObject(i);
                    new ExibirMensagem("TESTE", item.optString("nomeProduto"), ItensActivity.this);                                   
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }


    }
	 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
				
		getMenuInflater().inflate(R.menu.menu_layouts, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		
		switch(item.getItemId())
		{
		case R.id.action_valorTotal:
			new ExecutaActions(ItensActivity.this).openValorTotal();			
			return true;
		case R.id.action_chamarGarcom:
			new ExecutaActions(ItensActivity.this).chamarGarcom();	
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
