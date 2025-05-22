package simulador.cidade;

import org.json.JSONObject;
import org.json.JSONArray;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;

import simulador.estruturas.ListaEncadeada;
import simulador.estruturas.NoDuplo;

public class Grafo {
    ListaEncadeada<Intersecao> intersecoes = new ListaEncadeada<>();
    ListaEncadeada<Rua> ruas = new ListaEncadeada<>();

    public Grafo() throws IOException, InvalidKeyException {
        carregarVertices();
        carregarArestas();
        adicionarRuasVertices();
    }

    private void carregarVertices() throws IOException, InvalidKeyException {
        String caminho = "C:/Users/Romerson Filho/Downloads/FreiSerafimTeresinaPiauíBrazil.json";
        String stringJson = new String(Files.readAllBytes(Paths.get(caminho)));
        JSONObject json = new JSONObject(stringJson);

        JSONArray trafficLightsJson = json.optJSONArray("traffic_lights");
        ListaEncadeada<String> idsSemaforos = new ListaEncadeada<>();
        if (trafficLightsJson != null) {
            for (int i = 0; i < trafficLightsJson.length(); i++) {
                JSONObject obj = trafficLightsJson.getJSONObject(i);
                idsSemaforos.enfileirar(new NoDuplo<>(obj.getString("id")));
            }
        }

        JSONArray nodes = json.getJSONArray("nodes");
        for (int i = 0; i < nodes.length(); i++) {
            JSONObject node = nodes.getJSONObject(i);
            String id = node.getString("id");

            boolean ignorar = false;
            for (NoDuplo<String> no = idsSemaforos.head; no != null; no = no.proximo) {
                if (no.conteudo.equals(id)) {
                    ignorar = true;
                    break;
                }
            }
            if (ignorar) continue;

            double latitude = node.getDouble("latitude");
            double longitude = node.getDouble("longitude");
            Intersecao intersecao = new Intersecao(id, latitude, longitude);
            this.intersecoes.enfileirar(new NoDuplo<>(intersecao));
        }
    }

    private void carregarArestas() throws IOException {
        String caminho = "C:/Users/Romerson Filho/Downloads/FreiSerafimTeresinaPiauíBrazil.json";
        String stringJson = new String(Files.readAllBytes(Paths.get(caminho)));
        JSONObject json = new JSONObject(stringJson);

        JSONArray edges = json.getJSONArray("edges");
        for (int i = 0; i < edges.length(); i++) {
            JSONObject edge = edges.getJSONObject(i);
            String idOrigem = edge.getString("source");
            String idDestino = edge.getString("target");

            Intersecao origem = obterIntersecaoPorId(idOrigem);
            Intersecao destino = obterIntersecaoPorId(idDestino);

            if (origem != null && destino != null) {
                int comprimento = (int) edge.getDouble("length");
                double velocidade = edge.getDouble("maxspeed");
                double tempoTravessia = comprimento / velocidade;
                boolean via = edge.getBoolean("oneway");

                Rua rua = new Rua(origem, destino, comprimento, tempoTravessia, via, velocidade);
                ruas.enfileirar(new NoDuplo<>(rua));
            }
        }
    }


    public void adicionarRuasVertices() throws NullPointerException {
        try {
            for (int i = 0; i < this.intersecoes.tamanhoLista(); i++) {
                Intersecao intersecao = this.intersecoes.obter(i);
                ListaEncadeada<Rua> ruasTemp = this.obterRuaPorIntersecao(intersecao);

                int adicionadas = 0;

                for (int j = 0; j < ruasTemp.tamanhoLista(); j++) {
                        intersecao.listaRuas.enfileirar(ruasTemp.desenfileirar());
                        adicionadas++;
                }
            }
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    private ListaEncadeada<Rua> obterRuaPorIntersecao(Intersecao i) {
        ListaEncadeada<Rua> ruasTemp = new ListaEncadeada<>();
        for (NoDuplo<Rua> no = this.ruas.head; no != null; no = no.proximo) {
            if (no.conteudo.intercesaoOrigem.equals(i)) {
                ruasTemp.enfileirar(new NoDuplo<>(no.conteudo));
            } else if (no.conteudo.intercesaoDestino.equals(i)) {
                ruasTemp.enfileirar(new NoDuplo<>(no.conteudo));
            }
        }
        return ruasTemp;
    }

    public Intersecao obterIntersecaoPorId(String id) {
        for (NoDuplo<Intersecao> no = intersecoes.head; no != null; no = no.proximo) {
            if (no.conteudo.id.equals(id)) {
                return no.conteudo;
            }
        }
        return null;
    }


    public ListaEncadeada<Rua> obterArestasDe(Intersecao i) {
        ListaEncadeada<Rua> rua = new ListaEncadeada<>();

        NoDuplo<Rua> noAtual = getRuas().head;
        while (noAtual != null) {
            if (noAtual.conteudo.intercesaoOrigem.equals(i)) {
                rua.enfileirar(noAtual);
            }
            noAtual = noAtual.proximo;
        }

        return rua;
    }

    public Rua obterArestaDeOrigemEDestino(Intersecao origem, Intersecao destino) throws InvalidKeyException {
        for (int i = 0; i < this.ruas.tamanhoLista(); i++) {
            Rua ruaResposta = ruas.obter(i);
            if ((origem.equals(ruaResposta.intercesaoOrigem)) && (destino.equals(ruaResposta.intercesaoDestino))) {
                return ruaResposta;
            }
        }
        return null;
    }

    public ListaEncadeada<Intersecao> getIntersecoes() {
        return this.intersecoes;
    }

    public ListaEncadeada<Rua> getRuas() {
        return ruas;
    }

}
