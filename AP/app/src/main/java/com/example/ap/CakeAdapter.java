package com.example.ap;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ap.api.models.Cake;
import com.example.ap.api.models.History;
import com.example.ap.api.service.RestService;
import com.example.ap.ui.main.MainFragmentDirections;
import com.google.gson.Gson;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;


public class CakeAdapter extends RecyclerView.Adapter<CakeAdapter.CakeHolder> {
    private ArrayList<Cake> cakeList;
    private CompositeDisposable disposable;
    RestService restService;
    CakeAdapter( ArrayList<Cake> cakes) {
        this.cakeList = cakes;
    }

    @NonNull
    @Override
    public CakeAdapter.CakeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        restService = new RestService();
        disposable = new CompositeDisposable();
        return new CakeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CakeHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.nameView.setText("'" + cakeList.get(position).Name.toString() + "'");
        holder.weightView.setText("Weight: " + Integer.toString(cakeList.get(position).Weight) + " kg");
        holder.priceView.setText("Price: " + Integer.toString(cakeList.get(position).Price) + " zl");
        holder.descriptionView.setText("Description: " + cakeList.get(position).Description.toString());
        byte[] bytes = Base64.decode(cakeList.get(position).PathToImage, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        holder.imageView.setImageBitmap(bitmap);
        holder.wybierzButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                History history = new History();
                SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                Date date = new Date(System.currentTimeMillis());
                history.Date = date.toString();
                history.CakeId = (int)cakeList.get(position).Id;
                history.Done = false;
                disposable.add(restService.getHistoryService().AddHistory(history)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<Response<History>>() {
                            @Override
                            public void onSuccess(@NonNull Response<History> historyResponse) {

                            }
                            @Override
                            public void onError(Throwable e) {
                                System.out.println("NOT OK");
                                System.out.println(e.getMessage());
                            }
                        }));
            }
        });
    }


    @Override
    public int getItemCount() {
        return cakeList.size();
    }

    public class CakeHolder extends RecyclerView.ViewHolder {
        TextView priceView, descriptionView;
        TextView nameView, weightView;
        ImageView imageView;
        Button wybierzButton;
        public CakeHolder(@NonNull View view){
            super(view);
            imageView = view.findViewById(R.id.imageView);
            priceView = view.findViewById(R.id.priceView);
            descriptionView = view.findViewById(R.id.descriptionView);
            nameView = view.findViewById(R.id.nameView);
            weightView = view.findViewById(R.id.weightView);
            wybierzButton = view.findViewById(R.id.wybierzButton);
        }
    }
}
