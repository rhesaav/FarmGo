package id.sch.smktelkom_mlg.afinal.xirpl1052029.farmgo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haniivn on 4/30/2018.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private List<Data> dataList;
    private Context context;

    public DataAdapter(List<Data> data) {
        this.dataList = data;
    }

    public DataAdapter() {
        dataList = new ArrayList<>();
    }

    private void add(Data item) {
        dataList.add(item);
        notifyItemInserted(dataList.size() - 1);
    }

    public void addAll(List<Data> dataList) {
        for (Data data : dataList) {
            add(data);
        }
    }

    public void remove(Data item) {
        int position = dataList.indexOf(item);
        if (position > -1) {
            dataList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public Data getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview, null);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Data data = dataList.get(position);
        /*holder.title.setText(data.getEtanggal());*/
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), TampilData.class);
                view.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, tgl;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            tgl = itemView.findViewById(R.id.tanggal);
        }
    }
}