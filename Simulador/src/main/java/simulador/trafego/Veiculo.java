package simulador.trafego;

import simulador.cidade.Grafo;
import simulador.cidade.Rua;
import simulador.estruturas.FilaEncadeada;
import simulador.cidade.Intersecao;
import java.security.InvalidKeyException;

public class Veiculo {
    private int id;
    private Intersecao atual;
    private Double consumo = 0.0;
    private Double tempoViagem = 0.0;
    private int autonomia = 40;
    private final FilaEncadeada<Intersecao> caminho;
    private int passoAtual = 0;
    public Grafo mapaMatriz = new Grafo();


    public Veiculo(int id, FilaEncadeada<Intersecao> caminhoPredefinido) throws InvalidKeyException {
        this.id = id;
        this.caminho = caminhoPredefinido;
        this.atual = caminho.obter(0);
    }

    public void mover() throws InvalidKeyException {
        if (!atingiuDestino()) {
            Intersecao proxima = caminho.obter(passoAtual + 1);
            Rua rua = mapaMatriz.obterArestaPorOrigemDestino(atual, proxima);

            if (rua == null) {
                throw new InvalidKeyException("Não existe rua de " + atual.getId() +
                        " para " + proxima.getId());
            }

            passoAtual++;
            atualizarConsumo(atual, proxima);
            atualizarTempoViagem(atual, proxima);
            atual = proxima;

            System.out.printf("Veículo %d moveu de %s para %s%n",
                    id, atual.getId(), proxima.getId());
        }
    }

    public void atualizarConsumo(Intersecao iAnterior, Intersecao iAtual) throws InvalidKeyException {
        Rua rua = mapaMatriz.obterArestaPorOrigemDestino(iAnterior, iAtual);
        if (rua == null) {
            System.err.println("Rua não encontrada entre " + iAnterior.getId() + " e " + iAtual.getId());
            return;
        }

        rua.calcularConsumoRua(autonomia);
        this.consumo += rua.getConsumo();
    }

    public void atualizarTempoViagem(Intersecao iAnterior, Intersecao iAtual) throws InvalidKeyException {
        Rua rua = mapaMatriz.obterArestaPorOrigemDestino(iAnterior, iAtual);
        this.tempoViagem += rua.getTempoDeTravessia();
    }

    public boolean atingiuDestino() {
        return passoAtual >= caminho.tamanho() - 1;
    }

    public FilaEncadeada<Intersecao> getCaminho() {
        return this.caminho;
    }

    public int getAutonomia() {
        return autonomia;
    }

    public Double getConsumo() {
        return consumo;
    }

    public Double getTempoViagem() {
        return tempoViagem;
    }
}
