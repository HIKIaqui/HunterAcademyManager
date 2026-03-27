package com.huntermanager.render;

public class MainMenu {

    public void showMainMenu() {
        System.out.println("\n==== O DIRETOR NOS VALES ====");
        System.out.println("1 - Novo jogo");
        System.out.println("2 - Carregar Jogo");
        System.out.println("3 - Opções");
        System.out.println("0 - Sair");
        System.out.println("=============================");
        System.out.print("Escolha uma opção: ");
    }

    public void newGameDifficultyScreen() {
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
}