import java.util.Random;
import java.util.Scanner;

public class Combat_RPG_v0_1 {

    static Random numero = new Random();
    static Scanner ler = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        //combate("Murdock", 100, 100);
        // Exemplo: outro boss
        //combate("Dragão Ancião", 150, 150);

        System.out.println(" Escolha qual quer enfrentar: ");
        System.out.println(" 1 - Murdock ");
        System.out.println(" 2 - Dragão Ancião ");
        System.out.println(" 3 - Cthulhu ");
        System.out.println(" 4 - Kraken ");
        System.out.println(" 5 - Horus ");
        System.out.print(" Opção escolhida: ");
        opcao = ler.nextInt();

        switch (opcao) {
            case 1:
                combate("Murdock", 100, 100);
                break;
                case 2:
                    combate("Dragão Ancião",150, 100);
                    break;
                    case 3:
                        combate("Cthullu", 200, 100);
                        break;
                        case 4:
                            combate("Kraken", 250, 100);
                            break;
                            case 5:
                                combate("Horus", 250, 100);
                                break;
        }
    }

    // Função ataque(leve, medio e pesado)
    public static int ataque(int D6J, int VidaBossAtual){
        Random numero = new Random();
        int dano = 0;

        System.out.println(" --------------------- ");
        System.out.println(" Nivel de ataque!");

        // ataque leve
        if (D6J == 1 || D6J == 2){
            System.out.println(" D6J: " + D6J);
            dano = 4;//numero.nextInt(4) + 1; // D4
            System.out.println(" Ataque LEVE | Dano: " + dano);
        }
        // ataque médio
        else if (D6J == 3 || D6J == 4) {
            System.out.println(" D6J: " + D6J);
            dano = 8;//numero.nextInt(10) + 1; // D10
            System.out.println(" Ataque MÉDIO | Dano: " + dano);
        }
        // ataque pesado
        else if (D6J == 5 || D6J == 6) {
            System.out.println(" D6J: " + D6J);
            dano = 12;//numero.nextInt(12) + 1; // D12
            System.out.println(" Ataque PESADO | Dano: " + dano);
        }

        // retorna vida nova do boss
        return VidaBossAtual - dano;
    }

    // Função de combate
    public static void combate(String nomeBoss, int vidaBoss, int vidaJogador) {

        int VidaBossAtual = vidaBoss;
        int VidaJogadorAtual = vidaJogador;

        System.out.println(" ------------------------ ");
        System.out.println(" Combate contra " + nomeBoss + " começou!");
        System.out.println(" ------------------------ ");

        // Rola D20 apenas uma vez para definir quem começa
        int D20J = numero.nextInt(20) + 1;
        int D20C = numero.nextInt(20) + 1;

        System.out.println(" Rolagem para iniciativa:");
        System.out.println(" Jogador (d20) = " + D20J);
        System.out.println(" " + nomeBoss + " (d20) = " + D20C);
        System.out.println(" ------------------------ ");

        boolean jogadorComeca;

        if (D20J > D20C) {
            jogadorComeca = true;
            System.out.println(" Jogador ganhou a iniciativa e começa atacando!");
        } else {
            jogadorComeca = false;
            System.out.println(" " + nomeBoss + " ganhou a iniciativa e começa atacando!");
        }

        System.out.println(" ------------------------ ");

        // LOOP DE COMBATE
        while (VidaBossAtual > 0 && VidaJogadorAtual > 0) {

            System.out.println(" Vida do Jogador: " + VidaJogadorAtual + "%");
            System.out.println(" Vida do " + nomeBoss + ": " + VidaBossAtual + "%");
            System.out.println();

            if (jogadorComeca) {
                // Jogador ataca primeiro
                int D6J = numero.nextInt(6) + 1;
                System.out.println(" Jogador Ataca Primeiro (d6)! ");
                VidaBossAtual = ataque(D6J, VidaBossAtual);
                if (VidaBossAtual <= 0) break;

                // Boss ataca (simples)
                int D6C = numero.nextInt(6) + 1;
                System.out.println(" " + nomeBoss + " contra-ataca (d6)! Dano: " + D6C);
                VidaJogadorAtual -= D6C;
            } else {
                // Boss ataca primeiro
                int D6C = numero.nextInt(6) + 1;
                System.out.println(" " + nomeBoss + " ataca primeiro (d6)! Dano: " + D6C);
                VidaJogadorAtual -= D6C;
                if (VidaJogadorAtual <= 0) break;

                // Jogador ataca
                int D6J = numero.nextInt(6) + 1;
                System.out.println(" Jogador contra-ataca (d6)!");
                VidaBossAtual = ataque(D6J, VidaBossAtual);
            }

            System.out.println();
        }

        System.out.println(" ======================= ");

        if (VidaBossAtual <= 0) {
            System.out.println(" VITÓRIA! Você derrotou " + nomeBoss + "!");
        } else {
            System.out.println(" DERROTA! " + nomeBoss + " venceu você...");
        }
    }
}