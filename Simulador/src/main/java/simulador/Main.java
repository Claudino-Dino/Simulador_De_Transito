package simulador;

import simulador.cidade.Intersecao;
import simulador.cidade.Grafo;
import simulador.estruturas.FilaEncadeada;
import simulador.trafego.Roteador;
import simulador.trafego.Veiculo;

import java.io.IOException;
import java.security.InvalidKeyException;

public class Main {
    public static void main(String[] args) throws InvalidKeyException, IOException, InterruptedException {
        Grafo mapaMatriz = new Grafo();

        mapaMatriz.conectarRuas();

        Roteador roteador = new Roteador();

        FilaEncadeada<Intersecao> rota = roteador.gerarRotaValida(mapaMatriz);

        System.out.println(rota.tamanho());

        Veiculo carro = new Veiculo(0, rota);
        if (!carro.atingiuDestino()) {
            carro.mover();
        }

        System.out.println("Consumo do carro " + carro + ": " + carro.getConsumo());
        System.out.println("Tempo de viagem do carro " + carro + ": " + carro.getTempoViagem());
    }
}