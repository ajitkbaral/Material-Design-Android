package com.pagodalabs.materialdesign.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.pagodalabs.materialdesign.R;
import com.pagodalabs.materialdesign.entities.Professional;
import com.pagodalabs.materialdesign.network.VolleySingleton;

import java.util.ArrayList;

/**
 * Created by Ajit Kumar Baral on 6/19/2015.
 */
public class ProfessionalAdapter extends RecyclerView.Adapter<ProfessionalAdapter.ViewHolderProfessional>{
    private Context context;
    private ArrayList<Professional> professionalList = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;

    public ProfessionalAdapter(Context context){
        this.context = context;
        layoutInflater = layoutInflater.from(context);
        volleySingleton = VolleySingleton.getsInstance();
        imageLoader = volleySingleton.getImageLoader();

    }
    public void setProfessionalList(ArrayList<Professional> professionalList){
        if(professionalList!=null) {
            this.professionalList = professionalList;
            notifyItemRangeChanged(0, professionalList.size());
        }
    }

    @Override
    public ViewHolderProfessional onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.professional_layout, parent, false);
        ViewHolderProfessional viewHolderProfessional = new ViewHolderProfessional(view);
        return viewHolderProfessional;
    }

    @Override
    public void onBindViewHolder(ViewHolderProfessional holder, int position) {

        Professional professional = professionalList.get(position);
        holder.tvProfessionalTitle.setText(professional.getFirstName()+" "+professional.getLastName());
        String professionalDescription = professional.getDescription();
        if(professionalDescription.length()>=80){
            StringBuilder builder = new StringBuilder();
            for(int i = 0; i<=80; i++){
                builder.append(professionalDescription.charAt(i));
            }
            holder.tvProfessionalDescription.setText(builder.toString()+"....");
        }else {
            holder.tvProfessionalDescription.setText(professionalDescription);
        }



    }

    @Override
    public int getItemCount() {
        return professionalList.size();
    }


    public class ViewHolderProfessional extends RecyclerView.ViewHolder{

        private TextView tvProfessionalTitle;
        private TextView tvProfessionalDescription;

        public ViewHolderProfessional(View itemView) {
            super(itemView);
            tvProfessionalTitle = (TextView)itemView.findViewById(R.id.tvProfessionalTitle);
            tvProfessionalDescription = (TextView)itemView.findViewById(R.id.tvProfessionalDescription);
        }


    }


}