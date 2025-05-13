package simulador.cidade;

import simulador.estruturas.ListaEncadeada;
import simulador.estruturas.NoDuplo;
import simulador.semaforo.ModoOperacao;
import simulador.semaforo.Semaforo;

import static simulador.semaforo.ModoOperacao.*;

public class Intersecao {
    public Rua rua1 = new Rua();
    public Rua rua2 = new Rua();
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

    public void acionarSemaforos(ModoOperacao modo) throws InterruptedException {
        Semaforo s1 = new Semaforo(5000, 1000);
        Semaforo s2 = new Semaforo(5000, 1000);

        listaSemaforos.adicionar(new NoDuplo<>(s1),0);
        listaSemaforos.adicionar(new NoDuplo<>(s2),1);

        switch (modo) {
            case CICLO_FIXO:
                modo.acionarCicloFixo(this.listaSemaforos);
            case TEMPO_ESPERA:
                modo.acionarTempoEspera(this.listaSemaforos, this.rua1, this.rua2);
            case CONSUMO:
                modo.acionarConsumo(this.listaSemaforos, this.rua1, this.rua2);
            default:
                throw new IllegalStateException("Unexpected value: " + modo);
        }
    }

}
