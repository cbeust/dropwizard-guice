package com.leeco.video.views;

import com.google.inject.Inject;
import com.leeco.video.GithubService;
import com.leeco.video.IClock;
import com.leeco.video.api.Video;
import com.leeco.video.resources.VideoListApi;
import io.dropwizard.views.View;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

/**
 * @author Cedric Beust <cedric@beust.com>
 * @since 12/12/2016
 */
public class VideoListView extends View {
    @Inject
    private IClock clock;

    @Inject
    private VideoListApi videoListApi;

    public VideoListView() {
        super("/video-list-view.mustache");
    }

    public String getDate() {
        return clock.now();
    }

    public List<Video> getVideos() {
        return videoListApi.getVideos();
    }

    @Inject
    private GithubService githubService;

    public List<GithubService.Contributor> getContributors() throws IOException {
        Call<List<GithubService.Contributor>> result =
                githubService.repoContributors("cbeust", "testng");

        Response<List<GithubService.Contributor>> contributors = result.execute();
        return contributors.body();
    }

}
