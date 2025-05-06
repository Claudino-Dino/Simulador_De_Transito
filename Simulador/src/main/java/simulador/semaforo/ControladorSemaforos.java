package simulador.semaforo;

import simulador.estruturas.ListaCircular;
import simulador.estruturas.No;

import static simulador.semaforo.ModoOperacao.*;

public class ControladorSemaforos {
    public ListaCircular<Enum> modosOreperacao;
    public Enum modoOperacaoAtual;
    public Semaforo s1, s2;
    public boolean horarioPico;

    public ControladorSemaforos(Enum modo) {
        modosOreperacao.adicionar(new No<>(CICLO_FIXO));
        modosOreperacao.adicionar(new No<>(TEMPO_ESPERA));
        modosOreperacao.adicionar(new No<>(CONSUMO));

        this.modoOperacaoAtual = modo;
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
        if (horarioPico) {
            aplicarTempoEspera();
        } else {
            aplicarCicloFixo();
        }
    }

    public int calcularTempoEspera() {
//        try {
//            int tamanhoFila1 = r1.qtdeCarrosAresta.tamanho();
//            int tamanhoFila2 = r2.qtdeCarrosAresta.tamanho();
//            int qtdeCarros = tamanhoFila1 - tamanhoFila2;
//
//            if (qtdeCarros < 0) {
//                return ((qtdeCarros * 300) * -1);
//            }
//
//            return qtdeCarros * 300;
//
//        } catch (Exception e) {
//            throw new RuntimeException();
//        }
        return 0;
    }

    public void setModoAtual() {
        this.modosOreperacao.proximo();
        this.modoOperacaoAtual = this.modosOreperacao.getConteudoAtual();
    }


}