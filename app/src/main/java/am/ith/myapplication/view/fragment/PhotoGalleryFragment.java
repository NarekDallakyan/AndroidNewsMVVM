package am.ith.myapplication.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import am.ith.myapplication.R;
import am.ith.myapplication.local.Engine;
import am.ith.myapplication.model.AppResponse;
import am.ith.myapplication.view.adapter.GalleryRecycleViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoGalleryFragment extends Fragment {

    private View view;
    private Context context;
    private AppResponse.Metadatum metadata;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private GalleryRecycleViewAdapter adapter;
    public PhotoGalleryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_photo_gallery, container, false);
        context = getContext();
        init();
        metadata = Engine.getInstance().getMetadata();
        linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new GalleryRecycleViewAdapter(metadata, context);
        recyclerView.setAdapter(adapter);
        return view;
    }
    private void init() {
        recyclerView = view.findViewById(R.id.GalleryRecycleViewID);
    }

}
