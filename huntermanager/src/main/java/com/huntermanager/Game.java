package com.huntermanager;

import java.util.Scanner;

import com.huntermanager.data.Assignment;
import com.huntermanager.data.HunterAcademy;
import com.huntermanager.data.Item;
import com.huntermanager.data.MonsterHunter;
import com.huntermanager.data.enums.ItemTemplate;
import com.huntermanager.data.enums.MonsterHunterTemplates;
import com.huntermanager.data.enums.Trait;
import com.huntermanager.data.enums.Trauma;
import com.huntermanager.data.functions.TextRenderFunctions;

public class Game {
    private Scanner scanner = new Scanner(System.in);
    private boolean running = true;
    private int room = 0;
    int option;
    TextRenderFunctions trf = new TextRenderFunctions();

    private HunterAcademy academy;
    private MonsterHunter selectedHunter;
    private int selectedHunterIndex;
    private Assignment selectedAssignment;
    private int[] selectedHuntersForAssignment = {-1, -1, -1};
    private int[] selectedPositionsForAssignment = {-1, -1, -1};
    private int[] selectedCombatStyles = {-1, -1, -1};
    private int selectedAssignmentSlot = -1;

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
                case MainMenu:
                    trf.clearConsole();
                    showMainMenu();
                    option = readOption();
                    selectMenuOptions(option);
                    break;
                case DifficultySelection:
                    trf.clearConsole();
                    newGameDifficultyScreen();
                    option = readOption();
                    selectMenuOptions(option);
                    break;
                case DifficultyOpeningText:
                    trf.clearConsole();
                    if (null == difficulty) { System.out.print("Algo deu errado. A dificuldade não foi configurada. Tente reiniciar o jogo e tentar de novo. (E me envia o log)"); }
                    else switch (difficulty) {
                        case EASY:
                            System.out.print("O mundo será gentil com você.");
                            trf.sleep(1000);
                            System.out.print("\nA mensagem passada pode acabar distorcida.");
                            trf.sleep(1000);
                            System.out.print("\nA escolha foi sua.");
                            break;
                        case NORMAL:
                            System.out.print("A dificuldade padrão foi escolhida.");
                            trf.sleep(1000);
                            System.out.print("\nBoa escolha.");
                            trf.sleep(1000);
                            System.out.print("\nBoa sorte.");
                            break;
                        case HARD:
                            System.out.print("\nNenhuma concessão será feita.");
                            trf.sleep(1000);
                            System.out.print("\nVocê foi avisado.");
                            break;
                        default:
                            System.out.print("Algo deu errado. A dificuldade não foi configurada. Tente reiniciar o jogo e tentar de novo. (E me envia o log)");
                            break;
                    }
                    setupNewGame();
                    trf.sleep(1500);
                    room = AcademyMainMenu;
                    break;
                case AcademyMainMenu:
                    trf.clearConsole();
                    showAcademyMainMenu();
                    option = readOption();
                    selectMenuOptions(option);
                    break;

                case HunterManagementMenu:
                    trf.clearConsole();
                    showHuntersMenu();
                    option = readOption();
                    selectMenuOptions(option);
                    break;
                case HunterDetailsMenu:
                    trf.clearConsole();
                    showHunterDetailsMenu();
                    option = readOption();
                    selectMenuOptions(option);
                    break;
                case ClinicManagementMenu:
                    trf.clearConsole();
                    showClinicMenu();
                    option = readOption();
                    selectMenuOptions(option);
                    break;
                case BarManagementMenu:
                    trf.clearConsole();
                    showBarMenu();
                    option = readOption();
                    selectMenuOptions(option);
                    break;
                case AssignmentManagementMenu:
                    trf.clearConsole();
                    showAssignmentsMenu();
                    option = readOption();
                    selectMenuOptions(option);
                    break;
                case AssignmentPreparationMenu:
                    trf.clearConsole();
                    showAssignmentPreparationMenu();
                    option = readOption();
                    selectMenuOptions(option);
                    break;
                case AssignmentAddHunterMenu:
                    trf.clearConsole();
                    showAssignmentAddHunterMenu();
                    option = readOption();
                    selectMenuOptions(option);
                    break;
                case AssignmentRemoveHunterMenu:
                    trf.clearConsole();
                    showAssignmentRemoveHunterMenu();
                    option = readOption();
                    selectMenuOptions(option);
                    break;
                case AssignmentSetPositionMenu:
                    trf.clearConsole();
                    showAssignmentSetPositionMenu();
                    option = readOption();
                    selectMenuOptions(option);
                    break;

