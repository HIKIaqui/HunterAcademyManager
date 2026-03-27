package com.huntermanager.data;

public class HunterAcademy {

    private MonsterHunter[] roster = new MonsterHunter[10];
    private int maxRoster;
    private int stars;
    private int currentDay;
    private int currentDayTime;
    private int[] clinicSlots = {-1, -1};
    private int[] barSlots = {-1, -1};

    private Item[] inventory = new Item[20];

    public static final int MORNING = 0;
    public static final int AFTERNOON = 1;
    public static final int NIGHT = 2;


// ========== GETTERS ==========

    public MonsterHunter[] getRoster() {
        return roster;
    }

    public int getHunterCount() {
        int count = 0;
        for (MonsterHunter h : roster) {
            if (h != null) count++;
        }
        return count;
    }

    public int getHunterIndex(MonsterHunter hunter) {
    for (int i = 0; i < roster.length; i++) {
        if (roster[i] == hunter) {
            return i;
        }
    }
    return -1;
}

    public MonsterHunter getHunterByIndex(int index) {
        if (index >= 0 && index < roster.length) {
            return roster[index];
        }
        return null;
    }

    public MonsterHunter[] getActiveHunters() {
        int count = getHunterCount();
        MonsterHunter[] active = new MonsterHunter[count];

        int index = 0;
        for (int i = 0; i < maxRoster; i++) {
            if (roster[i] != null) {
                active[index++] = roster[i];
            }
        }
        return active;
    }

    public MonsterHunter getHunterByDisplayIndex(int displayIndex) {
        int realIndex = getRealIndexFromDisplayIndex(displayIndex);
        if (realIndex == -1) 
            { return null; } 
        else 
            { return getHunterByIndex(realIndex); }
    }

    public int getRealIndexFromDisplayIndex(int displayIndex) {
        if (displayIndex < 0 || displayIndex >= getHunterCount()) {
            return -1;
        }

        int currentDisplayIndex = 0;

        for (int i = 0; i < roster.length; i++) {
            if (roster[i] != null) {
                if (currentDisplayIndex == displayIndex) {
                    return i;
                }
                currentDisplayIndex++;
            }
        }

        return -1;
    }

    public int getMaxRoster() {
        return maxRoster;
    }

    public int getStars() {
        return stars;
    }

    public int getCurrentDay() {
        return currentDay;
    }

    public int getCurrentDayTime() {
        return currentDayTime;
    }

    public int[] getClinicSlots() {
        return clinicSlots;
    }

    public int[] getBarSlots() {
        return barSlots;
    }

// ========== SETTERS ==========

    public void setRoster(MonsterHunter[] roster) {
        this.roster = roster;
    }

