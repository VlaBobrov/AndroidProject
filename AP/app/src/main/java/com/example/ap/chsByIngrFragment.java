package com.example.ap;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.ap.api.models.Cake;
import com.example.ap.api.models.History;
import com.example.ap.api.models.Ingredients;
import com.example.ap.api.service.RestService;
import com.example.ap.ui.main.MainFragmentDirections;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;


public class chsByIngrFragment extends Fragment {

    private ArrayAdapter arrayAdapter;
    private Spinner weight,price,nadzienie,ciastka,smietana;
    RestService restService;
    Button serchByButton;
    ArrayList<Cake> cakes;
    private CompositeDisposable disposable;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_chs_by_ingr, container, false);
        Ingredients c = new Ingredients();
        Ingredients s = new Ingredients();
        Ingredients n = new Ingredients();
        Cake cake = new Cake();
        disposable = new CompositeDisposable();
        restService = new RestService();
        serchByButton = view.findViewById(R.id.serchByButton);
        arrayAdapter = ArrayAdapter.createFromResource(getContext(),R.array.ciastka, R.layout.support_simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        ciastka = view.findViewById(R.id.ciastkaSpinner);
        ciastka.setAdapter(arrayAdapter);
        ciastka.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                c.Type = "Ciastko";
               if(id==0){
                   c.Name ="Orzechowe";
               }
               else if(id==1){
                   c.Name = "Czekoladowe";
               }
               else if(id == 2){
                   c.Name= "Waniliowe";
               }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        arrayAdapter = ArrayAdapter.createFromResource(getContext(),R.array.smietana, R.layout.support_simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        smietana = view.findViewById(R.id.smietanaSpinner);
        smietana.setAdapter(arrayAdapter);
        smietana.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s.Type = "Smietana";
                if(id==0){

                    s.Name ="Kremny twarog";
                }
                else if(id==1){

                    s.Name = "Czekolada";
                }
                else if(id == 2){

                    s.Name= "Kwasna smietana";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        arrayAdapter = ArrayAdapter.createFromResource(getContext(),R.array.nadzienie, R.layout.support_simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        nadzienie = view.findViewById(R.id.nadzenieSpinner);
        nadzienie.setAdapter(arrayAdapter);
        nadzienie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                n.Type ="Nadzienie";
                if(id==0){
                    n.Name ="Jagodowe";
                }
                else if(id==1){

                    n.Name = "Orzehowe";
                }
                else if(id == 2){
                    n.Name= "Czekoladowe";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        arrayAdapter = ArrayAdapter.createFromResource(getContext(),R.array.price, R.layout.support_simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        price = view.findViewById(R.id.priceSpinner);
        price.setAdapter(arrayAdapter);

        arrayAdapter = ArrayAdapter.createFromResource(getContext(),R.array.weight, R.layout.support_simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        weight = view.findViewById(R.id.weightSpinner);
        weight.setAdapter(arrayAdapter);




        serchByButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Ingredients> ingredients= new ArrayList<Ingredients>();
                ingredients.add(n);
                ingredients.add(c);
                ingredients.add(s);
                disposable.add(restService.getCakeService().GetAllCakesBy(ingredients)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<Response<ArrayList<Cake>>>() {
                            @Override
                            public void onSuccess(Response<ArrayList<Cake>> response) {
                               try {
                                   cakes= response.body();
                                   Bundle bundle = new Bundle();
                                   Gson gson=new Gson();
                                   String s = gson.toJson(cakes);
                                   bundle.putString("cakes",s);
                                   chsByIngrFragmentDirections.ActionChsByIngrFragmentToCakeListFragment
                                           action = chsByIngrFragmentDirections.actionChsByIngrFragmentToCakeListFragment(s);
                                   Navigation.findNavController(view).navigate(action);
                               } catch (NullPointerException e){
                                   System.out.println(e.getMessage());
                               }

                            }
                            @Override
                            public void onError(Throwable e) {
                                System.out.println("NOT OK");
                                System.out.println(e.getMessage());
                            }
                        }));
            }
        });
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}