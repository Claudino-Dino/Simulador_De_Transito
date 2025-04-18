package simulador.trafego;

import simulador.cidade.Dijkstra;
import simulador.cidade.Grafo;
import simulador.estruturas.FilaEncadeada;
import simulador.cidade.Intersecao;

import java.security.InvalidKeyException;

public class Veiculo {
    private String id;
    private Intersecao origem;
    private Intersecao destino;
    private FilaEncadeada<Intersecao> caminho;


    public Veiculo(String id, Intersecao origem, Intersecao destino) {
        this.id = id;
        this.origem = origem;
        this.destino = destino;
    }

    public void mover() {
        if (!caminho.estaVazia()) {
            this.origem = caminho.desenfileirar().conteudo;
        }
    }

    public void calcularRota(Grafo grafo) {
        try {
            this.caminho = Dijkstra.encontrarMenorCaminho(grafo, this.origem, this.destino);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean atingiuDestino() {
        return this.origem.equals(this.destino);
    }

    public String getId() {
        return this.id;
    }

    public Intersecao getOrigem() {
        return this.origem;
    }

    public Intersecao getDestino() {
        return this.destino;
    }

    public FilaEncadeada<Intersecao> getCaminho() {
        return this.caminho;
    }

}
