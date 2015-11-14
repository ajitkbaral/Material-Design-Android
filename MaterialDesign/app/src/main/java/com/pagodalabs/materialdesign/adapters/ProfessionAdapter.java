package com.pagodalabs.materialdesign.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.pagodalabs.materialdesign.R;
import com.pagodalabs.materialdesign.entities.Profession;
import com.pagodalabs.materialdesign.network.VolleySingleton;

import java.util.ArrayList;

/**
 * Created by Ajit Kumar Baral on 6/12/2015.
 */
public class ProfessionAdapter extends RecyclerView.Adapter<ProfessionAdapter.ViewHolderProfession> {

    private ArrayList<Profession> professionList = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;

    public ProfessionAdapter(Context context){
        layoutInflater = LayoutInflater.from(context);
        volleySingleton = VolleySingleton.getsInstance();
        imageLoader = volleySingleton.getImageLoader();
    }

    public void setProfessionList(ArrayList<Profession> professionList){
        if(professionList!=null) {
            this.professionList = professionList;
            notifyItemRangeChanged(0, professionList.size());
        }
    }


    @Override
    public ViewHolderProfession onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.profession_layout, parent, false);
        ViewHolderProfession viewHolderProfession = new ViewHolderProfession(view);
        return viewHolderProfession;
    }

    @Override
    public void onBindViewHolder(final ViewHolderProfession holder, int position) {

        Profession profession = professionList.get(position);
        holder.tvProfessionTitle.setText(profession.getJobName());
        String urlImage = profession.getUrlImage();
        if(urlImage!=null){
            imageLoader.get(urlImage, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    holder.ivProfessionImage.setImageBitmap(response.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return professionList.size();
    }

    static class ViewHolderProfession extends RecyclerView.ViewHolder{

        private ImageView ivProfessionImage;
        private TextView tvProfessionTitle;


        public ViewHolderProfession(View itemView) {
            super(itemView);
            ivProfessionImage = (ImageView)itemView.findViewById(R.id.ivProfessionImage);
            tvProfessionTitle = (TextView)itemView.findViewById(R.id.tvProfessionTitle);
        }
    }
}
