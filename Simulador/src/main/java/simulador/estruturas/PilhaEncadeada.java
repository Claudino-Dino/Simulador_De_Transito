package simulador.estruturas;

public class PilhaEncadeada<T> {
    No<T> topo;

    public void empilhar(T conteudo) {
        No<T> novoNo = null;
        novoNo.conteudo = conteudo;
        topo = novoNo;
    }

    public No<T> desempilhar() throws NullPointerException {
        if (estaVazia()) {
            throw new NullPointerException("Impossível desempilhar um elemento em uma pilha vazia");
        }

        No<T> elementoDesempilhado = this.topo;
        this.topo = this.topo.proximo;
        return elementoDesempilhado;
    }


    public boolean estaVazia() {
        if (this.topo == null) {
            return false;
        }
        return true;
    }
}
