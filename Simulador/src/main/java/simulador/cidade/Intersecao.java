package simulador.cidade;

import simulador.estruturas.ListaEncadeada;
import simulador.estruturas.NoDuplo;
import simulador.semaforo.ModoOperacao;
import simulador.semaforo.Semaforo;

import javax.management.openmbean.InvalidKeyException;


public class Intersecao {
    public ListaEncadeada<Semaforo> listaSemaforos = new ListaEncadeada<>();
    public ListaEncadeada<Rua> listaRuas = new ListaEncadeada<>();
    public String id;
    public Double latitude;
    public Double longitude;

    public Intersecao(String id, Double latitude, Double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void acionarSemaforos(ModoOperacao modo) throws InterruptedException {
        if (listaSemaforos.estaVazia() || listaRuas.estaVazia()) {
            throw new InvalidKeyException("LISTA DE SEMAFORO OU RUA ESTÃO NULL");
        }

        switch (modo) {
            case CICLO_FIXO:
                modo.acionarCicloFixo(this.listaSemaforos);
            case TEMPO_ESPERA:
                modo.acionarTempoEspera(this.listaSemaforos, this.listaRuas);
            case CONSUMO:
                modo.acionarConsumo(this.listaSemaforos, this.listaRuas);
            default:
                throw new IllegalStateException("Unexpected value: " + modo);
        }
    }

    public ListaEncadeada<Semaforo> getListaSemaforos() {
        return listaSemaforos;
    }

    public void adicionarSemaforo(Semaforo s) {
        this.listaSemaforos.enfileirar(new NoDuplo<>(s));
    }

    public ListaEncadeada<Rua> getListaRuas() {
        return listaRuas;
    }

    public void adicionarRua(Rua r) {
        this.listaRuas.enfileirar(new NoDuplo<>(r));
    }

}
