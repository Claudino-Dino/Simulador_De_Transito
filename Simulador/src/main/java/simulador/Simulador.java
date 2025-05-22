package simulador;

import simulador.cidade.Direcao;
import simulador.cidade.Grafo;
import simulador.semaforo.ControladorSemaforos;
import simulador.semaforo.Semaforo;
import simulador.trafego.Veiculo;

import java.io.*;
import java.security.InvalidKeyException;
import java.util.Timer;
import java.util.TimerTask;
import static simulador.semaforo.ModoOperacao.*;

public class Simulador implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient Timer timer;
    private int tempoSimulado = 0;
    private boolean pausado = false;

    public void iniciar() throws IOException, InvalidKeyException {
//        GRAFO -> MAPA
        Grafo grafo = new Grafo();

//        SEMÁFOROS
        ControladorSemaforos controladorSemaforos = new ControladorSemaforos();
        Semaforo s1 = new Semaforo();
        Semaforo s2 = new Semaforo();
        s1.setDirecao(Direcao.HORIZONTAL);
        s2.setDirecao(Direcao.VERTICAL);
        controladorSemaforos.enviarMesmoSemaforoParaIntersecoes(grafo.getIntersecoes(), s1, s2);

//        CARROS
        Veiculo veiculo = new Veiculo("A", grafo);
//        veiculo.calcularRota(grafo);

        System.out.println("Simulação iniciada");
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (!pausado) {
                    try {
                    tempoSimulado++;
                    atualizarSimulacao();
                    //moverCarros;
                    controladorSemaforos.acionarCicloFixo(grafo.getIntersecoes().head.conteudo.getListaSemaforos());

//                        gravar("C:\\Users\\Romerson Filho\\Documents\\FACULDADE\\3° PERIODO\\ESTRUTURA DE DADOS\\instancias\\instancias.ser");
//                        System.out.println("Serialização funcionou!");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 0, 1000);
    }

    public void pausar() {
        this.pausado = true;
    }

    public void continuarSimulacao() {
        this.pausado = false;
    }

    public void encerrar() {
        if (timer != null) timer.cancel();
    }

    public void gravar(String caminho) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminho))) {
            oos.writeObject(this);
        }
    }

    public static Simulador carregar(String caminho) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminho))) {
            Simulador simulador = (Simulador) ois.readObject();
            simulador.timer = new Timer();
            return simulador;
        }
    }

    private void atualizarSimulacao() {
        System.out.println("segundo simulado: " + tempoSimulado);
    }

}
