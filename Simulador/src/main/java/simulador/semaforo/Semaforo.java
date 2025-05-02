package simulador.semaforo;

import simulador.estruturas.ListaCircular;
import simulador.estruturas.No;

public class Semaforo {
    public ListaCircular<String> estados;
    private int tempoVerde;
    private int tempoAmarelo;
    private int tempoVermelho;
    private boolean estaAberto;

    public Semaforo(int tmpVerde, int tmpAmarelo) {
        estados = new ListaCircular<>();

        estados.adicionar(new No("VERMELHO"));
        estados.adicionar(new No("VERDE"));
        estados.adicionar(new No("AMARELO"));

        tempoVerde = tmpVerde;
        tempoAmarelo = tmpAmarelo;
        tempoVermelho = tmpVerde + tmpAmarelo;

    }


    public String ficarVermelho() {
        while (!estados.getAtual().equals("VERMELHO")) {
            mudarEstado();
        }

        return estados.getAtual();
    }

    public String ficarVerde() {
        while (!estados.getAtual().equals("VERDE")) {
            mudarEstado();
        }

        return estados.getAtual();
    }

    public String ficarAmarelo() {
        while (!estados.getAtual().equals("AMARELO")) {
            mudarEstado();
        }

        return estados.getAtual();
    }

    public void semaforoAbrir() {
        try {
            ficarVerde();
            Thread.sleep(this.tempoVerde);

            ficarAmarelo();
            Thread.sleep(this.tempoAmarelo);

        } catch (InterruptedException e) {
            System.out.println("Execução do sinal interrompida!");
        }
    }

    public void semaforoFechar() {
        try {
            ficarVermelho();
            Thread.sleep(tempoVermelho);

        } catch (InterruptedException e) {
            System.out.println("Execução do sinal interrompida!");
        }
    }

    public String mudarEstado() {
        estados.proximo();
        return estados.getAtual();
    }

    public boolean isAberto(){
        return this.getEstadoAtual() == "VERMELHO" ? true : false;
    }

    public String getEstadoAtual() {
        return this.estados.atual.getConteudo();
    }

    public void setTempoVerde(int tempo) {
        this.tempoVerde=tempo;
    }

    public void setTempoVermelho(int tempo) {
        this.tempoVermelho=tempo;
    }

    public void setTempoAmarelo(int tempo) {
        this.tempoAmarelo=tempo;
    }

}
