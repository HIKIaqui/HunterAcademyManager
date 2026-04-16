package com.huntermanager;

import java.util.Scanner;

import com.huntermanager.data.Assignment;
import com.huntermanager.data.HunterAcademy;
import com.huntermanager.data.Item;
import com.huntermanager.data.MonsterHunter;
import com.huntermanager.data.SelectedAssignment;
import com.huntermanager.data.enums.ItemTemplate;
import com.huntermanager.data.enums.MonsterHunterTemplates;
import com.huntermanager.data.functions.TextRenderFunctions;
import com.huntermanager.render.AcademyMenu;
import com.huntermanager.render.AssignmentMenu;
import com.huntermanager.render.MainMenu;

public class Game {
    private Scanner scanner = new Scanner(System.in);
    private boolean running = true;
    private int room = 0;
    int option;
    TextRenderFunctions trf = new TextRenderFunctions();

    private HunterAcademy academy;
    private MonsterHunter selectedHunter;
    private int selectedHunterIndex;
    private int selectedAssignmentSlot = -1;

    private final SelectedAssignment selectedAssignment = new SelectedAssignment();
    private final MainMenu mainMenu = new MainMenu();
    private final AcademyMenu academyMenu = new AcademyMenu();
    private final AssignmentMenu assignmentMenu = new AssignmentMenu();
    // Menu Constants:
    private final int MainMenu = 0;
    private final int DifficultySelection = 1;
    private final int DifficultyOpeningText = 2;
    private final int AcademyMainMenu = 3;
    private final int HunterManagementMenu = 4;
    private final int AssignmentManagementMenu = 5;
    private final int ClinicManagementMenu = 6;
    private final int BarManagementMenu = 7;
    private final int AcademyAdvancedStatusMenu = 8;
    private final int HunterDetailsMenu = 9;
    private final int AssignmentPreparationMenu = 10;
    private final int AssignmentAddHunterMenu = 11;
    private final int AssignmentRemoveHunterMenu = 12;
    private final int AssignmentSetPositionMenu = 13;
    private final int AssignmentSetCombatStyleMenu = 14;
    private final int AssignmentChoosePositionMenu = 15;
    private final int AssignmentChooseCombatStyleMenu = 16;
    private final int StorageMenu = 17;
    
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


// ======================================== GAME RUNNING ========================================
    
