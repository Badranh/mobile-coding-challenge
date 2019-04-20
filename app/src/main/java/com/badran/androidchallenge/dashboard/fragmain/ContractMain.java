package com.badran.androidchallenge.dashboard.fragmain;

import com.badran.androidchallenge.dashboard.fragmain.adapters.AdapterRepo;

public interface ContractMain {
    interface View{
        void showToast(String Message);
    }

    interface Presenter{
        void fetchData();
        void bindViewHolders(AdapterRepo.ViewHolder holder, int pos);
        int getDataCount();
        void onDestroy();
    }
}
