package com.leeco.video;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Cedric Beust <cedric@beust.com>
 * @since 12/12/2016
 */
public class Clock implements IClock {
    public String now() {
        return LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("dd LLLL yyyy hh:mm:ss"));
    }
}