    public void startGame() {
        while(running) {
            switch(room){
                case MainMenu -> {
                    trf.clearConsole();
                    mainMenu.showMainMenu();
                    option = readOption();
                    selectMenuOptions(option);
                }
                case DifficultySelection -> {
                    trf.clearConsole();
                    mainMenu.newGameDifficultyScreen();
                    option = readOption();
                    selectMenuOptions(option);
                }
                case DifficultyOpeningText -> {
                    trf.clearConsole();
                    if (null == difficulty) { System.out.print("Algo deu errado. A dificuldade não foi configurada. Tente reiniciar o jogo e tentar de novo. (E me envia o log)"); }
                    else switch (difficulty) {
                        case EASY -> {
                            System.out.print("O mundo será gentil com você.");
                            trf.sleep(1000);
                            System.out.print("\nA mensagem passada pode acabar distorcida.");
                            trf.sleep(1000);
                            System.out.print("\nA escolha foi sua.");
                        }
                        case NORMAL -> {
                            System.out.print("A dificuldade padrão foi escolhida.");
                            trf.sleep(1000);
                            System.out.print("\nBoa escolha.");
                            trf.sleep(1000);
                            System.out.print("\nBoa sorte.");
                        }
                        case HARD -> {
                            System.out.print("\nNenhuma concessão será feita.");
                            trf.sleep(1000);
                            System.out.print("\nVocê foi avisado.");
                        }
                        default -> System.out.print("Algo deu errado. A dificuldade não foi configurada. Tente reiniciar o jogo e tentar de novo. (E me envia o log)");
                    }
                    setupNewGame();
                    trf.sleep(1500);
                    room = AcademyMainMenu;
                }
                case AcademyMainMenu -> {
                    trf.clearConsole();
                    academyMenu.showAcademyMainMenu(academy);
                    option = readOption();
                    selectMenuOptions(option);
                }

                case HunterManagementMenu -> {
                    trf.clearConsole();
                    academyMenu.showHuntersMenu(academy);
                    option = readOption();
                    selectMenuOptions(option);
                }
                case HunterDetailsMenu -> {
                    trf.clearConsole();
                    academyMenu.showHunterDetailsMenu(academy, selectedHunter, selectedHunterIndex);
                    option = readOption();
                    selectMenuOptions(option);
                }
                case ClinicManagementMenu -> {
                    trf.clearConsole();
                    academyMenu.showClinicMenu(academy);
                    option = readOption();
                    selectMenuOptions(option);
                }
                case BarManagementMenu -> {
                    trf.clearConsole();
                    academyMenu.showBarMenu(academy);
                    option = readOption();
                    selectMenuOptions(option);
                }
                case StorageMenu -> {
                    trf.clearConsole();
                    academyMenu.showStorageMenu(academy);
                    option = readOption();
                    selectMenuOptions(option);
                }
                case AssignmentManagementMenu -> {
                    trf.clearConsole();
                    assignmentMenu.showAssignmentsMenu(academy);
                    option = readOption();
                    selectMenuOptions(option);
                }
                case AssignmentPreparationMenu -> {
                    trf.clearConsole();
                    assignmentMenu.showAssignmentPreparationMenu(academy, selectedAssignment);
                    option = readOption();
                    selectMenuOptions(option);
                }
                case AssignmentAddHunterMenu -> {
                    trf.clearConsole();
                    assignmentMenu.showAssignmentAddHunterMenu(academy, selectedAssignment);
                    option = readOption();
                    selectMenuOptions(option);
                }
                case AssignmentRemoveHunterMenu -> {
                    trf.clearConsole();
                    assignmentMenu.showAssignmentRemoveHunterMenu(academy, selectedAssignment);
                    option = readOption();
                    selectMenuOptions(option);
                }
                case AssignmentSetPositionMenu -> {
                    trf.clearConsole();
                    assignmentMenu.showAssignmentSetPositionMenu(academy, selectedAssignment);
                    option = readOption();
                    selectMenuOptions(option);
                }

                case AssignmentChoosePositionMenu -> {
                    trf.clearConsole();
                    assignmentMenu.showAssignmentChoosePositionMenu(academy, selectedAssignment, selectedAssignmentSlot);
                    option = readOption();
                    selectMenuOptions(option);
                }

                case AssignmentSetCombatStyleMenu -> {
                    trf.clearConsole();
                    assignmentMenu.showAssignmentSetCombatStyleMenu(academy, selectedAssignment);
                    option = readOption();
                    selectMenuOptions(option);
                }

                case AssignmentChooseCombatStyleMenu -> {
                    trf.clearConsole();
                    assignmentMenu.showAssignmentChooseCombatStyleMenu(academy, selectedAssignment, selectedAssignmentSlot);
                    option = readOption();
                    selectMenuOptions(option);
                }
            }            
        }
    }

// ======================================== SELECT MENU OPTIONS ========================================

