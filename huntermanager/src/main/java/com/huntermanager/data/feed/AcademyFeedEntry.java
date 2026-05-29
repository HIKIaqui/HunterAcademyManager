package com.huntermanager.data.feed;

import com.huntermanager.data.enums.AcademyFeedType;

public class AcademyFeedEntry {
    private final int day;
    private final int dayTime;
    private final AcademyFeedType type;
    private final String text;

    public AcademyFeedEntry(int day, int dayTime, AcademyFeedType type, String text) {
        this.day = day;
        this.dayTime = dayTime;
        this.type = type;
        this.text = text;
    }

    public int getDay() {
        return day;
    }

    public int getDayTime() {
        return dayTime;
    }

    public AcademyFeedType getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public String getTimeLabel() {
        return switch (dayTime) {
            case 0 -> "Manhã";
            case 1 -> "Tarde";
            case 2 -> "Noite";
            default -> "???";
        };
    }
}
