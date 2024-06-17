public class Pecas {
    private String cor;
    private boolean isKing;

    public Pecas(String cor, boolean isKing) {
        this.cor = cor;
        this.isKing = false;
    }

    public String getCor() {
        return cor;
    }

    public boolean isKing() {
        return isKing;
    }
    public void tornaKing(){
        isKing = true;
    }
    public String getSimbolo(){
        if (isKing){
            return cor.equals("WHITE") ? "WK" : "BK";
        }else {
            return cor.equals("WHITE") ? "W" : "B";
        }

    }
}
