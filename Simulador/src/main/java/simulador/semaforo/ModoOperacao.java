package simulador.semaforo;

import simulador.cidade.Rua;
import simulador.estruturas.ListaEncadeada;

import java.security.InvalidKeyException;

public enum ModoOperacao {
    CICLO_FIXO,
    TEMPO_ESPERA,
    CONSUMO;

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

        s1.semaforoAbrir();
        s1.semaforoFechar();
        Thread.sleep(200);
        s2.semaforoAbrir();
        s2.semaforoFechar();
        Thread.sleep(200);

    }

    public void acionarTempoEspera(ListaEncadeada<Semaforo> listaSemaforos, ListaEncadeada<Rua> listaRuas) {
        Semaforo s1; Semaforo s2;
        Rua rua1; Rua rua2;

        s1 = listaSemaforos.desenfileirar().conteudo;
        s2 = listaSemaforos.desenfileirar().conteudo;
        rua1 = listaRuas.desenfileirar().conteudo;
        rua2 = listaRuas.desenfileirar().conteudo;

        System.out.println(s2.obterEstado("VERMELHO"));

//            CALCULAR TEMPO DE ESPERA BASEADO NA QUANTIDADE DE CARROS NAS DUAS RUAS;
        int tmpRua1 = calcularTempoEspera(rua1);
        int tmpRua2 = calcularTempoEspera(rua2);

//            ALTERAR TEMPO PARA CADA COR DO SINAL;
        s1.setTempoVerde(tmpRua1);
        s2.setTempoVermelho(tmpRua1 + s2.getTempoAmarelo());

        s1.semaforoAbrir();

        s1.setTempoVermelho(tmpRua2 + s1.getTempoAmarelo());
        s2.setTempoVerde(tmpRua2);

        System.out.println(s1.obterEstado("VERMELHO"));

        s2.semaforoAbrir();
        s2.semaforoFechar();

    }

    public void acionarConsumo(ListaEncadeada<Semaforo> listaSemaforos, ListaEncadeada<Rua> listaRuas) {
        try {
            if (cs.eHorarioPico()) {
                acionarTempoEspera(listaSemaforos, listaRuas);
            } else {
                acionarCicloFixo(listaSemaforos);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    public int calcularTempoEspera(Rua rua) {
        if (rua.filaCarrosRua == null) {
            return 1000;
        }

        int qtdeCarrosRua = rua.filaCarrosRua.tamanhoLista();

        System.out.println(qtdeCarrosRua + " carros na rua " + rua);
        return qtdeCarrosRua * 400;
    }

}
