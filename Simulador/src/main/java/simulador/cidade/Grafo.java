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
            if (noAtual.conteudo.intercesaoOrigem == i) {
                rua.enfileirar(noAtual);
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

    public void conectarNos(Intersecao origem, Intersecao destino, String direcao, int comprimento, double velocidadeMedia, int capacidadeDeFluxo) {
        NoDuplo<Rua> noRuaLigarNos = new NoDuplo<>(new Rua(
                origem,
                destino,
                direcao,
                comprimento,
                velocidadeMedia,
                capacidadeDeFluxo
        ));

        ruas.adicionar(noRuaLigarNos, 1);
    }
}
