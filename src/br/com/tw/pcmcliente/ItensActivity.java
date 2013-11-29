package br.com.tw.pcmcliente;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
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
import br.com.tw.adapter.DbAdapter;
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
		ArrayList<String> itens = new ArrayList<String>(); 
		DbAdapter db = new DbAdapter(getApplicationContext());
		db.open();
		
		Cursor cursor = db.consultarTodosItens();
		String consulta;
		if(cursor != null){
			while(cursor.moveToNext()){
				consulta = cursor.getString(cursor.getColumnIndex(DbAdapter.COLUNA_DESCRICAO_ITENS));
				itens.add(consulta);
			}
		}
		db.close();
		cursor.close();
		
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
