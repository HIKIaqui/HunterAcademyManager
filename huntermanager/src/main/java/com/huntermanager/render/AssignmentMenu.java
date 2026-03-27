package com.huntermanager.render;

import com.huntermanager.data.Assignment;
import com.huntermanager.data.HunterAcademy;
import com.huntermanager.data.MonsterHunter;
import com.huntermanager.data.SelectedAssignment;

public class AssignmentMenu {

    public void showAssignmentsMenu(HunterAcademy academy) {
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

        System.out.println("\n------------");
        System.out.println("0 - Voltar");
        System.out.println("============");
        System.out.print("\nEscolha uma opção: ");
    }

    public void showAssignmentPreparationMenu(HunterAcademy academy, SelectedAssignment selectedAssignment) {
        System.out.println("\n=== PREPARAÇÃO DO CONTRATO ===\n");

        Assignment assignment = selectedAssignment.getAssignment();

        if (assignment == null) {
            System.out.println("Nenhum contrato selecionado.");
            System.out.println("\n------------");
            System.out.println("0 - Voltar");
            System.out.println("============");
            System.out.print("\nEscolha uma opção: ");
            return;
        }

        System.out.println("Contrato: " + assignment.getTitle());
        System.out.println("Dificuldade: " + assignment.getDifficulty());
        System.out.println("Ameaça principal: " + assignment.getMainThreat().getDisplayName());
        System.out.println("Descrição: " + assignment.getDescription());

        System.out.println("\n--- EQUIPE ENVIADA ---");

        for (int i = 0; i < 3; i++) {
            int hunterIndex = selectedAssignment.getHunterAt(i);

            if (hunterIndex == -1) {
                System.out.println((i + 1) + " - [Vazio]");
                System.out.println("    Posição: [Não definida]");
                System.out.println("    Estilo: [Não definido]");
            } else {
                MonsterHunter hunter = academy.getHunterByIndex(hunterIndex);
                System.out.println((i + 1) + " - " + hunter.getName());
                System.out.println("    Posição: " + SelectedAssignment.getPositionName(selectedAssignment.getPositionAt(i)));
                System.out.println("    Estilo: " + SelectedAssignment.getCombatStyleName(selectedAssignment.getCombatStyleAt(i)));
            }

            System.out.println();
        }

        System.out.println("\n--- OPÇÕES ---");
        System.out.println("1 - Adicionar Caçador");
        System.out.println("2 - Remover Caçador");
        System.out.println("3 - Definir Posições");
        System.out.println("4 - Definir Estilos de Combate");
        System.out.println("5 - Confirmar Envio");
        System.out.println("\n------------");
        System.out.println("0 - Voltar");
        System.out.println("============");
        System.out.print("\nEscolha uma opção: ");
    }

    public void showAssignmentAddHunterMenu(HunterAcademy academy, SelectedAssignment selectedAssignment) {
        System.out.println("\n=== ADICIONAR CAÇADOR ===\n");

        int[] availableIndexes = academy.getAvailableHunterIndexesForAssignment();
        int visibleOption = 1;
        boolean hasAny = false;

        for (int hunterIndex : availableIndexes) {
            if (!selectedAssignment.isHunterAlreadySelected(hunterIndex)) {
                MonsterHunter hunter = academy.getHunterByIndex(hunterIndex);

                System.out.println(visibleOption + " - " + hunter.getName());
                System.out.println("    HP: " + hunter.getHP() + "/" + hunter.getMaxHP()
                        + " | PE: " + hunter.getPE() + "/" + hunter.getMaxPE()
                        + " | Estresse: " + hunter.getStress() + "/" + hunter.getMaxStress());
                System.out.println();

                visibleOption++;
                hasAny = true;
            }
        }

        if (!hasAny) {
            System.out.println("Nenhum caçador disponível.");
        }

        System.out.println("\n------------");
        System.out.println("0 - Voltar");
        System.out.println("============");
        System.out.print("\nEscolha uma opção: ");
    }

    public void showAssignmentRemoveHunterMenu(HunterAcademy academy, SelectedAssignment selectedAssignment) {
        System.out.println("\n=== REMOVER CAÇADOR ===\n");

        for (int i = 0; i < 3; i++) {
            int hunterIndex = selectedAssignment.getHunterAt(i);

            if (hunterIndex == -1) {
                System.out.println((i + 1) + " - [Vazio]");
            } else {
                MonsterHunter hunter = academy.getHunterByIndex(hunterIndex);
                System.out.println((i + 1) + " - " + hunter.getName());
            }
        }

        System.out.println("\n------------");
        System.out.println("0 - Voltar");
        System.out.println("============");
        System.out.print("\nEscolha uma opção: ");
    }

