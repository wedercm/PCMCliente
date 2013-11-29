package br.com.tw.adapter;

import java.util.ArrayList;

import br.com.tw.pcmcliente.R;
import com.android.volley.toolbox.ImageLoader;
 
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterListViewItem extends BaseAdapter {
	private Activity activity;
    private ArrayList<String> data;
    private String[] datas;
    private static LayoutInflater inflater=null;
 
    public AdapterListViewItem(Activity a, String[] itens) {
        activity = a;
        datas=itens;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public AdapterListViewItem(Activity a, ArrayList<String> itens) {
        activity = a;
        data=itens;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
 
    public int getCount() {
        return data.size();
    }
 
    public Object getItem(int position) {
        return position;
    }
 
    public long getItemId(int position) {
        return position;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
        	vi = inflater.inflate(R.layout.list_row_item, null);
 
        TextView nomeItem = (TextView)vi.findViewById(R.id.texteViewNomeItem);
        
        nomeItem.setText(data.get(position));  
       
        return vi;
    }
}
