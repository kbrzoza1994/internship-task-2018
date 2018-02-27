package pl.codewise.internships;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class Message implements Comparable {

    private final String userAgent;
    private final int errorCode;
    private LocalDateTime time;

    public LocalDateTime getTime() {
        return time;
    }


    public Message(String userAgent, int errorCode) {
        this.userAgent = userAgent;
        this.errorCode = errorCode;
        time = LocalDateTime.now();
    }

    public String getUserAgent() {
        return userAgent;
    }

    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
