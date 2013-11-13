package br.com.tw.pcmcliente;


import br.com.tw.adapter.AdapterListViewItens;
import br.com.tw.model.ExecutaActions;
import br.com.tw.model.ExibirMensagem;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
/**
 * 
 * @author cpd1
 * Classe responsável pela Activity Item(Produto).
 */
public class ItemActivity extends Activity 
{
   ListView list;
   AdapterListViewItens adapter;

   /**
    * 
    */
   @Override
   public void onCreate(Bundle savedInstanceState) 
   {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_itens);

       list=(ListView)findViewById(R.id.list);
       
       String[] itens = new String[8];
       itens[0] = "Brahma";
       itens[1] = "Skol";
       itens[2] = "Bohemia";
       itens[3] = "Original";
       itens[4] = "Antartica";
       itens[5] = "Heineken";
       itens[6] = "Stella Artois";
       itens[7] = "Skin";

       // Getting adapter by passing xml data ArrayList
       adapter=new AdapterListViewItens(this, itens);
       list.setAdapter(adapter);

       // Click event for single list row
       list.setOnItemClickListener(new OnItemClickListener() 
       {
    	   @Override    	   
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
    	   	{
    		   Object o = adapter.getItem(position);
    		   String item = o.toString();
    		   new ExibirMensagem("Cerveja",item, ItemActivity.this);
    		   
    		  if(list.getItemAtPosition(position).equals("Brahma"));
    		  	//new ExibirMensagem("Cerveja","Brahma", ItemActivity.this);
    		}    	   
       });
   }   
   @Override
	public boolean onCreateOptionsMenu(Menu menu) {				
		getMenuInflater().inflate(R.menu.menu_layouts, menu);
		return super.onCreateOptionsMenu(menu);
	}
   public boolean onOptionsItemSelected(MenuItem item)
	{		
		switch(item.getItemId())
		{			
		case R.id.action_valorTotal:
			new ExecutaActions(ItemActivity.this).openValorTotal();		
			return true;
		case R.id.action_chamarGarcom:
			new ExecutaActions(ItemActivity.this).chamarGarcom();				
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}   