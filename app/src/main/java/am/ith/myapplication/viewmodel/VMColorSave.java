package am.ith.myapplication.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import am.ith.myapplication.local.dao.ColorSaveDao;
import am.ith.myapplication.local.entity.SaveColorModel;
import am.ith.myapplication.local.roomDB.ColorRoomDatabase;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class VMColorSave extends AndroidViewModel {
    private ColorSaveDao colorSaveDao;
    private LiveData<List<SaveColorModel>> listLiveData;

    public VMColorSave(Application application) {
        super(application);
        ColorRoomDatabase colorRoomDatabase=ColorRoomDatabase.getInstance();
        colorSaveDao=colorRoomDatabase.colorSaveDao();
        if (listLiveData==null) {
            listLiveData = colorSaveDao.getAllColors();
        }
    }
    public void insert (SaveColorModel saveColorModel) {
        new insertAsyncTask(colorSaveDao).execute(saveColorModel);
    }
    private static class insertAsyncTask extends AsyncTask<SaveColorModel, Void, Void> {

        private ColorSaveDao mAsyncTaskDao;

        insertAsyncTask(ColorSaveDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final SaveColorModel... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }

    }
    public LiveData<List<SaveColorModel>> getListLiveData() {
        return listLiveData;
    }
}
