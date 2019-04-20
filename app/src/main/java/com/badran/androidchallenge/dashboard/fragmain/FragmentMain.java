package com.badran.androidchallenge.dashboard.fragmain;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.badran.androidchallenge.R;
import com.badran.androidchallenge.base.BaseFragment;
import com.badran.androidchallenge.dashboard.fragmain.adapters.AdapterRepo;
import com.badran.androidchallenge.di.annotations.ActivityScoped;
import javax.inject.Inject;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

@ActivityScoped
public class FragmentMain extends BaseFragment implements ContractMain.View {

    @Inject
    ContractMain.Presenter presenter;

    @Inject
    ViewModelMain viewModel;

    @Inject
    AdapterRepo adapterRepo;

    @BindView(R.id.rec_repos)
    RecyclerView reposRecyclerView;

    @BindView(R.id.loading_anim)
    LottieAnimationView loadingAnimation;

    @Override
    protected int LayoutRes() {
        return R.layout.frag_main;
    }

    @Inject
    public FragmentMain() {
    }

    @Override
    public void showToast(String Message) {
        Toast.makeText(getContext(),Message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
            initRecyclerView();
            observableViewModel();
    }

    private void initRecyclerView() {
        //Animations are no longer scary
        ScaleInAnimationAdapter scaleInAnimationAdapter = new ScaleInAnimationAdapter(adapterRepo);
        scaleInAnimationAdapter.setFirstOnly(false);
        //
        reposRecyclerView.setAdapter(scaleInAnimationAdapter);
        reposRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void observableViewModel() {
        //ah, you're here, well let me get rid of that weird cat
        viewModel.getRepos().observe(getViewLifecycleOwner(), repos -> {
            if(repos != null) {
                adapterRepo.notifyDataSetChanged();
            }
        });
        //Bye bye cat, there's no place for you any more. May you find better person...
        viewModel.getIsLoading().observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading != null) {
                loadingAnimation.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                loadingAnimation.cancelAnimation();
                //ok, I know how to kick u out :)
                loadingAnimation.invalidate();
            }
        });
        //Hey cat,I'm sorry please can you stay
        viewModel.getGotAnError().observe(getViewLifecycleOwner(), isError -> {
            if (isError != null) if(isError) {
                showToast("Error Loading Repos");
            }
        });
        //Please don't consume my megabytes :@, Screen Orientation Guard (& - &)
        viewModel.getSuccessfullyLoaded().observe(getViewLifecycleOwner(),isLoaded ->{
            if(isLoaded!=null)
                if(!isLoaded)
                    presenter.fetchData();
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }


}
