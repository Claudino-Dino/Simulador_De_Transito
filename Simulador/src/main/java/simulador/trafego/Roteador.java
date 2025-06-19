package simulador.trafego;

import simulador.cidade.*;
import simulador.estruturas.*;
import java.security.InvalidKeyException;
import java.util.Random;

public class Roteador {
    private final Random random = new Random();
    private final int MAX_TENTATIVAS = 20;

    public FilaEncadeada<Intersecao> gerarRotaValida(Grafo grafo) throws InvalidKeyException {
        validarGrafo(grafo);

        for (int tentativa = 0; tentativa < MAX_TENTATIVAS; tentativa++) {
            ListaEncadeada<Intersecao> par = selecionarParConectado(grafo);
            Intersecao origem = par.obter(0);
            Intersecao destino = par.obter(1);

            FilaEncadeada<Intersecao> rota = Dijkstra.encontrarMenorCaminho(
                    grafo, origem.getId(), destino.getId());

            if (rota != null && !rota.estaVazia()) {
                System.out.println("Rota válida encontrada:");
                System.out.println("Origem: " + origem.getId());
                System.out.println("Destino: " + destino.getId());
                System.out.println("Número de cruzamentos: " + rota.tamanho());
                return rota;
            }
        }

        throw new InvalidKeyException("Não foi possível encontrar uma rota válida após " +
                MAX_TENTATIVAS + " tentativas");
    }

    private ListaEncadeada<Intersecao> selecionarParConectado(Grafo grafo) throws InvalidKeyException {
        ListaEncadeada<Intersecao> intersecoes = grafo.getIntersecoes();
        int tamanho = intersecoes.tamanhoLista();

        // Primeiro tenta encontrar um par com conexão direta
        for (int i = 0; i < tamanho * 2; i++) {
            int origemIdx = random.nextInt(tamanho);
            Intersecao origem = intersecoes.obter(origemIdx);

            ListaEncadeada<Rua> ruasSaida = grafo.obterArestasDe(origem);
            if (!ruasSaida.estaVazia()) {
                int destinoIdx = random.nextInt(ruasSaida.tamanhoLista());
                Intersecao destino = ruasSaida.obter(destinoIdx).getIntercesaoDestino();
                return criarPar(origem, destino);
            }
        }

        return selecionarParAleatorio(grafo);
    }

    private ListaEncadeada<Intersecao> selecionarParAleatorio(Grafo grafo) throws InvalidKeyException {
        ListaEncadeada<Intersecao> intersecoes = grafo.getIntersecoes();
        int tamanho = intersecoes.tamanhoLista();
        int origemIdx = random.nextInt(tamanho);
        int destinoIdx;

        do {
            destinoIdx = random.nextInt(tamanho);
        } while (origemIdx == destinoIdx);

        return criarPar(intersecoes.obter(origemIdx), intersecoes.obter(destinoIdx));
    }

    private ListaEncadeada<Intersecao> criarPar(Intersecao origem, Intersecao destino) {
        ListaEncadeada<Intersecao> par = new ListaEncadeada<>();
        par.enfileirar(new NoDuplo<>(origem));
        par.enfileirar(new NoDuplo<>(destino));
        return par;
    }

    private void validarGrafo(Grafo grafo) {
        if (grafo == null) {
            throw new IllegalArgumentException("O grafo não pode ser nulo");
        }
        if (grafo.getIntersecoes() == null || grafo.getIntersecoes().tamanhoLista() < 2) {
            throw new IllegalArgumentException("O grafo deve conter pelo menos 2 interseções conectadas");
        }
    }
}