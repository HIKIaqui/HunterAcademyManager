package com.huntermanager.data;

import java.util.Arrays;

public class SelectedAssignment {
    private Assignment assignment;
    private final int[] selectedHuntersForAssignment = {-1, -1, -1};
    private final int[] selectedPositionsForAssignment = {-1, -1, -1};
    private final int[] selectedCombatStyles = {-1, -1, -1};

    public void selectAssignment(Assignment assignment) {
        this.assignment = assignment;
        clearTeam();
    }

    public void clear() {
        this.assignment = null;
        clearTeam();
    }

    private void clearTeam() {
        Arrays.fill(selectedHuntersForAssignment, -1);
        Arrays.fill(selectedPositionsForAssignment, -1);
        Arrays.fill(selectedCombatStyles, -1);
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public boolean hasAssignment() {
        return assignment != null;
    }

    public boolean addHunter(int hunterIndex) {
        if (isHunterAlreadySelected(hunterIndex)) {
            return false;
        }

        for (int i = 0; i < selectedHuntersForAssignment.length; i++) {
            if (selectedHuntersForAssignment[i] == -1) {
                selectedHuntersForAssignment[i] = hunterIndex;
                selectedPositionsForAssignment[i] = -1;
                selectedCombatStyles[i] = -1;
                return true;
            }
        }

        return false;
    }

    public boolean removeHunter(int slotIndex) {
        if (!hasHunterInSlot(slotIndex)) {
            return false;
        }

        selectedHuntersForAssignment[slotIndex] = -1;
        selectedPositionsForAssignment[slotIndex] = -1;
        selectedCombatStyles[slotIndex] = -1;
        return true;
    }

    public boolean hasHunterInSlot(int slotIndex) {
        return slotIndex >= 0
                && slotIndex < selectedHuntersForAssignment.length
                && selectedHuntersForAssignment[slotIndex] != -1;
    }

    public boolean setPosition(int slotIndex, int position) {
        if (!hasHunterInSlot(slotIndex)) {
            return false;
        }

        if (position < 0 || position > 2) {
            return false;
        }

        selectedPositionsForAssignment[slotIndex] = position;
        return true;
    }

    public boolean setCombatStyle(int slotIndex, int style) {
        if (!hasHunterInSlot(slotIndex)) {
            return false;
        }

        if (style < 0 || style > 3) {
            return false;
        }

        selectedCombatStyles[slotIndex] = style;
        return true;
    }

    public boolean isHunterAlreadySelected(int hunterIndex) {
        for (int selected : selectedHuntersForAssignment) {
            if (selected == hunterIndex) {
                return true;
            }
        }
        return false;
    }

    public boolean isReady() {
        boolean hasAtLeastOneHunter = false;

        for (int i = 0; i < selectedHuntersForAssignment.length; i++) {
            if (selectedHuntersForAssignment[i] != -1) {
                hasAtLeastOneHunter = true;

                if (selectedPositionsForAssignment[i] == -1
                        || selectedCombatStyles[i] == -1) {
                    return false;
                }
            }
        }

        return hasAtLeastOneHunter;
    }

    public int getHunterAt(int slotIndex) {
        return selectedHuntersForAssignment[slotIndex];
    }

    public int getPositionAt(int slotIndex) {
        return selectedPositionsForAssignment[slotIndex];
    }

    public int getCombatStyleAt(int slotIndex) {
        return selectedCombatStyles[slotIndex];
    }

    public static String getPositionName(int position) {
        return switch (position) {
            case 0 -> "Frente";
            case 1 -> "Meio";
            case 2 -> "Retaguarda";
            default -> "Não definida";
        };
    }

    public static String getCombatStyleName(int style) {
        return switch (style) {
            case 0 -> "Corpo a Corpo";
            case 1 -> "À Distância";
            case 2 -> "Suporte";
            case 3 -> "Magia";
            default -> "Não definido";
        };
    }
}