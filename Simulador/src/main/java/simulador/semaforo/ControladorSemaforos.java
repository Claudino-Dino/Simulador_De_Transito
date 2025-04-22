package simulador.semaforo;

import simulador.estruturas.ListaCircular;

public class ControladorSemaforos {
    public ListaCircular<Enum> modosOreperacao;
    public Enum modoOperacaoAtual;

    public ControladorSemaforos() {
        modoOperacaoAtual = modosOreperacao.getAtual();

        while (!modoOperacaoAtual.equals("CICLO_FIXO")){
            setModoAtual();
        }
    }

    public void aplicarControle() {
        System.out.println("Controle de semáforos aplicado.");
    }

    public Enum setModoAtual() {
        this.modosOreperacao.proximo();
        this.modoOperacaoAtual = this.modosOreperacao.getAtual();
        return modoOperacaoAtual;
    }

}
