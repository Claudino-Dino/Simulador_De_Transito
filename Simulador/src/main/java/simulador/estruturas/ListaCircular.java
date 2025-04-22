package simulador.estruturas;

public class ListaCircular<T> {

    public No<T> head;
    public No<T> atual;

    public ListaCircular() {
        this.head = null;
        this.atual = null;
    }

    public void adicionar(No<T> novoNo) {
        if (estaVazia(this.head)) {
            this.head = novoNo;
            novoNo.proximo = this.head;
            atual = this.head;
        } else {
            No<T> ultimo = this.head;
            while (ultimo.proximo != this.head) {
                ultimo = ultimo.proximo;
            }
            ultimo.proximo = novoNo;
            novoNo.proximo = this.head;
        }
    }

    public boolean estaVazia(No<T> head) {
        if (head == null) {
            return true;
        }
        return false;
    }

    public T getAtual() {
        return (atual != null) ? this.atual.getConteudo() : null;
    }

    public void proximo() {
        if (atual != null) {
            atual = atual.proximo;
        }
    }

}
