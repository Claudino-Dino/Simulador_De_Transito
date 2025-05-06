package simulador.cidade;

import simulador.estruturas.ListaEncadeada;
import simulador.semaforo.Semaforo;

public class Intersecao {
    public Semaforo s1 = new Semaforo(5000, 1000);
    public Semaforo s2 = new Semaforo(5000, 1000);
    public ListaEncadeada<Semaforo> listaSemaforos = new ListaEncadeada<>();
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
        s2.obterEstado("VERMELHO");
        System.out.println("simulador.semaforo.Semaforo@4fca772d : " + s2.getConteudoAtual());

        while (true) {
            s1.semaforoAbrir();
            s1.semaforoFechar();
            Thread.sleep(200);
            s2.semaforoAbrir();
            s2.semaforoFechar();
            Thread.sleep(200);
        }

    }

}
