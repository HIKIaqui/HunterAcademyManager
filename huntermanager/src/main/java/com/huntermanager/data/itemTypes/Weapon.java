package com.huntermanager.data.itemTypes;

import java.util.List;

import com.huntermanager.data.Item;
import com.huntermanager.data.MonsterHunter;
import com.huntermanager.data.enums.EquipmentSlot;
import com.huntermanager.data.enums.ItemType;
import com.huntermanager.data.itemTypes.itemData.Equippable;
import com.huntermanager.data.itemTypes.itemData.StatsModifier;

public class Weapon extends Item implements Equippable {
    private final List<StatsModifier> modifiers;

    public Weapon(String name, String description, List<StatsModifier> modifiers) {
        super(name, description, ItemType.WEAPON);
        this.modifiers = modifiers;
    }

    @Override
    public EquipmentSlot getSlot() {
        return EquipmentSlot.WEAPON;
    }

    @Override
    public void onEquip(MonsterHunter hunter) {
        for (StatsModifier modifier : modifiers) {
            hunter.addModifier(modifier);
        }
    }

    @Override
    public void onUnequip(MonsterHunter hunter) {
        for (StatsModifier modifier : modifiers) {
            hunter.removeModifier(modifier);
        }
    }

    public List<StatsModifier> getModifiers() {
        return modifiers;
    }
}