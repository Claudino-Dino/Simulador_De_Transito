package simulador;

import simulador.cidade.Grafo;
import simulador.cidade.Intersecao;
import simulador.cidade.Rua;
import simulador.estruturas.No;
import simulador.estruturas.NoDuplo;
import simulador.semaforo.Semaforo;
import simulador.trafego.Veiculo;

import java.io.IOException;
import java.security.InvalidKeyException;

import static simulador.semaforo.ModoOperacao.CICLO_FIXO;
import static simulador.semaforo.ModoOperacao.TEMPO_ESPERA;


public class Main {
    public static void main(String[] args) throws IOException, NullPointerException, InvalidKeyException, InterruptedException {
//        Simulador simulador = new Simulador();
//        simulador.iniciar();

//        FAZER INTERCESSEÇÃO COM DOIS SEMÁFOROS E COMUTAR

//        Intersecao i1 = new Intersecao( );

//        try {
//            i1.acionarSemaforos(TEMPO_ESPERA);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        Grafo grafo = new Grafo();

        Veiculo veiculo = new Veiculo("A", grafo);
        veiculo.mover();

//        Rua rua = grafo.obterArestaDeOrigemEDestino(Rua@1075, Rua@1077);

//        veiculo.printCaminho();
//        grafo.getIntersecoes().head.conteudo.acionarSemaforos(TEMPO_ESPERA);
//        for (int i=0; i<grafo.getIntersecoes().tamanhoLista(); i++){
//            NoDuplo<Intersecao> inter = grafo.getIntersecoes().desenfileirar();
//            System.out.println(inter.conteudo);
//        }
//
//        for (int i=0; i<grafo.getRuas().tamanhoLista(); i++){
//            NoDuplo<Rua> rua = grafo.getRuas().desenfileirar();
//            System.out.println(rua.conteudo);
//        }

//        try {
//            for (int i = 0; i < 70; i++) {
//                NoDuplo<Intersecao> inter = grafo.getIntersecoes().desenfileirar();
//                System.out.println(inter.conteudo.getListaRuas().tamanhoLista());
//            }
//        } catch (NullPointerException e) {
//
//        }
    }

}