    public void setMaxRoster(int maxRoster) {
        this.maxRoster = maxRoster;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public void setCurrentDay(int currentDay) {
        this.currentDay = currentDay;
    }

    public void setCurrentDayTime(int currentDayTime) {
        this.currentDayTime = currentDayTime;
    }

    public void setClinicSlots(int[] clinicSlots) {
        this.clinicSlots = clinicSlots;
    }

    public void setBarSlots(int[] barSlots) {
        this.barSlots = barSlots;
    }

// ========== FUNCTIONS ==========

    public boolean addHunter(MonsterHunter hunter) {
        for (int i = 0; i < maxRoster; i++) {
            if (roster[i] == null) {
                roster[i] = hunter;
                return true;
            }
        }
        return false;
    }

    public boolean removeHunter(int index) {
        if (index >= 0 && index < roster.length && roster[index] != null) {
            removeHunterFromClinic(index);
            removeHunterFromBar(index);
            roster[index] = null;
            return true;
        }
        return false;
    }

    public boolean isRosterFull() {
        return getHunterCount() >= maxRoster;
    }

    public void advanceDay() {
        currentDay++;
        currentDayTime = 0;
        generateNewAssignments();
    }

    public void advanceDayTime() {
        barCycleLogic();
        clinicCycleLogic();
        if (currentDayTime < NIGHT) {
            currentDayTime++;
        } else {
            advanceDay();
        }
    }

    public boolean hasStars(int amount) {
        return stars >= amount;
    }

    public boolean spendStars(int amount) {
        if (stars >= amount) {
            stars -= amount;
            return true;
        }
        return false;
    }

    public void addStars(int amount) {
        stars += amount;
    }


// ====== CLÍNICA ======

    public boolean isClinicFull() {
        for (int slot : clinicSlots) {
            if (slot == -1) return false;
        }
        return true;
    }

    public MonsterHunter[] getHuntersInClinic() {
        int count = 0;
        for (int slot : clinicSlots) {
            if (slot != -1) count++;
        }

        MonsterHunter[] hunters = new MonsterHunter[count];
        int index = 0;

        for (int slot : clinicSlots) {
            if (slot != -1) {
                hunters[index++] = roster[slot];
            }
        }

        return hunters;
    }

    public boolean isHunterInClinic(int hunterIndex) {
        for (int slot : clinicSlots) {
            if (slot == hunterIndex) {
                return true;
            }
        }
        return false;
    }

    public boolean addHunterToClinic(int hunterIndex) {
        if (hunterIndex < 0 || hunterIndex >= roster.length || roster[hunterIndex] == null) {
            return false;
        }
        if (isHunterInBar(hunterIndex)) {
            return false;
        }

        if (isHunterInClinic(hunterIndex)) {
            return false;
        }

        for (int i = 0; i < clinicSlots.length; i++) {
            if (clinicSlots[i] == -1) {
                clinicSlots[i] = hunterIndex;
                return true;
            }
        }

        return false;
    }

    public boolean removeHunterFromClinic(int hunterIndex) {
        for (int i = 0; i < clinicSlots.length; i++) {
            if (clinicSlots[i] == hunterIndex) {
                clinicSlots[i] = -1;
                return true;
            }
        }
        return false;
    }
    


    public void clinicCycleLogic() {
    for (int slot : clinicSlots) {
        if (slot != -1) {
            MonsterHunter hunter = roster[slot];

            if (hunter != null) {
                int healAmount = 8 + (hunter.getConstitution() * 2);
                hunter.heal(healAmount);

                int stressReduction = 1 * (Math.max(0, hunter.getSocial() - 1));
                hunter.reduceStress(stressReduction);

                removeHunterFromClinic(getHunterIndex(hunter));
            }
        }
    }
}

// ====== BAR ======

    public boolean isBarFull() {
        for (int slot : barSlots) {
            if (slot == -1) return false;
        }
        return true;
    }

    public MonsterHunter[] getHuntersInBar() {
        int count = 0;
        for (int slot : barSlots) {
            if (slot != -1) count++;
        }

        MonsterHunter[] hunters = new MonsterHunter[count];
        int index = 0;

        for (int slot : barSlots) {
            if (slot != -1) {
                hunters[index++] = roster[slot];
            }
        }

        return hunters;
    }

    public boolean isHunterInBar(int hunterIndex) {
        for (int slot : barSlots) {
            if (slot == hunterIndex) {
                return true;
            }
        }
        return false;
    }

    public boolean addHunterToBar(int hunterIndex) {
        if (hunterIndex < 0 || hunterIndex >= roster.length || roster[hunterIndex] == null) {
            return false;
        }

        if (isHunterInClinic(hunterIndex)) {
            return false;
        }

        if (isHunterInBar(hunterIndex)) {
            return false;
        }

        for (int i = 0; i < barSlots.length; i++) {
            if (barSlots[i] == -1) {
                barSlots[i] = hunterIndex;
                return true;
            }
        }

        return false;
    }

    public boolean removeHunterFromBar(int hunterIndex) {
        for (int i = 0; i < barSlots.length; i++) {
            if (barSlots[i] == hunterIndex) {
                barSlots[i] = -1;
                return true;
            }
        }
        return false;
    }

    public void barCycleLogic() {
        for (int slot : barSlots) {
            if (slot != -1) {
                MonsterHunter hunter = roster[slot];
                if (hunter != null) {
                    int stressReduction = 1 + hunter.getSocial();
                    hunter.reduceStress(stressReduction);

                    int peRecovery = 2 * (Math.max(0, hunter.getMind() - 1));
                    hunter.recoverPE(peRecovery);

                    removeHunterFromBar(getHunterIndex(hunter));
                }
            }
        }
    }

// ========== CONTRATOS ==========

    private Assignment[] availableAssignments = new Assignment[3];

    public Assignment[] getAvailableAssignments() {
        return availableAssignments;
    }

    public void setAvailableAssignments(Assignment[] availableAssignments) {
        this.availableAssignments = availableAssignments;
    }

    public void generateNewAssignments() {
        for (int i = 0; i < availableAssignments.length; i++) {
            availableAssignments[i] = com.huntermanager.data.enums.AssignmentTemplates.createRandomAssignment();
        }
    }

    public boolean isHunterAvailableForAssignment(int hunterIndex) {
        if (hunterIndex < 0 || hunterIndex >= roster.length) return false;
        if (roster[hunterIndex] == null) return false;
        if (isHunterInClinic(hunterIndex)) return false;
        if (isHunterInBar(hunterIndex)) return false;
        return true;
    }

    public MonsterHunter[] getAvailableHuntersForAssignment() {
        int count = 0;

        for (int i = 0; i < maxRoster; i++) {
            if (isHunterAvailableForAssignment(i)) {
                count++;
            }
        }

        MonsterHunter[] available = new MonsterHunter[count];
            int index = 0;

            for (int i = 0; i < maxRoster; i++) {
                if (isHunterAvailableForAssignment(i)) {
                    available[index++] = roster[i];
                }
            }

            return available;
        }

    public int[] getAvailableHunterIndexesForAssignment() {
        int count = 0;

        for (int i = 0; i < maxRoster; i++) {
            if (isHunterAvailableForAssignment(i)) {
                count++;
            }
        }

        int[] availableIndexes = new int[count];
        int index = 0;

        for (int i = 0; i < maxRoster; i++) {
            if (isHunterAvailableForAssignment(i)) {
                availableIndexes[index++] = i;
            }
        }

        return availableIndexes;
    }

// ========== ARMAZENAMENTO ==========

    public boolean addItem(Item item) {
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] == null) {
                inventory[i] = item;
                return true;
            }
        }
        return false;
    }

    public boolean removeItem(int index) {
        if (index >= 0 && index < inventory.length && inventory[index] != null) {
            inventory[index] = null;
            return true;
        }
        return false;
    }

    public Item[] getInventory() {
        return inventory;
    }

    public int getInventoryCount() {
        int count = 0;
        for (Item item : inventory) {
            if (item != null) count++;
        }
        return count;
    }

    public int getRealInventoryIndex(int displayIndex) {
        if (displayIndex < 0 || displayIndex >= getInventoryCount()) {
            return -1;
        }

        int current = 0;

        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null) {
                if (current == displayIndex) {
                    return i;
                }
                current++;
            }
        }

        return -1;
    }

