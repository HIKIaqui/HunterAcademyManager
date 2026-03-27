package com.huntermanager.render;

import com.huntermanager.data.HunterAcademy;
import com.huntermanager.data.Item;
import com.huntermanager.data.MonsterHunter;
import com.huntermanager.data.enums.Trait;
import com.huntermanager.data.enums.Trauma;

public class AcademyMenu {

    public void showAcademyMainMenu(HunterAcademy academy) {
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

    public void showHuntersMenu(HunterAcademy academy) {
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
                } else if (academy.isHunterInClinic(hunterIndex)) {
                    localizacao = "Clínica";
                } else {
                    localizacao = "Livre";
                }

                if ("Livre".equals(localizacao)) {
                    System.out.println((i + 1) + " - " + hunter.getName());
                } else {
                    System.out.println((i + 1) + " - " + hunter.getName() + " (" + localizacao + ")");
                }

                System.out.println("    HP: " + hunter.getHP() + "/" + hunter.getMaxHP()
                        + " | PE: " + hunter.getPE() + "/" + hunter.getMaxPE()
                        + " | Estresse: " + hunter.getStress() + "/" + hunter.getMaxStress());
                System.out.println();
            }
        }

        System.out.println("\n------------");
        System.out.println("0 - Voltar");
        System.out.println("============");
        System.out.print("\nEscolha uma opção: ");
    }

    public void showHunterDetailsMenu(HunterAcademy academy, MonsterHunter selectedHunter, int selectedHunterIndex) {
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
        System.out.println("\n------------");
        System.out.println("0 - Voltar");
        System.out.println("============");
        System.out.print("\nEscolha uma opção: ");
    }

    public void showClinicMenu(HunterAcademy academy) {
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

                if (recoveryStress > 0) {
                    System.out.println("    Recuperação prevista: +" + recoveryHP + " HP e -" + recoveryStress + " Estresse\n");
                } else {
                    System.out.println("    Recuperação prevista: +" + recoveryHP + " HP\n");
                }
            }
        }

        System.out.println("\n------------");
        System.out.println("0 - Voltar");
        System.out.println("============");
        System.out.print("\nEscolha uma opção: ");
    }

    public void showBarMenu(HunterAcademy academy) {
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

                if (recoveryPE > 0) {
                    System.out.println("    Recuperação prevista: -" + recoveryStress + " Estresse e +" + recoveryPE + " PE\n");
                } else {
                    System.out.println("    Recuperação prevista: -" + recoveryStress + " Estresse\n");
                }
            }
        }

        System.out.println("\n------------");
        System.out.println("0 - Voltar");
        System.out.println("============");
        System.out.print("\nEscolha uma opção: ");
    }

    public void showStorageMenu(HunterAcademy academy) {
        System.out.println("\n======= ARMAZÉM =======\n");

        for (Item item : academy.getActiveItems()) {
            String status = item.isEquipped()
                    ? " [Equipado por " + item.getEquippedBy().getName() + "]"
                    : " [Livre]";

            System.out.println(item.getName() + " - " + item.getType() + status);
        }

        System.out.println("\n------------");
        System.out.println("0 - Voltar");
        System.out.println("============");
        System.out.print("\nEscolha uma opção: ");
    }
}