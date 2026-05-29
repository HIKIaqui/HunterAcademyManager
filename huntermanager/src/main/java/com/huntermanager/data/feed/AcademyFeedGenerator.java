package com.huntermanager.data.feed;

import java.util.Random;

import com.huntermanager.data.HunterAcademy;
import com.huntermanager.data.MonsterHunter;
import com.huntermanager.data.enums.AcademyFeedType;

public class AcademyFeedGenerator {
    private static final Random random = new Random();

    public static void generateTimePassageFeed(HunterAcademy academy) {
        if (academy == null) {
            return;
        }

        MonsterHunter[] hunters = academy.getActiveHunters();

        if (hunters.length == 0) {
            generateEmptyAcademyFeed(academy);
            return;
        }

        // Primeiro tenta gerar uma fala baseada em caçador.
        if (random.nextInt(100) < 70) {
            generateHunterStatusFeed(academy, hunters);
            return;
        }

        // Se não, gera flavor geral.
        generateGeneralFlavorFeed(academy);
    }

    private static void generateEmptyAcademyFeed(HunterAcademy academy) {
        String[] messages = {
            "A academia está quieta demais. Isso seria bom, se não parecesse ameaça.",
            "O salão vazio ecoa cada passo. Dramático, mas péssimo para moral.",
            "Nenhum caçador disponível. A administração chama isso de oportunidade de reflexão. Péssimo sinal."
        };

        academy.addFeedEntry(randomOf(messages), AcademyFeedType.SYSTEM);
    }

    private static void generateHunterStatusFeed(HunterAcademy academy, MonsterHunter[] hunters) {
        MonsterHunter hunter = hunters[random.nextInt(hunters.length)];

        if (hunter == null) {
            return;
        }

        // Prioridade: estresse alto.
        if (hunter.getStress() >= 8) {
            academy.addFeedEntry(getHighStressLine(hunter), AcademyFeedType.WARNING);
            return;
        }

        // Prioridade: HP baixo.
        if (hunter.getHP() <= hunter.getMaxHP() / 3) {
            academy.addFeedEntry(getLowHpLine(hunter), AcademyFeedType.WARNING);
            return;
        }

        // Social baixo.
        if (hunter.getSocial() <= 0) {
            academy.addFeedEntry(getLowSocialLine(hunter), AcademyFeedType.HUNTER);
            return;
        }

        // Social alto.
        if (hunter.getSocial() >= 3) {
            academy.addFeedEntry(getHighSocialLine(hunter), AcademyFeedType.HUNTER);
            return;
        }

        // Fala comum.
        academy.addFeedEntry(getNeutralHunterLine(hunter), AcademyFeedType.FLAVOR);
    }

    private static void generateGeneralFlavorFeed(HunterAcademy academy) {
        String[] messages = switch (academy.getCurrentDayTime()) {
            case HunterAcademy.MORNING -> new String[] {
                "O café foi servido fraco, amargo e tecnicamente líquido.",
                "A academia acordou antes do sino. Ninguém admitiu o motivo.",
            };
            case HunterAcademy.AFTERNOON -> new String[] {
                "A tarde começou. Tempo suficiente para preparar uma equipe ou cometer erro com antecedência.",
                "O pátio ficou cheio de gente fingindo não olhar para o mural de contratos.",
                "Alguém treinou no boneco errado. O boneco certo parece aliviado."
            };
            case HunterAcademy.NIGHT -> new String[] {
                "A noite caiu. Monstros gostam disso. Péssimo gosto, boa estratégia.",
                "As luzes foram apagadas. Nem todo mundo dormiu.",
                "O vento bateu nas janelas. Três caçadores pegaram armas. Um pegou travesseiro."
            };
            default -> new String[] {
                "O tempo passou. Infelizmente, as responsabilidades também."
            };
        };

        academy.addFeedEntry(randomOf(messages), AcademyFeedType.FLAVOR);
    }

    private static String getHighStressLine(MonsterHunter hunter) {
        String name = hunter.getName();

        String[] messages = {
            name + ": \"Tem alguém andando no corredor.\"",
            name + " ficou olhando para a própria mão por tempo demais.",
            name + " disse que não vai dormir. O sonho, aparentemente, estava esperando.",
            name + " contou as portas do corredor. Havia cinco. " + name + " contou sete.",
            name + " riu de uma piada que ninguém contou."
        };

        return randomOf(messages);
    }

    private static String getLowHpLine(MonsterHunter hunter) {
        String name = hunter.getName();

        String[] messages = {
            name + ": \"Eu tô bem.\" " + name + " tossiu sangue logo depois.",
            name + " tentou levantar sem apoiar na parede. A parede venceu.",
            name + " passou a manhã segurando o próprio braço. Disse que era costume.",
            name + ": \"Eu consigo ir.\" A enfermaria discordou em termos agressivamente médicos.",
            name + " recusou ajuda. Aceitou três segundos depois. Recusou de novo por orgulho."
        };

        return randomOf(messages);
    }

    private static String getLowSocialLine(MonsterHunter hunter) {
        String name = hunter.getName();

        String[] messages = {
            name + ": \"Vocês falam demais.\" Ninguém estava falando com " + name + ".",
            name + ": \"Isso era um elogio.\" O salão discordou em silêncio.",
            name + " tentou consolar alguém dizendo que todo mundo morre. Não ajudou.",
            name + " recusou companhia porque 'a respiração coletiva reduz a qualidade do ambiente'.",
            name + ": \"Se isso ofendeu você, o problema começou antes de mim.\""
        };

        return randomOf(messages);
    }

    private static String getHighSocialLine(MonsterHunter hunter) {
        String name = hunter.getName();

        String[] messages = {
            name + " percebeu que alguém não tinha comido e deixou pão por perto sem dizer nada.",
            name + " fez o salão rir por alguns segundos. Pequena vitória contra o mundo.",
            name + " interrompeu uma discussão antes que ela virasse inventário de ferimentos.",
            name + " ficou no corredor até um colega conseguir dormir.",
            name + ": \"Respira primeiro. Depois você decide se odeia o mundo.\""
        };

        return randomOf(messages);
    }

    private static String getNeutralHunterLine(MonsterHunter hunter) {
        String name = hunter.getName();

        String[] messages = {
            name + " procurou a própria arma. Ela estava exatamente onde deveria. Isso não ajudou.",
            name + " reclamou do cheiro da cozinha. A cozinha não comentou.",
            name + " sentou no salão como se estivesse descansando. A expressão sugeria negociação com a existência.",
            name + ": \"A gente chama isso de rotina? Corajoso.\"",
            name + " encarou o mural de contratos e decidiu beber água. Grande estratégia."
        };

        return randomOf(messages);
    }

    private static String randomOf(String[] messages) {
        return messages[random.nextInt(messages.length)];
    }
}