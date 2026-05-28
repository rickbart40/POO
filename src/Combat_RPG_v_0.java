import java.util.Random;
import java.util.Scanner;

public class Combat_RPG_v_0 {
    public static void main(String[] args) {
        int vidaBoss = 100, manaBoss = 100;
        int VidaBossAtual = vidaBoss, manaBossAtual = manaBoss;
        int vidaJogador = 100, manaJogador = 100;
        int VidaJogadorAtual = vidaJogador, manaJogadorAtual = manaJogador;
        int D20J = 0, D6J = 0, D20C = 0, D6C = 0;
        Random numero = new Random();
        Scanner ler = new Scanner(System.in);

        // Texto Inicio do combate
        System.out.println(" ------------------------ ");
        System.out.println(" Combate contra o chefe... ");
        System.out.println(" ------------------------ ");

        while (VidaBossAtual > 0 && VidaJogadorAtual > 0) {

            // Texto nome, vida e mana do boss
            System.out.println(" -------- Murdock ------- ");
            System.out.println(" Vida " + VidaBossAtual + "%");
            System.out.println(" Mana " + manaBossAtual + "%");
            System.out.println(" ------------------------ ");
            System.out.println(" ");

            //Jogador joga dado
            System.out.println(" Dados do jogador  (d20)");
            D20J = numero.nextInt(20) + 1;
            System.out.println(" Resultado: " + D20J);
            System.out.println(" ------------------------ ");

            //CPU joga dado
            System.out.println(" Dados do Boss (D20)");
            D20C = numero.nextInt(20) + 1;
            System.out.println(" Resultado: " + D20C);
            System.out.println(" ------------------------ ");

            if (D20J > D20C) {
                System.out.println(" JOGADOR começa ");
                System.out.println(" ------------------------ ");
                System.out.println(" JOGADOR Ataca (d6)!");
                D6J = numero.nextInt(6) + 1;
                System.out.println(" Resultado: " + D6J);
                VidaBossAtual -= D6J;
                System.out.println(" Vida do Boss " + VidaBossAtual + "%");

            } else {
                System.out.println(" CPU começa ");
                System.out.println(" ------------------------ ");
                System.out.println(" CPU Ataca (d6)!");
                D6C = numero.nextInt(6) + 1;
                VidaJogadorAtual -= D6C;
                System.out.println(" Resultado: " + D6C);
                System.out.println(" Vida do Jogador " + VidaJogadorAtual + "%");
            }

        }

        // Resultado final
        if (VidaBossAtual <= 0) {
            System.out.println(" ======================= ");
            System.out.println(" VITÓRIA! Você derrotou Murdock!");
        } else {
            System.out.println(" ======================= ");
            System.out.println(" DERROTA! O chefe venceu você...");
        }
    }
}
