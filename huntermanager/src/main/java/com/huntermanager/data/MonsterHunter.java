package com.huntermanager.data;

import java.util.ArrayList;
import java.util.List;

import com.huntermanager.data.enums.Trait;
import com.huntermanager.data.enums.Trauma;



public class MonsterHunter extends Entity {
    private final int maxStress = 10;
    private int stress;
    private Item equippedWeapon;
    private Item equippedArmor;
    private Item equippedAccessory;

    private List<Trauma> traumas;
    private List<Trait> traits;

    public MonsterHunter(String name, int constitution, int agility, int mind, int social, int luck) {
        super(
            name,
            15 + (constitution * 10),          // maxHP
            4 + (mind * 3) + constitution,     // maxPE
            constitution,
            agility,
            mind,
            social,
            luck
        );

        this.stress = 0;
        this.traits = new ArrayList<>();
        this.traumas = new ArrayList<>();

    }

// ========== GETTERS ==========

    public int getMaxStress() {
        return maxStress;
    }

    public int getStress() {
        return stress;
    }

    public List<Trauma> getTraumas() {
        return traumas;
    }

    public List<Trait> getTraits() {
        return traits;
    }

    public Item getEquippedWeapon() {
        return equippedWeapon;
    }

    public Item getEquippedArmor() {
        return equippedArmor;
    }

    public Item getEquippedAccessory() {
        return equippedAccessory;
    }

// ========== SETTERS ==========

    public void setEquippedWeapon(Item item) {
        this.equippedWeapon = item;
    }

    public void setEquippedArmor(Item item) {
        this.equippedArmor = item;
    }

    public void setEquippedAccessory(Item item) {
        this.equippedAccessory = item;
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

// ========== TRAUMAS ==========

    public boolean hasTrauma(Trauma trauma) {
        return traumas.contains(trauma);
    }

    public boolean addTrauma(Trauma trauma) {
        if (trauma != null && !traumas.contains(trauma)) {
            traumas.add(trauma);
            return true;
        }
        return false;
    }

    public boolean removeTrauma(Trauma trauma) {
        return traumas.remove(trauma);
    }

// ========== TRAITS ==========

    public boolean hasTrait(Trait trait) {
        return traits.contains(trait);
    }

    public boolean addTrait(Trait trait) {
        if (trait != null && !traits.contains(trait)) {
            traits.add(trait);
            return true;
        }
        return false;
    }

    public boolean removeTrait(Trait trait) {
        return traits.remove(trait);
    }

// ========== RECALCULAR STATUS ==========

    public void recalculateStats() {
        setMaxHP(15 + (getConstitution() * 10));
        setMaxPE(4 + (getMind() * 3) + getConstitution());

        if (getHP() > getMaxHP()) {
            setHP(getMaxHP());
        }

        if (getPE() > getMaxPE()) {
            setPE(getMaxPE());
        }
    }
}