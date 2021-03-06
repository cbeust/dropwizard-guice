package com.leeco.video.resources;

import com.google.inject.Inject;
import com.leeco.video.Db;
import com.leeco.video.api.Video;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Cedric Beust <cedric@beust.com>
 * @since 12/15/2016
 */
@Path("/v0/getVideos")
public class VideoListApi {
    @Inject
    private Db db;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Video> getVideos() {
        return db.getVideos();
    }
}