    public void selectMenuOptions(int option) {
        switch(room){
            case MainMenu -> {
                switch(option) {
                    case 1 -> room = DifficultySelection;
                    case 2 -> {
                    }
                    case 3 -> {
                    }
                    case 0 -> running = false;
                }
            }

            case DifficultySelection -> {
                switch(option) {
                    case 1 -> {
                        difficulty = Difficulty.EASY;
                        room = DifficultyOpeningText;
                    }
                    case 2 -> {
                        difficulty = Difficulty.NORMAL;
                        room = DifficultyOpeningText;
                    }
                    case 3 -> {
                        difficulty = Difficulty.HARD;
                        room = DifficultyOpeningText;
                    }
                    case 0 -> room = MainMenu;
                }
            }


            case AcademyMainMenu -> {
                switch(option) {
                    case 1 -> room = HunterManagementMenu;
                    case 2 -> room = AssignmentManagementMenu;
                    case 3 -> room = ClinicManagementMenu;
                    case 4 -> room = BarManagementMenu;
                    case 5 -> room = StorageMenu;
                    case 6 -> academy.advanceDayTime();
                    case 7 -> {
                    }
                    case 0 -> room = MainMenu;
                }
            }

            case HunterManagementMenu -> {
                if (option == 0) {
                    room = AcademyMainMenu;
                } else {
                    MonsterHunter hunter = academy.getHunterByDisplayIndex(option - 1);

                    if (hunter != null) {
                        selectedHunter = hunter;
                        selectedHunterIndex = academy.getHunterIndex(selectedHunter);
                        room = HunterDetailsMenu;
                    }
                }
            }
            case HunterDetailsMenu -> {
                switch(option) {
                    case 0 -> {
                        selectedHunter = null;
                        room = HunterManagementMenu;
                    }
                    case 1 -> {
                        if (selectedHunterIndex != -1) {
                            if (!academy.isHunterInClinic(selectedHunterIndex) && !academy.isHunterInBar(selectedHunterIndex)){
                                if (academy.isClinicFull()) {
                                    System.out.println("\nA clínica está cheia.");
                                } else if (academy.addHunterToClinic(selectedHunterIndex)) {
                                    System.out.println("\n" + selectedHunter.getName() + " foi para a clínica.");
                                } else {
                                    System.out.println("\nNão foi possível enviar o caçador.");
                                }
                            } else { 
                                if (academy.isHunterInClinic(selectedHunterIndex)) {
                                    
                                    System.out.println("\n" + selectedHunter.getName() + " já está na clínica");
                                } else if (academy.isHunterInBar(selectedHunterIndex)) {

                                    System.out.println("\n" + selectedHunter.getName() + " já está no bar");
                                } 
                            }
                        } else {
                            System.out.println("\nErro: caçador não encontrado no roster.");
                        }
                        trf.sleep(1000);
                    }
                    case 2 -> {
                        if (selectedHunterIndex != -1) {
                            if (!academy.isHunterInClinic(selectedHunterIndex) && !academy.isHunterInBar(selectedHunterIndex)){
                                if (academy.isBarFull()) {
                                    System.out.println("\nO bar está cheio.");
                                } else if (academy.addHunterToBar(selectedHunterIndex)) {
                                    System.out.println("\n" + selectedHunter.getName() + " foi para o bar.");
                                } else {
                                    System.out.println("\nNão foi possível enviar " + selectedHunter.getName());
                                }
                            } else { 
                                if (academy.isHunterInClinic(selectedHunterIndex)) {
                                    
                                    System.out.println("\n" + selectedHunter.getName() + " já está na clínica");
                                } else if (academy.isHunterInBar(selectedHunterIndex)) {

                                    System.out.println("\n" + selectedHunter.getName() + " já está no bar");
                                } 
                            }
                        } else {
                            System.out.println("\nErro: caçador não encontrado no roster.");
                        }
                        trf.sleep(1000);
                    }
                    case 3 -> {
                        System.out.println("\nEssa opção ainda não foi implementada.");
                        trf.sleep(1000);
                    }
                }
            }

            case ClinicManagementMenu -> {
                switch (option) {
                    case 0 -> room = AcademyMainMenu;
                }
            }

            case BarManagementMenu -> {
                switch (option) {
                    case 0 -> room = AcademyMainMenu;
                }
            }

            case AssignmentManagementMenu -> {
                if (option == 0) {
                    room = AcademyMainMenu;
                } else {
                    Assignment[] assignments = academy.getAvailableAssignments();

                    if (option >= 1 && option <= assignments.length && assignments[option - 1] != null) {
                        // limpa seleção anterior
                        selectedAssignment.selectAssignment(assignments[option - 1]);
                        room = AssignmentPreparationMenu;
                    }
                }
            }
            case AssignmentPreparationMenu -> {
                switch(option) {
                    case 0 -> {
                        selectedAssignment.clear();
                        selectedAssignmentSlot = -1;
                        room = AssignmentManagementMenu;
                    }
                    case 1 -> room = AssignmentAddHunterMenu;
                    case 2 -> room = AssignmentRemoveHunterMenu;
                    case 3 -> room = AssignmentSetPositionMenu;
                    case 4 -> room = AssignmentSetCombatStyleMenu;
                    case 5 -> {
                        if (selectedAssignment.isReady()) {
                            System.out.println("\nEquipe pronta. Envio ainda não foi implementado.");
                        } else {
                            System.out.println("\nA equipe ainda não está pronta. Defina posição e estilo para todos os caçadores enviados.");
                        }
                        trf.sleep(1200);
                    }
                }
            }

            
            case AssignmentAddHunterMenu -> {
                if (option == 0) {
                    room = AssignmentPreparationMenu;
                } else {
                    int[] availableIndexes = academy.getAvailableHunterIndexesForAssignment();

                    int visibleCount = 0;
                    int chosenHunterIndex = -1;

                    for (int hunterIndex : availableIndexes) {
                        if (!selectedAssignment.isHunterAlreadySelected(hunterIndex)) {
                            visibleCount++;
                            if (visibleCount == option) {
                                chosenHunterIndex = hunterIndex;
                                break;
                            }
                        }
                    }

                    if (chosenHunterIndex != -1) {
                        if (selectedAssignment.addHunter(chosenHunterIndex)) {
                            System.out.println("\nCaçador adicionado à equipe.");
                        } else {
                            System.out.println("\nNão foi possível adicionar o caçador.");
                        }
                        trf.sleep(500);
                    }

                    room = AssignmentPreparationMenu;
                }
            }
            
            case AssignmentRemoveHunterMenu -> {
                if (option == 0) {
                    room = AssignmentPreparationMenu;
                } else {
                    int slotIndex = option - 1;

                    if (selectedAssignment.removeHunter(slotIndex)) {
                        System.out.println("\nCaçador removido da equipe.");
                    } else {
                        System.out.println("\nNão foi possível remover o caçador.");
                    }

                    trf.sleep(500);
                    room = AssignmentPreparationMenu;
                }
            }
            case AssignmentSetPositionMenu -> {
                if (option == 0) {
                    room = AssignmentPreparationMenu;
                } else {
                    int slotIndex = option - 1;

                    if (selectedAssignment.hasHunterInSlot(slotIndex)) {
                        selectedAssignmentSlot = slotIndex;
                        room = AssignmentChoosePositionMenu;
                    } else {
                        System.out.println("\nSlot inválido.");
                        trf.sleep(500);
                    }
                }
            }
            case AssignmentChoosePositionMenu -> {
                switch(option) {
                    case 0 -> {
                        selectedAssignmentSlot = -1;
                        room = AssignmentSetPositionMenu;
                    }
                    case 1 -> {
                        selectedAssignment.setPosition(selectedAssignmentSlot, 0); // Frente
                        System.out.println("\nPosição definida como Frente.");
                        trf.sleep(500);
                        selectedAssignmentSlot = -1;
                        room = AssignmentSetPositionMenu;
                    }
                    case 2 -> {
                        selectedAssignment.setPosition(selectedAssignmentSlot, 1); // Meio
                        System.out.println("\nPosição definida como Meio.");
                        trf.sleep(500);
                        selectedAssignmentSlot = -1;
                        room = AssignmentSetPositionMenu;
                    }
                    case 3 -> {
                        selectedAssignment.setPosition(selectedAssignmentSlot, 2); // Retaguarda
                        System.out.println("\nPosição definida como Retaguarda.");
                        trf.sleep(500);
                        selectedAssignmentSlot = -1;
                        room = AssignmentSetPositionMenu;
                    }
                }
            }

            case AssignmentSetCombatStyleMenu -> {
                if (option == 0) {
                    room = AssignmentPreparationMenu;
                } else {
                    int slotIndex = option - 1;

                    if (selectedAssignment.hasHunterInSlot(slotIndex)) {
                        selectedAssignmentSlot = slotIndex;
                        room = AssignmentChooseCombatStyleMenu;
                    } else {
                        System.out.println("\nSlot inválido.");
                        trf.sleep(500);
                    }
                }
            }
            case AssignmentChooseCombatStyleMenu -> {
                switch(option) {
                    case 0 -> {
                        selectedAssignmentSlot = -1;
                        room = AssignmentSetCombatStyleMenu;
                    }
                    case 1 -> {
                        selectedAssignment.setCombatStyle(selectedAssignmentSlot, 0); // Corpo a Corpo
                        System.out.println("\nEstilo definido como Corpo a Corpo.");
                        trf.sleep(500);
                        selectedAssignmentSlot = -1;
                        room = AssignmentSetCombatStyleMenu;
                    }
                    case 2 -> {
                        selectedAssignment.setCombatStyle(selectedAssignmentSlot, 1); // À Distância
                        System.out.println("\nEstilo definido como À Distância.");
                        trf.sleep(500);
                        selectedAssignmentSlot = -1;
                        room = AssignmentSetCombatStyleMenu;
                    }
                    case 3 -> {
                        selectedAssignment.setCombatStyle(selectedAssignmentSlot, 2); // Suporte
                        System.out.println("\nEstilo definido como Suporte.");
                        trf.sleep(500);
                        selectedAssignmentSlot = -1;
                        room = AssignmentSetCombatStyleMenu;
                    }
                    case 4 -> {
                        selectedAssignment.setCombatStyle(selectedAssignmentSlot, 3); // Magia
                        System.out.println("\nEstilo definido como Magia.");
                        trf.sleep(500);
                        selectedAssignmentSlot = -1;
                        room = AssignmentSetCombatStyleMenu;
                    }
                }
            }

            case StorageMenu -> {
                switch (option) {
                    case 0 -> room = AcademyMainMenu;
                }
            }

        }
    }

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
        academy.addItem(Item.fromTemplate(ItemTemplate.IRON_SWORD));
        academy.addItem(Item.fromTemplate(ItemTemplate.HUNTER_DAGGER));
        academy.addItem(Item.fromTemplate(ItemTemplate.IRON_ARMOR));
        academy.addItem(Item.fromTemplate(ItemTemplate.LEATHER_ARMOR));
        academy.addItem(Item.fromTemplate(ItemTemplate.LUCKY_CHARM));
        academy.addItem(Item.fromTemplate(ItemTemplate.BASIC_MEDKIT));
    }
}
