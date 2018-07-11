package am.ith.myapplication.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import am.ith.myapplication.model.AppResponse;
import am.ith.myapplication.AppApplication;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VMNews extends AndroidViewModel {

 //   private AppApplication appApplication;
    private MutableLiveData<AppResponse> appResponseMutableLiveData = new MutableLiveData<>();

    public VMNews(@NonNull Application application) {
        super(application);
        getJson();
    }


    private void getJson() {

   //     appApplication = new AppApplication();
        AppApplication.appApplication.getNetworkService().getAllProduct().enqueue(new Callback<AppResponse>() {
            @Override
            public void onResponse(Call<AppResponse> call, Response<AppResponse> response) {
                Observable.just(response.body())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subscribeData());
            }

            @Override
            public void onFailure(Call<AppResponse> call, Throwable t) {

            }
        });
    }

    private Observer<AppResponse> subscribeData() {
        return new Observer<AppResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AppResponse appResponse) {
                appResponseMutableLiveData.setValue(appResponse);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    public MutableLiveData<AppResponse> getMutableLiveData() {
        return appResponseMutableLiveData;
    }
}

