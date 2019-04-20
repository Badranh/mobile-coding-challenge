package com.badran.androidchallenge.dashboard.fragmain;

import com.badran.androidchallenge.dashboard.fragmain.adapters.RepoViewHolder;
import com.badran.androidchallenge.data.GitColorsUtil;
import com.badran.androidchallenge.data.api.DataRepository;
import com.badran.androidchallenge.data.models.GithubRepo;
import com.badran.androidchallenge.data.models.GithubRepos;
import com.badran.androidchallenge.di.annotations.ActivityScoped;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


//This class maintains the presenter state only with no coupling to the view

@ActivityScoped
public class PresenterMain implements ContractMain.Presenter {

    @Inject
    ViewModelMain viewModel;
    @Inject
    DataRepository dataRepository;

    private CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    public PresenterMain() {
    }

    @Override
    public void fetchData() {
        viewModel.setIsLoading(true);
        disposable.add(dataRepository.getTrendingRepos("created:>2019-01-01", "stars", "desc", viewModel.getCurPage()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<GithubRepos>() {
                    @Override
                    public void onSuccess(GithubRepos value) {
                        if (viewModel.reposSize() == 0)
                            viewModel.setReposData(value.getGithubRepo());
                        else
                            viewModel.addToRepos(value.getGithubRepo());
                        viewModel.setGotAnError(false);
                        viewModel.setIsLoading(false);
                        viewModel.setSuccessfullyLoaded(true);
                        viewModel.incrementPage();
                    }

                    @Override
                    public void onError(Throwable e) {
                        viewModel.setGotAnError(true);
                        viewModel.setIsLoading(false);
                    }
                }));
    }

    @Override
    public void bindViewHolders(RepoViewHolder holder, int pos) {
        GithubRepo githubRepo = viewModel.getRepos().getValue().get(pos);
        if(viewModel.getRepos().getValue()!=null){
            String languageName;

            String hex = GitColorsUtil.getColorHex(githubRepo.getLanguageUsed()).getColor();

            if(githubRepo.getLanguageUsed() == null || githubRepo.getLanguageUsed().isEmpty())
                languageName = "Unknown";
            else
                languageName = githubRepo.getLanguageUsed();

            holder.setDataToTextView(githubRepo.getRepoName(), githubRepo.getRepoDesc(), languageName,
                    githubRepo.getOwner().getUsername(), String.valueOf(githubRepo.getStarCount()));
            holder.setImageToAvatar(githubRepo.getOwner().getAvatarUrl());

            if(hex!=null)
              holder.setLanguageColor(hex);
        }
    }

    @Override
    public int getDataCount() {
        return viewModel.getRepos().getValue()==null?0:viewModel.getRepos().getValue().size();
    }

    @Override
    public void onDestroy() {
        if(disposable!=null && !disposable.isDisposed()){
            disposable.dispose();
            disposable = null;
        }

    }
}
