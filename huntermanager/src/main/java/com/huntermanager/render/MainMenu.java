package com.huntermanager.render;

import com.huntermanager.data.Assignment;
import com.huntermanager.data.HunterAcademy;
import com.huntermanager.data.MonsterHunter;
import com.huntermanager.data.SelectedAssignment;
import com.huntermanager.data.enums.Trait;
import com.huntermanager.data.enums.Trauma;

public class MainMenu {
    HunterAcademy academy = new HunterAcademy();
    SelectedAssignment sa = new SelectedAssignment();

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

    private void showHunterDetailsMenu(MonsterHunter selectedHunter) {
        int selectedHunterIndex = academy.getHunterIndex(selectedHunter);
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

    private void showAssignmentPreparationMenu(Assignment selectedAssignment, int[] selectedHuntersForAssignment, int[] selectedPositionsForAssignment, int[] selectedCombatStyles) {
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
                System.out.println("    Posição: " + sa.getPositionName(selectedPositionsForAssignment[i]));
                System.out.println("    Estilo: " + sa.getCombatStyleName(selectedCombatStyles[i]));
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
}