// RETORNA O REAL NÚMERO DE ITENS NO INVENTÁRIO
    public Item[] getActiveItems() {
        int count = getInventoryCount();
        Item[] active = new Item[count];

        int index = 0;
        for (Item item : inventory) {
            if (item != null) {
                active[index++] = item;
            }
        }

        return active;
    }

    public boolean equipItemToHunter(Item item, MonsterHunter hunter) {
        if (item == null || hunter == null) return false;

        // item precisa existir no inventário da academia
        boolean itemExists = false;
        for (Item inventoryItem : inventory) {
            if (inventoryItem == item) {
                itemExists = true;
                break;
            }
        }

        if (!itemExists) return false;

        // se já estiver equipado em alguém, remove de lá primeiro
        if (item.isEquipped()) {
            MonsterHunter previousHunter = item.getEquippedBy();
            unequipItemFromHunter(item, previousHunter);
        }

        switch (item.getType()) {
            case WEAPON:
                if (hunter.getEquippedWeapon() != null) {
                    hunter.getEquippedWeapon().setEquippedBy(null);
                }
                hunter.setEquippedWeapon(item);
                break;
            case ARMOR:
                if (hunter.getEquippedArmor() != null) {
                    hunter.getEquippedArmor().setEquippedBy(null);
                }
                hunter.setEquippedArmor(item);
                break;
            case ACCESSORY:
                if (hunter.getEquippedAccessory() != null) {
                    hunter.getEquippedAccessory().setEquippedBy(null);
                }
                hunter.setEquippedAccessory(item);
                break;
            default:
                return false;
        }

        item.setEquippedBy(hunter);
        return true;
    }

    public boolean unequipItemFromHunter(Item item, MonsterHunter hunter) {
        if (item == null || hunter == null) return false;

        switch (item.getType()) {
            case WEAPON:
                if (hunter.getEquippedWeapon() == item) {
                    hunter.setEquippedWeapon(null);
                }
                break;
            case ARMOR:
                if (hunter.getEquippedArmor() == item) {
                    hunter.setEquippedArmor(null);
                }
                break;
            case ACCESSORY:
                if (hunter.getEquippedAccessory() == item) {
                    hunter.setEquippedAccessory(null);
                }
                break;
            default:
                return false;
        }

        if (item.getEquippedBy() == hunter) {
            item.setEquippedBy(null);
        }

        return true;
    }
}