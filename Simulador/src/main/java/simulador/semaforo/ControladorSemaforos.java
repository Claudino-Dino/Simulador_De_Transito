package simulador.semaforo;

import simulador.cidade.Grafo;
import simulador.cidade.Intersecao;
import simulador.cidade.Rua;
import simulador.estruturas.ListaCircular;
import simulador.estruturas.ListaEncadeada;
import simulador.estruturas.NoDuplo;

import java.security.InvalidKeyException;

public class ControladorSemaforos {
    public ListaCircular<Enum> modosOreperacao;
    public boolean horarioPico;



    public void acionarCicloFixo(ListaEncadeada<Semaforo> listaSemaforos) throws InterruptedException {
        Semaforo s1 = null;
        Semaforo s2 = null;
        try {
            s2 = listaSemaforos.obter(1);
            s1 = listaSemaforos.obter(0);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }

        s2.obterEstado("VERMELHO");
        System.out.println("simulador.semaforo.Semaforo@4fca772d : " + s2.getConteudoAtual());

        s1.semaforoAbrir();
        s1.obterEstado("VERMELHO");
        Thread.sleep(200);
        s2.semaforoAbrir();
        Thread.sleep(200);

    }

    public void enviarMesmoSemaforoParaIntersecoes(ListaEncadeada<Intersecao> listaIntersecoes, Semaforo s1, Semaforo s2) throws InvalidKeyException {
        for (int i = 0; i < listaIntersecoes.tamanhoLista(); i++) {
            Intersecao inter = listaIntersecoes.obter(i);
            inter.adicionarSemaforo(s1);
            inter.adicionarSemaforo(s2);
        }
    }

    public boolean eHorarioPico() {
        return horarioPico;
    }

}