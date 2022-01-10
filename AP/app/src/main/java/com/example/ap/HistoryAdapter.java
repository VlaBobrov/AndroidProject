package com.example.ap;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ap.api.models.Cake;
import com.example.ap.api.models.History;
import com.example.ap.api.service.RestService;
import com.example.ap.dao.DaoSession;


import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;


public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder> {
    private ArrayList<History> historyList;
    public DaoSession daoSession;
    private App app;
    public Cake cake;
    private CompositeDisposable disposable;;
    RestService restService;
    HistoryAdapter( ArrayList<History> histories,App app) {
        this.historyList = histories;
        this.app = app;
    }

    @NonNull
    @Override
    public HistoryAdapter.HistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item_list, parent, false);
        restService = new RestService();
        disposable = new CompositeDisposable();
        return new HistoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.HistoryHolder holder, @SuppressLint("RecyclerView") int position) {
        daoSession = app.getDaoSession();
        holder.dateView.setText("'"+historyList.get(position).Date.toString()+"'");
        if (historyList.get(position).Done){
            holder.doneView.setText("Done");
        }
        else{
            holder.doneView.setText("In progress");
        }

        cake = daoSession.load(Cake.class,(long)historyList.get(position).CakeId);
        System.out.println(cake.getId() + "  " + (long)historyList.get(position).CakeId);
        holder.nameView.setText("'"+cake.Name.toString()+"'");
        byte[] bytes= Base64.decode(cake.PathToImage,Base64.DEFAULT);
        Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        holder.imageView.setImageBitmap(bitmap);

        holder.doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                History history = historyList.get(position);
                history.Done = true;
                disposable.add(restService.getHistoryService().UpdateHistory(history)
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
        return historyList.size();
    }

    public class HistoryHolder extends RecyclerView.ViewHolder {
        TextView nameView, dateView,doneView;
        ImageView imageView;
        Button doneButton;
        public HistoryHolder(@NonNull View view){
            super(view);
            imageView = view.findViewById(R.id.imageView);
            dateView = view.findViewById(R.id.dateView);
            doneView = view.findViewById(R.id.doneView);
            nameView = view.findViewById(R.id.nameView);
            doneButton = view.findViewById(R.id.doneButton);
        }
    }
}
