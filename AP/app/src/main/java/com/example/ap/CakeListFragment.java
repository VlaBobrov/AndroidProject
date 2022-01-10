package com.example.ap;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.example.ap.api.models.Cake;
import com.example.ap.api.service.RestService;
import com.example.ap.dao.DaoSession;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.TimeUnit;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class CakeListFragment extends Fragment {
    Context thiscontext;
    RecyclerView cakeList;
    CakeAdapter adapter;
    ArrayList<Cake> cakes;
    CakeListFragmentArgs clargs;
    private CompositeDisposable disposable;
    RestService restService;
    DaoSession daoSession;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restService = new RestService();
        disposable = new CompositeDisposable();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        thiscontext= container.getContext();
        View view = inflater.inflate(R.layout.fragment_cake_list, container, false);
        cakeList = view.findViewById(R.id.cakeList);

        cakeList.setLayoutManager((new LinearLayoutManager(getContext())));
        cakeList =(RecyclerView) view.findViewById(R.id.cakeList);
        clargs = CakeListFragmentArgs.fromBundle(getArguments());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        daoSession = ((App) getActivity().getApplication()).getDaoSession();
        List<Cake> cakesUp = daoSession.loadAll(Cake.class);
        for (Cake c : cakesUp) {
            disposable.add(restService.getCakeService().GetCakeById((int)c.getId())
                    .subscribeOn(Schedulers.io())
                    .timeout(500, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<Response<Cake>>() {
                        @Override
                        public void onSuccess(Response<Cake> response) {
                            if (response.code() == 200) {
                                Cake cake = response.body();
                                daoSession.update(cake);
                            }
                            else
                                daoSession.delete(c);
                        }
                        @Override
                        public void onError(@NonNull Throwable e) {
                        }
                    }));
        }


        Gson gson = new Gson();
        System.out.println(clargs.getCakes());
        Type listType = new TypeToken<ArrayList<Cake>>(){}.getType();
        cakes = gson.fromJson(clargs.getCakes(),listType);
        adapter = new CakeAdapter(cakes);
        cakeList.setAdapter(adapter);
    }
}
