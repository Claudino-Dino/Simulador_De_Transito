package simulador;

import simulador.cidade.Grafo;
import simulador.cidade.Intersecao;
import simulador.cidade.Rua;
import simulador.estruturas.NoDuplo;

import java.io.IOException;
import java.security.InvalidKeyException;

import static simulador.semaforo.ModoOperacao.CICLO_FIXO;
import static simulador.semaforo.ModoOperacao.TEMPO_ESPERA;


public class Main {
    public static void main(String[] args) throws IOException {
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

//        for (int i=0; i<grafo.getIntersecoes().tamanhoLista(); i++){
//            NoDuplo<Intersecao> inter = grafo.getIntersecoes().desenfileirar();
//            System.out.println(inter.conteudo);
//        }
//
//        for (int i=0; i<grafo.getRuas().tamanhoLista(); i++){
//            NoDuplo<Rua> rua = grafo.getRuas().desenfileirar();
//            System.out.println(rua.conteudo);
//        }

        grafo.adicionarRuasVertices();

        for (int i = 0; i < grafo.getIntersecoes().tamanhoLista(); i++) {
            NoDuplo<Intersecao> inter = grafo.getIntersecoes().desenfileirar();
            System.out.println(inter.conteudo.listaRuas.head);
        }

    }

}
