package com.buzz.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class DateTimeUtil {
    private final long NONE_TIME = 0;
    private ZoneOffset offset = ZoneId.systemDefault().getRules().getOffset(Instant.now());

    public LocalDateTime toLocalDateTime(Long timestamp) {
        if (timestamp == null || timestamp == NONE_TIME) {
            return null;
        }
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp * 1000L), ZoneId.systemDefault());
    }

    public long toTimestamp(LocalDateTime dateTime) {
        if (dateTime == null) {
            return NONE_TIME;
        }

        return dateTime.toInstant(offset).toEpochMilli() / 1000L;
    }

    public long valueOf(Long time) {
        if (time == null) {
            return NONE_TIME;
        }

        return time.longValue();
    }
}
