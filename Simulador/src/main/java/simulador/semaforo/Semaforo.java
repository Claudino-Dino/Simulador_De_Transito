package simulador.semaforo;

import simulador.estruturas.ListaCircular;
import simulador.estruturas.No;

public class Semaforo {
    public ListaCircular<String> estados;
    private int tempoVerde;
    private int tempoAmarelo;
    private int tempoVermelho;

    public Semaforo(int tmpVerde, int tmpAmarelo) {
        estados = new ListaCircular<>();

        estados.adicionar(new No("VERMELHO"));
        estados.adicionar(new No("VERDE"));
        estados.adicionar(new No("AMARELO"));

        tempoVerde = tmpVerde;
        tempoAmarelo = tmpAmarelo;
        tempoVermelho = tmpAmarelo + tmpVerde;

    }


    public String ficarVermelho() {
        while (!estados.getConteudoAtual().equals("VERMELHO")) {
            proximoEstado();
        }
        System.out.println(this.toString() + " : " + getConteudoAtual());

        return estados.getConteudoAtual();
    }

    public String ficarVerde() {
        while (!estados.getConteudoAtual().equals("VERDE")) {
            proximoEstado();
        }
        System.out.println(this.toString() + " : " + getConteudoAtual());

        return estados.getConteudoAtual();
    }

    public String ficarAmarelo() {
        while (!estados.getConteudoAtual().equals("AMARELO")) {
            proximoEstado();
        }
        System.out.println(this.toString() + " : " + getConteudoAtual());

        return estados.getConteudoAtual();
    }

    public void semaforoAbrir() {
        try {
            ficarVerde();
            Thread.sleep(this.tempoVerde);

        } catch (InterruptedException e) {
            System.out.println("Execução do sinal interrompida!");
        }
    }

    public void semaforoFechar() {
        try {
            ficarAmarelo();
            Thread.sleep(this.tempoAmarelo);

            ficarVermelho();
            Thread.sleep(this.tempoVermelho);

        } catch (InterruptedException e) {
            System.out.println("Execução do sinal interrompida!");
        }
    }

    public String getConteudoAtual() {
        return this.estados.atual.getConteudo();
    }

    public String proximoEstado() {
        estados.proximo();
        return estados.getConteudoAtual();
    }

    public String obterEstado(String nome) {
        while (!estados.getConteudoAtual().equals(nome)) {
            proximoEstado();
        }

        return estados.getConteudoAtual();
    }

    public void setTempoVerde(int tempo) {
        this.tempoVerde = tempo;
    }

    public void setTempoVermelho(int tempo) {
        this.tempoVermelho = tempo;
    }

    public void setTempoAmarelo(int tempo) {
        this.tempoAmarelo = tempo;
    }

}
