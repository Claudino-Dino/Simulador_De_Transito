package simulador.estruturas;

public class PilhaEncadeada<T> {
    public No<T> topo;

    public PilhaEncadeada() {
        this.topo = null;
    }

    public void empilhar(T conteudo) {
        No<T> novoNo = new No<>(conteudo);
        novoNo.proximo = this.topo;
        this.topo = novoNo;
    }

    public No desempilhar() {
        if (estaVazia()) {
            throw new NullPointerException("Impossível desempilhar um elemento em uma pilha vazia");
        }

        No elementoDesempilhado = this.topo;
        this.topo = this.topo.proximo;
        return elementoDesempilhado;

    }


    public boolean estaVazia() {
        if (this.topo == null) {
            return true;
        }
        return false;
    }
}
