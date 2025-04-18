package simulador.cidade;

public class Intersecao {
    public int id;
    public int qtdeSemaforos;
    public int valor;
    public boolean temSemaforo;
    public boolean origem;

    public Intersecao(int id, int valor, int qtdeSemaforos, boolean temSemaforo) {
        this.id = id;
        this.temSemaforo = temSemaforo;
        this.qtdeSemaforos = qtdeSemaforos;
        this.valor = valor;
    }

    public boolean ehOrigem() {
        return valor == 1 ? true : false;
    }

}
