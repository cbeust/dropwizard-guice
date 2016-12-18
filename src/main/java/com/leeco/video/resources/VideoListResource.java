package com.leeco.video.resources;

import com.google.inject.Inject;
import com.leeco.video.views.VideoListView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Cedric Beust <cedric@beust.com>
 * @since 12/12/2016
 */
@Path("/list")
public class VideoListResource {
    @Inject
    private VideoListView videoListView;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public VideoListView getVideoList() {
        return videoListView;
    }
}
