package com.example.demo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;

public final class ZonedDateTime implements Temporal, ChronoZonedDateTime<LocalDate>, Serializable {


    @Override
    public ChronoLocalDateTime<LocalDate> toLocalDateTime() {
        return null;
    }

    @Override
    public ZoneOffset getOffset() {
        return null;
    }

    @Override
    public ZoneId getZone() {
        return null;
    }

    @Override
    public ChronoZonedDateTime<LocalDate> withEarlierOffsetAtOverlap() {
        return null;
    }

    @Override
    public ChronoZonedDateTime<LocalDate> withLaterOffsetAtOverlap() {
        return null;
    }

    @Override
    public ChronoZonedDateTime<LocalDate> withZoneSameLocal(ZoneId zoneId) {
        return null;
    }

    @Override
    public ChronoZonedDateTime<LocalDate> withZoneSameInstant(ZoneId zoneId) {
        return null;
    }

    @Override
    public ChronoZonedDateTime<LocalDate> with(TemporalField temporalField, long l) {
        return null;
    }

    @Override
    public ChronoZonedDateTime<LocalDate> plus(long l, TemporalUnit temporalUnit) {
        return null;
    }

    @Override
    public long until(Temporal temporal, TemporalUnit temporalUnit) {
        return 0;
    }

    @Override
    public boolean isSupported(TemporalField temporalField) {
        return false;
    }
}