                case AssignmentChoosePositionMenu:
                    trf.clearConsole();
                    showAssignmentChoosePositionMenu();
                    option = readOption();
                    selectMenuOptions(option);
                    break;

                case AssignmentSetCombatStyleMenu:
                    trf.clearConsole();
                    showAssignmentSetCombatStyleMenu();
                    option = readOption();
                    selectMenuOptions(option);
                    break;

                case AssignmentChooseCombatStyleMenu:
                    trf.clearConsole();
                    showAssignmentChooseCombatStyleMenu();
                    option = readOption();
                    selectMenuOptions(option);
                    break;
                case StorageMenu:
                    trf.clearConsole();
                    showStorageMenu();
                    option = readOption();
                    selectMenuOptions(option);
                    break;
            }            
        }
    }

// ======================================== SELECT MENU OPTIONS ========================================

    public void selectMenuOptions(int option) {
        switch(room){
            case MainMenu:
                switch(option) {
                    case 1:
                        room = DifficultySelection;
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 0: 
                        running = false;
                        break;
                }
                break;
            case DifficultySelection:
                switch(option) {
                    case 1:
                        difficulty = Difficulty.EASY;
                        room = DifficultyOpeningText;
                        break;
                    case 2:
                        difficulty = Difficulty.NORMAL;
                        room = DifficultyOpeningText;
                        break;
                    case 3:
                        difficulty = Difficulty.HARD;
                        room = DifficultyOpeningText;
                        break;
                    case 0: 
                        room = MainMenu;
                        break;
                }
                break;

            case AcademyMainMenu:
                switch(option) {
                    case 1:
                        room = HunterManagementMenu;
                        break;
                    case 2:
                        room = AssignmentManagementMenu;
                        break;
                    case 3:
                        room = ClinicManagementMenu;
                        break;
                    case 4:
                        room = BarManagementMenu;
                        break;
                    case 5:
                        room = StorageMenu;
                        break;
                    case 6:
                        academy.advanceDayTime();
                        break;
                    case 7:
                        break;
                    case 0:
                        room = MainMenu;
                        break;
                }
                break;
            case HunterManagementMenu:
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
                break;
            case HunterDetailsMenu:
                switch(option) {
                    case 0:
                        selectedHunter = null;
                        room = HunterManagementMenu;
                        break;
                    case 1:
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
                        break;
                    case 2:
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
                        break;
                    case 3:
                        System.out.println("\nEssa opção ainda não foi implementada.");
                        trf.sleep(1000);
                        break;
                }
                break;
            case ClinicManagementMenu:
                switch (option) {
                    case 0:
                        room = AcademyMainMenu;
                        break;
                }
                break;
            case BarManagementMenu:
                switch (option) {
                    case 0:
                        room = AcademyMainMenu;
                        break;
                }
                break;
            case AssignmentManagementMenu:
                if (option == 0) {
                    room = AcademyMainMenu;
                } else {
                    Assignment[] assignments = academy.getAvailableAssignments();

                    if (option >= 1 && option <= assignments.length && assignments[option - 1] != null) {
                        selectedAssignment = assignments[option - 1];

                        // limpa seleção anterior
                        selectedHuntersForAssignment = new int[] {-1, -1, -1};
                        selectedPositionsForAssignment = new int[] {-1, -1, -1};
                        selectedCombatStyles = new int[] {-1, -1, -1};

                        room = AssignmentPreparationMenu;
                    }
                }
                break;
            case AssignmentPreparationMenu:
                switch(option) {
                    case 0:
                        selectedAssignment = null;
                        selectedHuntersForAssignment = new int[] {-1, -1, -1};
                        selectedPositionsForAssignment = new int[] {-1, -1, -1};
                        selectedCombatStyles = new int[] {-1, -1, -1};
                        room = AssignmentManagementMenu;
                        break;
                    case 1:
                        room = AssignmentAddHunterMenu;
                        break;
                    case 2:
                        room = AssignmentRemoveHunterMenu;
                        break;
                    case 3:
                        room = AssignmentSetPositionMenu;
                        break;
                    case 4:
                        room = AssignmentSetCombatStyleMenu;
                        break;
                    case 5:
                        if (isAssignmentReady()) {
                            System.out.println("\nEquipe pronta. Envio ainda não foi implementado.");
                        } else {
                            System.out.println("\nA equipe ainda não está pronta. Defina posição e estilo para todos os caçadores enviados.");
                        }
                        trf.sleep(1200);
                        break;
                }
                break;
            
            case AssignmentAddHunterMenu:
                if (option == 0) {
                    room = AssignmentPreparationMenu;
                } else {
                    int[] availableIndexes = academy.getAvailableHunterIndexesForAssignment();

                    int visibleCount = 0;
                    int chosenHunterIndex = -1;

                    for (int hunterIndex : availableIndexes) {
                        if (!isHunterAlreadySelectedForAssignment(hunterIndex)) {
                            visibleCount++;
                            if (visibleCount == option) {
                                chosenHunterIndex = hunterIndex;
                                break;
                            }
                        }
                    }

                    if (chosenHunterIndex != -1) {
                        if (addHunterToSelectedAssignment(chosenHunterIndex)) {
                            System.out.println("\nCaçador adicionado à equipe.");
                        } else {
                            System.out.println("\nNão foi possível adicionar o caçador.");
                        }
                        trf.sleep(500);
                    }

                    room = AssignmentPreparationMenu;
                }
                break;
            
            case AssignmentRemoveHunterMenu:
                if (option == 0) {
                    room = AssignmentPreparationMenu;
                } else {
                    int slotIndex = option - 1;

                    if (removeHunterFromSelectedAssignment(slotIndex)) {
                        System.out.println("\nCaçador removido da equipe.");
                    } else {
                        System.out.println("\nNão foi possível remover o caçador.");
                    }

                    trf.sleep(500);
                    room = AssignmentPreparationMenu;
                }
                break;
            case AssignmentSetPositionMenu:
                if (option == 0) {
                    room = AssignmentPreparationMenu;
                } else {
                    int slotIndex = option - 1;

                    if (slotIndex >= 0 && slotIndex < selectedHuntersForAssignment.length
                            && selectedHuntersForAssignment[slotIndex] != -1) {
                        selectedAssignmentSlot = slotIndex;
                        room = AssignmentChoosePositionMenu;
                    } else {
                        System.out.println("\nSlot inválido.");
                        trf.sleep(500);
                    }
                }
                break;
            case AssignmentChoosePositionMenu:
                switch(option) {
                    case 0:
                        selectedAssignmentSlot = -1;
                        room = AssignmentSetPositionMenu;
                        break;
                    case 1:
                        selectedPositionsForAssignment[selectedAssignmentSlot] = 0; // Frente
                        System.out.println("\nPosição definida como Frente.");
                        trf.sleep(500);
                        selectedAssignmentSlot = -1;
                        room = AssignmentSetPositionMenu;
                        break;
                    case 2:
                        selectedPositionsForAssignment[selectedAssignmentSlot] = 1; // Meio
                        System.out.println("\nPosição definida como Meio.");
                        trf.sleep(500);
                        selectedAssignmentSlot = -1;
                        room = AssignmentSetPositionMenu;
                        break;
                    case 3:
                        selectedPositionsForAssignment[selectedAssignmentSlot] = 2; // Retaguarda
                        System.out.println("\nPosição definida como Retaguarda.");
                        trf.sleep(500);
                        selectedAssignmentSlot = -1;
                        room = AssignmentSetPositionMenu;
                        break;
                }
                break;
            case AssignmentSetCombatStyleMenu:
                if (option == 0) {
                    room = AssignmentPreparationMenu;
                } else {
                    int slotIndex = option - 1;

                    if (slotIndex >= 0 && slotIndex < selectedHuntersForAssignment.length
                            && selectedHuntersForAssignment[slotIndex] != -1) {
                        selectedAssignmentSlot = slotIndex;
                        room = AssignmentChooseCombatStyleMenu;
                    } else {
                        System.out.println("\nSlot inválido.");
                        trf.sleep(500);
                    }
                }
                break;
            case AssignmentChooseCombatStyleMenu:
                switch(option) {
                    case 0:
                        selectedAssignmentSlot = -1;
                        room = AssignmentSetCombatStyleMenu;
                        break;
                    case 1:
                        selectedCombatStyles[selectedAssignmentSlot] = 0; // Corpo a Corpo
                        System.out.println("\nEstilo definido como Corpo a Corpo.");
                        trf.sleep(500);
                        selectedAssignmentSlot = -1;
                        room = AssignmentSetCombatStyleMenu;
                        break;
                    case 2:
                        selectedCombatStyles[selectedAssignmentSlot] = 1; // À Distância
                        System.out.println("\nEstilo definido como À Distância.");
                        trf.sleep(500);
                        selectedAssignmentSlot = -1;
                        room = AssignmentSetCombatStyleMenu;
                        break;
                    case 3:
                        selectedCombatStyles[selectedAssignmentSlot] = 2; // Suporte
                        System.out.println("\nEstilo definido como Suporte.");
                        trf.sleep(500);
                        selectedAssignmentSlot = -1;
                        room = AssignmentSetCombatStyleMenu;
                        break;
                    case 4:
                        selectedCombatStyles[selectedAssignmentSlot] = 3; // Magia
                        System.out.println("\nEstilo definido como Magia.");
                        trf.sleep(500);
                        selectedAssignmentSlot = -1;
                        room = AssignmentSetCombatStyleMenu;
                        break;
                }
                break;
            case StorageMenu:
                switch (option) {
                    case 0:
                        room = AcademyMainMenu;
                        break;
                }
                break;
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

    // +++++++++++++++++ NEW GAME STARTUP +++++++++++++++++
    private void setupNewGame() {
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

    private boolean addHunterToSelectedAssignment(int hunterIndex) {
        for (int i = 0; i < selectedHuntersForAssignment.length; i++) {
            if (selectedHuntersForAssignment[i] == hunterIndex) {
                return false; // já está na equipe
            }
        }

        for (int i = 0; i < selectedHuntersForAssignment.length; i++) {
            if (selectedHuntersForAssignment[i] == -1) {
                selectedHuntersForAssignment[i] = hunterIndex;
                return true;
            }
        }

        return false; // equipe cheia
    }

    private boolean removeHunterFromSelectedAssignment(int slotIndex) {
        if (slotIndex >= 0 && slotIndex < selectedHuntersForAssignment.length && selectedHuntersForAssignment[slotIndex] != -1) {
            selectedHuntersForAssignment[slotIndex] = -1;
            selectedPositionsForAssignment[slotIndex] = -1;
            selectedCombatStyles[slotIndex] = -1;
            return true;
        }
        return false;
    }

    private String getPositionName(int position) {
        return switch (position) {
            case 0 -> "Frente";
            case 1 -> "Meio";
            case 2 -> "Retaguarda";
            default -> "Não definida";
            };
        }

    private String getCombatStyleName(int style) {
        return switch (style) {
            case 0 -> "Corpo a Corpo";
            case 1 -> "À Distância";
            case 2 -> "Suporte";
            case 3 -> "Magia";
            default -> "Não definido";
        };
    }

    private boolean isHunterAlreadySelectedForAssignment(int hunterIndex) {
        for (int selected : selectedHuntersForAssignment) {
            if (selected == hunterIndex) {
                return true;
            }
        }
        return false;
    }

    private boolean isAssignmentReady() {
        boolean hasAtLeastOneHunter = false;

        for (int i = 0; i < selectedHuntersForAssignment.length; i++) {
            if (selectedHuntersForAssignment[i] != -1) {
                hasAtLeastOneHunter = true;

                if (selectedPositionsForAssignment[i] == -1 || selectedCombatStyles[i] == -1) {
                    return false;
                }
            }
        }

        return hasAtLeastOneHunter;
    }

// ======================================== MENU DESIGNS ========================================

    private void showMainMenu() {
        System.out.println("\n==== O DIRETOR NOS VALES ====");
        System.out.println("1 - Novo jogo");
        System.out.println("2 - Carregar Jogo");
        System.out.println("3 - Opções");
        System.out.println("0 - Sair");
        System.out.println("=============================");
        System.out.print("Escolha uma opção: ");
    }

    private void newGameDifficultyScreen() {
        System.out.println("\n=== DIFICULDADE ===");

        System.out.println("\n1 - Superfície");
        System.out.println("    -> Dificuldade Menos Punitiva.");
        System.out.println("    -> O mundo é muito menos perigoso, e consequências são menos graves.");
        System.out.println("    -> Não é a experiência recomendada e idealizada para o jogo.");
        System.out.println("    -> Os Vales são sobre dor e sofrimento. Essa dificuldade dilui a mensagem.");

        System.out.println("\n2 - Vales");
        System.out.println("    -> Dificuldade Padrão.");
        System.out.println("    -> O mundo é extremamente punitivo e consequências são na maioria das vezes permanentes.");
        System.out.println("    -> Experiência recomendada para sentir os vales em primeira mão.");

        System.out.println("\n3 - Fundo do Poço");
        System.out.println("    -> Dificuldade Brutal");
        System.out.println("    -> O mundo não se contém.");
        System.out.println("    -> Erros são severos e irreversíveis.");
        System.out.println("    -> Requer conhecimento profundo das mecânicas do jogo.");
        System.out.println("    -> Experiência não recomendada para ninguém.");
        System.out.println("    -> Sobrevivência não é esperada.");
        
        System.out.println("\n------------------");
        System.out.println("0 - Voltar");
        System.out.println("==================");
        System.out.println("\nEscolha uma opção: ");
    }

    private void showAcademyMainMenu() {
        System.out.println("\n===== ACADEMIA DE CAÇADORES =====\n");

        String time = switch (academy.getCurrentDayTime()) {
            case HunterAcademy.MORNING -> "Manhã";
            case HunterAcademy.AFTERNOON -> "Tarde";
            case HunterAcademy.NIGHT -> "Noite";
            default -> "???";
        };
        System.out.println("Dia: " + academy.getCurrentDay() + " (" + time + ")");
        System.out.println("Estrelas: " + academy.getStars());
        System.out.println("Caçadores: " + academy.getHunterCount() + "/" + academy.getMaxRoster());

        System.out.println("\n1 - Caçadores");
        System.out.println("2 - Contratos");
        System.out.println("3 - Clínica");
        System.out.println("4 - Bar");
        System.out.println("5 - Armazém");
        System.out.println("6 - Passar Tempo");
        System.out.println("7 - Status da Academia");
        System.out.println("0 - Voltar ao Menu Principal");

        System.out.print("\nEscolha uma opção: ");
    }

    private void showHuntersMenu() {
        System.out.println("\n=== CAÇADORES ===\n");
        MonsterHunter[] hunters = academy.getActiveHunters();

        if (hunters.length == 0) {
            System.out.println("Nenhum caçador disponível.");
        } else {
            for (int i = 0; i < hunters.length; i++) {
                MonsterHunter hunter = hunters[i];
                int hunterIndex = academy.getHunterIndex(hunter);
                String localizacao;
                if (academy.isHunterInBar(hunterIndex)) {
                    localizacao = "Bar";
                } 
                else if (academy.isHunterInClinic(hunterIndex)) {
                    localizacao = "Clínica"; 
                }
                else {localizacao = "Livre";}
                if ("Livre".equals(localizacao)) {
                    System.out.println((i + 1) + " - " + hunter.getName());
                }
                else {
                    System.out.println((i + 1) + " - " + hunter.getName() + " (" + localizacao + ")");
                }
                
                System.out.println("    HP: " + hunter.getHP() + "/" + hunter.getMaxHP()
                        + " | PE: " + hunter.getPE() + "/" + hunter.getMaxPE()
                        + " | Estresse: " + hunter.getStress() + "/" + hunter.getMaxStress());
                System.out.println();
            }
        }
        System.out.println("0 - Voltar");
        System.out.print("\nEscolha uma opção: ");
    }

    private void showHunterDetailsMenu() {
        if (selectedHunter == null) {
            System.out.println("Nenhum caçador selecionado.");
            return;
        }

        System.out.println("\n=== FICHA DO CAÇADOR ===\n");

        System.out.println("Nome: " + selectedHunter.getName());
        System.out.println("HP: " + selectedHunter.getHP() + "/" + selectedHunter.getMaxHP());
        System.out.println("PE: " + selectedHunter.getPE() + "/" + selectedHunter.getMaxPE());
        System.out.println("Estresse: " + selectedHunter.getStress() + "/" + selectedHunter.getMaxStress());

        System.out.println("\n--- ATRIBUTOS ---");
        System.out.println("Constituição: " + selectedHunter.getConstitution());
        System.out.println("Agilidade: " + selectedHunter.getAgility());
        System.out.println("Mente: " + selectedHunter.getMind());
        System.out.println("Social: " + selectedHunter.getSocial());
        System.out.println("Sorte: " + selectedHunter.getLuck());

        System.out.println("\n--- CARACTERÍSTICAS ---");
            if (selectedHunter.getTraits().isEmpty()) {
                System.out.println("Nenhuma.");
            } else {
                for (Trait trait : selectedHunter.getTraits()) {
                    System.out.println("-> " + trait.getDisplayName());
                }
            }

        System.out.println("\n--- TRAUMAS ---");
            if (selectedHunter.getTraumas().isEmpty()) {
                System.out.println("Nenhum.");
            } else {
                for (Trauma trauma : selectedHunter.getTraumas()) {
                    System.out.println("-> " + trauma.getDisplayName());
                }
            }

        System.out.println("\n--- SITUAÇÃO ---");
            if (academy.isHunterInBar(selectedHunterIndex)) {
                System.out.println("< No Bar até o fim do Ciclo >");
            } else if (academy.isHunterInClinic(selectedHunterIndex)) {
                System.out.println("< Na Clínica até o fim do Ciclo >");
            } else {
                System.out.println("< Livre >");
            }

        System.out.println("\n--- OPÇÕES ---");
        System.out.println("1 - Enviar para a Clínica");
        System.out.println("2 - Enviar para o Bar");
        System.out.println("3 - Ver mais detalhes");
        System.out.println("0 - Voltar");

        System.out.print("\nEscolha uma opção: ");
    }

    private void showClinicMenu() {
        System.out.println("\n=== CLÍNICA ===\n");
        System.out.println("Caçadores alocados aqui permanecem indisponíveis até o fim do ciclo.\n");

        int[] clinicSlots = academy.getClinicSlots();

        for (int i = 0; i < clinicSlots.length; i++) {
            int hunterIndex = clinicSlots[i];

            if (hunterIndex == -1) {
                System.out.println((i + 1) + " - [Vazio]\n");
            } else {
                MonsterHunter hunter = academy.getHunterByIndex(hunterIndex);
                int recoveryHP = 8 + (hunter.getConstitution() * 2);
                int recoveryStress = 1 * (Math.max(0, hunter.getSocial() - 1));

                System.out.println((i + 1) + " - " + hunter.getName());
                System.out.println("    HP atual: " + hunter.getHP() + "/" + hunter.getMaxHP());
                if (recoveryStress > 0) { System.out.println("    Recuperação prevista: +" + recoveryHP + " HP e -" + recoveryStress + " Estresse\n"); }
                else { System.out.println("    Recuperação prevista: +" + recoveryHP + " HP\n"); }
            }
        }

        System.out.println("0 - Voltar");
        System.out.print("\nEscolha uma opção: ");
    }

    private void showBarMenu() {
        System.out.println("\n=== BAR ===\n");
        System.out.println("Caçadores alocados aqui permanecem indisponíveis até o fim do ciclo.\n");

        int[] barSlots = academy.getBarSlots();

        for (int i = 0; i < barSlots.length; i++) {
            int hunterIndex = barSlots[i];

            if (hunterIndex == -1) {
                System.out.println((i + 1) + " - [Vazio]\n");
            } else {
                MonsterHunter hunter = academy.getHunterByIndex(hunterIndex);
                int recoveryStress = 1 + hunter.getSocial();
                int recoveryPE = 2 * (Math.max(0, hunter.getMind() - 1));

                System.out.println((i + 1) + " - " + hunter.getName());
                System.out.println("    Estresse atual: " + hunter.getStress() + "/" + hunter.getMaxStress());
                if (recoveryPE > 0) { System.out.println("    Recuperação prevista: -" + recoveryStress + " Estresse e +" + recoveryPE + " PE\n"); }
                    else { System.out.println("    Recuperação prevista: -" + recoveryStress + " Estresse\n"); }
            }
        }

        System.out.println("0 - Voltar");
        System.out.print("\nEscolha uma opção: ");
    }
    private void showAssignmentsMenu() {
        System.out.println("\n=== CONTRATOS ===\n");

        Assignment[] assignments = academy.getAvailableAssignments();

        for (int i = 0; i < assignments.length; i++) {
            Assignment a = assignments[i];

            if (a != null) {
                System.out.println((i + 1) + " - " + a.getTitle());
                System.out.println("    Dificuldade: " + a.getDifficulty());
                System.out.println("    Ameaça principal: " + a.getMainThreat().getDisplayName());
                System.out.println("    " + a.getDescription());
                System.out.println();
            }
        }

        System.out.println("0 - Voltar");
        System.out.print("\nEscolha uma opção: ");
    }

    private void showAssignmentPreparationMenu() {
        System.out.println("\n=== PREPARAÇÃO DO CONTRATO ===\n");

        if (selectedAssignment == null) {
            System.out.println("Nenhum contrato selecionado.");
            System.out.println("\n0 - Voltar");
            System.out.print("\nEscolha uma opção: ");
            return;
        }

        System.out.println("Contrato: " + selectedAssignment.getTitle());
        System.out.println("Dificuldade: " + selectedAssignment.getDifficulty());
        System.out.println("Ameaça principal: " + selectedAssignment.getMainThreat().getDisplayName());
        System.out.println("Descrição: " + selectedAssignment.getDescription());

        System.out.println("\n--- EQUIPE ENVIADA ---");

        for (int i = 0; i < selectedHuntersForAssignment.length; i++) {
            int hunterIndex = selectedHuntersForAssignment[i];

            if (hunterIndex == -1) {
                System.out.println((i + 1) + " - [Vazio]");
                System.out.println("    Posição: [Não definida]");
                System.out.println("    Estilo: [Não definido]");
            } else {
                MonsterHunter hunter = academy.getHunterByIndex(hunterIndex);
                System.out.println((i + 1) + " - " + hunter.getName());
                System.out.println("    Posição: " + getPositionName(selectedPositionsForAssignment[i]));
                System.out.println("    Estilo: " + getCombatStyleName(selectedCombatStyles[i]));
            }
            System.out.println();
        }

        System.out.println("\n--- OPÇÕES ---");
        System.out.println("1 - Adicionar Caçador");
        System.out.println("2 - Remover Caçador");
        System.out.println("3 - Definir Posições");
        System.out.println("4 - Definir Estilos de Combate");
        System.out.println("5 - Confirmar Envio");
        System.out.println("0 - Voltar");

        System.out.print("\nEscolha uma opção: ");
    }

    private void showAssignmentAddHunterMenu() {
        System.out.println("\n=== ADICIONAR CAÇADOR ===\n");

        int[] availableIndexes = academy.getAvailableHunterIndexesForAssignment();

        if (availableIndexes.length == 0) {
            System.out.println("Nenhum caçador disponível.");
        } else {
            for (int i = 0; i < availableIndexes.length; i++) {
                int hunterIndex = availableIndexes[i];

                if (!isHunterAlreadySelectedForAssignment(hunterIndex)) {
                    MonsterHunter hunter = academy.getHunterByIndex(hunterIndex);
                    System.out.println((i + 1) + " - " + hunter.getName());
                    System.out.println("    HP: " + hunter.getHP() + "/" + hunter.getMaxHP()
                            + " | PE: " + hunter.getPE() + "/" + hunter.getMaxPE()
                            + " | Estresse: " + hunter.getStress() + "/" + hunter.getMaxStress());
                    System.out.println();
                }
            }
        }

        System.out.println("0 - Voltar");
        System.out.print("\nEscolha uma opção: ");
    }

    private void showAssignmentRemoveHunterMenu() {
        System.out.println("\n=== REMOVER CAÇADOR ===\n");

        for (int i = 0; i < selectedHuntersForAssignment.length; i++) {
            int hunterIndex = selectedHuntersForAssignment[i];

            if (hunterIndex == -1) {
                System.out.println((i + 1) + " - [Vazio]");
            } else {
                MonsterHunter hunter = academy.getHunterByIndex(hunterIndex);
                System.out.println((i + 1) + " - " + hunter.getName());
            }
        }

        System.out.println("\n0 - Voltar");
        System.out.print("\nEscolha uma opção: ");
    }

    private void showAssignmentSetPositionMenu() {
        System.out.println("\n=== DEFINIR POSIÇÕES ===\n");

        for (int i = 0; i < selectedHuntersForAssignment.length; i++) {
            int hunterIndex = selectedHuntersForAssignment[i];

            if (hunterIndex == -1) {
                System.out.println((i + 1) + " - [Vazio]");
            } else {
                MonsterHunter hunter = academy.getHunterByIndex(hunterIndex);
                System.out.println((i + 1) + " - " + hunter.getName()
                        + " | Posição atual: " + getPositionName(selectedPositionsForAssignment[i]));
            }
        }

        System.out.println("\nEscolha o slot para alterar.");
        System.out.println("0 - Voltar");
        System.out.print("\nEscolha uma opção: ");
    }
    
    private void showAssignmentChoosePositionMenu() {
        System.out.println("\n=== ESCOLHER POSIÇÃO ===\n");

        if (selectedAssignmentSlot == -1 || selectedHuntersForAssignment[selectedAssignmentSlot] == -1) {
            System.out.println("Nenhum slot válido selecionado.");
            System.out.println("\n0 - Voltar");
            System.out.print("\nEscolha uma opção: ");
            return;
        }

        MonsterHunter hunter = academy.getHunterByIndex(selectedHuntersForAssignment[selectedAssignmentSlot]);

        System.out.println("Caçador: " + hunter.getName());
        System.out.println("Posição atual: " + getPositionName(selectedPositionsForAssignment[selectedAssignmentSlot]));

        System.out.println("\n1 - Frente");
        System.out.println("2 - Meio");
        System.out.println("3 - Retaguarda");
        System.out.println("0 - Voltar");

        System.out.print("\nEscolha uma opção: ");
    }

    private void showAssignmentSetCombatStyleMenu() {
        System.out.println("\n=== DEFINIR ESTILO DE COMBATE ===\n");

        for (int i = 0; i < selectedHuntersForAssignment.length; i++) {
            int hunterIndex = selectedHuntersForAssignment[i];

            if (hunterIndex == -1) {
                System.out.println((i + 1) + " - [Vazio]");
            } else {
                MonsterHunter hunter = academy.getHunterByIndex(hunterIndex);
                System.out.println((i + 1) + " - " + hunter.getName()
                        + " | Estilo atual: " + getCombatStyleName(selectedCombatStyles[i]));
            }
        }

        System.out.println("\nEscolha o slot para alterar.");
        System.out.println("0 - Voltar");
        System.out.print("\nEscolha uma opção: ");
    }

    private void showAssignmentChooseCombatStyleMenu() {
        System.out.println("\n=== ESCOLHER ESTILO DE COMBATE ===\n");

        if (selectedAssignmentSlot == -1 || selectedHuntersForAssignment[selectedAssignmentSlot] == -1) {
            System.out.println("Nenhum slot válido selecionado.");
            System.out.println("\n0 - Voltar");
            System.out.print("\nEscolha uma opção: ");
            return;
        }

        MonsterHunter hunter = academy.getHunterByIndex(selectedHuntersForAssignment[selectedAssignmentSlot]);

        System.out.println("Caçador: " + hunter.getName());
        System.out.println("Estilo atual: " + getCombatStyleName(selectedCombatStyles[selectedAssignmentSlot]));

        System.out.println("\n1 - Corpo a Corpo");
        System.out.println("2 - À Distância");
        System.out.println("3 - Suporte");
        System.out.println("4 - Magia");
        System.out.println("0 - Voltar");

        System.out.print("\nEscolha uma opção: ");
    }

    private void showStorageMenu() {
        System.out.println("\n======= ARMAZÉM =======\n");

        for (Item item : academy.getActiveItems()) {
            System.out.print(item.getName() + " - " + item.getType());

            if (item.isEquipped()) {
                System.out.println(" [Equipado por " + item.getEquippedBy().getName() + "]");
            } else {
                System.out.println(" [Livre]");
            }
        }
        System.out.println("\n------------");
        System.out.println("\n0 - Voltar");
    }
}
