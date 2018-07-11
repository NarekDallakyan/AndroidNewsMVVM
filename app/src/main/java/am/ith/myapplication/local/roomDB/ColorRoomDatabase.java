package am.ith.myapplication.local.roomDB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import am.ith.myapplication.AppApplication;
import am.ith.myapplication.AppConstants;
import am.ith.myapplication.local.dao.ColorSaveDao;
import am.ith.myapplication.local.entity.SaveColorModel;

@Database(entities = SaveColorModel.class, version = AppConstants.DB_VERSION, exportSchema = false)
public abstract class ColorRoomDatabase extends RoomDatabase {
    private static ColorRoomDatabase INSTANCE;

    public static ColorRoomDatabase getInstance() {
        if (INSTANCE == null) {
            return INSTANCE = Room.
                    databaseBuilder(AppApplication.appApplication.getApplicationContext(), ColorRoomDatabase.class, AppConstants.DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        } else {
            return INSTANCE;
        }

    }
    public abstract ColorSaveDao colorSaveDao();
}
