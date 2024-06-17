public class Tabuleiro {

    private Pecas[][] pecaTabuleiro;

    public Tabuleiro() {
        this.pecaTabuleiro = new Pecas[8][8];
    }
    public void iniciarTabuleiro() {
        for (int i = 0; i < 3; i++) {
            for (int j = (i % 2); j < 8; j += 2) {
                pecaTabuleiro[i][j] = new Pecas("BLACK", false);
            }
        }
        for (int i = 5; i < 8; i++) {
            for (int j = (i % 2); j < 8; j += 2) {
                pecaTabuleiro[i][j] = new Pecas("WHITE", false);
            }
        }

    }
    public void imprimirTabuleiro() {
        System.out.print("  ");
        for (int j = 0; j < 8; j++) {
            System.out.print( j + " ");
        }
        System.out.println();
        for (int i = 0; i < 8; i++){
            System.out.print(i + " ");
            for (int j = 0; j < 8; j++){
                if (pecaTabuleiro[i][j] == null){
                    System.out.print("- ");
                }else{
                    System.out.print(pecaTabuleiro[i][j].getSimbolo() + " ");
                }
            }
            System.out.println();
        }
    }

    public Pecas getPeca(int x, int y) {
        if (x < 0 || x >= 8 || y < 0 && y >= 8) {
            return null;
        }
        return pecaTabuleiro[x][y];
    }

    public boolean movePeca(int fromX, int fromY, int toX, int toY) {
        if (moveValid(fromX, fromY, toX, toY)) {
            Pecas peca = pecaTabuleiro[fromX][fromY];
            pecaTabuleiro[toX][toY] = peca;
            pecaTabuleiro[fromX][fromY] = null;

            if (peca.getCor().equals("WHITE") && toY == 7) {
                peca.tornaKing();
            }
            if (peca.getCor().equals("BLACK") && toY == 0) {
                peca.tornaKing();
            }
            // Verificar se a peça tornou-se rei
            if ((peca.getCor().equals("WHITE") && toX == 0) || (peca.getCor().equals("BLACK") && toX == 7)) {
                peca.tornaKing();
            }
            // Capturar peças
            if (Math.abs(fromX - toX) == 2) {
                int captureX = (fromX + toX) / 2;
                int captureY = (fromY + toY) / 2;
                pecaTabuleiro[captureX][captureY] = null;
            }
            return true;
        }
        return false;
    }
    public boolean moveValid(int fromX, int fromY, int toX, int toY) {
        if (fromX < 0 || fromX >= 8 || fromY < 0 || fromY >= 8 || toX < 0 || toX >= 8 || toY < 0 || toY >= 8) {
            return false;
        }
        if (pecaTabuleiro[fromX][fromY] == null || pecaTabuleiro[toX][toY] != null){
            return false;
        }
        Pecas peca = pecaTabuleiro[fromX][fromY];
        int dx = toX - fromX;
        int dy = toY - fromY;

        if (peca.isKing()){
            if (peca.getCor().equals("WHITE") && dx != -1 && dx != -2){
                return false;
            }
            if (peca.getCor().equals("BLACK") && dx != 1 && dx != 2){
                return false;
            }
        }else {
            if (Math.abs(dx) != 1 && Math.abs(dx) != 2) {
                return false;
            }
        }
        if (Math.abs(dy)!= 1 && Math.abs(dy) != 2){
            return false;
        }
        if (Math.abs(dx) == 2 && Math.abs(dy) == 2){
            int captureX = (fromX + toX) / 2;
            int captureY = (fromY + toY) / 2;
            if (pecaTabuleiro[captureX][captureY] == null || pecaTabuleiro[captureX][captureY].getCor().equals(peca.getCor())){
                return false;
            }
        }
        return true;
    }
}
