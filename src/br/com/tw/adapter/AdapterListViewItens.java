package br.com.tw.adapter;

import br.com.tw.pcmcliente.R;
import com.android.volley.toolbox.ImageLoader;
 
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterListViewItens extends BaseAdapter {
	private Activity activity;
    private String[]data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader; 
 
    public AdapterListViewItens(Activity a, String[] itens) {
        activity = a;
        data=itens;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        imageLoader=new ImageLoader(activity.getApplicationContext());
    }
 
    public int getCount() {
        return data.length;
    }
 
    public Object getItem(int position) {
        return position;
    }
 
    public long getItemId(int position) {
        return position;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        ViewHolder holder;
        if(convertView==null){
	        vi = inflater.inflate(R.layout.list_row, null);    	
	        holder = new ViewHolder();
	        
	        holder.title = (TextView)vi.findViewById(R.id.title); 
	        holder.artist = (TextView)vi.findViewById(R.id.artist);
	        //holder.thumb_image=(ImageView)vi.findViewById(R.id.list_image);
	        vi.setTag(holder);
    	}else{
    		holder = (ViewHolder)convertView.getTag();
    	}
        holder.title.setText(data[position]);
        holder.artist.setText(data[position]);
        
        //imageLoader.DisplayImage(song.get(CustomizedListView.KEY_THUMB_URL), thumb_image);
        return vi;
    }
    static class ViewHolder{
    	ImageView thumb_image;
    	TextView title;
    	TextView artist;
    }
}
