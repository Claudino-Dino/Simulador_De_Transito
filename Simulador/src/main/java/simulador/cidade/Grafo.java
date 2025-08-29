package simulador.cidade;

import simulador.estruturas.ListaEncadeada;
import simulador.estruturas.NoDuplo;

import java.security.InvalidKeyException;

public class Grafo {
    public ListaEncadeada<Intersecao> intersecoes = new ListaEncadeada<>();
    public ListaEncadeada<Rua> ruas = new ListaEncadeada<>();

    public void carregarMapa(int qtdeIntersecoes) {
    }

    public void carregarIntersecoes() {
        try {
            Intersecao freiSerafimRua1 = new Intersecao("Frei Serafim x Rua 1", -5.0920, -42.8030);
            Intersecao freiSerafimRua2 = new Intersecao("Frei Serafim x Rua 2", -5.0920, -42.8020);
            Intersecao miguelRosaRua1 = new Intersecao("Miguel Rosa x Rua 1", -5.0930, -42.8030);
            Intersecao miguelRosaRua2 = new Intersecao("Miguel Rosa x Rua 2", -5.0930, -42.8020);

            this.intersecoes.enfileirar(new NoDuplo<>(freiSerafimRua1));
            this.intersecoes.enfileirar(new NoDuplo<>(freiSerafimRua2));
            this.intersecoes.enfileirar(new NoDuplo<>(miguelRosaRua1));
            this.intersecoes.enfileirar(new NoDuplo<>(miguelRosaRua2));

            if (intersecoes.tamanhoLista() != 4) {
                throw new RuntimeException("Failed to load all intersections");
            }
        } catch (Exception e) {
            System.err.println("Erro ao enfileirar interseções: " + e.getMessage());
            throw e;
        }

    }

    public void conectarRuas() throws InvalidKeyException {
        carregarIntersecoes();

        try {
            Intersecao inter1 = intersecoes.obter(0);
            Intersecao inter2 = intersecoes.obter(1);
            Intersecao inter3 = intersecoes.obter(2);
            Intersecao inter4 = intersecoes.obter(3);

            System.out.println("Inter1: " + inter1.getId());
            System.out.println("Inter2: " + inter2.getId());
            System.out.println("Inter3: " + inter3.getId());
            System.out.println("Inter4: " + inter4.getId());

            // Sentido original
            Rua ruaAvFreiSerafim = new Rua(inter1, inter2, 100, 60);
            Rua avMiguelRosa = new Rua(inter3, inter4, 100, 60);
            Rua ruaAlvaroMendes = new Rua(inter1, inter3, 110, 60);
            Rua ruaAoreolinoAbreu = new Rua(inter2, inter4, 110, 60);

            // Sentido inverso
            Rua ruaAvFreiSerafimInv = new Rua(inter2, inter1, 100, 60);
            Rua avMiguelRosaInv = new Rua(inter4, inter3, 100, 60);
            Rua ruaAlvaroMendesInv = new Rua(inter3, inter1, 110, 60);
            Rua ruaAoreolinoAbreuInv = new Rua(inter4, inter2, 110, 60);

            ruas.enfileirar(new NoDuplo<>(ruaAvFreiSerafim));
            ruas.enfileirar(new NoDuplo<>(ruaAvFreiSerafimInv));

            ruas.enfileirar(new NoDuplo<>(avMiguelRosa));
            ruas.enfileirar(new NoDuplo<>(avMiguelRosaInv));

            ruas.enfileirar(new NoDuplo<>(ruaAlvaroMendes));
            ruas.enfileirar(new NoDuplo<>(ruaAlvaroMendesInv));

            ruas.enfileirar(new NoDuplo<>(ruaAoreolinoAbreu));
            ruas.enfileirar(new NoDuplo<>(ruaAoreolinoAbreuInv));

        } catch (Exception e) {
            System.err.println("Erro ao conectar ruas: " + e.getMessage());
        }

    }

    public Rua obterArestaPorOrigemDestino(Intersecao origem, Intersecao destino) {
        if (origem == null || destino == null) {
            return null;
        }

        for (NoDuplo<Rua> no = ruas.head; no != null; no = no.proximo) {
            Rua rua = no.conteudo;
            if (rua.getIntercesaoOrigem().equals(origem) &&
                    rua.getIntercesaoDestino().equals(destino)) {
                return rua;
            }
        }
        return null;
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

    public ListaEncadeada<Intersecao> getIntersecoes() {
        return intersecoes;
    }

    public ListaEncadeada<Rua> getRuas() {
        return ruas;
    }
}
