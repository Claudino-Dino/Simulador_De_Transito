package simulador.semaforo;

import simulador.estruturas.ListaCircular;
import simulador.estruturas.No;

import javax.print.DocFlavor;

public class Semaforo {
    public ListaCircular<String> estados;
    private int tempoVerde;
    private int tempoAmarelo;
    private int tempoVermelho;

    public Semaforo() {
        estados = new ListaCircular<>();

        estados.adicionar(new No("VERMELHO"));
        estados.adicionar(new No("VERDE"));
        estados.adicionar(new No("AMARELO"));

        while (!estados.atual.getConteudo().equals("VERMELHO")) {
            estados.proximo();
        }
    }


    public String ficarVermelho(){
        while (!estados.getAtual().equals("VERMELHO")){
            mudarEstado();
        }

        return estados.getAtual();
    }

    public String ficarVerde(){
        while (!estados.getAtual().equals("VERDE")){
            mudarEstado();
        }

        return estados.getAtual();
    }

    public String ficarAmarelo(){
        while (!estados.getAtual().equals("AMARELO")){
            mudarEstado();
        }

        return estados.getAtual();
    }

    public void semaforoAbrir() {
        try {
            ficarVermelho();
            Thread.sleep(this.tempoVermelho);

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

        } catch (InterruptedException e) {
            System.out.println("Execução do sinal interrompida!");
        }
    }

    public String getConteudoAtual() {
        return this.estados.atual.getConteudo();
    }

    public String mudarEstado() {
        estados.proximo();
        return estados.getAtual();
    }
}
