package Simulador;

public class Pais {

    private final String nome;
    private int qtdSoldados;
    private final boolean temArmasNucleares;
    private final double capacidadeEconomica;
    private int credibilidade;
    private Estrategia estrategia;

    public Pais(String nome, int qtdSoldados, boolean temArmasNucleares, double capacidadeEconomica, int credibilidade) {
        this.nome = nome;
        this.qtdSoldados = qtdSoldados;
        this.temArmasNucleares = temArmasNucleares;
        this.capacidadeEconomica = capacidadeEconomica;
        this.credibilidade = credibilidade;
    }

    public String getNome() {
        return nome;
    }

    public int getQtdSoldados() {
        return qtdSoldados;
    }

    public void setQtdSoldados(int qtdSoldados) {
        this.qtdSoldados = qtdSoldados;
    }

    public boolean isTemArmasNucleares() {
        return temArmasNucleares;
    }

    public double getCapacidadeEconomica() {
        return capacidadeEconomica;
    }

    public int getCredibilidade() {
        return credibilidade;
    }

    public void setCredibilidade(int credibilidade) {
        this.credibilidade = credibilidade;
    }

    public Estrategia getEstrategia() {
        return estrategia;
    }

    public void setEstrategia(Estrategia estrategia) {
        this.estrategia = estrategia;
    }

    public void declararGuerra(Pais adversario) {
        this.estrategia.agir(this, adversario);        
    }

    public boolean podeUsarBombaNuclear() {
        return this.temArmasNucleares;
    }

    public boolean estaDerrubado() {
        return this.qtdSoldados <= 0 || this.credibilidade <= 0;
    }

    public void exibirInformacoes() {
        System.out.println("Nome: " + nome);
        System.out.println("Quantidade de Soldados: " + qtdSoldados);
        System.out.println("Tem Armas Nucleares: " + (temArmasNucleares ? "Sim" : "Não"));
        System.out.println("Capacidade Econômica: " + capacidadeEconomica);
        System.out.println("Credibilidade: " + credibilidade);
    }
}
