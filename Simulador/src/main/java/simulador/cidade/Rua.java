package simulador.cidade;

import simulador.estruturas.ListaEncadeada;
import simulador.estruturas.NoDuplo;
import simulador.semaforo.Semaforo;
import simulador.trafego.Veiculo;

public class Rua {
    public Intersecao intercesaoOrigem;
    public Intersecao intercesaoDestino;
    public int comprimento; // Comprimento em Km
    public double tempoDeTravessia; // Tempo em segundos
    public double velocidadeMedia; // Velocidade em Km/h
    public int capacidadeDeFluxo;
    public Direcao direcao;
    public ListaEncadeada<Veiculo> filaCarrosRua = new ListaEncadeada<>();
    private Semaforo semaforo;
    private Double consumo;

    public Rua(
            Intersecao intercesaoOrigem, Intersecao intercesaoDestino,
            int comprimento, double velocidadeMedia
    ) {
        this.intercesaoOrigem = intercesaoOrigem;
        this.intercesaoDestino = intercesaoDestino;
        this.comprimento = comprimento;
        this.velocidadeMedia = velocidadeMedia;

        // tempo em horas
        double tempoHoras = comprimento / velocidadeMedia;

        // convertendo para segundos
        this.tempoDeTravessia = tempoHoras * 3600;

        this.capacidadeDeFluxo = (comprimento * 1000) / 4; // 4m por veículo

        // Calcula pela latitude e longitude se a direção da rua é Horizontal ou Vertical;
        calcularDirecaoRua();
    }

    public boolean adicionarCarro(Veiculo carro) {
        if (filaCarrosRua.tamanhoLista() < capacidadeDeFluxo) {
            this.filaCarrosRua.enfileirar(new NoDuplo<>(carro));
            this.consumo = (double) this.comprimento / (double) carro.getAutonomia();
            return true;
        }
        return false;
    }


    public void calcularDirecaoRua() {
        double deltaLat = Math.abs(intercesaoOrigem.getLatitude() - intercesaoDestino.getLatitude());
        double deltaLong = Math.abs(intercesaoOrigem.getLongitude() - intercesaoDestino.getLongitude());

        if (deltaLat > deltaLong) {
            this.setDirecao(Direcao.VERTICAL);
        } else {
            this.setDirecao(Direcao.HORIZONTAL);
        }
    }

    public void calcularConsumoRua(double qtdeConsumoCarro){
        this.consumo = this.comprimento/qtdeConsumoCarro;
    }

    public double getConsumo(){
        return this.consumo;
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

    public int getComprimento() {
        return comprimento;
    }
}