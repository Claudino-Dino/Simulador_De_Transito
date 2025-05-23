package simulador.cidade;

import simulador.estruturas.FilaEncadeada;
import simulador.estruturas.No;
import simulador.trafego.Veiculo;

public class Rua {
    public Intersecao intercesaoOrigem;
    public Intersecao intercesaoDestino;
    public int comprimento;
    public double velocidadeMedia;
    public String direcao;
    public int capacidadeDeFluxo;
    public double tempoDeTravessia;
    public FilaEncadeada<Veiculo> qtdeCarrosAresta;

    public Rua(
            Intersecao intercesaoOrigem,
            Intersecao intercesaoDestino,
            String direcao,
            int comprimento,
            double velocidadeMedia,
            int capacidadeDeFluxo

    ) {
        this.intercesaoOrigem = intercesaoOrigem;
        this.intercesaoDestino = intercesaoDestino;
        this.direcao = direcao;
        this.comprimento = comprimento;
        this.velocidadeMedia = velocidadeMedia;
        this.capacidadeDeFluxo = capacidadeDeFluxo;
    }

    public boolean adicionarCarro(Veiculo carro) {
        if (qtdeCarrosAresta.tamanho() < capacidadeDeFluxo) {
            this.qtdeCarrosAresta.enfileirar(new No<Veiculo>(carro));
            return true;
        }
        return false;
    }

    public void removerCarro(){
//        SE O CARRO CHEGAR NO VÉRTICE FINAL DO GRAFO, SIGNIFICA QUE ELE SAIU DO MAPA
//        ENTÃO FAZER OUTRO CARRO APARECER "geradorCarros.gerar();"
        qtdeCarrosAresta.desenfileirar();
    }

    public Intersecao getIntercesaoOrigem() {
        return this.intercesaoOrigem;
    }

    public Intersecao getIntercesaoDestino() {
        return this.intercesaoDestino;
    }

    public double getTempoDeTravessia() {
        return this.tempoDeTravessia;
    }

    public double calcularTempoDeTravessia() {
        return this.comprimento / this.velocidadeMedia;
    }

}
