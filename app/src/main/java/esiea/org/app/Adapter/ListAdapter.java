package esiea.org.app.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import esiea.org.app.Activities.OnRecyclerClickListener;
import esiea.org.app.Model.Login;
import esiea.org.app.Model.Profile;
import esiea.org.app.R;

/**
 * Created by Ayoub Bouthoukine on 08/11/2016.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{

    private Activity activity;
    private LayoutInflater inflater;
    private List<Profile> profileItems;
    private OnRecyclerClickListener listener;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is justTextView textV; a string in this case
        public TextView name;
        public TextView city;
        public ViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.name);
            city = (TextView) v.findViewById(R.id.city);
        }

    }

    public ListAdapter( List<Profile> profileItems,OnRecyclerClickListener listener ) {
        this.profileItems = profileItems;
        this.listener = listener;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View convertView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);

        ViewHolder vh = new ViewHolder(convertView);
        return vh;/*TextView name = (TextView) convertView.findViewById(R.id.name);
        Profile p = profileItems.get(position);
        name.setText(p.getFirstname()+" "+p.getLastname());*/

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Profile p = profileItems.get(position);
        holder.name.setText(p.getFirstname()+" "+p.getLastname());
        holder.city.setText(p.getCity());


        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override public void onClick(View v) {
                listener.onRecyclerClickListener(p.getId());
            }

        });

    }

    @Override
    public int getItemCount() {
        return profileItems.size();
    }


}
