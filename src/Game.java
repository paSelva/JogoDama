import java.util.Scanner;

public class Game {
    private Tabuleiro tabuleiro;
    private Jogador jogador1;
    private Jogador jogador2;
    private Jogador jogadorAtual;

    public Game() {
        tabuleiro = new Tabuleiro();
        jogador1 = new Jogador("WHITE",true);
        jogador2 = new Jogador("BLACK",true);
        jogadorAtual = jogador1;
    }
    public void iniciarJogo(){
        tabuleiro.iniciarTabuleiro();

        while(!gamaOver()){
            tabuleiro.imprimirTabuleiro();
            imprimiMovimento();
            proximoJogador();
        }
        anunciarVitoria();

    }
    public boolean gamaOver(){
        return false;
    }

    private void proximoJogador(){
        jogadorAtual = (jogadorAtual == jogador1) ? jogador2 : jogador1;
    }
    private boolean verificaJogoOver(Jogador jogador){
        String color = jogador.getCor();
        for (int i = 0; i < 8;i++){
            for (int j = 0; j < 8;j++){
                Pecas peca = tabuleiro.getPeca(i,j);
                if (peca != null && peca.getCor().equals(color)){
                        return false;
                }
            }
        }
    return true;
    }
    private boolean movimentoValido(int X,  int y, Pecas peca){
        int [][] direcao = peca.isKing() ? new int[][]{{-1, -1}, {-1, 1}, {1, -1}, {1, 1}} : peca.getCor().equals("White") ? new int[][]{{-1, -1}, {-1, 1}} : new int[][]{{1, -1}, {1, 1}};
        for (int [] dir : direcao){
            int newX = X + dir[0];
            int newY = y + dir[1];
            if (tabuleiro.moveValid(X, y, newX, newY)){
                return true;
            }
        }
        return false;
    }

    private boolean movimentoInvalido(Jogador jogador){
        String color = jogador.getCor();
        for (int i = 0; i < 8;i++){
            for (int j = 0; j < 8;j++){
                Pecas peca = tabuleiro.getPeca(i,j);
                if (peca != null && peca.getCor().equals(color)){
                    if (movimentoValido(i, j, peca)){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    private void imprimiMovimento(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(jogadorAtual.getCor() + " Move (from X Y) to (to X Y): ");
        System.out.print("from X: ");
        int fromX = scanner.nextInt();
        System.out.print("from Y: ");
        int fromY = scanner.nextInt();
        System.out.print("to X: ");
        int toX = scanner.nextInt();
        System.out.print("to Y: ");
        int toY = scanner.nextInt();

        if (!tabuleiro.movePeca(fromX, fromY, toX, toY)){
            System.out.println("Movimento Invalido");
            imprimiMovimento();
        }
    }

    private boolean semPecas(Jogador jogador){
        String color = jogador.getCor();
        for (int i = 0; i < 8;i++){
            for (int j = 0; j < 8;j++){
                Pecas peca = tabuleiro.getPeca(i,j);
                if (peca != null && peca.getCor().equals(color)){
                    return false;
                }
            }
        }
        return true;
    }

    private void anunciarVitoria(){
        Jogador vencedor = semPecas(jogador1) || movimentoInvalido(jogador1) ? jogador2 : jogador1;
        System.out.println("Fim de Jogo. Vencedor: " + vencedor.getCor()+ "!!!");
    }
}
