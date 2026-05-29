package com.huntermanager.data.itemTypes.itemData;

import com.huntermanager.data.MonsterHunter;
import com.huntermanager.data.enums.EquipmentSlot;

public interface Equippable {
    EquipmentSlot getSlot();

    MonsterHunter getEquippedBy();
    
    void setEquippedBy(MonsterHunter hunter);

    void onEquip(MonsterHunter hunter);
    void onUnequip(MonsterHunter hunter);

    default boolean isEquipped() {
        return getEquippedBy() != null;
    }
}