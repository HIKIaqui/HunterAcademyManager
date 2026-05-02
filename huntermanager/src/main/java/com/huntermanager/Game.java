package com.huntermanager;

import java.util.Scanner;

import com.huntermanager.data.HunterAcademy;
import com.huntermanager.data.MonsterHunter;
import com.huntermanager.data.SelectedAssignment;
import com.huntermanager.data.enums.ItemTemplate;
import com.huntermanager.data.enums.MonsterHunterTemplates;

public class Game {
    private Scanner scanner = new Scanner(System.in);
    int option;

    private HunterAcademy academy;
    private MonsterHunter selectedHunter;
    private int selectedHunterIndex;
    private int selectedAssignmentSlot = -1;

    private final SelectedAssignment selectedAssignment = new SelectedAssignment();

    
    public enum Difficulty {
        EASY("Superfície"),
        NORMAL("Vales"),
        HARD("Fundo do Poço");

        private final String displayName;

        Difficulty(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }
    private Difficulty difficulty;

// ======================================== EXTRA ========================================

    private int readOption() {
        while (true) {
            String input = scanner.nextLine();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Digite um número: ");
            }
        }
    }

    public HunterAcademy getAcademy() {
        return academy;
    }

    public void setSelectedHunter(MonsterHunter selectedHunter) {
        this.selectedHunter = selectedHunter;
    }

    public MonsterHunter getSelectedHunter() {
        return selectedHunter;
    }

    // +++++++++++++++++ NEW GAME STARTUP +++++++++++++++++
    public void setupNewGame() {
        academy = new HunterAcademy();
        academy.setMaxRoster(6);

        // gera 3 caçadores iniciais
        for (int i = 0; i < 3; i++) {
            academy.addHunter(MonsterHunterTemplates.createRandomNewMonsterHunter());
        }
        academy.generateNewAssignments();
        academy.setStars(10);
        academy.setCurrentDay(1);
        academy.setCurrentDayTime(HunterAcademy.MORNING);
        academy.addItem(ItemTemplate.RUSTY_SWORD.createItem());
        academy.addItem(ItemTemplate.SMALL_MEDICINE.createItem());
        academy.addItem(ItemTemplate.HEAVY_ARMOR.createItem());
    }
}
