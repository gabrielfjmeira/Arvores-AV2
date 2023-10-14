public class Node {

    //Atributo da informação do nó
    private int Informacao;

    //Atributo do filho a esquerda do nó
    private Node FEsquerda;

    //Atributo do filho a direita do nó
    private Node FDireita;

    //Método Construtor
    public Node(int informacao) {
        this.Informacao = informacao;
        this.FEsquerda = null;
        this.FDireita = null;
    }

    //Função que retorna a informação do nó em questão
    public int getInformacao() {
        return this.Informacao;
    }

    //Função que define a informação do nó em questão
    public void setInformacao(Integer informacao) {
        this.Informacao = informacao;
    }

    //Função que retorna o filho a esquerda do nó em questão
    public Node getFEsquerda() {
        return this.FEsquerda;
    }

    //Função que define o filho a esquerda do nó em questão
    public void setFEsquerda(Node filhoEsquerda) {
        this.FEsquerda = filhoEsquerda;
    }

    //Função que retorna o filho a direita do nó em questão
    public Node getFDireita() {
        return this.FDireita;
    }

    //Função que define o filho a direita do nó em questão
    public void setFDireita(Node filhoDireita) {
        this.FDireita = filhoDireita;
    }
}

