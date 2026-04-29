package com.huntermanager.data.enums;

import java.util.Random;

import com.huntermanager.data.HunterNames;
import com.huntermanager.data.MonsterHunter;


public class MonsterHunterTemplates {
    
    public void CreateNewMonsterHunter () {
        MonsterHunter hunter = new MonsterHunter("Robert", 2, 2,1, 2, 1);
    }

    public static int[] generateAttributes() { // Gera atributos aleatórios pra um caçador, com limite de 3 pontos em cada atributo, adicionando 1 ponto por vez.
        Random random = new Random();
        int[] attributes = new int[5]; // 5 atributos
        int points = 8;

        while (points > 0) {
            int i = random.nextInt(5);

            if (attributes[i] < 3) {
                attributes[i]++;
                points--;
            }
        }
        return attributes;
    }

    public static MonsterHunter createRandomNewMonsterHunter() {
        Random random = new Random();
        int[] attributes = generateAttributes();
        String name = HunterNames.generateName();
        MonsterHunter hunter = new MonsterHunter(name, attributes[0], attributes[1],attributes[2], attributes[3], attributes[4]);

        Trait[] values = Trait.values();
        hunter.addTrait(values[random.nextInt(values.length)]);
        
        Trauma[] values2 = Trauma.values();
        hunter.addTrauma(values2[random.nextInt(values2.length)]);

        Origin[] values3 = Origin.values();
        hunter.addOrigin(values3[random.nextInt(values3.length)]);
        
        return hunter;

    }
}
