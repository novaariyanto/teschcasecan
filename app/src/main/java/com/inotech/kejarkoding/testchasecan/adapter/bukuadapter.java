package com.inotech.kejarkoding.testchasecan.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.inotech.kejarkoding.testchasecan.R;
import com.inotech.kejarkoding.testchasecan.entity.ValBuku;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class bukuadapter extends RecyclerView.Adapter<bukuadapter.ViewHolder> {
    String base_url = "https://spooker.000webhostapp.com/api/perpustakaan/CoverBuku/";
    private List<ValBuku> item;
    Context context;
    public bukuadapter(Context context,List<ValBuku> items){
        Log.d("123","Recycleadapter");
        this.item = items;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.item_buku,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.texttitle.setText(item.get(position).getJudul());
    Picasso.get()
            .load(base_url+item.get(position).getGambar())
            .into(holder.imageView);
    }
    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView texttitle;
        private ImageView imageView;
        public ViewHolder(View view) {
            super(view);
        texttitle = (TextView)view.findViewById(R.id.text_title);
        imageView = (ImageView)view.findViewById(R.id.imgb);
        }
    }
}