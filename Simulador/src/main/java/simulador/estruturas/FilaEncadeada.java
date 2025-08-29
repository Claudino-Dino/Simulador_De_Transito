package simulador.estruturas;

import simulador.cidade.Intersecao;

public class FilaEncadeada<T> {
    public No<T> head, tail;

//    public FilaEncadeada() {
//        this.head = null;
//        this.tail = null;
//    }

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
        if (estaVazia()) {
            throw new IllegalStateException("Tentativa de desenfileirar fila vazia");
        }

        No<T> removido = head;
        head = head.proximo;

        if (head == null) {
            tail = null;
        }

        removido.proximo = null;
        return removido;
    }

    public T obter(int p) {
        if (!estaVazia() && p >= 0 && p < tamanho()) {
            No<T> noAtual = this.head;
            for (int i = 0; i < p; i++) {
                noAtual = noAtual.proximo;
            }
            return noAtual.conteudo;
        }

        throw new RuntimeException();
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

    public No<T> getHead() {
        return head;
    }

    public No<T> getTail() {
        return tail;
    }
}
