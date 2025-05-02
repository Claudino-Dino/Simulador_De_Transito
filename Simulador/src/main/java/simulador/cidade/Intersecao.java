package simulador.cidade;

import simulador.semaforo.Semaforo;

public class Intersecao {
    public Semaforo s1 = new Semaforo(5000, 1000);
    public Semaforo s2 = new Semaforo(5000, 1000);
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

    public void acionarSemaforos() throws InterruptedException {
        s1.semaforoAbrir();
        s2.semaforoFechar();
        Thread.sleep(300);
        s1.semaforoFechar();
        s2.semaforoAbrir();
    }

}
