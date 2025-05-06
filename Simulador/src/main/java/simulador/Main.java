package simulador;

import simulador.cidade.Intersecao;
import simulador.estruturas.ListaEncadeada;
import simulador.estruturas.NoDuplo;
import simulador.semaforo.Semaforo;

public class Main {
    public static void main(String[] args) {
//        Simulador simulador = new Simulador();
//        simulador.iniciar();

//        FAZER INTERCESSEÇÃO COM DOIS SEMÁFOROS E COMUTAR

        Intersecao i1 = new Intersecao(1, 1, 2, true);

        try {
            i1.acionarSemaforos();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
