package simulador.cidade;

import simulador.estruturas.ListaEncadeada;
import simulador.estruturas.NoDuplo;

public class Grafo {
    ListaEncadeada<Intersecao> intersecoes;
    ListaEncadeada<Rua> ruas;

    ListaEncadeada<Rua> obterArestasDe(Intersecao i) {
        ListaEncadeada<Rua> rua = new ListaEncadeada<>();

        NoDuplo<Rua> noAtual = getRuas().head;
        while (noAtual != null) {
            if (noAtual.conteudo.noOrigem == i) {
                rua.adicionar(noAtual, 0);
            }
            noAtual = noAtual.proximo;
        }

        return rua;
    }


    public ListaEncadeada<Intersecao> getIntersecoes() {
        return intersecoes;
    }

    public ListaEncadeada<Rua> getRuas() {
        return ruas;

    }
}