    public void showAssignmentSetPositionMenu(HunterAcademy academy, SelectedAssignment selectedAssignment) {
        System.out.println("\n=== DEFINIR POSIÇÕES ===\n");

        for (int i = 0; i < 3; i++) {
            int hunterIndex = selectedAssignment.getHunterAt(i);

            if (hunterIndex == -1) {
                System.out.println((i + 1) + " - [Vazio]");
            } else {
                MonsterHunter hunter = academy.getHunterByIndex(hunterIndex);
                System.out.println((i + 1) + " - " + hunter.getName()
                        + " | Posição atual: "
                        + SelectedAssignment.getPositionName(selectedAssignment.getPositionAt(i)));
            }
        }

        System.out.println("\nEscolha o slot para alterar.");
        System.out.println("\n------------");
        System.out.println("0 - Voltar");
        System.out.println("============");
        System.out.print("\nEscolha uma opção: ");
    }

    public void showAssignmentChoosePositionMenu(HunterAcademy academy, SelectedAssignment selectedAssignment, int selectedAssignmentSlot) {
        System.out.println("\n=== ESCOLHER POSIÇÃO ===\n");

        if (selectedAssignmentSlot == -1 || !selectedAssignment.hasHunterInSlot(selectedAssignmentSlot)) {
            System.out.println("Nenhum slot válido selecionado.");
            System.out.println("\n------------");
            System.out.println("0 - Voltar");
            System.out.println("============");
            System.out.print("\nEscolha uma opção: ");
            return;
        }

        MonsterHunter hunter = academy.getHunterByIndex(selectedAssignment.getHunterAt(selectedAssignmentSlot));

        System.out.println("Caçador: " + hunter.getName());
        System.out.println("Posição atual: "
                + SelectedAssignment.getPositionName(selectedAssignment.getPositionAt(selectedAssignmentSlot)));

        System.out.println("\n1 - Frente");
        System.out.println("2 - Meio");
        System.out.println("3 - Retaguarda");
        System.out.println("\n------------");
        System.out.println("0 - Voltar");
        System.out.println("============");
        System.out.print("\nEscolha uma opção: ");
    }

    public void showAssignmentSetCombatStyleMenu(HunterAcademy academy, SelectedAssignment selectedAssignment) {
        System.out.println("\n=== DEFINIR ESTILO DE COMBATE ===\n");

        for (int i = 0; i < 3; i++) {
            int hunterIndex = selectedAssignment.getHunterAt(i);

            if (hunterIndex == -1) {
                System.out.println((i + 1) + " - [Vazio]");
            } else {
                MonsterHunter hunter = academy.getHunterByIndex(hunterIndex);
                System.out.println((i + 1) + " - " + hunter.getName()
                        + " | Estilo atual: "
                        + SelectedAssignment.getCombatStyleName(selectedAssignment.getCombatStyleAt(i)));
            }
        }

        System.out.println("\n------------");
        System.out.println("0 - Voltar");
        System.out.println("============");
        System.out.print("\nEscolha uma opção: ");
    }

    public void showAssignmentChooseCombatStyleMenu(HunterAcademy academy, SelectedAssignment selectedAssignment, int selectedAssignmentSlot) {
        System.out.println("\n=== ESCOLHER ESTILO DE COMBATE ===\n");

        if (selectedAssignmentSlot == -1 || !selectedAssignment.hasHunterInSlot(selectedAssignmentSlot)) {
            System.out.println("Nenhum slot válido selecionado.");
            System.out.println("\n------------");
            System.out.println("0 - Voltar");
            System.out.println("============");
            System.out.print("\nEscolha uma opção: ");
            return;
        }

        MonsterHunter hunter = academy.getHunterByIndex(selectedAssignment.getHunterAt(selectedAssignmentSlot));

        System.out.println("Caçador: " + hunter.getName());
        System.out.println("Estilo atual: "
                + SelectedAssignment.getCombatStyleName(selectedAssignment.getCombatStyleAt(selectedAssignmentSlot)));

        System.out.println("\n1 - Corpo a Corpo");
        System.out.println("2 - À Distância");
        System.out.println("3 - Suporte");
        System.out.println("4 - Magia");
        System.out.println("\n------------");
        System.out.println("0 - Voltar");
        System.out.println("============");
        System.out.print("\nEscolha uma opção: ");
    }
}