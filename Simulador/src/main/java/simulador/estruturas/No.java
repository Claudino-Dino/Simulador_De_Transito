package simulador.estruturas;

public class No<T> {
    public T conteudo;
    public No proximo;

    public No(T conteudo) {
        this.conteudo = conteudo;
        this.proximo = null;
    }

    public T getConteudo() {
        return this.conteudo;
    }
}
