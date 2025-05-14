package simulador;

import simulador.cidade.Grafo;
import simulador.cidade.Intersecao;

import static simulador.semaforo.ModoOperacao.CICLO_FIXO;
import static simulador.semaforo.ModoOperacao.TEMPO_ESPERA;


public class Main {
    public static void main(String[] args) {
//        Simulador simulador = new Simulador();
//        simulador.iniciar();

//        FAZER INTERCESSEÇÃO COM DOIS SEMÁFOROS E COMUTAR

        Intersecao i1 = new Intersecao("a", 2.0, 2.1);

//        try {
//            i1.acionarSemaforos(TEMPO_ESPERA);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        Grafo grafo = new Grafo();

    }

}
