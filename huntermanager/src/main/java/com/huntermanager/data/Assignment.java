package com.huntermanager.data;

import com.huntermanager.data.enums.AssignmentThreat;

public class Assignment {
    private String title;
    private String description;
    private int difficulty;
    private AssignmentThreat mainThreat;

    public Assignment(String title, String description, int difficulty, AssignmentThreat mainThreat) {
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.mainThreat = mainThreat;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public AssignmentThreat getMainThreat() {
        return mainThreat;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void setMainThreat(AssignmentThreat mainThreat) {
        this.mainThreat = mainThreat;
    }
}