package com.huntermanager.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huntermanager.data.enums.EquipmentSlot;
import com.huntermanager.data.enums.Origin;
import com.huntermanager.data.enums.Stats;
import com.huntermanager.data.enums.Trait;
import com.huntermanager.data.enums.Trauma;
import com.huntermanager.data.itemTypes.itemData.Equippable;
import com.huntermanager.data.itemTypes.itemData.StatsModifier;



public class MonsterHunter extends Entity {
    private final int maxStress = 10;
    private final Map<EquipmentSlot, Equippable> equipment = new HashMap<>();
    private final List<StatsModifier> modifiers = new ArrayList<>();



    private int stress;
    private Item equippedWeapon;
    private Item equippedArmor;
    private Item equippedAccessory;

    private List<Trauma> traumas;
    private List<Trait> traits;
    private List<Origin> origins;

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
        this.origins = new ArrayList<>();

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
    public List<Origin> getOrigins() {
        return origins;
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

// ========== ORIGINS ==========

    public boolean hasOrigin(Origin origin) {
        return origins.contains(origin);
    }

    public boolean addOrigin(Origin origin) {
        if (origin != null && !origins.contains(origin)) {
            origins.add(origin);
            return true;
        }
        return false;
    }

    public boolean removeOrigin(Origin origin) {
        return origins.remove(origin);
    }

// ========== STATS MODIFIERS ==========

    public void equip(Equippable item) {
        EquipmentSlot slot = item.getSlot();

        if (equipment.containsKey(slot)) {
            equipment.get(slot).onUnequip(this);
        }

        equipment.put(slot, item);
        item.onEquip(this);
    }

    public void unequip(EquipmentSlot slot) {
        Equippable item = equipment.get(slot);

        if (item != null) {
            item.onUnequip(this);
            equipment.remove(slot);
        }
    }

    public void addModifier(StatsModifier modifier) {
        modifiers.add(modifier);
    }

    public void removeModifier(StatsModifier modifier) {
        modifiers.remove(modifier);
    }

    public int getStatBonus(Stats stats) {
        int total = 0;

        for (StatsModifier modifier : modifiers) {
            if (modifier.getStat() == stats) {
                total += modifier.getAmount();
            }
        }

        return total;
    }


// ========== RECALCULATE STATUS ==========

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