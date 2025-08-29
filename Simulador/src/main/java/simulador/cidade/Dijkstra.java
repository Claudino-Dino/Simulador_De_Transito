package simulador.cidade;

import simulador.estruturas.FilaEncadeada;
import simulador.estruturas.ListaEncadeada;
import simulador.estruturas.No;
import simulador.estruturas.PilhaEncadeada;
import java.security.InvalidKeyException;
import java.util.HashMap;
import java.util.HashSet;

public class Dijkstra {

    public static FilaEncadeada<Intersecao> encontrarMenorCaminho(Grafo grafo, String idOrigem, String idDestino)
            throws InvalidKeyException {

        Intersecao origem = grafo.obterIntersecaoPorId(idOrigem);
        Intersecao destino = grafo.obterIntersecaoPorId(idDestino);

        if (origem == null || destino == null) {
            throw new InvalidKeyException("Intersecão de origem ou destino não existe");
        }

        if (origem.equals(destino)) {
            FilaEncadeada<Intersecao> caminho = new FilaEncadeada<>();
            caminho.enfileirar(new No<>(origem));
            return caminho;
        }

        HashMap<Intersecao, Integer> distancias = new HashMap<>();
        HashMap<Intersecao, Intersecao> anteriores = new HashMap<>();
        HashSet<Intersecao> visitados = new HashSet<>();
        ListaEncadeada<Intersecao> todosVertices = grafo.getIntersecoes();

        for (int i = 0; i < todosVertices.tamanhoLista(); i++) {
            Intersecao vertice = todosVertices.obter(i);
            distancias.put(vertice, Integer.MAX_VALUE);
            anteriores.put(vertice, null);
        }
        distancias.put(origem, 0);


        while (visitados.size() < todosVertices.tamanhoLista()) {
            Intersecao atual = encontrarVerticeNaoVisitadoMaisProximo(distancias, visitados);

            if (atual == null || distancias.get(atual) == Integer.MAX_VALUE) {
                break;
            }

            visitados.add(atual);


            ListaEncadeada<Rua> arestasSaida = grafo.obterArestasDe(atual);
            for (int i = 0; i < arestasSaida.tamanhoLista(); i++) {
                Rua rua = arestasSaida.obter(i);
                Intersecao vizinho = rua.getIntercesaoDestino();

                if (!visitados.contains(vizinho)) {
                    int distanciaAlternativa = distancias.get(atual) + rua.getComprimento();

                    if (distanciaAlternativa < distancias.get(vizinho)) {
                        distancias.put(vizinho, distanciaAlternativa);
                        anteriores.put(vizinho, atual);
                    }
                }
            }
        }


        if (anteriores.get(destino) == null && !origem.equals(destino)) {
            System.err.println("AVISO: Destino " + destino.getId() +
                    " não alcançável a partir de " + origem.getId());
            return null;
        }

        return reconstruirCaminho(anteriores, origem, destino);
    }

    private static Intersecao encontrarVerticeNaoVisitadoMaisProximo(
            HashMap<Intersecao, Integer> distancias, HashSet<Intersecao> visitados) {

        Intersecao verticeMaisProximo = null;
        int menorDistancia = Integer.MAX_VALUE;

        for (Intersecao vertice : distancias.keySet()) {
            if (!visitados.contains(vertice) && distancias.get(vertice) < menorDistancia) {
                menorDistancia = distancias.get(vertice);
                verticeMaisProximo = vertice;
            }
        }

        return verticeMaisProximo;
    }

    private static FilaEncadeada<Intersecao> reconstruirCaminho(
            HashMap<Intersecao, Intersecao> anteriores, Intersecao origem, Intersecao destino) {

        PilhaEncadeada<Intersecao> pilha = new PilhaEncadeada<>();
        Intersecao atual = destino;


        while (atual != null && !atual.equals(origem)) {
            pilha.empilhar(atual);
            atual = anteriores.get(atual);
        }

        if (atual == null) {
            return null;
        }

        pilha.empilhar(origem);


        FilaEncadeada<Intersecao> caminho = new FilaEncadeada<>();
        while (!pilha.estaVazia()) {
            caminho.enfileirar(pilha.desempilhar());
        }

        return caminho;
    }

    public static boolean existeCaminho(Grafo grafo, String idOrigem, String idDestino) {
        try {
            FilaEncadeada<Intersecao> caminho = encontrarMenorCaminho(grafo, idOrigem, idDestino);
            return caminho != null && caminho.tamanho() >= 2;
        } catch (InvalidKeyException e) {
            return false;
        }
    }
}