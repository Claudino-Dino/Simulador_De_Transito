package simulador.trafego;

import simulador.cidade.Dijkstra;
import simulador.cidade.Grafo;
import simulador.cidade.Rua;
import simulador.estruturas.FilaEncadeada;
import simulador.cidade.Intersecao;

import java.security.InvalidKeyException;

public class Veiculo {
    private String id;
    private Intersecao origem;
    private Intersecao atual;
    private Intersecao destino;
    private FilaEncadeada<Intersecao> caminho;
    private int consumo;
    private Grafo grafo;

    public Veiculo(String id, Grafo grafo) throws InvalidKeyException {
        this.id = id;
        this.consumo = 0;
        this.grafo = grafo;
        this.caminho = new FilaEncadeada<>();
        calcularRota(grafo);
        this.origem = caminho.getHead().conteudo;
        this.destino = caminho.getTail().conteudo;
    }

    public void mover() throws InvalidKeyException, InterruptedException {
        int i = 0;
        while (this.atual != this.destino) {
            Intersecao anterior = this.atual;
            this.atual = caminho.obter(i);
            Rua ruaAtual = null;
            ruaAtual = this.grafo.obterArestaDeOrigemEDestino(anterior, this.atual);
            System.out.println(ruaAtual);


            ruaAtual.adicionarCarro(this);



            System.out.println("Veiculo movendo para: " + this.atual);
            consumo++;
            Thread.sleep(3000);
            i++;
        }

    }

    public void calcularRota(Grafo grafo) throws InvalidKeyException {
        this.caminho.enfileirar(Dijkstra.encontrarMenorCaminho(grafo, grafo.getIntersecoes().head.conteudo.id, grafo.getIntersecoes().tail.conteudo.id).desenfileirar());
    }

    public Intersecao getAtual() {
        return atual;
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
