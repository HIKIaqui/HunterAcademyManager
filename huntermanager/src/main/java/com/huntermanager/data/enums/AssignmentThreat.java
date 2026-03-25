package com.huntermanager.data.enums;

public enum AssignmentThreat {
    ESQUECIDO("Esquecidos"), // Esquecidos, Torturados e Amálgamas
    CARNICIDA("Carnicidas"), // Carnicidas e Carniceiros
    ESCARNICADOR("Escarniçador"),
    ANIMADOR("Animador"),
    AMALDICOADO_MAIOR("Amaldiçoados Maiores"), // Espreitador, Ressonante, Testemunha da Culpa e criaturas mais absurdas.
    WAKWAK("Wakwak"),
    LADRAO_DE_TUMULO("Ladrão de Túmulo"),
    LEMBRADO("Lembrado"), // Lembrados e possíveis corrompidos.
    ATRITORES("Atritores"),
    DRAGAO("Dragão"),
    CONSTRUCTOS_MALAQUITA("Constructos de Malaquita"),
    CONSTRUCTOS_ERRANTES("Constructos Errantes"), // Abominação e Remendado
    WARG_INFERIOR("Warg INFERIOR"),
    WARG_SUPERIOR("Warg Superior"),
    WARG_ANCIAO("Warg Ancião"),
    LOBOS("Lobos"),
    VERME_SUBSTITUIDOR("Verme Substituidor"),
    ENTIDADE_ONIRICA("Entidade Onírica"),
    CRIATURA_DESCONHECIDA("Criatura Desconhecida");

    private final String displayName;

    AssignmentThreat(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}