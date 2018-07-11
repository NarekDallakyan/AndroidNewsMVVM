package am.ith.myapplication.view.activity;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.Date;
import java.util.List;

import am.ith.myapplication.R;
import am.ith.myapplication.local.Engine;
import am.ith.myapplication.model.AppResponse;
import am.ith.myapplication.view.fragment.PhotoGalleryFragment;
import am.ith.myapplication.view.fragment.VideoGalleryFragment;

public class DetailsActivity extends AppCompatActivity {
    private ImageView photoGallery;
    private ImageView videoGallery;
    private ImageView detailsScreenImage;
    private ProgressBar progressBar;
    private TextView date;
    private TextView category;
    private TextView title;
    private TextView body;
    private AppResponse.Metadatum metadata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        init();
        metadata= Engine.getInstance().getMetadata();
        // Display each item’s
        setImageWithGilde(metadata.coverPhotoUrl);
        setTitle(metadata.title);
        setCategory(metadata.category);
        setDate(metadata.date);
        setBody(metadata.body);

        //check photo and video images
        checkPhotoGallery();
        checkVideoGallery();

        if (checkPhotoGallery()){
            imageViewAnimation(photoGallery);
            photoGallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.main_Container,new PhotoGalleryFragment()).addToBackStack("");
                    fragmentTransaction.commit();

                }
            });
        }
        if (checkVideoGallery()){
            imageViewAnimation(videoGallery);
            videoGallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.main_Container,new VideoGalleryFragment()).addToBackStack("");
                    fragmentTransaction.commit();
                }
            });
        }


    }
    private void init() {
        detailsScreenImage = findViewById(R.id.detailsScreenImageID);
        progressBar = findViewById(R.id.progressBarDetailsScreenID);
        date = findViewById(R.id.dateDetailsScreenTxtID);
        category = findViewById(R.id.categoryDetailsScreenTxtID);
        title = findViewById(R.id.titleDetailsScreenTxtID);
        photoGallery=findViewById(R.id.photoGalleryID);
        videoGallery=findViewById(R.id.videoGalleryID);
        body=findViewById(R.id.bobyDetailsScreenTxtID);
    }
    private void setImageWithGilde(String model_Url) {

        Glide
                .with(this)
                .load(model_Url)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(detailsScreenImage);
    }
    private void setTitle(String model_Title){
        title.setText(model_Title);
    }
    private void setCategory(String model_Category){
        category.setText(model_Category);
    }
    private void setDate(int model_Date){
        Date timeStamp = new Date(model_Date);
        date.setText(timeStamp.toString());
    }
    private void setBody(String model_Body){
        body.setText(model_Body);
    }
    private boolean checkPhotoGallery(){
        if (metadata.gallery!=null){
            photoGallery.setVisibility(View.VISIBLE);
            return true;
        }
        return false;
    }
    private boolean checkVideoGallery(){
        if (metadata.video!=null){
            videoGallery.setVisibility(View.VISIBLE);
            return true;
        }
        return false;
    }
    private void imageViewAnimation(View view){
        Animation xmlAnimationSample;
        xmlAnimationSample = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animimage);
        view.startAnimation(xmlAnimationSample);
    }
}
