package am.ith.myapplication.local.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "color_table")
public class SaveColorModel {
    @PrimaryKey( autoGenerate = true)
    private long id;
    @ColumnInfo(name = "position")
    private long position;

    public SaveColorModel(long position) {
        this.position = position;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }
}
