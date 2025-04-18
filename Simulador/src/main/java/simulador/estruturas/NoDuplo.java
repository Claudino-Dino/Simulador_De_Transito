package simulador.estruturas;

public class NoDuplo<T> {
    public T conteudo;
    public NoDuplo<T> anterior, proximo;

    public NoDuplo(T conteudo) {
        this.conteudo = conteudo;
        this.anterior = null;
        this.proximo = null;
    }
}
