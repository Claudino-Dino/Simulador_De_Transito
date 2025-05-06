package simulador.estruturas;

import java.security.InvalidKeyException;

public class ListaEncadeada<T> {
    public NoDuplo<T> head;
    public NoDuplo<T> tail;

    public ListaEncadeada() {
        this.head = null;
        this.tail = null;
    }

    public void adicionar(NoDuplo<T> novoNo, int posicao) throws NullPointerException {
        // Verifica se a posição é menor que zero, ou se o tamanho é maior que a lista
        int tamanhoLista = tamanhoLista();
        if (posicao < 0 || posicao > tamanhoLista) {
            throw new IllegalArgumentException("Posição inválida: " + posicao);
        }

        // Verifica se a lista está vazia e adiciona o novoNo
        if (estaVazia()) {
            this.head = novoNo;
            this.tail = novoNo;
            return;
        }

        // Adiciona o novoNo no inicio
        if (posicao == 0) {
            novoNo.proximo = this.head;
            this.head.anterior = novoNo;
            this.head = novoNo;
            return;
        }

        // Adiciona o novoNo no fim.proximo da lista
        if (posicao == tamanhoLista) {
            this.tail.proximo = novoNo;
            novoNo.anterior = this.tail;
            this.tail = novoNo;
            return;
        }

        NoDuplo<T> noAtual = this.head;
        int contador = 0;

        while ((contador < posicao - 1) && (noAtual.proximo != null)) {
            noAtual = noAtual.proximo;
            contador++;
        }

        // Adiciona o novoNó no meio da lista;
        novoNo.anterior = noAtual;
        novoNo.proximo = noAtual.proximo;
        noAtual.proximo.anterior = novoNo;
        noAtual.proximo = novoNo;
    }

    public T remover(int posicao) {
        // Verifica se a posição é menor que zero, ou se o tamanho é maior que a lista
                int tamanhoLista = tamanhoLista();
        if (posicao < 0 || posicao >= tamanhoLista || estaVazia()) {
            throw new IllegalArgumentException("Posição inválida: " + posicao);
        }

        // Verifica se a lista está vazia
        if (estaVazia()) {
            throw new NullPointerException("Lista vazia!");
        }

        NoDuplo<T> noRemovido = this.head;

        if (posicao == 0) {
            // Remove o nó do início da lista;
            this.head = this.head.proximo;
            noRemovido.proximo = null;
            return noRemovido.conteudo;
        }

        // Remove o nó do fim da lista;
        if (posicao == tamanhoLista() - 1) {
            noRemovido = this.tail;
            this.tail = noRemovido.anterior;
            return noRemovido.conteudo;
        }

        NoDuplo<T> noAtual = this.head;

        for (int i = 0; i < posicao; i++) {
            noAtual = noAtual.proximo;
        }

        // remove o nó do meio da lista;
        noRemovido = noAtual;
        noAtual.proximo.anterior = noAtual.anterior;
        noAtual.anterior.proximo = noAtual.proximo;
        noRemovido.proximo = null;
        noRemovido.anterior = null;
        return noRemovido.conteudo;
    }

    public NoDuplo<T> enfileirar(NoDuplo<T> novoNo) {
        if (estaVazia()) {
            this.tail = novoNo;
            this.head = novoNo;
            return head;
        }

        this.tail.proximo = novoNo;
        this.tail = novoNo;
        return head;
    }

    public NoDuplo<T> desenfileirar() {
        try {
            if (estaVazia()) {
                throw new NullPointerException("Impossível desenfileirar um objeto em uma fila vazia!");
            }

            if (this.head.proximo == null) {
                this.head = null;
                this.tail = null;
                return head;
            }

            NoDuplo<T> noDesenfileirar = this.head;
            this.head = this.head.proximo;
            noDesenfileirar.proximo = null;

            return head;
        } catch (NullPointerException e) {
            throw new NullPointerException("FilaEncadeada vazia!");
        }
    }

    public boolean estaVazia() {
        return this.head == null;
    }

    public int tamanhoLista() {

        int tamanho = 0;
        NoDuplo<T> noAtual = this.head;

        while (noAtual != null) {
            tamanho++;
            noAtual = noAtual.proximo;
        }

        return tamanho;
    }

    public T obter(int i) throws InvalidKeyException {
//        TRATAMENTO PARA ÍNDICE INVÁLIDO: EX: -1; TAMANHO DA LISTA;
        if (i < 0 || i > tamanhoLista() - 1) {
            throw new InvalidKeyException("Índice menor que zero, ou indice maior que o tamanho da lista.");
        }

        if (estaVazia()) {
            throw new NullPointerException("Impossível obter um nó de uma lista vazia.");
        }

        int contador = 0;

        NoDuplo<T> noAtual = this.head;
        while (noAtual != null) {
            if (contador == i) {
                return noAtual.conteudo;
            }

            noAtual = noAtual.proximo;
            contador++;
        }

        throw new InvalidKeyException("Erro inesperado ao acessar o índice.");
    }

}
