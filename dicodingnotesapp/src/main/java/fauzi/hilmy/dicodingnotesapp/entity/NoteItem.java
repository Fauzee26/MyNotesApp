package fauzi.hilmy.dicodingnotesapp.entity;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import fauzi.hilmy.dicodingnotesapp.DatabaseContract;

import static fauzi.hilmy.dicodingnotesapp.DatabaseContract.getColumnInt;
import static fauzi.hilmy.dicodingnotesapp.DatabaseContract.getColumnString;

public class NoteItem implements Parcelable {
    private int id;
    private String title, description, date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    protected NoteItem(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        date = in.readString();
    }

    public NoteItem(Cursor cursor) {
        this.id = getColumnInt(cursor, DatabaseContract.NoteColumns._ID);
        this.title = getColumnString(cursor, DatabaseContract.NoteColumns.TITLE);
        this.description = getColumnString(cursor, DatabaseContract.NoteColumns.DESCRIPTION);
        this.date = getColumnString(cursor, DatabaseContract.NoteColumns.DATE);
    }

    public NoteItem() {

    }

    public static final Creator<NoteItem> CREATOR = new Creator<NoteItem>() {
        @Override
        public NoteItem createFromParcel(Parcel in) {
            return new NoteItem(in);
        }

        @Override
        public NoteItem[] newArray(int size) {
            return new NoteItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(date);
    }
}
