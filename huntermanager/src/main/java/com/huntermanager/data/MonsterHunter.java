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



public class MonsterHunter extends Entity {
    private final int maxStress = 10;
    private final Map<EquipmentSlot, Equippable> equipment = new HashMap<>();



    private int stress;

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

    public Equippable getEquipment(EquipmentSlot slot) {
        return equipment.get(slot);
    }

    public Equippable getEquippedWeapon() {
        return equipment.get(EquipmentSlot.WEAPON);
    }

    public Equippable getEquippedSuit() {
        return equipment.get(EquipmentSlot.SUIT);
    }

    public Equippable getEquippedAccessory() {
        return equipment.get(EquipmentSlot.ACCESSORY);
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

// ========== ITENS ==========

    public void equip(Equippable item) {
        if (item == null) return;

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


// ========== RECALCULATE STATUS ==========

    public void recalculateStats() {
        setMaxHP(15 + (getConstitution() * 10) + getStatBonus(Stats.MAX_HP));
        setMaxPE(4 + (getMind() * 3) + getConstitution() + getStatBonus(Stats.MAX_PE));

        if (getHP() > getMaxHP()) {
            setHP(getMaxHP());
        }

        if (getPE() > getMaxPE()) {
            setPE(getMaxPE());
        }
    }

    @Override
    protected void onStatsChanged() {
        recalculateStats();
    }
}