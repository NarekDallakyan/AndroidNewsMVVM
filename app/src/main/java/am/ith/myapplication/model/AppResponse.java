package am.ith.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AppResponse {
    @SerializedName("success")
    @Expose
    public Boolean success;
    @SerializedName("errors")
    @Expose
    public List<Object> errors = null;
    @SerializedName("internal_errors")
    @Expose
    public List<Object> internalErrors = null;
    @SerializedName("metadata")
    @Expose
    public List<Metadatum> metadata = null;

    public class Gallery {

        @SerializedName("title")
        @Expose
        public String title;
        @SerializedName("thumbnailUrl")
        @Expose
        public String thumbnailUrl;
        @SerializedName("contentUrl")
        @Expose
        public String contentUrl;

    }
    public class Metadatum {

        @SerializedName("category")
        @Expose
        public String category;
        @SerializedName("title")
        @Expose
        public String title;
        @SerializedName("body")
        @Expose
        public String body;
        @SerializedName("shareUrl")
        @Expose
        public String shareUrl;
        @SerializedName("coverPhotoUrl")
        @Expose
        public String coverPhotoUrl;
        @SerializedName("date")
        @Expose
        public Integer date;
        @SerializedName("gallery")
        @Expose
        public List<Gallery> gallery = null;
        @SerializedName("video")
        @Expose
        public List<Video> video = null;

    }
    public class Video {

        @SerializedName("title")
        @Expose
        public String title;
        @SerializedName("thumbnailUrl")
        @Expose
        public String thumbnailUrl;
        @SerializedName("youtubeId")
        @Expose
        public String youtubeId;

    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

    public List<Object> getInternalErrors() {
        return internalErrors;
    }

    public void setInternalErrors(List<Object> internalErrors) {
        this.internalErrors = internalErrors;
    }

    public List<Metadatum> getMetadata() {
        return metadata;
    }

    public void setMetadata(List<Metadatum> metadata) {
        this.metadata = metadata;
    }
}

