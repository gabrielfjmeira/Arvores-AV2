public class ArvoreAVL {

    //Atributo da raíz
    private Node Raiz;

    //Método construtor
    public ArvoreAVL(){
        this.Raiz = null;
    }

    //Método que retorna a raíz da árvore AVL
    public Node getRaiz() {
        return this.Raiz;
    }

    //Método para definir a raiz da árvore AVL
    public void setRaiz(Node raiz) {
        this.Raiz = raiz;
    }

    //Método que retorna a altura de um nó da árvore AVL
    public int altura(Node no){
        if(no == null){
            return -1;
        }
        int esquerda = altura(no.getFEsquerda());
        int direita = altura(no.getFDireita());
        if(esquerda > direita){
            return 1 + esquerda;
        }
        return 1 + direita;
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

    //Método para inserir um elemento/novo nó na árvore AVL
    public void insertElementoArvore(Node noInserido, Node noAtual){
        //Verifica se é a raiz
        if (noAtual != null){
            //O nó aux é um nó de auxílio do código
            Node atual = noAtual;
            //Verifica o lado para manipular
            //Esquerda do nó auxiliar (informação n é menor q a informação do nó auxiliar)
            if (noInserido.getInformacao() < atual.getInformacao()) {
                //Com filho a esquerda disponível
                if (atual.getFEsquerda() == null) {
                    //Define o filho da esquerda
                    atual.setFEsquerda(noInserido);
                    noInserido.setNoPai(atual);
                    //Sem filho a esquerda disponível
                }else{
                    //Atualiza o nó auxiliar para prosseguir a execução do loop
                    insertElementoArvore(noInserido, atual.getFEsquerda());
                }
                //Direita do nó auxiliar (informação n é maior ou igual a informação do nó auxiliar)
            } else {
                //Com filho a direita disponível
                if (atual.getFDireita() == null) {
                    atual.setFDireita(noInserido);
                    noInserido.setNoPai(atual);
                    //Sem filho a direita disponível
                }else{
                    //Atualiza o nó auxiliar para prosseguir a execução do loop
                    insertElementoArvore(noInserido, atual.getFDireita());
                }
            }
        }else{
            //Caso o nó passado ser nulo, o mesmo é a raiz
            this.Raiz = noInserido;
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
            //Árvore possuí filho a esquerda
            if (atual.getFEsquerda() != null){
                //Pega o nó mais a direita do filho da esquerda para substituição
                Node aux = atual.getFEsquerda();
                Node noPaiAux = atual;
                while (aux.getFDireita() != null){
                    noPaiAux = aux;
                    aux = aux.getFDireita();
                }

                //Verifica se o aux possuí filho a esquerda
                Node filhoAux = null;
                if (aux.getFEsquerda() != null){
                    filhoAux = aux.getFEsquerda();
                }

                //Filhos do nó atual
                Node filhoEAtual = atual.getFEsquerda();
                Node filhoDAtual = null;
                if (atual.getFDireita() != null){
                    filhoDAtual = atual.getFDireita();
                }

                //Remove o nó folha do nó paiAuxiliar
                if (aux.getFEsquerda() != null){
                    noPaiAux.setFDireita(filhoAux);
                    filhoAux.setNoPai(noPaiAux);
                }else{
                    noPaiAux.setFDireita(null);
                }

                //Atualiza o nó que irá mudar de posição com o filho a direita do nó q foi removido
                if (filhoDAtual != null){
                    aux.setFDireita(filhoDAtual);
                    filhoDAtual.setNoPai(aux);
                }else{
                    aux.setFDireita(null);
                }

                //Atualiza o nó que irá mudar de posição com o filho a esquerda do nó q foi removido
                if (filhoEAtual != aux){
                    aux.setFEsquerda(filhoEAtual);
                    filhoEAtual.setNoPai(aux);
                }else{
                    aux.setFEsquerda(filhoAux);
                }

                //Verifica se o nó possuí pai (Verifica se o nó em questão é a raíz da arvore AVL)
                if (noPai != null){
                    //Atualiza o filho a esquerda do nó pai da árvore binária
                    if (atual.getInformacao() < noPai.getInformacao()){
                        noPai.setFEsquerda(aux);
                    //Atualiza o filho a direita do nó pai
                    }else{
                        noPai.setFDireita(aux);
                    }
                    aux.setNoPai(noPai);
                }else{ //Único caso em que um nó não possuí pai é a raiz
                    this.Raiz = aux;
                    aux.setNoPai(null);
                }

                //Verifica Balanceamento
                Node noFolha = null;
                if (aux.getFEsquerda() != null || aux.getFDireita()!= null){
                    //Subárvore da esquerda
                    if (aux.getFEsquerda() != null){
                        noFolha = aux.getFEsquerda();
                        while (true){
                            if (noFolha.getFDireita() != null){
                                noFolha = noFolha.getFDireita();
                            }else if(noFolha.getFEsquerda() != null){
                                noFolha = noFolha.getFEsquerda();
                            }else{
                                break;
                            }
                        }
                        verificacaoBalanceamento(noFolha);
                    }
                    //Subárvore da direita
                    if (aux.getFDireita() != null) {
                        noFolha = aux.getFDireita();
                        while (true) {
                            if (noFolha.getFEsquerda() != null) {
                                noFolha = noFolha.getFEsquerda();
                            } else if (noFolha.getFDireita() != null) {
                                noFolha = noFolha.getFDireita();
                            } else {
                                break;
                            }
                        }
                        verificacaoBalanceamento(noFolha);
                    }
                //Nó Folha
                }else{
                    //Subárvore a esquerda do nó pai
                    if (noPaiAux.getFEsquerda() != null){
                        noFolha = noPaiAux.getFEsquerda();
                        while (true){
                            if (noFolha.getFDireita() != null){
                                noFolha = noFolha.getFDireita();
                            }else if(noFolha.getFEsquerda() != null){
                                noFolha = noFolha.getFEsquerda();
                            }else{
                                break;
                            }
                        }
                    //Subárvore a direita do nó pai
                    }else if (noPaiAux.getFDireita() != null){
                        noFolha = noPaiAux.getFDireita();
                        while (true){
                            if (noFolha.getFEsquerda() != null){
                                noFolha = noFolha.getFEsquerda();
                            }else if(noFolha.getFDireita() != null){
                                noFolha = noFolha.getFDireita();
                            }else{
                                break;
                            }
                        }
                    //Nó pai é um nó folha
                    }else{
                        noFolha = noPaiAux;
                    }

                    verificacaoBalanceamento(noFolha);
                }

            //Árvore possuí filho a direita
            } else if (atual.getFDireita() != null){
                //Pega o nó mais a esquerda do filho da direita para substituição
                Node aux = atual.getFDireita();
                Node noPaiAux = atual;
                while (aux.getFEsquerda() != null){
                    noPaiAux = aux;
                    aux = aux.getFEsquerda();
                }

                //Verifica se o aux possuí filho a direita
                Node filhoAux = null;
                if (aux.getFDireita() != null){
                    filhoAux = aux.getFDireita();
                }

                //Filhos do nó atual
                Node filhoDAtual = atual.getFDireita();
                Node filhoEAtual = null;
                if (atual.getFEsquerda() != null){
                    filhoEAtual = atual.getFEsquerda();
                }

                //Remove o nó folha do nó paiAuxiliar
                if (aux.getFDireita() != null){
                    noPaiAux.setFEsquerda(filhoAux);
                    filhoAux.setNoPai(noPaiAux);
                }else{
                    noPaiAux.setFEsquerda(null);
                }

                //Atualiza o nó que irá mudar de posição com o filho a esquerda do nó q foi removido
                if (filhoEAtual != null){
                    aux.setFEsquerda(filhoEAtual);
                    filhoEAtual.setNoPai(aux);
                }else{
                    aux.setFEsquerda(null);
                }

                //Atualiza o nó que irá mudar de posição com o filho a direita do nó q foi removido
                if (filhoDAtual != aux){
                    aux.setFDireita(filhoDAtual);
                    filhoDAtual.setNoPai(aux);
                }else{
                    aux.setFDireita(filhoAux);
                }

                //Verifica se o nó possuí pai (Verifica se o nó em questão é a raíz da arvore AVL)
                if (noPai != null) {
                    //Atualiza o filho a esquerda do nó pai da árvore binária
                    if (atual.getInformacao() < noPai.getInformacao()) {
                        noPai.setFEsquerda(aux);
                        //Atualiza o filho a direita do nó pai
                    } else {
                        noPai.setFDireita(aux);
                    }
                    aux.setNoPai(noPai);
                }else{ //Único caso em que um nó não possuí pai é a raiz
                    this.Raiz = aux;
                    aux.setNoPai(null);
                }

                //Verifica balanceamento
                Node noFolha = null;
                if (aux.getFEsquerda() != null || aux.getFDireita() != null){
                    //Subárvore da direita
                    if (aux.getFDireita() != null){
                        noFolha = aux.getFDireita();
                        while (true){
                            if (noFolha.getFEsquerda() != null){
                                noFolha = noFolha.getFEsquerda();
                            }else if(noFolha.getFDireita() != null){
                                noFolha = noFolha.getFDireita();
                            }else{
                                break;
                            }
                        }
                        verificacaoBalanceamento(noFolha);
                    }

                    //Subárvore da esquerda
                    if (aux.getFEsquerda() != null){
                        noFolha = aux.getFEsquerda();
                        while (true){
                            if (noFolha.getFDireita() != null){
                                noFolha = noFolha.getFDireita();
                            }else if(noFolha.getFEsquerda() != null){
                                noFolha = noFolha.getFEsquerda();
                            }else{
                                break;
                            }
                        }
                        verificacaoBalanceamento(noFolha);
                    }
                //Nó Folha
                }else{
                    //Subárvore da esquerda do nó pai
                    if (noPaiAux.getFEsquerda() != null){
                        noFolha = noPaiAux.getFEsquerda();
                        while (true){
                            if (noFolha.getFDireita() != null){
                                noFolha = noFolha.getFDireita();
                            }else if(noFolha.getFEsquerda() != null){
                                noFolha = noFolha.getFEsquerda();
                            }else{
                                break;
                            }
                        }
                    //Subárvore da direita do nó pai
                    }else if (noPaiAux.getFDireita() != null){
                        noFolha = noPaiAux.getFDireita();
                        while (true){
                            if (noFolha.getFEsquerda() != null){
                                noFolha = noFolha.getFEsquerda();
                            }else if(noFolha.getFDireita() != null){
                                noFolha = noFolha.getFDireita();
                            }else{
                                break;
                            }
                        }
                    //Nó pai é um nó folha
                    }else{
                        noFolha = noPaiAux;
                    }

                    verificacaoBalanceamento(noFolha);
                }
            //Nó não tem filhos(nó folha)
            }else{
                //Verifica se o nó possuí pai (Verifica se o nó em questão é a raíz da arvore AVL)
                if (noPai != null) {
                    //Atualiza o filho a esquerda do nó pai
                    if (atual.getInformacao() < noPai.getInformacao()) {
                        noPai.setFEsquerda(null);
                        //Atualiza o filho a direita do nó pai
                    } else {
                        noPai.setFDireita(null);
                    }

                    //Verifica balanceamento
                    Node noFolha = null;
                    //Subárvore da esquerda do nó pai
                    if (noPai.getFEsquerda() != null){
                        noFolha = noPai.getFEsquerda();
                        while (true){
                            if (noFolha.getFDireita() != null){
                                noFolha = noFolha.getFDireita();
                            }else if(noFolha.getFEsquerda() != null){
                                noFolha = noFolha.getFEsquerda();
                            }else{
                                break;
                            }
                        }
                    //Subárvore da direita do nó pai
                    }else if (noPai.getFDireita() != null){
                        noFolha = noPai.getFDireita();
                        while (true){
                            if (noFolha.getFDireita() != null){
                                noFolha = noFolha.getFDireita();
                            }else if(noFolha.getFDireita() != null){
                                noFolha = noFolha.getFDireita();
                            }else{
                                break;
                            }
                        }
                    //Nó pai é um nó folha
                    }else{
                        noFolha = noPai;
                    }

                    verificacaoBalanceamento(noFolha);

                }else{ //Único caso em que um nó não possuí pai é a raiz
                    this.Raiz = null;
                }
            }
        }
    }

    //Verifica desbalanceamento dos nós
    public void verificacaoBalanceamento(Node no){
        if (no == null){
            return;
        }

        //Verifica o desbalanceamento dos nós
        if (no.getNoPai() != null){
            Node noPai = no.getNoPai();

            //Busca o nó vô e o armazena, se possuir
            Node noVo = null;
            if (noPai.getNoPai() != null){
                noVo = noPai.getNoPai();

                //Calcula a altura dos nós
                int alturaVo = 0;
                int alturaPai = 0;

                //Altura do Vo
                if (noVo != null){
                    alturaVo = altura(noVo.getFEsquerda()) - altura(noVo.getFDireita());
                }

                //Altura do nó pai
                alturaPai = altura(noPai.getFEsquerda()) - altura(noPai.getFDireita());

                //Caso de dupla rotação a direita no nó atual
                if (alturaVo == 2 && alturaPai == -1) {
                    duplaRotacaoDireita(noVo);
                //Rotação a direita no nó Pai
                } else if (alturaVo == 2) {
                    rotacaoDireita(noPai);
                }

                //Caso de dupla rotação a esquerda no nó vô
                if (alturaVo == -2 && alturaPai == 1) {
                    duplaRotacaoEsquerda(noVo);
                //Rotação a esquerda no nó pai
                } else if(alturaVo == -2) {
                    rotacaoEsquerda(noPai);
                }

                //Percorre a árvore verificando o balanceamento até a raiz
                verificacaoBalanceamento(noPai);
            }
        }
    }

    //Método da rotação direita
    public void rotacaoDireita(Node no){
        //Definição dos nós filhos, pai, vô e pai Vô
        Node noPai = no;
        Node noVo = noPai.getNoPai();
        Node noPaiVo = noVo.getNoPai();
        Node noFilho = noPai.getFEsquerda();
        Node noFilhoD = null;

        //Verifica se a nova raiz possuí filhos a direita
        if (noPai.getFDireita() != null){
            noFilhoD = noPai.getFDireita();
        }

        //Atualiza o filho a direita da nova raiz
        noPai.setFDireita(noVo);

        //Reposiciona o nó temporário
        if (noFilhoD != null){
            noVo.setFEsquerda(noFilhoD);
            noFilhoD.setNoPai(noVo);
        }else{
            noVo.setFEsquerda(null);
        }

        //Atualiza o nó pai do filho a direita da nova raiz
        noVo.setNoPai(noPai);

        //Se o pai do nó vô não for nulo não é a raiz
        if (noPaiVo != null){
            //Atualiza o filho a direita do nó pai do vô
            if (noPai.getInformacao() >= noPaiVo.getInformacao()){
                noPaiVo.setFDireita(noPai);
            //Atualiza o filho a esquerda do nó pai do vô
            }else{
                noPaiVo.setFEsquerda(noPai);
            }
            noPai.setNoPai(noPaiVo);
        }else {
            this.Raiz = noPai;
            noPai.setNoPai(null);
        }

    }

    //Método da dupla rotação a direita
    public void duplaRotacaoDireita(Node noVo){

        //Definição de variáveis
        Node noPaiVo = noVo.getNoPai();
        Node noPai = noVo.getFEsquerda();
        Node noFilho = noPai.getFDireita();

        //Manipulação de dados que podem ser filhos do filho
        Node noFilhoE = null;
        Node noFilhoD = null;

        if (noFilho.getFEsquerda() != null){
            noFilhoE = noFilho.getFEsquerda();
        }

        if (noFilho.getFDireita() != null) {
            noFilhoD = noFilho.getFDireita();
        }

        //Atualização da nova raiz
        noFilho.setFDireita(noVo);
        noFilho.setFEsquerda(noPai);

        //Vô "perdeu um filho"
        if (noFilhoD != null) {
            noVo.setFEsquerda(noFilhoD);
            noFilhoD.setNoPai(noVo);
        }else {
            noVo.setFEsquerda(null);
        }

        //Pai "perdeu seu filho"
        if (noFilhoE != null){
            noPai.setFDireita(noFilhoE);
            noFilhoE.setNoPai(noPai);
        }else{
            noPai.setFDireita(null);
        }

        //Atualiza a nova raiz como pai do nó pai e nó vô
        noVo.setNoPai(noFilho);
        noPai.setNoPai(noFilho);

        //Se o pai do nó Vô for nulo, logo o nó vô é a raiz
        if (noPaiVo != null){
            //Atualiza o filho a esquerda do nó pai do vô
            if (noFilho.getInformacao() >= noPaiVo.getInformacao()){
                noPaiVo.setFDireita(noFilho);
            //Atualiza o filho a direita do nó pai do vô
            }else{
                noPaiVo.setFEsquerda(noFilho);
            }
            noFilho.setNoPai(noPaiVo);
        }else{
            this.Raiz = noFilho;
            noFilho.setNoPai(null);
        }
    }

    //Método da rotação esquerda
    public void rotacaoEsquerda(Node no){
        //Definição dos nós filhos, pai, vô e pai do vô
        Node noPai = no;
        Node noVo = noPai.getNoPai();
        Node noPaiVo = noVo.getNoPai();
        Node noFilho = noPai.getFDireita();
        Node noFilhoE = null;

        //Verifica se a nova raiz possuí filhos a esquerda
        if (noPai.getFEsquerda() != null){
            noFilhoE = noPai.getFEsquerda();
        }

        //Atualiza o filho a esquerda da nova raiz
        noPai.setFEsquerda(noVo);

        //Reposiciona o nó temporário
        if (noFilhoE != null){
            noVo.setFDireita(noFilhoE);
            noFilhoE.setNoPai(noVo);
        }else{
            noVo.setFDireita(null);
        }

        //Atualiza o nó pai do filho a esquerda da nova raiz
        noVo.setNoPai(noPai);

        //Se o vô não for nulo não é a raiz
        if (noPaiVo != null){
            //Atualiza o filho a direita do nó pai do vô
            if (noPai.getInformacao() >= noPaiVo.getInformacao()){
                noPaiVo.setFDireita(noPai);
            //Atualiza o filho a esquerda do nó pai do vô
            }else{
                noPaiVo.setFEsquerda(noPai);
            }
            noPai.setNoPai(noPaiVo);
        }else{
            this.Raiz = noPai;
            noPai.setNoPai(null);
        }
    }

    //Método da dupla rotação a esquerda
    public void duplaRotacaoEsquerda(Node noVo){
        //Definição de variáveis
        Node noPaiVo = noVo.getNoPai();
        Node noPai = noVo.getFDireita();
        Node noFilho = noPai.getFEsquerda();

        //Manipulação de dados que podem ser filhos do filho
        Node noFilhoE = null;
        Node noFilhoD = null;

        if (noFilho.getFEsquerda() != null){
            noFilhoE = noFilho.getFEsquerda();
        }

        if (noFilho.getFDireita() != null) {
            noFilhoD = noFilho.getFDireita();
        }

        //Atualização da nova raiz
        noFilho.setFEsquerda(noVo);
        noFilho.setFDireita(noPai);

        //Vô "perdeu um filho"
        if (noFilhoE != null) {
            noVo.setFDireita(noFilhoE);
            noFilhoE.setNoPai(noVo);
        }else{
            noVo.setFDireita(null);
        }

        //Pai "perdeu seu filho"
        if (noFilhoD != null){
            noPai.setFEsquerda(noFilhoD);
            noFilhoD.setNoPai(noPai);
        }else{
            noPai.setFEsquerda(null);
        }

        //Atualiza a nova raiz como pai do nó pai e do nó vô
        noVo.setNoPai(noFilho);
        noPai.setNoPai(noFilho);

        //Se o pai do nó vô for nulo, logo o nó vô é a raiz
        if (noPaiVo != null){
            //Atualiza o filho a direita do nó pai do vô
            if (noFilho.getInformacao() >= noPaiVo.getInformacao()){
                noPaiVo.setFDireita(noFilho);
                //Atualiza o filho a esquerda do nó pai do vô
            }else{
                noPaiVo.setFEsquerda(noFilho);
            }
            noFilho.setNoPai(noPaiVo);
        }else{
            this.Raiz = noFilho;
            noFilho.setNoPai(null);
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
        if (n != null) {
            //Se tiver filho a esquerda chama o mesmo
            if (n.getFEsquerda() != null) {
                emOrdem(n.getFEsquerda());
            }
            //Imprime a informação da raíz
            System.out.print(n.getInformacao() + " ");
            //Se tiver filho a direita chama o mesmo
            if (n.getFDireita() != null) {
                emOrdem(n.getFDireita());
            }
        }
    }

    //Impressão em Pós-Ordem
    public void posOrdem(Node n){
        if (n != null) {
            //Se tiver filho a esquerda chama o mesmo
            if (n.getFEsquerda() != null) {
                posOrdem(n.getFEsquerda());
            }
            //Se tiver filho a direita chama o mesmo
            if (n.getFDireita() != null) {
                posOrdem(n.getFDireita());
            }
            //Imprime a informação da raíz
            System.out.print(n.getInformacao() + " ");
        }
    }
}
