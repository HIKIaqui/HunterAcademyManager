package com.huntermanager.data.itemTypes.itemData;

import com.huntermanager.data.enums.Stats;

public class StatsModifier {
    private final Stats stats;
    private final int amount;

    public StatsModifier(Stats stats, int amount) {
        this.stats = stats;
        this.amount = amount;
    }

    public Stats getStat() {
        return stats;
    }

    public int getAmount() {
        return amount;
    }
}
