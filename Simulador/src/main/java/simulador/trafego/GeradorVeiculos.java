package simulador.trafego;

import simulador.cidade.Grafo;
import simulador.cidade.Intersecao;
import simulador.estruturas.ListaEncadeada;
import simulador.estruturas.NoDuplo;

import java.util.Random;

public class GeradorVeiculos {
    ListaEncadeada<Veiculo> veiculosMapa;
    ListaEncadeada<Intersecao> intercesoes;

    public GeradorVeiculos(Grafo grafo) {
        this.intercesoes = grafo.getIntersecoes();
    }

    public void gerar(String id) throws RuntimeException {
        ListaEncadeada<Intersecao> extremidades = obterIntercesaoPeso(1);

        //        ESCOLHE UM VALOR ALEATÓRIO DOS VÉRTICES DE EXTREMIDADES PARA ORIGEM;

        Random rand = new Random();
        int indiceOrigem = rand.nextInt(extremidades.tamanhoLista());
        Intersecao origem = extremidades.remover(indiceOrigem);

        //        ESCOLHE UM VALOR ALEATÓRIO DOS VÉRTICES DE EXTREMIDADES PARA DESTINO;
        //        DESTINO DEVE SER DIFERENTE DE ORIGEM;

        Intersecao destino;
        do {
            int indiceDestino = rand.nextInt(extremidades.tamanhoLista());
            destino = extremidades.remover(indiceDestino);
        } while (destino == origem);

        //        CRIA UM NOVO VEÍCULO E ADICIONA NO MAPA;

        Veiculo veiculo = new Veiculo(id, origem, destino);
        NoDuplo<Veiculo> noVeiculo = new NoDuplo<>(veiculo);
        veiculosMapa.enfileirar(noVeiculo);

    }


    public ListaEncadeada<Intersecao> obterIntercesaoPeso(int valor) {
        NoDuplo<Intersecao> noAtual = intercesoes.head;
        ListaEncadeada<Intersecao> resposta = new ListaEncadeada<>();

        while (noAtual != null) {
            if (noAtual.conteudo.valor == valor) {
                resposta.enfileirar(noAtual);
            }

            noAtual = noAtual.proximo;
        }

        return resposta;
    }
}
