package am.ith.myapplication.view.activity;

import android.app.Application;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import am.ith.myapplication.R;
import am.ith.myapplication.local.Engine;
import am.ith.myapplication.local.dao.ColorSaveDao;
import am.ith.myapplication.local.entity.SaveColorModel;
import am.ith.myapplication.model.AppResponse;
import am.ith.myapplication.view.adapter.GeneralRecycleViewAdapter;
import am.ith.myapplication.viewmodel.VMColorSave;
import am.ith.myapplication.viewmodel.VMNews;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="MainActivity";
    private VMNews vmNews;
    private VMColorSave vmColorSave;
    private RecyclerView recyclerView;
    private GeneralRecycleViewAdapter adapter;
    private GridLayoutManager gridLayoutManager;
    private List<SaveColorModel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        createViewModels();
        getNetworkData();
    }

    private void createViewModels() {
        vmNews = ViewModelProviders.of(this).get(VMNews.class);
        vmColorSave=ViewModelProviders.of(this).get(VMColorSave.class);

    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.generalRecycleViewID);
    }

    private void getNetworkData() {

        vmNews.getMutableLiveData().observe(MainActivity.this, new Observer<AppResponse>() {
            @Override
            public void onChanged(@Nullable AppResponse appResponse) {
                Engine.getInstance().setAppResponse(appResponse.getMetadata());
                serDataRecyclerViewAdapter(appResponse);


            }
        });

    }

    private List<SaveColorModel> getAllRoomData() {
         list = new LinkedList<>();
        vmColorSave.getListLiveData().observe(this, new Observer<List<SaveColorModel>>() {
            @Override
            public void onChanged(@Nullable List<SaveColorModel> colorSaveDaos) {
                list.addAll(Objects.requireNonNull(colorSaveDaos));
            }
        });
        return list;
    }
    @Override
    protected void onResume() {
        super.onResume();
        getAllRoomData();
    }

    private void serDataRecyclerViewAdapter(AppResponse appResponse) {
        gridLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new GeneralRecycleViewAdapter(appResponse, getAllRoomData(), this);
        recyclerView.setAdapter(adapter);
    }

}

