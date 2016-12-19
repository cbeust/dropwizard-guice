package com.leeco.video;

import com.leeco.video.api.Video;

import java.util.List;

/**
 * @author Cedric Beust <cedric@beust.com>
 * @since 12/19/2016
 */
public interface Db {
    List<Video> getVideos();
}
