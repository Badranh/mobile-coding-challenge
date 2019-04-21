package com.badran.androidchallenge.dashboard.fragmain;

import com.badran.androidchallenge.dashboard.fragmain.adapters.RepoViewHolder;

public interface ContractMain {
    interface View{
        void showToast(String Message);
    }

    interface Presenter{
        void fetchData();
        void bindViewHolders(RepoViewHolder holder, int pos);
        int getDataCount();
        void onDestroy();
    }
}
