package simulador.cidade;

public class Rua {
    public String id;
    public Intersecao intercesaoOrigem;
    public Intersecao intercesaoDestino;
    public int comprimento;
    public double velocidadeMaxima;
    public String direcao;
    public int capacidadeDeFluxo;
    public double tempoDeTravessia;
    public int filaCarrosRua;

    public Rua(
            String id,
            Intersecao intercesaoOrigem,
            Intersecao intercesaoDestino,
            String direcao,
            int comprimento,
            double tempoDeTravessia,
            int capacidadeDeFluxo,
            double velocidadeMaxima
    ) {
        this.intercesaoOrigem = intercesaoOrigem;
        this.intercesaoDestino = intercesaoDestino;
        this.direcao = direcao;
        this.comprimento = comprimento;
        this.tempoDeTravessia = tempoDeTravessia;
        this.velocidadeMaxima = velocidadeMaxima;
        this.capacidadeDeFluxo = capacidadeDeFluxo;
    }

    public boolean adicionarCarro(String carro) {
//            if (filaCarrosRua.tamanho() < capacidadeDeFluxo) {
//                this.filaCarrosRua.enfileirar(new No<>(carro));
//                return true;
//            }
//            return false;
        return true;
    }

    public void removerCarro() {
//        SE O CARRO CHEGAR NO VÉRTICE FINAL DO GRAFO, SIGNIFICA QUE ELE SAIU DO MAPA
//        ENTÃO FAZER OUTRO CARRO APARECER "geradorCarros.gerar();"
//            filaCarrosRua.desenfileirar();
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
        return this.comprimento / this.velocidadeMaxima;
    }

}
