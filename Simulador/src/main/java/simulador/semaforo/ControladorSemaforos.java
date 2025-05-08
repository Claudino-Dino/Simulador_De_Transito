package simulador.semaforo;

import simulador.estruturas.ListaCircular;

public class ControladorSemaforos {
    public ListaCircular<Enum> modosOreperacao;
    public boolean horarioPico;

    public boolean eHorarioPico() {
        return horarioPico;
    }

}