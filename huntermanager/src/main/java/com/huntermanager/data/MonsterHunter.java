package com.huntermanager.data;

public class MonsterHunter {
    private String name;
    private int constitution;
    private int agility;
    private int mind;
    private int social;
    private int luck;

    private int maxPE;
    private int PE;
    private int maxHP;
    private int HP;
    private final int maxStress = 10;
    private int stress;

    private int baseArmor;

    private void calculateMaxStats() {
        this.maxHP = 15 + (constitution * 10);
        this.maxPE = 4 + (mind * 2) + (constitution * 2);
    }

    private void resetCurrentStats() {
        this.HP = maxHP;
        this.PE = maxPE;
        this.stress = 0;
    }

    public MonsterHunter(String name, int constitution, int agility, int mind, int social, int luck) {
        this.name = name;
        this.constitution = constitution;
        this.agility = agility;
        this.mind = mind;
        this.social = social;
        this.luck = luck;
        this.baseArmor = 0;

        calculateMaxStats();
        resetCurrentStats();
    }

    // ========== GETTERS ==========

    public String getName() {
        return name;
    }

    public int getConstitution() {
        return constitution;
    }

    public int getAgility() {
        return agility;
    }

    public int getMind() {
        return mind;
    }

    public int getSocial() {
        return social;
    }

    public int getLuck() {
        return luck;
    }

    public int getMaxPE() {
        return maxPE;
    }

    public int getPE() {
        return PE;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getHP() {
        return HP;
    }

    public int getMaxStress() {
        return maxStress;
    }

    public int getStress() {
        return stress;
    }

    public int getArmor() {
        return baseArmor;
    }

    public int getDodge() {
        return 5 + (agility * 5);
    }

    // ========== SETTERS ==========

    public void setArmor(int armor) {
        this.baseArmor = Math.max(0, armor);
    }

    // ========== HP ==========

    public void setHP(int HP) {
        this.HP = Math.max(0, Math.min(HP, maxHP));
    }

    public void takeDamage(int damage) {
        if (damage > 0) {
            int finalDamage = Math.max(1, damage - getArmor());
            setHP(this.HP - finalDamage);
        }
    }

    public void takeDamageIgnoreArmor(int damage) {
        if (damage > 0) {
            int finalDamage = Math.max(1, damage);
            setHP(this.HP - finalDamage);
        }
    }

    public void heal(int amount) {
        if (amount > 0) {
            setHP(this.HP + amount);
        }
    }

    public boolean isAlive() {
        return HP > 0;
    }

    // ========== PE ==========

    public void setPE(int PE) {
        this.PE = Math.max(0, Math.min(PE, maxPE));
    }

    public void spendPE(int amount) {
        if (amount > 0) {
            setPE(this.PE - amount);
        }
    }

    public void recoverPE(int amount) {
        if (amount > 0) {
            setPE(this.PE + amount);
        }
    }

    public boolean isExhausted() {
        return PE <= 0;
    }

    // ========== STRESS ==========

    public void setStress(int stress) {
        this.stress = Math.max(0, Math.min(stress, maxStress));
    }

    public void addStress(int amount) {
        if (amount > 0) {
            setStress(this.stress + amount);
        }
    }

    public void reduceStress(int amount) {
        if (amount > 0) {
            setStress(this.stress - amount);
        }
    }

    public boolean isStressedOut() {
        return stress >= maxStress;
    }
}