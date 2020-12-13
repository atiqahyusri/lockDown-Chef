package com.example.androidlogin;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;


public class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder>{

    private Context context;
    private List<FavItem> favItemList;
    private FavDB favDB;

    public FavAdapter(Context context, List<FavItem> favItemList) {
        this.context = context;
        this.favItemList = favItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_item,
                parent, false);
        favDB = new FavDB(context);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final FavItem coffeeItem = favItemList.get(position);

        holder.favTextView.setText(favItemList.get(position).getItem_title());
        holder.favImageView.setImageResource(favItemList.get(position).getItem_image());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(favItemList.get(position).getItem_title().equals("Mint tea")){
                    Intent i = new Intent(context,drinkslemon.class);
                    i.putExtra("name",coffeeItem.getItem_title());
                    context.startActivity(i);
                }
                if(favItemList.get(position).getItem_title().equals("Lemonade")){
                    Intent i = new Intent(context,drinkslemonade.class);
                    i.putExtra("name",coffeeItem.getItem_title());
                    context.startActivity(i);
                }

                if(favItemList.get(position).getItem_title().equals("Coconut shake")){
                    Intent i = new Intent(context,drinkscoconutshake.class);
                    i.putExtra("name",coffeeItem.getItem_title());
                    context.startActivity(i);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return favItemList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView favTextView;
        Button favBtn;
        LinearLayout relativeLayout;
        ImageView favImageView;
        ItemClickListener itemClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            favTextView = itemView.findViewById(R.id.favTextView);
            favBtn = itemView.findViewById(R.id.favBtn);
            favImageView = itemView.findViewById(R.id.favImageView);
            relativeLayout = itemView.findViewById(R.id.parent_layouts);

            //remove from fav after click
            favBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final FavItem favItem = favItemList.get(position);
                    favDB.remove_fav(favItem.getKey_id());
                    removeItem(position);
                }
            });
        }


        public void setItemClickListener(ItemClickListener ic) {
            this.itemClickListener = ic;
        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClickListener(v, getLayoutPosition());
        }
    }

    private void removeItem(int position) {
        favItemList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,favItemList.size());
    }
}
