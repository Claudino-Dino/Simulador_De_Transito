package simulador.semaforo;

import simulador.cidade.Rua;
import simulador.estruturas.ListaEncadeada;

import java.security.InvalidKeyException;
import java.util.Random;

public enum ModoOperacao {
    CICLO_FIXO,
    TEMPO_ESPERA,
    CONSUMO;

    Random gerador = new Random();

    ControladorSemaforos cs = new ControladorSemaforos();

    public void acionarCicloFixo(ListaEncadeada<Semaforo> listaSemaforos) throws InterruptedException {
        Semaforo s1 = null;
        Semaforo s2 = null;
        try {
            s2 = listaSemaforos.obter(1);
            s1 = listaSemaforos.obter(0);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }

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

    public void acionarTempoEspera(ListaEncadeada<Semaforo> listaSemaforos, Rua rua1, Rua rua2) {
        Semaforo s1 = null;
        Semaforo s2 = null;
        try {
            s2 = listaSemaforos.obter(1);
            s1 = listaSemaforos.obter(0);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }

        System.out.println(s2.obterEstado("VERMELHO"));

        while (true) {
            int tmpRua1 = calcularTempoEspera(rua1);
            int tmpRua2 = calcularTempoEspera(rua2);

            s1.setTempoVerde(tmpRua1);
            s2.setTempoVermelho(tmpRua1 + s2.getTempoAmarelo());

            s1.semaforoAbrir();

            s1.setTempoVermelho(tmpRua2 + s1.getTempoAmarelo());
            s2.setTempoVerde(tmpRua2);

            s1.obterEstado("VERMELHO");

            s2.semaforoAbrir();
            s2.semaforoFechar();
        }
    }

    public void acionarConsumo(ListaEncadeada<Semaforo> listaSemaforos, Rua rua1, Rua rua2) {
        try {
            if (cs.eHorarioPico()) {
                acionarTempoEspera(listaSemaforos, rua1, rua2);
            } else {
                acionarCicloFixo(listaSemaforos);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    public int calcularTempoEspera(Rua rua) {
        int qtdeCarrosRua = rua.filaCarrosRua; //.tamanho();

        int rand = gerador.nextInt(40);
        System.out.println(rand + " carros na rua " + rua);
        return rand * 400;
    }

}
