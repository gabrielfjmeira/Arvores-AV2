public class ArvoreBinaria {

    //Atributo da raíz
    private Node Raiz;

    //Método construtur
    public ArvoreBinaria(){
        this.Raiz = null;
    }

    //Método que retorna a raíz da árvore binária
    public Node getRaiz() {
        return this.Raiz;
    }

    //Método que define a raíz da arvore binária
    public void setRaiz(Node raiz) {
        this.Raiz = raiz;
    }

    //Método que busca um nó na árvore
    public boolean buscar(int n){
        Node atual = this.Raiz;
        while (atual != null && atual.getInformacao() != n){
            if (n >= atual.getInformacao()){
                atual = atual.getFDireita();
            }else {
                atual = atual.getFEsquerda();
            }
        }

        if (atual != null){
            if (atual.getInformacao() == n){
                return true;
            }
        }

        return false;
    }

    //Método para inserir um elemento/novo nó na árvore binária
    public void insertElementoArvore(int n, Node no){
        //Verifica se é a raiz
        if (no != null){
            //O nó aux é um nó de auxílio do código
            Node atual = no;
            //Verifica o lado para manipular
            //Esquerda do nó auxiliar (informação n é menor q a informação do nó auxiliar)
            if (n < atual.getInformacao()) {
                //Com filho a esquerda disponível
                if (atual.getFEsquerda() == null) {
                    //Define o filho da esquerda
                    Node fe = new Node(n);
                    atual.setFEsquerda(fe);
                //Sem filho a esquerda disponível
                }else{
                    //Atualiza o nó auxiliar para prosseguir a execução do loop
                    insertElementoArvore(n, atual.getFEsquerda());
                }
                //Direita do nó auxiliar (informação n é maior ou igual a informação do nó auxiliar)
            } else {
                //Com filho a direita disponível
                if (atual.getFDireita() == null) {
                    Node fd = new Node(n);
                    atual.setFDireita(fd);
                //Sem filho a direita disponível
                }else{
                    //Atualiza o nó auxiliar para prosseguir a execução do loop
                    insertElementoArvore(n, atual.getFDireita());
                }
            }
        }else{
            //Caso o nó passado ser nulo, o mesmo é a raiz
            Node raiz = new Node(n);
            this.Raiz = raiz;
        }
    }

    //Remoção de um elemento
    public void removeElemento(int n, Node no, Node noP){
        Node atual = no;
        Node noPai = noP;
        //Busca o elemento na árvore binária, passando o próximo nó a ser verificado e o pai dele
        //Elemento menor do que o nó atual(esquerda)
        if (n < atual.getInformacao()){
            removeElemento(n, atual.getFEsquerda(), atual);
        //Elemento maior do que o nó atual(direita)
        }else if (n > atual.getInformacao()){
            removeElemento(n, atual.getFDireita(), atual);
        //Achou o elemento
        }else{
            //Atual possuí filho a esquerda
            if (atual.getFEsquerda() != null){
                //Pega o nó mais a direita do filho da esquerda para substituição
                Node aux = atual.getFEsquerda();
                Node noPaiAux = atual;
                while (aux.getFDireita() != null){
                    noPaiAux = aux;
                    aux = aux.getFDireita();
                }

                //Remove o nó folha do nó paiAuxiliar
                if (aux.getInformacao() < noPaiAux.getInformacao()){
                    noPaiAux.setFEsquerda(null);
                    //Atualiza o filho a direita do nó pai
                }else{
                    noPaiAux.setFDireita(null);
                }

                //Atualiza o nó que irá mudar de posição com o filho a direita do nó q foi removido
                if (atual.getFDireita() != null){
                    aux.setFDireita(atual.getFDireita());
                }else{
                    aux.setFDireita(null);
                }

                //Atualiza o nó que irá mudar de posição com o filho a esquerda do nó q foi removido
                if (atual.getFEsquerda()!= null){
                    if (atual.getFEsquerda().getInformacao() != aux.getInformacao()){
                        aux.setFEsquerda(atual.getFEsquerda());
                    }else{
                        aux.setFEsquerda(null);
                    }
                }else{
                    aux.setFEsquerda(null);
                }

                //Verifica se o nó possuí pai (Verifica se o nó em questão é a raíz da arvore binária)
                if (noPai != null){
                    //Atualiza o filho a esquerda do nó pai da árvore binária
                    if (atual.getInformacao() < noPai.getInformacao()){
                        noPai.setFEsquerda(aux);
                        //Atualiza o filho a direita do nó pai
                    }else{
                        noPai.setFDireita(aux);
                    }
                }else{ //Único caso em que um nó não possuí pai é a raiz
                    this.Raiz = aux;
                }
            //Atual possuí filho a direita
            } else if (atual.getFDireita() != null){
                //Pega o nó mais a esquerda do filho da direita para substituição
                Node aux = atual.getFDireita();
                Node noPaiAux = atual;
                while (aux.getFEsquerda() != null){
                    noPaiAux = aux;
                    aux = aux.getFEsquerda();
                }

                //Remove o nó folha do nó paiAuxiliar
                if (aux.getInformacao() < noPaiAux.getInformacao()){
                    noPaiAux.setFEsquerda(null);
                    //Atualiza o filho a direita do nó pai
                }else{
                    noPaiAux.setFDireita(null);
                }

                //Atualiza o nó que irá mudar de posição com o filho a esquerda do nó q foi removido
                if (atual.getFEsquerda() != null){
                    aux.setFEsquerda(atual.getFEsquerda());
                }else{
                    aux.setFEsquerda(null);
                }

                //Atualiza o nó que irá mudar de posição com o filho a direita do nó q foi removido
                if (atual.getFDireita()!= null){
                    if (atual.getFDireita().getInformacao() != aux.getInformacao()){
                        aux.setFDireita(atual.getFDireita());
                    }else{
                        aux.setFDireita(null);
                    }
                }else{
                    aux.setFDireita(null);
                }

                //Verifica se o nó possuí pai (Verifica se o nó em questão é a raíz da arvore binária)
                if (noPai != null) {
                    //Atualiza o filho a esquerda do nó pai da árvore binária
                    if (atual.getInformacao() < noPai.getInformacao()) {
                        noPai.setFEsquerda(aux);
                        //Atualiza o filho a direita do nó pai
                    } else {
                        noPai.setFDireita(aux);
                    }
                }else{ //Único caso em que um nó não possuí pai é a raiz
                    this.Raiz = aux;
                }
            //Nó não tem filhos(nó folha)
            }else{
                //Verifica se o nó possuí pai (Verifica se o nó em questão é a raíz da arvore binária)
                if (noPai != null) {
                    //Atualiza o filho a esquerda do nó pai
                    if (atual.getInformacao() < noPai.getInformacao()) {
                        noPai.setFEsquerda(null);
                        //Atualiza o filho a direita do nó pai
                    } else {
                        noPai.setFDireita(null);
                    }
                }else{ //Único caso em que um nó não possuí pai é a raiz
                    this.Raiz = null;
                }
            }
        }
    }

    //Impressão em Pré-Ordem
    public void preOrdem(Node n){
        if (n != null){
            //Imprime a informação da raíz
            System.out.print(n.getInformacao() + " ");
            //Se tiver filho a esquerda chama o mesmo
            if (n.getFEsquerda() != null){
                preOrdem(n.getFEsquerda());
            }
            //Se tiver filho a direita chama o mesmo
            if (n.getFDireita() != null){
                preOrdem(n.getFDireita());
            }
        }
    }

    //Impressão em emOrdem
    public void emOrdem(Node n){
        if (n != null){
            //Se tiver filho a esquerda chama o mesmo
            if (n.getFEsquerda() != null){
                emOrdem(n.getFEsquerda());
            }
            //Imprime a informação da raíz
            System.out.print(n.getInformacao() + " ");
            //Se tiver filho a direita chama o mesmo
            if (n.getFDireita() != null){
                emOrdem(n.getFDireita());
            }
        }
    }

    //Impressão em Pós-Ordem
    public void posOrdem(Node n){
        if (n != null){
            //Se tiver filho a esquerda chama o mesmo
            if (n.getFEsquerda() != null){
                posOrdem(n.getFEsquerda());
            }
            //Se tiver filho a direita chama o mesmo
            if (n.getFDireita() != null){
                posOrdem(n.getFDireita());
            }
            //Imprime a informação da raíz
            System.out.print(n.getInformacao() + " ");
        }
    }
}
