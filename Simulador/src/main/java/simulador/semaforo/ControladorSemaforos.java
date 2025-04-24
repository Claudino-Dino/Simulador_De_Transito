package simulador.semaforo;

import simulador.cidade.Intersecao;
import simulador.cidade.Rua;
import simulador.estruturas.ListaCircular;
import simulador.estruturas.No;

import static simulador.semaforo.ModoOperacao.*;

public class ControladorSemaforos {
    public ListaCircular<Enum> modosOreperacao;
    public Enum modoOperacaoAtual;
    public Semaforo s1, s2;
    public Rua r1, r2;
    public boolean horarioPico;

    public ControladorSemaforos(Enum modo, Intersecao i1, Intersecao i2, Intersecao i3, Intersecao i4, String direcao1, String direcao2, int comprimento1, int comprimento2, double velocidadeMedia1, double velocidadeMedia2, int capacidadeDeFluxo1, int capacidadeDeFluxo2) {
        modosOreperacao.adicionar(new No<>(CICLO_FIXO));
        modosOreperacao.adicionar(new No<>(TEMPO_ESPERA));
        modosOreperacao.adicionar(new No<>(CONSUMO));

        this.modoOperacaoAtual = modo;

        r1 = new Rua(i1, i2, direcao1, comprimento1, velocidadeMedia1, capacidadeDeFluxo1);
        r2 = new Rua(i3, i4, direcao2, comprimento2, velocidadeMedia2, capacidadeDeFluxo2);
        s1 = new Semaforo(2000, 800, 2500);
        s2 = new Semaforo(2000, 800, 2500);
    }


    public void aplicarControle() throws InterruptedException {
        switch (modoOperacaoAtual) {
            case CICLO_FIXO:
                aplicarCicloFixo();
            case TEMPO_ESPERA:
                aplicarTempoEspera();
            case CONSUMO:
                aplicarConsumo();
            default:
                throw new IllegalStateException("Unexpected value: " + modoOperacaoAtual);
        }
    }

    public void aplicarCicloFixo() throws InterruptedException {
        while (true) {
            s1.semaforoAbrir();
            s2.semaforoFechar();
            Thread.sleep(300); // TEMPO DE SEGURANÇA
            s1.semaforoFechar();
            s2.semaforoAbrir();
        }
    }

    public void aplicarTempoEspera() throws InterruptedException {
        while (true) {
            int tempoVerdeS1 = calcularTempoEspera();
            int tempoVerdeS2 = calcularTempoEspera();

            s1.setTempoVerde(tempoVerdeS1);
            s2.setTempoVermelho(tempoVerdeS1);

            s1.setTempoVermelho(tempoVerdeS2);
            s2.setTempoVerde(tempoVerdeS2);

            s1.semaforoAbrir();
            s2.semaforoFechar();
            Thread.sleep(300); // TEMPO DE SEGURANÇA
            s1.semaforoFechar();
            s2.semaforoAbrir();
        }
    }

    public void aplicarConsumo() throws InterruptedException {
        if (horarioPico){
            aplicarTempoEspera();
        } else {
            aplicarCicloFixo();
        }
    }

    public int calcularTempoEspera() {
        try {
            if (modoOperacaoAtual == CICLO_FIXO || modoOperacaoAtual == CONSUMO) {
                return 0;
            }

            int tamanhoFila1 = r1.qtdeCarrosAresta.tamanho();
            int tamanhoFila2 = r2.qtdeCarrosAresta.tamanho();
            int qtdeCarros = tamanhoFila1 - tamanhoFila2;

            if (tamanhoFila1 > tamanhoFila2) {
                return (qtdeCarros * 300);

            } else {
                qtdeCarros *= -1;
                return (qtdeCarros * 300);

            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void setModoAtual() {
        this.modosOreperacao.proximo();
        this.modoOperacaoAtual = this.modosOreperacao.getAtual();
    }


}