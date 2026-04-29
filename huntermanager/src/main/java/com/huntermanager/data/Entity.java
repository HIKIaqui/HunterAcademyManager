package com.huntermanager.data;

public class Entity {
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
    private int baseArmor;

    public Entity(String name, int maxHP, int maxPE, int constitution, int agility, int mind, int social, int luck) {
        this.name = name;
        this.maxHP = maxHP;
        this.HP = maxHP;
        this.maxPE = maxPE;
        this.PE = maxPE;
        this.constitution = constitution;
        this.agility = agility;
        this.mind = mind;
        this.social = social;
        this.luck = luck;
        this.baseArmor = 0;
    }

     // ========== GETTERS ==========

    public String getName() {
        return name;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getHP() {
        return HP;
    }

    public int getArmor() {
        return baseArmor;
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

    public int getBaseArmor() {
        return baseArmor;
    }

    public int getDodge() {
        return 5 + (agility * 5);
    }
    

    // ========== SETTERS ==========

    public void setName(String name) {
        this.name = name;
    }

    protected void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public void setHP(int HP) {
        this.HP = Math.max(0, Math.min(HP, maxHP));
    }

    public void setArmor(int armor) {
        this.baseArmor = Math.max(0, armor);
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    protected void setAgility(int agility) {
        this.agility = agility;
    }

    public void setMind(int mind) {
        this.mind = mind;
    }

    public void setSocial(int social) {
        this.social = social;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public void setMaxPE(int maxPE) {
        this.maxPE = maxPE;
    }

    public void setBaseArmor(int baseArmor) {
        this.baseArmor = baseArmor;
    }

    // ========== HP ==========

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

}
