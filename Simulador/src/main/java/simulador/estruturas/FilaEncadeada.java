package simulador.estruturas;

public class FilaEncadeada<T> {
    public No<T> head, tail;

    public FilaEncadeada() {
        this.head = null;
        this.tail = null;
    }

    public No<T> enfileirar(No<T> novoNo) {
        if (estaVazia()) {
            this.tail = novoNo;
            this.head = novoNo;
            return head;
        }

        this.tail.proximo = novoNo;
        this.tail = novoNo;
        return head;
    }

    public No<T> desenfileirar() {
        try {
            if (estaVazia()) {
                throw new NullPointerException("Impossível desenfileirar um objeto em uma fila vazia!");
            }

            if (this.head.proximo == null) {
                this.head = null;
                this.tail = null;
                return head;
            }

            No<T> noDesenfileirar = this.head;
            this.head = this.head.proximo;
            noDesenfileirar.proximo = null;

            return head;
        } catch (NullPointerException e) {
            throw new NullPointerException("FilaEncadeada vazia!");
        }
    }

    public int tamanho() {
        int tamanho = 0;

        if (estaVazia()) {
            return tamanho;
        }

        No<T> atual = this.head;
        while (atual != null) {
            tamanho++;
            atual = atual.proximo;
        }

        return tamanho;
    }

    public boolean estaVazia() {
        if (this.head == null) {
            return true;
        }

        return false;
    }


}
