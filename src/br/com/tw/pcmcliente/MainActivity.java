package br.com.tw.pcmcliente;

import br.com.tw.model.ExecutaActions;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
