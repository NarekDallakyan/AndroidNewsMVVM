package am.ith.myapplication.view.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import am.ith.myapplication.R;
import am.ith.myapplication.model.AppResponse;
import am.ith.myapplication.view.fragment.PhotoGalleryFullScreenFragment;

public class GalleryRecycleViewAdapter extends RecyclerView.Adapter<GalleryRecycleViewAdapter.GalleryViewHolder>{
    private AppResponse.Metadatum metadata;
    private Context context;

    public GalleryRecycleViewAdapter(AppResponse.Metadatum metadata, Context context) {
        this.metadata = metadata;
        this.context = context;
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_item,parent,false);
        return new GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GalleryViewHolder holder, final int position) {
        Glide
                .with(context)
                .load(metadata.gallery.get(position).thumbnailUrl)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.galleryProgressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.galleryProgressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.galleryThumbnail);
        holder.titleThumbnail.setText(metadata.gallery.get(position).title);
        holder.galleryThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //send image Content URL   on PhotoGalleryFullScreenFragment
                PhotoGalleryFullScreenFragment photoGalleryFullScreenFragment=new PhotoGalleryFullScreenFragment();
                photoGalleryFullScreenFragment.setFullScreenImageUrl(metadata.gallery.get(position).contentUrl);

                // go to Full Screen Fragment
                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack("")
                        .replace(R.id.main_Container,photoGalleryFullScreenFragment)
                        .commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return metadata.gallery.size();
    }

    public class GalleryViewHolder extends RecyclerView.ViewHolder{
        ImageView galleryThumbnail;
        TextView titleThumbnail;
        ProgressBar galleryProgressBar;
        public GalleryViewHolder(View itemView) {
            super(itemView);
            galleryThumbnail=itemView.findViewById(R.id.galleryItemID);
            galleryProgressBar=itemView.findViewById(R.id.galleryProgressBarID);
            titleThumbnail=itemView.findViewById(R.id.galleryItemTitleID);
        }
    }
}

