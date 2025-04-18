package simulador.cidade;

import simulador.estruturas.FilaEncadeada;
import simulador.estruturas.No;
import simulador.trafego.Veiculo;

public class Rua {
    public Intersecao noOrigem;
    public Intersecao noDestino;
    public int comprimento;
    public double velocidadeMedia;
    public String direcao;
    public int capacidadeDeFluxo;
    public double tempoDeTravessia;
    public FilaEncadeada<Veiculo> qtdeCarrosAresta;

    public Rua(
            Intersecao noOrigem,
            Intersecao noDestino,
            String direcao,
            int comprimento,
            double velocidadeMedia,
            int capacidadeDeFluxo

    ) {
        this.noOrigem = noOrigem;
        this.noDestino = noDestino;
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

    public Intersecao getNoOrigem() {
        return this.noOrigem;
    }

    public Intersecao getNoDestino() {
        return this.noDestino;
    }

    public double getTempoDeTravessia() {
        return this.tempoDeTravessia;
    }

    public double calcularTempoDeTravessia() {
        return this.comprimento / this.velocidadeMedia;
    }

}
