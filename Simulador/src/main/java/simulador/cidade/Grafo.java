package simulador.cidade;

import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import simulador.estruturas.ListaEncadeada;
import simulador.estruturas.NoDuplo;

public class Grafo {
    ListaEncadeada<Intersecao> intersecoes;
    ListaEncadeada<Rua> ruas;

    ListaEncadeada<Rua> obterArestasDe(Intersecao i) {
        ListaEncadeada<Rua> rua = new ListaEncadeada<>();

        NoDuplo<Rua> noAtual = getRuas().head;
        while (noAtual != null) {
            if (noAtual.conteudo.intercesaoOrigem == i) {
                rua.enfileirar(noAtual);
            }
            noAtual = noAtual.proximo;
        }

        return rua;
    }

    public Grafo () {
        ListaEncadeada<Object> listaKeyJson = null;

        try {
            String caminho = "C:/Users/Romerson Filho/Downloads/FreiSerafimTeresinaPiauíBrazil.json";

            String stringJson = new String(Files.readAllBytes(Paths.get(caminho)));

            JSONObject json = new JSONObject(stringJson);

            for (Object key : json.keySet()) {
                System.out.println(key);

//                assert false;
                listaKeyJson.enfileirar(new NoDuplo<>(key));

//                for (int i=0; i<listaKeyJson.tamanhoLista(); i++){
//                    System.out.println(listaKeyJson.desenfileirar());
//                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public ListaEncadeada<Intersecao> getIntersecoes() {
        return intersecoes;
    }

    public ListaEncadeada<Rua> getRuas() {
        return ruas;
    }

    public void conectarNos(Intersecao origem, Intersecao destino, String direcao, int comprimento, double velocidadeMedia, int capacidadeDeFluxo) {
//        NoDuplo<Rua> noRuaLigarNos = new NoDuplo<>(new Rua(
//                origem,
//                destino,
//                direcao,
//                comprimento,
//                velocidadeMedia,
//                capacidadeDeFluxo
//        ));

    }
}
