package com.badran.androidchallenge.dashboard.fragmain.adapters;

import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.badran.androidchallenge.R;
import com.badran.androidchallenge.dashboard.fragmain.ContractMain;
import com.badran.androidchallenge.di.annotations.ActivityScoped;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

@ActivityScoped
public class AdapterRepo extends RecyclerView.Adapter<AdapterRepo.ViewHolder> {
//Adapter Repo: I'm not good in handling data things.
//Presenter: dw <3, I'll help you hun, I'll always be by your side.
    @Inject
    ContractMain.Presenter presenter;

    @Inject
    public AdapterRepo() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_repo,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        presenter.bindViewHolders(holder,position);
    }

    @Override
    public int getItemCount() {
        return presenter.getDataCount();
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvRepoName,tvLanguageName,tvUserName,tvStarsCount;
        private final ImageView imAvatar;
        private final View languageColor;
        private final TextView tvRepoDesc;
        private final CardView cardLanguageColor;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRepoName = itemView.findViewById(R.id.tv_repo_name);
            imAvatar = itemView.findViewById(R.id.im_avatar);
            languageColor = itemView.findViewById(R.id.view_language_color);
            tvRepoDesc = itemView.findViewById(R.id.tv_desc);
            cardLanguageColor = itemView.findViewById(R.id.card_language_color);
            tvLanguageName = itemView.findViewById(R.id.tv_language_name);
            tvUserName = itemView.findViewById(R.id.tv_username);
            tvStarsCount = itemView.findViewById(R.id.tv_stars_count);
        }

        public TextView getTvRepoDesc() {
            return tvRepoDesc;
        }

        public TextView getTvRepoName() {
            return tvRepoName;
        }

        public TextView getTvLanguageName() {
            return tvLanguageName;
        }

        public TextView getTvUserName() {
            return tvUserName;
        }

        public TextView getTvStarsCount() {
            return tvStarsCount;
        }

        public ImageView getImAvatar() {
            return imAvatar;
        }

        public void setLanguageColor(String Hex){
            languageColor.setBackgroundColor(Color.parseColor(Hex));
            cardLanguageColor.setCardBackgroundColor(Color.parseColor(Hex));
        }

    }
}
