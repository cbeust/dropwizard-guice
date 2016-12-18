package com.leeco.video;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

/**
 * @author Cedric Beust <cedric@beust.com>
 * @since 12/13/2016
 */
public interface GithubService {
    @GET("repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> repoContributors(
            @Path("owner") String owner,
            @Path("repo") String repo);


    static class Contributor {
        String login;
        String html_url;

        int contributions;

        @Override
        public String toString() {
            return login + " (" + contributions + ")";
        }
    }
}

