package am.ith.myapplication.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import am.ith.myapplication.R;
import am.ith.myapplication.model.AppResponse;
import am.ith.myapplication.view.activity.YouTubePlayerActivity;

public class VideoGalleryRecycleViewAdapter extends RecyclerView.Adapter<VideoGalleryRecycleViewAdapter.VideoViewHolder> {

    private AppResponse.Metadatum metadata;
    private Context context;

    public VideoGalleryRecycleViewAdapter(AppResponse.Metadatum metadata, Context context) {
        this.metadata = metadata;
        this.context = context;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_gallery_item, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, final int position) {
        holder.videoTitleThumbnail.setText(metadata.video.get(position).title);
        Glide
                .with(context)
                .load(metadata.video.get(position).thumbnailUrl)
                .into(holder.videoImageThumbnail);
        holder.videoGalleryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,YouTubePlayerActivity.class);
                intent.putExtra("youtubeID",metadata.video.get(position).youtubeId);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return metadata.video.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        TextView videoTitleThumbnail;
        ImageView videoImageThumbnail;
        LinearLayout videoGalleryLayout;
        public VideoViewHolder(View itemView) {
            super(itemView);
            videoImageThumbnail=itemView.findViewById(R.id.videoImageID);
            videoTitleThumbnail=itemView.findViewById(R.id.videoTitleID);
            videoGalleryLayout=itemView.findViewById(R.id.videoLayoutID);
        }
    }
}
