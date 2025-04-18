package simulador.cidade;

import simulador.estruturas.FilaEncadeada;
import simulador.estruturas.ListaEncadeada;
import simulador.estruturas.PilhaEncadeada;

import java.security.InvalidKeyException;
import java.util.HashMap;
import java.util.HashSet;

public class Dijkstra {

    public static FilaEncadeada<Intersecao> encontrarMenorCaminho(Grafo grafo, Intersecao origem, Intersecao destino) throws InvalidKeyException {
        HashMap<Intersecao, Integer> distancias = new HashMap<>();
        HashMap<Intersecao, Intersecao> anteriores = new HashMap<>();
        HashSet<Intersecao> visitados = new HashSet<>();

        ListaEncadeada<Intersecao> todosVertices = grafo.getIntersecoes();
        for (int i = 0; i < todosVertices.tamanhoLista(); i++) {
            Intersecao v = todosVertices.obter(i);
            distancias.put(v, Integer.MAX_VALUE);
            anteriores.put(v, null);
        }
        distancias.put(origem, 0);

        while (visitados.size() < todosVertices.tamanhoLista()) {
            Intersecao atual = encontrarMenorVertice(distancias, visitados);
            if (atual == null) break;
            visitados.add(atual);

            ListaEncadeada<Rua> adjacentes = grafo.obterArestasDe(atual);
            for (int i = 0; i < adjacentes.tamanhoLista(); i++) {
                Rua aresta = adjacentes.obter(i);
                Intersecao vizinho = aresta.noDestino;
                if (!visitados.contains(vizinho)) {
                    int novaDist = distancias.get(atual) + aresta.comprimento;
                    if (novaDist < distancias.get(vizinho)) {
                        distancias.put(vizinho, novaDist);
                        anteriores.put(vizinho, atual);
                    }
                }
            }
        }

        return construirCaminho(anteriores, origem, destino);
    }

    private static Intersecao encontrarMenorVertice(HashMap<Intersecao, Integer> distancias, HashSet<Intersecao> visitados) {
        Intersecao menor = null;
        int menorDistancia = Integer.MAX_VALUE;
        for (Intersecao v : distancias.keySet()) {
            if (!visitados.contains(v) && distancias.get(v) < menorDistancia) {
                menor = v;
                menorDistancia = distancias.get(v);
            }
        }
        return menor;
    }

    private static FilaEncadeada<Intersecao> construirCaminho(HashMap<Intersecao, Intersecao> anteriores, Intersecao origem, Intersecao destino) {
        PilhaEncadeada<Intersecao> pilha = new PilhaEncadeada<>();
        Intersecao atual = destino;

        while (atual != null) {
            pilha.empilhar(atual);
            atual = anteriores.get(atual);
        }

        FilaEncadeada<Intersecao> caminho = new FilaEncadeada<>();
        while (!pilha.estaVazia()) {
            caminho.enfileirar(pilha.desempilhar());
        }

        return caminho;
    }
}
