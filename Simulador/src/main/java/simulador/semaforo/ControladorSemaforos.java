package simulador.semaforo;

import simulador.estruturas.ListaCircular;
import simulador.estruturas.No;

import static simulador.semaforo.ModoOperacao.*;

public class ControladorSemaforos {
    public ListaCircular<Enum> modosOreperacao;
    public Enum modoOperacaoAtual;
    public Semaforo s1, s2;

    public ControladorSemaforos(Enum modo) {
        modosOreperacao.adicionar(new No<>(CICLO_FIXO));
        modosOreperacao.adicionar(new No<>(TEMPO_ESPERA));
        modosOreperacao.adicionar(new No<>(CONSUMO));

        modoOperacaoAtual = modosOreperacao.getAtual();
        while (modoOperacaoAtual != modo) {
            setModoAtual();
        }

        switch (modoOperacaoAtual) {
            case CICLO_FIXO:
                s1 = new Semaforo(2000, 800, 2500);
                s2 = new Semaforo(2000, 800, 2500);
                break;
            case TEMPO_ESPERA:
                s1 = new Semaforo(2000, 800, 2500); // Tempos serão alterados futuramente
                s2 = new Semaforo(2000, 800, 2500); // Tempos serão alterados futuramente
                break;
            case CONSUMO:
                s1 = new Semaforo(2000, 800, 2500); // Tempos serão alterados futuramente
                s2 = new Semaforo(2000, 800, 2500); // Tempos serão alterados futuramente
                break;
            default:
                throw new RuntimeException();
        }
    }

    public void aplicarControle() throws InterruptedException {
        while (true) {
            s1.semaforoAbrir();
            s2.semaforoFechar();
            Thread.sleep(300); // TEMPO DE SEGURANÇA
            s1.semaforoFechar();
            s2.semaforoAbrir();
        }
    }

    public Enum setModoAtual() {
        this.modosOreperacao.proximo();
        this.modoOperacaoAtual = this.modosOreperacao.getAtual();
        return modoOperacaoAtual;
    }

}
