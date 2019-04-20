package com.badran.androidchallenge;


import com.badran.androidchallenge.data.api.DataRepository;
import com.badran.androidchallenge.data.api.GithubService;
import com.badran.androidchallenge.data.models.GithubRepo;
import com.badran.androidchallenge.data.models.GithubRepoOwner;
import com.badran.androidchallenge.data.models.GithubRepos;
import com.google.gson.Gson;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

public class ServerTest {

    private static final String BASE_URL = "https://api.github.com/";
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    @Mock
    DataRepository dataRepository;
    private GithubRepos githubRepos;
    private TestObserver<GithubRepos> mSubscriber;
    private MockWebServer mMockWebServer;
    private Retrofit retrofit;

    @Before
    public void init() {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
        mSubscriber = new TestObserver<>();
        mMockWebServer = new MockWebServer();
        githubRepos = new GithubRepos();
        githubRepos.getGithubRepo().add(new GithubRepo("asd", "asd", 1, 1, "a", new GithubRepoOwner("asd", "asd")));
        githubRepos.getGithubRepo().add(new GithubRepo("ba", "asds", 1, 1, "a", new GithubRepoOwner("asd", "asd")));
        githubRepos.getGithubRepo().add(new GithubRepo("avasd", "asdd", 1, 1, "a", new GithubRepoOwner("asd", "asd")));

    }


    @Test
    public void serverCallWithError() {
        //Given
        mMockWebServer.enqueue(new MockResponse().setBody(new Gson().toJson("asd")));
        retrofit = new Retrofit.Builder().baseUrl(mMockWebServer.url("asd/").toString())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GithubService remoteDataSource = retrofit.create(GithubService.class);

        remoteDataSource.getRepos("asd", "Asd", "Asd").subscribe(mSubscriber);

        mSubscriber.assertValueCount(0);
        mSubscriber.assertNoTimeout();
        mSubscriber.assertNotComplete();
    }

    @Test
    public void serverCallWithSuccess() {
        //Given
        mMockWebServer.enqueue(new MockResponse().setBody(new Gson().toJson(githubRepos)));
        retrofit = new Retrofit.Builder().baseUrl(mMockWebServer.url(BASE_URL).toString())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GithubService remoteDataSource = retrofit.create(GithubService.class);

        remoteDataSource.getRepos("asd", "Asd", "Asd").subscribe(mSubscriber);

        mSubscriber.assertValueCount(1);
        mSubscriber.assertNoTimeout();
        mSubscriber.assertComplete();
    }


    @Test
    public void testOne() {
        when(dataRepository.getTrendingRepos("crfgkd", "stadsrs", "dfsk")).thenReturn(
                Single.error(new Throwable())
        );
        dataRepository.getTrendingRepos("crfgkd", "stadsrs", "dfsk").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<GithubRepos>() {
            @Override
            public void onSuccess(GithubRepos value) {

                fail();
            }

            @Override
            public void onError(Throwable e) {
            }
        });

    }
}
