package com.huntermanager.data.enums;

import java.util.Random;

import com.huntermanager.data.Assignment;

public class AssignmentTemplates {
    private static final Random random = new Random();

    public static Assignment createRandomAssignment() {
        int difficulty = random.nextInt(6); // 0 a 5
        return createRandomAssignment(difficulty);
    }

    public static Assignment createRandomAssignment(int difficulty) {
        String[] titles = getPossibleTitlesByDifficulty(difficulty);
        String[] descriptions = getPossibleDescriptionsByDifficulty(difficulty);
        AssignmentThreat[] threats = getPossibleThreatsByDifficulty(difficulty);

        String title = titles[random.nextInt(titles.length)];
        String description = descriptions[random.nextInt(descriptions.length)];
        AssignmentThreat threat = threats[random.nextInt(threats.length)];

        return new Assignment(title, description, difficulty, threat);
    }

    private static String[] getPossibleTitlesByDifficulty(int difficulty) {
        return switch (difficulty) {
            case 0 -> new String[] {
                "Barulhos Estranhos no Celeiro",
                "Animal Agressivo na Estrada",
                "Desaparecimento de Mantimentos",
                "Criatura Pequena nos Arredores",
                "Criaturas Menores",
                "Possível Perigo"
            };
            case 1 -> new String[] {
                "Infestação na Vila",
                "Ataques no Caminho Comercial",
                "Morador Desaparecido na Mata",
                "Presença Hostil nos Arredores"
            };
            case 2 -> new String[] {
                "Mortes na Estrada Velha",
                "Desaparecimentos na Floresta",
                "Criatura de Médio Porte Avistada",
                "Infestação Persistente",
                "Aldeia em Estado de Pânico"
            };
            case 3 -> new String[] {
                "Sobreviventes Falam de Horrores",
                "Abate em Massa nas Fazendas",
                "Cadáveres Acumulados em Massa",
                "Massacre na Vila",
                "Povoado Completamente Perdido"
            };
            case 4 -> new String[] {
                "Zona de Morte Confirmada",
                "Criatura Absurdamente Letal à Solta",
                "Região em Colapso",
                "Retorno de Ameaça Conhecida"
            };
            case 5 -> new String[] {
                "Manifestação de Horror Maior",
                "Ameaça de Classe Extrema",
                "Região em Colapso Total",
                "Presença Existencial Detectada"
            };
            default -> new String[] { "Contrato Desconhecido" };
        };
    }

    private static String[] getPossibleDescriptionsByDifficulty(int difficulty) {
        return switch (difficulty) {
            case 0 -> new String[] {
                "Relatos indicam uma ameaça menor, mas ainda capaz de ferir civis despreparados.",
                "A situação parece controlável, embora negligência ainda possa transformar o problema em algo pior.",
                "Moradores relatam atividade anormal durante a noite. A ameaça aparenta ser limitada, mas real."
            };
            case 1 -> new String[] {
                "Ataques recentes deixaram feridos e espalharam medo local. A ameaça exige resposta rápida.",
                "A situação já ultrapassou a capacidade dos moradores locais de reagir sozinhos.",
                "Há sinais claros de presença hostil recorrente. O risco de novas baixas é alto."
            };
            case 2 -> new String[] {
                "Há mortes confirmadas e comportamento hostil consistente. O envio de caçadores é considerado necessário.",
                "A ameaça demonstra persistência, agressividade e capacidade real de romper defesas improvisadas.",
                "Relatos indicam um inimigo que já não pode ser tratado como incidente isolado."
            };
            case 3 -> new String[] {
                "A área já apresenta sinais de colapso parcial. Sobrevivência depende de ação precisa e preparada.",
                "Testemunhas descrevem uma ameaça brutal, rápida e difícil de conter por meios convencionais.",
                "O contrato envolve risco severo, com chance real de ferimentos permanentes ou perda total da equipe."
            };
            case 4 -> new String[] {
                "A região afetada está à beira do abandono. A ameaça ultrapassa o que caçadores comuns enfrentam com segurança.",
                "Há evidências de que falhas anteriores agravaram ainda mais a situação.",
                "Os últimos 3 grupos enviados foram mortos ao chegar.",
                "Já foram registradas mais de centenas de fatalidades, com o número crescendo mais a cada minuto.",
                "O contrato é considerado altamente letal. Qualquer erro tende a gerar consequências irreversíveis."
            };
            case 5 -> new String[] {
                "Não há expectativa razoável de retorno seguro. O contrato existe porque ignorá-lo pode ser ainda pior.",
                "A presença detectada representa risco extremo à continuidade da região afetada, e talvez dessa realidade.",
                "Milhares de mortes já foram registradas desde o início da atividade da ameaça. Os inúmeros caçadores enviados foram dizimados.",
                "O contrato envolve uma ameaça fora do padrão operacional comum. O envio da equipe é, em si, um ato desesperado."
            };
            default -> new String[] {
                "Não foi possível determinar a natureza exata do contrato."
            };
        };
    }

    private static AssignmentThreat[] getPossibleThreatsByDifficulty(int difficulty) {
        return switch (difficulty) {
            case 0 -> new AssignmentThreat[] {
                AssignmentThreat.CRIATURA_DESCONHECIDA,
                AssignmentThreat.ESQUECIDO,
                AssignmentThreat.LEMBRADO,
                AssignmentThreat.LADRAO_DE_TUMULO,
                AssignmentThreat.LOBOS
            };
            case 1 -> new AssignmentThreat[] {
                AssignmentThreat.ESQUECIDO,
                AssignmentThreat.CARNICIDA,
                AssignmentThreat.CONSTRUCTOS_ERRANTES,
                AssignmentThreat.ATRITORES,
                AssignmentThreat.LEMBRADO,
                AssignmentThreat.CRIATURA_DESCONHECIDA
            };
            case 2 -> new AssignmentThreat[] {
                AssignmentThreat.ESQUECIDO,
                AssignmentThreat.CARNICIDA,
                AssignmentThreat.CONSTRUCTOS_ERRANTES,
                AssignmentThreat.ESCARNICADOR,
                AssignmentThreat.LEMBRADO,
                AssignmentThreat.CONSTRUCTOS_MALAQUITA,
                AssignmentThreat.ATRITORES,
                AssignmentThreat.WARG_INFERIOR
            };
            case 3 -> new AssignmentThreat[] {
                AssignmentThreat.AMALDICOADO_MAIOR,
                AssignmentThreat.ESCARNICADOR,
                AssignmentThreat.CONSTRUCTOS_MALAQUITA,
                AssignmentThreat.ESQUECIDO,
                AssignmentThreat.ATRITORES,
                AssignmentThreat.WARG_SUPERIOR,
                AssignmentThreat.ANIMADOR,
                AssignmentThreat.WAKWAK
                
            };
            case 4 -> new AssignmentThreat[] {
                AssignmentThreat.CONSTRUCTOS_MALAQUITA,
                AssignmentThreat.DRAGAO,
                AssignmentThreat.AMALDICOADO_MAIOR,
                AssignmentThreat.WARG_ANCIAO
            };
            case 5 -> new AssignmentThreat[] {
                AssignmentThreat.ENTIDADE_ONIRICA,
                AssignmentThreat.DRAGAO,
                AssignmentThreat.VERME_SUBSTITUIDOR,
                AssignmentThreat.CRIATURA_DESCONHECIDA
            };
            default -> new AssignmentThreat[] {
                AssignmentThreat.CRIATURA_DESCONHECIDA
            };
        };
    }
}