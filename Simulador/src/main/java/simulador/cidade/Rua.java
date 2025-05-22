package simulador.cidade;

import simulador.estruturas.ListaEncadeada;
import simulador.estruturas.NoDuplo;
import simulador.semaforo.Semaforo;
import simulador.trafego.Veiculo;

public class Rua {
    public Intersecao intercesaoOrigem;
    public Intersecao intercesaoDestino;
    public int comprimento;
    public double tempoDeTravessia;
    public boolean via;
    public double velocidadeMedia;
    public int capacidadeDeFluxo;
    public Direcao direcao;
    public ListaEncadeada<Veiculo> filaCarrosRua = new ListaEncadeada<>();
    private Semaforo semaforo;

    public Rua(
            Intersecao intercesaoOrigem, Intersecao intercesaoDestino,
            int comprimento, double tempoDeTravessia,
            boolean via, double velocidadeMedia
    ) {
        this.intercesaoOrigem = intercesaoOrigem;
        this.intercesaoDestino = intercesaoDestino;
        this.comprimento = comprimento;
        this.tempoDeTravessia = tempoDeTravessia;
        this.via = via;
        this.velocidadeMedia = velocidadeMedia;
        this.capacidadeDeFluxo = comprimento / 4; // 4 é o tamanho fixo do veículo (em metros).
//        this.filaCarrosRua = 20;
    }

    public boolean adicionarCarro(Veiculo carro) {
        if (filaCarrosRua.tamanhoLista() < capacidadeDeFluxo) {
            this.filaCarrosRua.enfileirar(new NoDuplo<>(carro));
            return true;
        }
        return false;
    }

    public Veiculo removerCarro() {
        NoDuplo<Veiculo> noVeiculo = this.filaCarrosRua.desenfileirar();
        return noVeiculo.conteudo;
    }

    public void calculoDirecao() {
        double deltaLat = intercesaoOrigem.latitude - intercesaoDestino.latitude;
        double deltaLong = intercesaoOrigem.longitude - intercesaoDestino.longitude;

        if (deltaLat > deltaLong) {
            this.setDirecao(Direcao.VERTICAL);
        } else {
            this.setDirecao(Direcao.HORIZONTAL);
        }
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

    public void receberSemaforo(Semaforo semaforo) {
        this.semaforo = semaforo;
    }

    public void setDirecao(Direcao direcao) {
        this.direcao = direcao;
    }
}