public class Jogador {

    private String cor;
    private boolean peSimples;

    public Jogador(String cor, boolean PeSimples) {
        this.cor = cor;
        this.peSimples = PeSimples;
    }

    public String getCor() {
        return cor;
    }
    public boolean isPeSimples() {
        return peSimples;
    }
}
