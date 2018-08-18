package fauzi.hilmy.mynotesapp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

import fauzi.hilmy.mynotesapp.CustomOnItemClickListener;
import fauzi.hilmy.mynotesapp.FormAddUpdateActivity;
import fauzi.hilmy.mynotesapp.R;
import fauzi.hilmy.mynotesapp.entity.Note;

import static fauzi.hilmy.mynotesapp.db.DatabaseContract.CONTENT_URI;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private Cursor listNotes;
    private Activity activity;

    public NoteAdapter(Activity activity) {
        this.activity = activity;
    }

//    public LinkedList<Note> getListNotes() {
//        return listNotes;
//    }

    public void setListNotes(Cursor listNotes) {
        this.listNotes = listNotes;
    }

    @NonNull
    @Override
    public NoteAdapter.NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.NoteViewHolder holder, int position) {
        final Note note = getItem(position);
        holder.tvTitle.setText(note.getTitle());
        holder.tvDate.setText(note.getDate());
        holder.tvDescription.setText(note.getDescription());
        holder.cvNote.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallBack() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(activity, FormAddUpdateActivity.class);

                Uri uri = Uri.parse(CONTENT_URI + "/" + note.getId());
//                intent.putExtra(FormAddUpdateActivity.EXTRA_POSITION, position);
//                intent.putExtra(FormAddUpdateActivity.EXTRA_NOTE, getListNotes().get(position));
                intent.setData(uri);
                activity.startActivityForResult(intent, FormAddUpdateActivity.REQUEST_UPDATE);

            }
        }));
    }

    @Override
    public int getItemCount() {
        if (listNotes == null) return 0;
        return listNotes.getCount();
//        return getListNotes().size();
    }

    private Note getItem(int position) {
        if (!listNotes.moveToPosition(position)) {
            throw new IllegalStateException("Position Invalid");
        }
        return new Note(listNotes);
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription, tvDate;
        CardView cvNote;

        public NoteViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            cvNote = itemView.findViewById(R.id.cv_item_note);
        }
    }
}
