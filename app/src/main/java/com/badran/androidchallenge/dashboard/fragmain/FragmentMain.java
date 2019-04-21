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
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(RecyclerView.VERTICAL);
        reposRecyclerView.setLayoutManager(llm);
        reposRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (llm.findLastVisibleItemPosition() == llm.getItemCount() - 1) {
                    //prevent duplicate requests
                    if (viewModel.getIsLoading().getValue() == false)
                        presenter.fetchData();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void observableViewModel() {
        viewModel.getRepos().observe(getViewLifecycleOwner(), repos -> {
            if(repos != null) {
                adapterRepo.notifyDataSetChanged();
            }
        });
        viewModel.getIsLoading().observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading != null) {
                if (isLoading) {
                    loadingAnimation.setVisibility(View.VISIBLE);
                    loadingAnimation.playAnimation();
                } else {
                    loadingAnimation.setVisibility(View.GONE);
                    loadingAnimation.pauseAnimation();
                }
            }
        });
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
        if (presenter != null)
            presenter.onDestroy();
        presenter = null;
    }


}
