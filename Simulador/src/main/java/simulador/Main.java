package simulador;

import simulador.cidade.Intersecao;

import static simulador.semaforo.ModoOperacao.CICLO_FIXO;


public class Main {
    public static void main(String[] args) {
//        Simulador simulador = new Simulador();
//        simulador.iniciar();

//        FAZER INTERCESSEÇÃO COM DOIS SEMÁFOROS E COMUTAR

        Intersecao i1 = new Intersecao(1, 1, 2, true);

        try {
            i1.acionarSemaforos(CICLO_FIXO);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
