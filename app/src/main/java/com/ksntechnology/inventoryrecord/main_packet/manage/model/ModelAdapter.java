package com.ksntechnology.inventoryrecord.main_packet.manage.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ksntechnology.inventoryrecord.R;

import java.util.List;

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.ModelViewHolder> {
    private Context mContext;
    private List<ModelItemDao> mDao;


    public void setmDao(List<ModelItemDao> mDao) {
        this.mDao = mDao;
    }

    public ModelAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ModelViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(
                R.layout.custom_categories_layout,
                viewGroup, false
        );

        return new ModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelViewHolder vh, int position) {
        ModelItemDao item = mDao.get(position);
        vh.txtId.setText(item.getId() + "");
        vh.txtModelName.setText(item.getModelName());
    }

    @Override
    public int getItemCount() {
        return mDao.size();
    }

    public class ModelViewHolder extends RecyclerView.ViewHolder {
        public TextView txtId;
        public TextView txtModelName;

        public ModelViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txtCustomModel_id);
            txtModelName = itemView.findViewById(R.id.txtCustomModel_modelName);
        }
    }
}
