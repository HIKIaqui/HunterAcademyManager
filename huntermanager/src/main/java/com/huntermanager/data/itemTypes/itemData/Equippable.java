package com.huntermanager.data.itemTypes.itemData;
import com.huntermanager.data.MonsterHunter;
import com.huntermanager.data.enums.EquipmentSlot;

public interface Equippable {
    EquipmentSlot getSlot();

    void onEquip(MonsterHunter hunter);

    void onUnequip(MonsterHunter hunter);
}
