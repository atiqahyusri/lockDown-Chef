package com.example.androidlogin;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import java.util.ArrayList;

public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.ViewHolder> {

    private ArrayList<CoffeeItem> coffeeItems;
    private Context context;
    private FavDB favDB;

    public CoffeeAdapter(ArrayList<CoffeeItem> coffeeItems, Context context) {
        this.coffeeItems = coffeeItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        favDB = new FavDB(context);
        //create table on first
        SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);
        if (firstStart) {
            createTableOnFirstStart();
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final CoffeeItem coffeeItem = coffeeItems.get(position);

        readCursorData(coffeeItem, holder);
        holder.imageView.setImageResource(coffeeItem.getImageResourse());
        holder.titleTextView.setText(coffeeItem.getTitle());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(coffeeItems.get(position).getTitle().equals("Mint tea")){
                    Intent i = new Intent(context,drinkslemon.class);
                    i.putExtra("name",coffeeItem.getTitle());
                    context.startActivity(i);
                }
                if(coffeeItems.get(position).getTitle().equals("Lemonade")){
                    Intent i = new Intent(context,drinkslemonade.class);
                    i.putExtra("name",coffeeItem.getTitle());
                    context.startActivity(i);
                }

                if(coffeeItems.get(position).getTitle().equals("Coconut shake")){
                    Intent i = new Intent(context,drinkscoconutshake.class);
                    i.putExtra("name",coffeeItem.getTitle());
                    context.startActivity(i);
                }
            }
        });
/**
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {

                if (coffeeItems.get(position).getTitle().equals("Mint tea")) {
                    //go to mint
                    Intent a = new Intent(context, drinkslemon.class);
                    context.startActivity(a);
                }
                if (coffeeItems.get(position).getTitle().equals("Lemonade")) {
                    //go to lemon
                    Intent b = new Intent(context, drinkslemonade.class);
                    context.startActivity(b);
                }
                if (coffeeItems.get(position).getTitle().equals("Coconut shake")) {
                    //go to coconut
                    Intent c = new Intent(context, drinkscoconutshake.class);
                    context.startActivity(c);
                }
            }
        });
**/
    }

    @Override
    public int getItemCount() {
        return coffeeItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView titleTextView;
        LinearLayout relativeLayout;
        Button favBtn;
        ItemClickListener itemClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            favBtn = itemView.findViewById(R.id.favBtn);
            relativeLayout = itemView.findViewById(R.id.parent_layouts);
            //likeCountTextView = itemView.findViewById(R.id.likeCountTextView);

            //add to fav btn
            favBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    CoffeeItem coffeeItem = coffeeItems.get(position);

                    //likeClick(coffeeItem, favBtn);

                    if (coffeeItem.getFavStatus().equals("0")) {
                        coffeeItem.setFavStatus("1");
                        favDB.insertIntoTheDatabase(coffeeItem.getTitle(), coffeeItem.getImageResourse(),
                                coffeeItem.getKey_id(), coffeeItem.getFavStatus());
                        favBtn.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
                    } else {
                        coffeeItem.setFavStatus("0");
                        favDB.remove_fav(coffeeItem.getKey_id());
                        favBtn.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);

                    }

                }
            });
        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClickListener(v, getLayoutPosition());
        }

        public void setItemClickListener(ItemClickListener ic) {
            this.itemClickListener = ic;
        }
    }

    private void createTableOnFirstStart() {
        favDB.insertEmpty();

        SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

    private void readCursorData(CoffeeItem coffeeItem, ViewHolder viewHolder) {
        Cursor cursor = favDB.read_all_data(coffeeItem.getKey_id());
        SQLiteDatabase db = favDB.getReadableDatabase();
        try {
            while (cursor.moveToNext()) {
                String item_fav_status = cursor.getString(cursor.getColumnIndex(FavDB.FAVORITE_STATUS));
                coffeeItem.setFavStatus(item_fav_status);

                //check fav status
                if (item_fav_status != null && item_fav_status.equals("1")) {
                    viewHolder.favBtn.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
                } else if (item_fav_status != null && item_fav_status.equals("0")) {
                    viewHolder.favBtn.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
                }
            }
        } finally {
            if (cursor != null && cursor.isClosed())
                cursor.close();
            db.close();
        }

    }

}
