package simulador.cidade;

import simulador.estruturas.ListaEncadeada;
import simulador.estruturas.NoDuplo;
import simulador.semaforo.ModoOperacao;
import simulador.semaforo.Semaforo;

import static simulador.semaforo.ModoOperacao.*;

public class Intersecao {
    public ListaEncadeada<Semaforo> listaSemaforos = new ListaEncadeada<>();
    public ListaEncadeada<Rua> listaRuas = new ListaEncadeada<>();


    public String id;
    public String latitude;
    public String longitude;

    public Intersecao(String id, String latitude, String longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void acionarSemaforos(ModoOperacao modo, Rua rua1, Rua rua2) throws InterruptedException {
        Semaforo s1 = new Semaforo();
        Semaforo s2 = new Semaforo();

        listaSemaforos.adicionar(new NoDuplo<>(s1), 0);
        listaSemaforos.adicionar(new NoDuplo<>(s2), 1);

        switch (modo) {
            case CICLO_FIXO:
                modo.acionarCicloFixo(this.listaSemaforos);
            case TEMPO_ESPERA:
                modo.acionarTempoEspera(this.listaSemaforos, rua1, rua2);
            case CONSUMO:
                modo.acionarConsumo(this.listaSemaforos, rua1, rua2);
            default:
                throw new IllegalStateException("Unexpected value: " + modo);
        }
    }

}
