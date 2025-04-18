package simulador.semaforo;

import simulador.estruturas.ListaCircular;
import simulador.estruturas.No;

public class Semaforo {
    public ListaCircular<String> estados;

    public Semaforo() {
        estados = new ListaCircular<>();

        estados.adicionar(new No("VERMELHO"));
        estados.adicionar(new No("VERDE"));
        estados.adicionar(new No("AMARELO"));

        while (!estados.atual.getConteudo().equals("VERMELHO")) {
            estados.proximo();
        }
    }

    public void semaforoAbrir() {
        try {
            System.out.println(this.getConteudoAtual());
            Thread.sleep(3000);
            this.mudarEstado();
            System.out.println(this.getConteudoAtual());
            Thread.sleep(2800);
        } catch (InterruptedException e) {
            System.out.println("Execução do sinal interrompida!");
        }
    }

    public void semaforoFechar() {
        try {
            this.mudarEstado();
            System.out.println(this.getConteudoAtual());
            Thread.sleep(600);
            this.mudarEstado();
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
