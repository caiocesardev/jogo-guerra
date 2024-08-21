package Simulador;

import java.util.Random;
import java.util.Scanner;

public class AtacarSozinho implements Estrategia {
    Scanner input = new Scanner(System.in);

    @Override
    public void agir(Pais agressor, Pais adversario) {
        int qtdInicialSoldados = adversario.getQtdSoldados();
        if (plantarEvidenciasFalsas(agressor)) {
            System.out.println("Evidência falsa plantada com sucesso!");
            int perdaCredibilidade = (int) (adversario.getCredibilidade() - (adversario.getCredibilidade() * 0.2));
            adversario.setCredibilidade(perdaCredibilidade);
            while (adversario.getQtdSoldados() > (qtdInicialSoldados * 0.3)) {
                soltarBombas(agressor, adversario);
            }
            derrubarGoverno(adversario);
        } else {
            int perdaCredibilidade = (int) (agressor.getCredibilidade() - (agressor.getCredibilidade() * 0.3));
            agressor.setCredibilidade(perdaCredibilidade);
            System.out.println("Evidência falsa falhou. Deseja atacar mesmo assim? (S/N): ");
            char desejaAtacar = input.next().charAt(0);
            if (desejaAtacar == 'S') {
                while (adversario.getQtdSoldados() > (qtdInicialSoldados * 0.3)) {
                    soltarBombas(agressor, adversario);
                }
                derrubarGoverno(adversario);
            } else {
                System.out.println("Aguardando ação...");
            }
        }
    }

    private boolean plantarEvidenciasFalsas(Pais agressor) {
        if (agressor.getCredibilidade() < 20) {
            System.out.println("Credibilidade insuficiente para plantar evidências falsas!");
            return false; 
        }     
        Random tentativa = new Random();
        return tentativa.nextBoolean();
    }
    
    private void soltarBombas(Pais agressor, Pais adversario) {
        if (agressor.podeUsarBombaNuclear()) {
            soltarBombaNuclear(adversario);
        } else {
            soltarBombaNormal(adversario);
        }
    }

    private void soltarBombaNormal(Pais adversario) {
        Random random = new Random();
        int baixas = random.nextInt(1001) + 500; // Gera uma quantidade de baixas aleatória de soldados entre 500 e 1500
        int chanceDeFalha = random.nextInt(100); // Chance de falha no ataque (0 a 99)
        
        if (chanceDeFalha < 20) { // 20% de chance de falha
            System.out.println("A bomba normal falhou e não causou dano!");
        } else {
            baixas = Math.max(0, baixas);
            adversario.setQtdSoldados(adversario.getQtdSoldados() - baixas);
            System.out.println("Bomba normal lançada! A quantidade de baixas inimigas é de: " + baixas + ". Soldados restantes do adversário: " + adversario.getQtdSoldados());
        }
    }

    private void soltarBombaNuclear(Pais adversario) {
        Random random = new Random();
        int chanceDeSucesso = random.nextInt(31) + 20; // Chance de sucesso entre 20% e 50%
        int chanceDeFalha = random.nextInt(100); // Chance de falha no ataque (0 a 99)

        if (chanceDeFalha < chanceDeSucesso) {
            int porcentagemDano = random.nextInt(31) + 70; // Porcentagem de soldados eliminados entre 70% e 100%
            int qtdSoldadosAntes = adversario.getQtdSoldados();
            int baixas = (int) (qtdSoldadosAntes * (porcentagemDano / 100.0));
            adversario.setQtdSoldados(0); // Após uma bomba nuclear, todos os soldados são eliminados
            
            System.out.println("Bomba nuclear lançada com sucesso! A quantidade de baixas inimigas é de: " + baixas + ". Todos os soldados do adversário foram eliminados.");
        } else {
            System.out.println("A bomba nuclear falhou e não causou dano!");
        }
    }

    private void derrubarGoverno(Pais adversario) {
        if (adversario.getCredibilidade() <= 0 || adversario.getQtdSoldados() <= 0) {
            System.out.println("Governo do país " + adversario.getNome() + " foi derrubado!");
        } else {
            System.out.println("O país " + adversario.getNome() + " ainda está em pé.");
        }
    }

}
