
package com.leeco.video.api;

import javax.persistence.*;

/**
 * @author Cedric Beust <cedric@beust.com>
 * @since 12/12/2016
 */
@Entity(name = "videos")
public class Video {
    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;

    @Column
    public String title;

    @Column
    public int year;

    public Video() {}

    public Video(String title, int year) {
        this.title = title;
        this.year = year;
    }
}
