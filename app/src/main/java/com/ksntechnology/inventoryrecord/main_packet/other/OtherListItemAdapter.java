package com.ksntechnology.inventoryrecord.main_packet.other;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ksntechnology.inventoryrecord.R;

import java.util.List;

public class OtherListItemAdapter extends RecyclerView.Adapter<OtherListItemAdapter.OtherListItemViewHolder> {
    private Context mContext;
    private List<OtherListItemDao> dao;

    public void setDao(List<OtherListItemDao> dao) {
        this.dao = dao;
    }


    public OtherListItemAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getItemCount() {
        return dao.size();
    }

    @NonNull
    @Override
    public OtherListItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(
                        R.layout.list_item_other,
                        viewGroup,
                        false);
        return new OtherListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OtherListItemViewHolder holder, int position) {
        final OtherListItemDao item = dao.get(position);
        setupListItem(item, holder);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.itemClicked(item);
            }
        });
    }

    private void setupListItem(OtherListItemDao item, OtherListItemViewHolder holder) {
        setIconType(item, holder);
        holder.txtTitle.setText(item.getTitle());
    }

    private void setIconType(OtherListItemDao item, OtherListItemViewHolder holder) {
        if (item.getImgIcon() == R.drawable.ic_setting) {
            holder.imgIcon.setImageResource(R.drawable.ic_setting);
        } else if (item.getImgIcon() == R.drawable.ic_contact) {
            holder.imgIcon.setImageResource(R.drawable.ic_contact);
        } else if (item.getImgIcon() == R.drawable.ic_manual) {
            holder.imgIcon.setImageResource(R.drawable.ic_manual);
        }
    }


    interface OnItemClicked{
        void itemClicked(OtherListItemDao dao);
    }

    private OnItemClicked mCallBack;

    public void setOnItemClickListener(OnItemClicked listener) {
        mCallBack = listener;
    }


    public class OtherListItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgIcon;
        public TextView txtTitle;

        public OtherListItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imgIcon = itemView.findViewById(R.id.imgOther_itemListIcon);
            txtTitle = itemView.findViewById(R.id.txtOther_itemListTitle);
        }
    }
}
