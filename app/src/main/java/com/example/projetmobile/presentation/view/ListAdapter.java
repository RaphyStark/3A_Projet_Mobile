package com.example.projetmobile.presentation.view;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projetmobile.R;
import com.example.projetmobile.presentation.model.Mark;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<Mark> values;
    //private Context mContext;

    // Provide a suitable constructor (depends on the kind of Dataset)
    public ListAdapter(List<Mark> markList) {
        //mContext = context;
        values = markList;
    }



    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView photo;
        public TextView txtHeader;
        public TextView txtFooter;
        public View layout;


        public ViewHolder(View v) {
            super(v);
            photo = (ImageView) v.findViewById(R.id.icon);
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
            layout = v;
        }
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater;
        inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //String pathname = "C:\\Users\\user\\AndroidStudioProjects\\ProjetMobile\\app\\src\\main\\res\\drawable\\mark_1.jpg";
        final Mark currentMark = values.get(position);
        holder.txtHeader.setText(currentMark.getName());
        holder.txtFooter.setText(currentMark.getFilm());
        //try to get the picture from drawable and to put it in each view
        //holder.photo.setImageDrawable(currentMark.getUrl());
        //Picasso.with(mContext).load(new File(pathname)).into(imageView);
        //  Picasso.with(mContext).load("http://i.imgur.com/DvpvklR.png").into(holder.photo);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

    public void add(int position, Mark item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

}