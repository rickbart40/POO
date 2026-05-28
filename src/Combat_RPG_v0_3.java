import java.util.Random;
import java.util.Scanner;

public class Combat_RPG_v0_3 {

    static Random numero = new Random();
    static Scanner ler = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            exibirMenu();
            System.out.print(" Escolha uma opção: ");

            int opcaoMenu;

            try {
                opcaoMenu = Integer.parseInt(ler.next());
            } catch (Exception e) {
                System.out.println(" Entrada inválida!");
                continue;
            }

            switch (opcaoMenu) {

                case 1 -> {
                    int boss;
                    listarChefes();
                    System.out.print(" Escolha um chefe para enfrentar: ");
                    boss = ler.nextInt();

                    switch (boss) {
                        case 1 -> combate("Murdock", 100, 100);
                        case 2 -> combate("Dragão Ancião",150, 100);
                        case 3 -> combate("Cthulhu", 200, 100);
                        case 4 -> combate("Kraken", 250, 100);
                        case 5 -> combate("Horus", 250, 100);
                        default -> System.out.println(" Chefe inválido!");
                    }
                }

                case 2 -> listarChefes();

                case 3 -> instrucoes();

                case 4 -> creditos();

                case 5 -> {
                    System.out.println(" Saindo do jogo...!");
                    return;
                }

                default -> System.out.println(" Opção inválida!");
            }
        }
    }

    public static String barraDeVidaColorida(int vidaAtual, int vidaMaxima) {

        // Códigos ANSI de cor
        final String RESET = "\u001B[0m";
        final String VERDE = "\u001B[32m";
        final String AMARELO = "\u001B[33m";
        final String VERMELHO = "\u001B[31m";

        int tamanhoBarra = 20;
        double porcentagem = (double) vidaAtual / vidaMaxima;

        if (porcentagem < 0) porcentagem = 0;
        int blocosCheios = (int) (porcentagem * tamanhoBarra);

        // Cor baseada na porcentagem
        String cor;
        if (porcentagem > 0.6) {
            cor = VERDE;
        } else if (porcentagem > 0.3) {
            cor = AMARELO;
        } else {
            cor = VERMELHO;
        }

        StringBuilder barra = new StringBuilder("[");

        for (int i = 0; i < tamanhoBarra; i++) {
            if (i < blocosCheios) barra.append(cor).append("█");
            else barra.append(RESET).append(" ");
        }

        barra.append(RESET).append("] ").append(vidaAtual).append("/").append(vidaMaxima);

        return barra.toString();
    }

    // Função ataque (leve, médio e pesado)
    public static int ataque(int dadoAtaque, int vidaBossAtual){
        int dano;

        System.out.println(" --------------------- ");
        System.out.println(" Nível de ataque!");
        System.out.println(" D6J: " + dadoAtaque);

        if (dadoAtaque <= 2) {             // leve
            dano = 4;
            System.out.println(" Ataque LEVE | Dano: " + dano);
        }
        else if (dadoAtaque <= 4) {        // médio
            dano = 8;
            System.out.println(" Ataque MÉDIO | Dano: " + dano);
        }
        else {                             // pesado
            dano = 12;
            System.out.println(" Ataque PESADO | Dano: " + dano);
        }

        return vidaBossAtual - dano;
    }

    // Função de combate
    public static void combate(String nomeBoss, int vidaBoss, int vidaJogador) {

        int vidaBossAtual = vidaBoss;
        int vidaJogadorAtual = vidaJogador;

        System.out.println(" ------------------------ ");
        System.out.println(" Combate contra " + nomeBoss + " começou!");
        System.out.println(" ------------------------ ");

        // Rola D20 uma vez para definir quem começa
        int D20J = numero.nextInt(20) + 1;
        int D20C = numero.nextInt(20) + 1;

        System.out.println(" Rolagem para iniciativa:");
        System.out.println(" Jogador (d20): " + D20J);
        System.out.println(" " + nomeBoss + " (d20): " + D20C);
        System.out.println(" ------------------------ ");

        boolean jogadorComeca = D20J > D20C;

        if (jogadorComeca) {
            System.out.println(" Jogador ganhou a iniciativa e começa atacando!");
        } else {
            System.out.println(" " + nomeBoss + " ganhou a iniciativa e começa atacando!");
        }

        System.out.println(" ------------------------ ");

        // Loop principal do combate
        while (vidaBossAtual > 0 && vidaJogadorAtual > 0) {

            System.out.println(" Vida do Jogador: " + barraDeVidaColorida(vidaJogadorAtual, vidaJogador));
            System.out.println(" Vida do " + nomeBoss + ": " + barraDeVidaColorida(vidaBossAtual, vidaBoss));

            System.out.println();

            if (jogadorComeca) {
                // Jogador ataca primeiro
                int D6J = numero.nextInt(6) + 1;
                System.out.println(" Jogador ataca (d6)!");
                vidaBossAtual = ataque(D6J, vidaBossAtual);

                if (vidaBossAtual <= 0) break;

                // Boss ataca
                int D6C = numero.nextInt(6) + 1;
                System.out.println(" " + nomeBoss + " contra-ataca (d6)! Dano: " + D6C);
                vidaJogadorAtual -= D6C;

            } else {
                // Boss ataca primeiro
                int D6C = numero.nextInt(6) + 1;
                System.out.println(" " + nomeBoss + " ataca primeiro (d6)! Dano: " + D6C);
                vidaJogadorAtual -= D6C;

                if (vidaJogadorAtual <= 0) break;

                // Jogador ataca depois
                int D6J = numero.nextInt(6) + 1;
                System.out.println(" Jogador contra-ataca (d6)!");
                vidaBossAtual = ataque(D6J, vidaBossAtual);
            }

            System.out.println();
        }

        System.out.println(" ====== Resultado =======");
        System.out.println(" Vida do Jogador: " + barraDeVidaColorida(vidaJogadorAtual, vidaJogador));
        System.out.println(" Vida do " + nomeBoss + ": " + barraDeVidaColorida(vidaBossAtual, vidaBoss));

        System.out.println(" ======================= ");

        if (vidaBossAtual <= 0) {
            System.out.println(" VITÓRIA! Você derrotou " + nomeBoss + "!");
        } else {
            System.out.println(" DERROTA! " + nomeBoss + " venceu você...");
        }
    }

    public static void creditos() {
        System.out.println("\n===== CRÉDITOS =====");
        System.out.println(" Jogo criado por: Rick S. Dravenhall");
        System.out.println(" Assistência técnica: ChatGPT, o Arquimago da Lógica");
        System.out.println("=====================\n");
    }

    public static void instrucoes() {
        System.out.println("\n===== INSTRUÇÕES =====");
        System.out.println(" - Um D20 inicial define quem ataca primeiro.");
        System.out.println(" - Cada turno você e o chefe rolam um D6.");
        System.out.println(" - Resultado do D6 do jogador define ataque: leve, médio ou pesado.");
        System.out.println(" - Chefes têm vida maior que a do jogador.");
        System.out.println(" - Vence quem zerar a vida primeiro!");
        System.out.println("=======================\n");
    }

    public static void listarChefes() {
        System.out.println("\n===== LISTA DE CHEFES =====");
        System.out.println(" 1 - Murdock (100 HP)");
        System.out.println(" 2 - Dragão Ancião (150 HP)");
        System.out.println(" 3 - Cthulhu (200 HP)");
        System.out.println(" 4 - Kraken (250 HP)");
        System.out.println(" 5 - Horus (250 HP)");
        System.out.println("============================\n");
    }

    public static void exibirMenu() {
        final String RESET = "\u001B[0m";
        final String CYAN = "\u001B[36m";
        final String VERDE = "\u001B[32m";
        final String AMARELO = "\u001B[33m";
        final String VERMELHO = "\u001B[31m";
        final String MAGENTA = "\u001B[35m";

        System.out.println(CYAN +
                "\n========================================" +
                "\n           BATALHA   LEGÊNDARIA"          +
                "\n========================================" + RESET
        );

        System.out.println(VERDE + " 1 - Iniciar Combate" + RESET);
        System.out.println(AMARELO + " 2 - Lista de Chefes" + RESET);
        System.out.println(CYAN + " 3 - Instruções" + RESET);
        System.out.println(MAGENTA + " 4 - Créditos" + RESET);
        System.out.println(VERMELHO + " 5 - Sair" + RESET);
    }

}
