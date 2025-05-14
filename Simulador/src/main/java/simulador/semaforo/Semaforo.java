package simulador.semaforo;

import simulador.estruturas.ListaCircular;
import simulador.estruturas.ListaEncadeada;
import simulador.estruturas.No;

public class Semaforo {
    public ListaCircular<String> estados;
    private int tempoVerde;
    private int tempoAmarelo;
    private int tempoVermelho;
    public String id;
    public Double latitude;
    public Double longitude;
    public ListaEncadeada<String> atributos;


    public Semaforo() {
        estados = new ListaCircular<>();

        estados.adicionar(new No("VERMELHO"));
        estados.adicionar(new No("VERDE"));
        estados.adicionar(new No("AMARELO"));

        tempoVerde = 5000;
        tempoAmarelo = 1000;
        tempoVermelho = 6000;
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

    public int getTempoAmarelo() {
        return tempoAmarelo;
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
