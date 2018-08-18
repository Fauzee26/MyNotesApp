package fauzi.hilmy.dicodingnotesapp.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import fauzi.hilmy.dicodingnotesapp.R;

import static fauzi.hilmy.dicodingnotesapp.DatabaseContract.NoteColumns.DATE;
import static fauzi.hilmy.dicodingnotesapp.DatabaseContract.NoteColumns.DESCRIPTION;
import static fauzi.hilmy.dicodingnotesapp.DatabaseContract.NoteColumns.TITLE;
import static fauzi.hilmy.dicodingnotesapp.DatabaseContract.getColumnString;

public class DicodingNotesAdapter extends CursorAdapter {
    public DicodingNotesAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dicoding_note, parent, false);
        return view;
    }

    @Override
    public Cursor getCursor() {
        return super.getCursor();
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        if (cursor != null) {
            TextView tvTitle = view.findViewById(R.id.tv_item_title);
            TextView tvDate = view.findViewById(R.id.tv_item_date);
            TextView tvDesc = view.findViewById(R.id.tv_item_description);

            tvTitle.setText(getColumnString(cursor, TITLE));
            tvDate.setText(getColumnString(cursor, DATE));
            tvDesc.setText(getColumnString(cursor, DESCRIPTION));

        }
    }
}
