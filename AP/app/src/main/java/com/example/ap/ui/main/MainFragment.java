package com.example.ap.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.Toast;

import com.example.ap.App;
import com.example.ap.R;
import com.example.ap.api.models.Cake;
import com.example.ap.api.models.History;
import com.example.ap.api.service.RestService;
import com.example.ap.dao.DaoSession;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    RestService restService;
    private CompositeDisposable disposable;
    List<Cake> cakes;
    List<History> histories;
    DaoSession daoSession;
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        disposable = new CompositeDisposable();
        restService = new RestService();
        daoSession = ((App) getActivity().getApplication()).getDaoSession();
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        Button cake_list_button = getView().findViewById(R.id.cake_list_button);
        cake_list_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disposable.add(restService.getCakeService().GetCakes()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .timeout(4000, TimeUnit.MILLISECONDS)
                        .subscribeWith(new DisposableSingleObserver<Response<ArrayList<Cake>>>() {
                            @Override
                            public void onSuccess(Response<ArrayList<Cake>> response) {
                                if(response.code() == 200) {
                                    cakes = response.body();
                                    Bundle bundle = new Bundle();
                                    Gson gson = new Gson();
                                    String s = gson.toJson(cakes);
                                    bundle.putString("cakes", s);
                                    daoSession = ((App) getActivity().getApplication()).getDaoSession();
                                    for (Cake c :
                                            cakes) {
                                        daoSession.insertOrReplace(c);
                                    }
                                    MainFragmentDirections.ActionMainFragmentToCakeListFragment action =
                                            MainFragmentDirections.actionMainFragmentToCakeListFragment(s);
                                    Navigation.findNavController(view).navigate(action);
                                }
                                else{
                                    daoSession = ((App) getActivity().getApplication()).getDaoSession();
                                    cakes = daoSession.loadAll(Cake.class);
                                    Bundle bundle = new Bundle();
                                    Gson gson=new Gson();
                                    String s = gson.toJson(cakes);
                                    bundle.putString("cakes",s);
                                    Toast.makeText(getContext(),"Offline mode",Toast.LENGTH_LONG).show();
                                    MainFragmentDirections.ActionMainFragmentToCakeListFragment action =
                                            MainFragmentDirections.actionMainFragmentToCakeListFragment(s);
                                    Navigation.findNavController(view).navigate(action);
                                }
                            }
                            @Override
                            public void onError(Throwable e) {
                                daoSession = ((App) getActivity().getApplication()).getDaoSession();
                                cakes = daoSession.loadAll(Cake.class);
                                Bundle bundle = new Bundle();
                                Gson gson=new Gson();
                                String s = gson.toJson(cakes);
                                bundle.putString("cakes",s);
                                System.out.println("NOT OK");
                                MainFragmentDirections.ActionMainFragmentToCakeListFragment action =
                                        MainFragmentDirections.actionMainFragmentToCakeListFragment(s);
                                Navigation.findNavController(view).navigate(action);
                            }
                        }));

            }
        });
        Button choose_by_button = getView().findViewById(R.id.choose_by_button);
        choose_by_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(
                        R.id.action_mainFragment_to_chsByIngrFragment);
            }
        });
        Button history_button = getView().findViewById(R.id.history_button);
        history_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disposable.add(restService.getHistoryService().GetHistories()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .timeout(4000, TimeUnit.MILLISECONDS)
                        .subscribeWith(new DisposableSingleObserver<Response<List<History>>>() {
                            @Override
                            public void onSuccess(Response<List<History>> response) {
                                if(response.code() == 200) {
                                        histories= response.body();
                                        Bundle bundle = new Bundle();
                                        Gson gson=new Gson();
                                        String s = gson.toJson(histories);
                                        bundle.putString("cakes",s);
                                        daoSession = ((App) getActivity().getApplication()).getDaoSession();
                                        for (History h :
                                                histories) {
                                            daoSession.insertOrReplace(h);
                                        }

                                        MainFragmentDirections.ActionMainFragmentToHistoryFragment actionH =
                                                MainFragmentDirections.actionMainFragmentToHistoryFragment(s);
                                        Navigation.findNavController(view).navigate(
                                                actionH);
                                    }
                                    else {
                                        daoSession = ((App) getActivity().getApplication()).getDaoSession();
                                        histories = daoSession.loadAll(History.class);
                                        Bundle bundle = new Bundle();
                                        Gson gson = new Gson();
                                        String s = gson.toJson(histories);
                                        bundle.putString("cakes", s);
                                        Toast.makeText(getContext(), "Offline mode", Toast.LENGTH_LONG).show();
                                        MainFragmentDirections.ActionMainFragmentToHistoryFragment actionH =
                                                MainFragmentDirections.actionMainFragmentToHistoryFragment(s);
                                        Navigation.findNavController(view).navigate(
                                                actionH);
                                    }
                            }
                            @Override
                            public void onError(Throwable e) {
                                daoSession = ((App) getActivity().getApplication()).getDaoSession();
                                histories = daoSession.loadAll(History.class);
                                Bundle bundle = new Bundle();
                                Gson gson=new Gson();
                                String s = gson.toJson(histories);
                                bundle.putString("cakes",s);
                                System.out.println("NOT OK");
                                MainFragmentDirections.ActionMainFragmentToHistoryFragment actionH =
                                        MainFragmentDirections.actionMainFragmentToHistoryFragment(s);
                                Navigation.findNavController(view).navigate(
                                        actionH);
                            }
                        }));
            }
        });
    }
}