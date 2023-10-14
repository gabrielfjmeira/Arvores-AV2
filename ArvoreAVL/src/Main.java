import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Criação da Árvore AVL
        ArvoreAVL arvoreAVL = new ArvoreAVL();

        //Criação do gerador de números aleatórios
        Random gerador = new Random();

        //Criação dos scanners
        Scanner sc = new Scanner(System.in);
        Scanner scF;

        //Menu
        int option = -1;
        while (option != 0){
            System.out.println("===== Árvore AVL =====");

            //Impressão da árvore AVL nos três modos
            System.out.println();
            System.out.println("Pré-Ordem: ");
            arvoreAVL.preOrdem(arvoreAVL.getRaiz());
            System.out.println();

            System.out.println();
            System.out.println("Em-Ordem: ");
            arvoreAVL.emOrdem(arvoreAVL.getRaiz());
            System.out.println();

            System.out.println();
            System.out.println("Pós-Ordem: ");
            arvoreAVL.posOrdem(arvoreAVL.getRaiz());
            System.out.println();
            System.out.println("=========================");

            System.out.println("0. Sair");
            System.out.println("1. Inserção");
            System.out.println("2. Remoção");
            System.out.println("3. Busca");
            System.out.println("4. Geração de árvore AVL com 100 números aleatórios");
            System.out.println("5. Geração de árvore AVL com 500 números aleatórios");
            System.out.println("6. Geração de árvore AVL com 1000 números aleatórios");
            System.out.println("7. Geração de árvore AVL com 10000 números aleatórios");
            System.out.println("8. Geração de árvore AVL com 20000 números aleatórios");
            System.out.println("9. Carregar txt");
            System.out.println("Escolha uma opção: ");
            option = sc.nextInt();

            //Variáveis de auxílio para execução
            int numero = 0;
            long comeco = 0;
            long fim = 0;
            long tempoTotal = 0;

            //Navegação do menu
            switch (option){
                case 0:
                    //Encerra o programa
                    System.exit(0);
                    break;
                case 1:
                    //Inserção
                    boolean parou = false;
                    while (parou == false) {
                        System.out.println();
                        System.out.println("Entre com o número (-1 para parar): ");
                        numero = sc.nextInt();
                        comeco = System.nanoTime();
                        if (numero != -1){
                            Node novoNo = new Node(numero);
                            arvoreAVL.insertElementoArvore(novoNo, arvoreAVL.getRaiz());
                            arvoreAVL.verificacaoBalanceamento(novoNo);
                        }else {
                            parou = true;
                        }
                        fim = System.nanoTime();
                        tempoTotal = (fim - comeco) / 1000; // Converter para microssegundos
                        System.out.println("Tempo total de execução da inserção: " + tempoTotal + " microssegundos");
                    }
                    break;
                case 2:
                    //Remoção
                    System.out.println();
                    System.out.println("Entre com o número: ");
                    numero = sc.nextInt();
                    comeco = System.nanoTime();
                    boolean noEncontradoR = arvoreAVL.buscar(numero);
                    if (noEncontradoR == true){
                        arvoreAVL.removeElemento(numero, arvoreAVL.getRaiz(), null);
                    }else {
                        System.out.println("Nó " + numero + " não foi encontrado para remoção!");
                    }
                    fim = System.nanoTime();
                    tempoTotal = (fim - comeco) / 1000; // Converter para microssegundos
                    System.out.println("Tempo total de execução da remoção: " + tempoTotal + " microssegundos");
                    break;
                case 3:
                    //Busca
                    int numeroBusca = -1;
                    System.out.println("Insira um número para ser realizado a busca: ");
                    numeroBusca = sc.nextInt();
                    comeco = System.nanoTime();
                    boolean noEncontradoB = arvoreAVL.buscar(numeroBusca);
                    if (noEncontradoB == true){
                        System.out.println("Nó " + numeroBusca + " foi encontrado!");
                    }else{
                        System.out.println("Nó " + numeroBusca + " não foi encontrado!");
                    }
                    fim = System.nanoTime();
                    tempoTotal = (fim - comeco) / 1000; // Converter para microssegundos
                    System.out.println("Tempo total de execução da busca: " + tempoTotal + " microssegundos");
                    break;
                case 4:
                    //Inserção de 100 números aleatórios
                    System.out.println();

                    //Começa a contagem do tempo
                    comeco = System.nanoTime();

                    try {
                        //Criação do arquivo TXT de 100 números
                        FileWriter fw = new FileWriter("CemElementos.txt");
                        PrintWriter pw = new PrintWriter(fw);

                        //Inserção dos elementos na arvore AVL
                        for (int i = 0; i < 100; i++) {
                            numero = gerador.nextInt(100);
                            pw.println(numero);
                            Node novoNo = new Node(numero);
                            arvoreAVL.insertElementoArvore(novoNo, arvoreAVL.getRaiz());
                            arvoreAVL.verificacaoBalanceamento(novoNo);
                        }

                        System.out.println();

                        //Finaliza a contagem do tempo
                        fim = System.nanoTime();

                        //Calcula o tempo total da execução
                        tempoTotal = (fim - comeco) / 1000; // Converter para microssegundos

                        //Imprime o tempo total da execução
                        System.out.println("Tempo total da inserção de 100 números aleatórios: " + tempoTotal + " microssegundos");

                        //Salva e fecha o txt
                        pw.flush();
                        pw.close();
                        fw.close();

                    }catch (Exception erro){
                        System.out.println("Erro ao gerar árvore AVL com 100 elementos!");
                    }
                    break;
                case 5:
                    //Inserção de 500 números aleatórios
                    System.out.println();

                    //Começa a contagem do tempo
                    comeco = System.nanoTime();

                    try {
                        //Criação do arquivo TXT de 500 números
                        FileWriter fw = new FileWriter("QuinhentosElementos.txt");
                        PrintWriter pw = new PrintWriter(fw);

                        //Inserção dos elementos na arvore AVL
                        for (int i = 0; i < 500; i++) {
                            numero = gerador.nextInt(100);
                            pw.println(numero);
                            Node novoNo = new Node(numero);
                            arvoreAVL.insertElementoArvore(novoNo, arvoreAVL.getRaiz());
                            arvoreAVL.verificacaoBalanceamento(novoNo);
                        }

                        System.out.println();

                        //Finaliza a contagem do tempo
                        fim = System.nanoTime();

                        //Calcula o tempo total da execução
                        tempoTotal = (fim - comeco) / 1000; // Converter para microssegundos

                        //Imprime o tempo total da execução
                        System.out.println("Tempo total da inserção de 500 números aleatórios: " + tempoTotal + " microssegundos");

                        //Salva e fecha o txt
                        pw.flush();
                        pw.close();
                        fw.close();

                    }catch (Exception erro){
                        System.out.println("Erro ao gerar árvore AVL com 500 elementos!");
                    }
                    break;
                case 6:
                    //Inserção de 1000 números aleatórios
                    System.out.println();

                    //Começa a contagem do tempo
                    comeco = System.nanoTime();

                    try {
                        //Criação do arquivo TXT de 1000 números
                        FileWriter fw = new FileWriter("MilElementos.txt");
                        PrintWriter pw = new PrintWriter(fw);

                        //Inserção dos elementos na arvore AVL
                        for (int i = 0; i < 1000; i++) {
                            numero = gerador.nextInt(100);
                            pw.println(numero);
                            Node novoNo = new Node(numero);
                            arvoreAVL.insertElementoArvore(novoNo, arvoreAVL.getRaiz());
                            arvoreAVL.verificacaoBalanceamento(novoNo);
                        }

                        System.out.println();

                        //Finaliza a contagem do tempo
                        fim = System.nanoTime();

                        //Calcula o tempo total da execução
                        tempoTotal = (fim - comeco) / 1000; // Converter para microssegundos

                        //Imprime o tempo total da execução
                        System.out.println("Tempo total da inserção de 1000 números aleatórios: " + tempoTotal + " microssegundos");

                        //Salva e fecha o txt
                        pw.flush();
                        pw.close();
                        fw.close();

                    }catch (Exception erro){
                        System.out.println("Erro ao gerar árvore AVL com 1000 elementos!");
                    }
                    break;
                case 7:
                    //Inserção de 10000 números aleatórios
                    System.out.println();

                    //Começa a contagem do tempo
                    comeco = System.nanoTime();

                    try {
                        //Criação do arquivo TXT de 10000 números
                        FileWriter fw = new FileWriter("DezMilElementos.txt");
                        PrintWriter pw = new PrintWriter(fw);

                        //Inserção dos elementos na arvore AVL
                        for (int i = 0; i < 10000; i++) {
                            numero = gerador.nextInt(100);
                            pw.println(numero);
                            Node novoNo = new Node(numero);
                            arvoreAVL.insertElementoArvore(novoNo, arvoreAVL.getRaiz());
                            arvoreAVL.verificacaoBalanceamento(novoNo);
                        }

                        System.out.println();

                        //Finaliza a contagem do tempo
                        fim = System.nanoTime();

                        //Calcula o tempo total da execução
                        tempoTotal = (fim - comeco) / 1000; // Converter para microssegundos

                        //Imprime o tempo total da execução
                        System.out.println("Tempo total da inserção de 10000 números aleatórios: " + tempoTotal + " microssegundos");

                        //Salva e fecha o txt
                        pw.flush();
                        pw.close();
                        fw.close();

                    }catch (Exception erro){
                        System.out.println("Erro ao gerar árvore AVL com 10000 elementos!");
                    }
                    break;
                case 8:
                    //Inserção de 20000 números aleatórios
                    System.out.println();

                    //Começa a contagem do tempo
                    comeco = System.nanoTime();

                    try {
                        //Criação do arquivo TXT de 20000 números
                        FileWriter fw = new FileWriter("VinteMilElementos.txt");
                        PrintWriter pw = new PrintWriter(fw);

                        //Inserção dos elementos na arvore AVL
                        for (int i = 0; i < 20000; i++) {
                            numero = gerador.nextInt(100);
                            pw.println(numero);
                            Node novoNo = new Node(numero);
                            arvoreAVL.insertElementoArvore(novoNo, arvoreAVL.getRaiz());
                            arvoreAVL.verificacaoBalanceamento(novoNo);
                        }

                        System.out.println();

                        //Finaliza a contagem do tempo
                        fim = System.nanoTime();

                        //Calcula o tempo total da execução
                        tempoTotal = (fim - comeco) / 1000; // Converter para microssegundos

                        //Imprime o tempo total da execução
                        System.out.println("Tempo total da inserção de 20000 números aleatórios: " + tempoTotal + " microssegundos");

                        //Salva e fecha o txt
                        pw.flush();
                        pw.close();
                        fw.close();

                    }catch (Exception erro){
                        System.out.println("Erro ao gerar árvore AVL com 20000 elementos!");
                    }
                    break;
                case 9:
                    //Leitura de arquivo TXT com os dados
                    Path caminho100 = Paths.get("C:\\Users\\Gabriel F\\Documents\\Faculdade\\4º Período\\Estrutura de Dados\\Árvores\\ArvoreAVL\\CemElementos.txt");
                    Path caminho500 = Paths.get("C:\\Users\\Gabriel F\\Documents\\Faculdade\\4º Período\\Estrutura de Dados\\Árvores\\ArvoreAVL\\QuinhentosElementos.txt");
                    Path caminho1000 = Paths.get("C:\\Users\\Gabriel F\\Documents\\Faculdade\\4º Período\\Estrutura de Dados\\Árvores\\ArvoreAVL\\MilElementos.txt");
                    Path caminho10000 = Paths.get("C:\\Users\\Gabriel F\\Documents\\Faculdade\\4º Período\\Estrutura de Dados\\Árvores\\ArvoreAVL\\DezMilElementos.txt");
                    Path caminho20000 = Paths.get("C:\\Users\\Gabriel F\\Documents\\Faculdade\\4º Período\\Estrutura de Dados\\Árvores\\ArvoreAVL\\VinteMilElementos.txt");

                    //Opção de txt a ser lido:
                    int opcaoTxt = -1;

                    System.out.println();
                    System.out.println("1 - 100 Elementos");
                    System.out.println("2 - 500 Elementos");
                    System.out.println("3 - 1000 Elementos");
                    System.out.println("4 - 10000 Elementos");
                    System.out.println("5 - 20000 Elementos");
                    System.out.println("Escolha um txt a ser lido:");

                    //Vetor para armazenar os valores
                    ArrayList<Integer> numerosTxt = new ArrayList<>();

                    //Leitura de opção TXT
                    opcaoTxt = sc.nextInt();

                    //Seleção do TXT
                    switch (opcaoTxt){
                        case 1:
                            //100 Elementos
                            try{
                                //Começa a contagem do tempo
                                comeco = System.nanoTime();

                                //Percorre arquivo TXT de 100 elementos
                                scF = new Scanner(caminho100);
                                while(scF.hasNextLine()){
                                    numerosTxt.add(Integer.parseInt(scF.nextLine()));
                                }

                                //Apaga a árvore binária
                                arvoreAVL.setRaiz(null);

                                //Preenche com os elementos do txt
                                for (Integer i : numerosTxt){
                                    Node noNovo = new Node(i);
                                    arvoreAVL.insertElementoArvore(noNovo, arvoreAVL.getRaiz());
                                    arvoreAVL.verificacaoBalanceamento(noNovo);
                                }

                                //Finaliza a contagem do tempo
                                fim = System.nanoTime();

                                //Calcula o tempo total da execução
                                tempoTotal = (fim - comeco) / 1000; // Converter para microssegundos

                                //Imprime o tempo total da execução
                                System.out.println("Tempo total da leitura do txt de 100 números: " + tempoTotal + " microssegundos");
                            }catch (Exception erro){
                                System.out.println("Erro ao ler o arquivo TXT com 100 números!");
                            }
                            break;
                        case 2:
                            //500 Elementos
                            try{
                                //Começa a contagem do tempo
                                comeco = System.nanoTime();

                                //Percorre arquivo TXT de 500 elementos
                                scF = new Scanner(caminho500);
                                while(scF.hasNextLine()){
                                    numerosTxt.add(Integer.parseInt(scF.nextLine()));
                                }

                                //Apaga a árvore binária
                                arvoreAVL.setRaiz(null);

                                //Preenche com os elementos do txt
                                for (Integer i : numerosTxt){
                                    Node noNovo = new Node(i);
                                    arvoreAVL.insertElementoArvore(noNovo, arvoreAVL.getRaiz());
                                    arvoreAVL.verificacaoBalanceamento(noNovo);
                                }

                                //Finaliza a contagem do tempo
                                fim = System.nanoTime();

                                //Calcula o tempo total da execução
                                tempoTotal = (fim - comeco) / 1000; // Converter para microssegundos

                                //Imprime o tempo total da execução
                                System.out.println("Tempo total da leitura do txt de 500 números: " + tempoTotal + " microssegundos");
                            }catch (Exception erro){
                                System.out.println("Erro ao ler o arquivo TXT com 500 números!");
                            }
                            break;
                        case 3:
                            //1000 Elementos
                            try{
                                //Começa a contagem do tempo
                                comeco = System.nanoTime();

                                //Percorre arquivo TXT de 1000 elementos
                                scF = new Scanner(caminho1000);
                                while(scF.hasNextLine()){
                                    numerosTxt.add(Integer.parseInt(scF.nextLine()));
                                }

                                //Apaga a árvore binária
                                arvoreAVL.setRaiz(null);

                                //Preenche com os elementos do txt
                                for (Integer i : numerosTxt){
                                    Node noNovo = new Node(i);
                                    arvoreAVL.insertElementoArvore(noNovo, arvoreAVL.getRaiz());
                                    arvoreAVL.verificacaoBalanceamento(noNovo);
                                }

                                //Finaliza a contagem do tempo
                                fim = System.nanoTime();

                                //Calcula o tempo total da execução
                                tempoTotal = (fim - comeco) / 1000; // Converter para microssegundos

                                //Imprime o tempo total da execução
                                System.out.println("Tempo total da leitura do txt de 1000 números: " + tempoTotal + " microssegundos");
                            }catch (Exception erro){
                                System.out.println("Erro ao ler o arquivo TXT com 1000 números!");
                            }
                            break;
                        case 4:
                            //10000 Elementos
                            try{
                                //Começa a contagem do tempo
                                comeco = System.nanoTime();

                                //Percorre arquivo TXT de 10000 elementos
                                scF = new Scanner(caminho10000);
                                while(scF.hasNextLine()){
                                    numerosTxt.add(Integer.parseInt(scF.nextLine()));
                                }

                                //Apaga a árvore binária
                                arvoreAVL.setRaiz(null);

                                //Preenche com os elementos do txt
                                for (Integer i : numerosTxt){
                                    Node noNovo = new Node(i);
                                    arvoreAVL.insertElementoArvore(noNovo, arvoreAVL.getRaiz());
                                    arvoreAVL.verificacaoBalanceamento(noNovo);
                                }

                                //Finaliza a contagem do tempo
                                fim = System.nanoTime();

                                //Calcula o tempo total da execução
                                tempoTotal = (fim - comeco) / 1000; // Converter para microssegundos

                                //Imprime o tempo total da execução
                                System.out.println("Tempo total da leitura do txt de 10000 números: " + tempoTotal + " microssegundos");
                            }catch (Exception erro){
                                System.out.println("Erro ao ler o arquivo TXT com 10000 números!");
                            }
                            break;
                        case 5:
                            //20000 Elementos
                            try{
                                //Começa a contagem do tempo
                                comeco = System.nanoTime();

                                //Percorre arquivo TXT de 20000 elementos
                                scF = new Scanner(caminho20000);
                                while(scF.hasNextLine()){
                                    numerosTxt.add(Integer.parseInt(scF.nextLine()));
                                }

                                //Apaga a árvore binária
                                arvoreAVL.setRaiz(null);

                                //Preenche com os elementos do txt
                                for (Integer i : numerosTxt){
                                    Node noNovo = new Node(i);
                                    arvoreAVL.insertElementoArvore(noNovo, arvoreAVL.getRaiz());
                                    arvoreAVL.verificacaoBalanceamento(noNovo);
                                }

                                //Finaliza a contagem do tempo
                                fim = System.nanoTime();

                                //Calcula o tempo total da execução
                                tempoTotal = (fim - comeco) / 1000; // Converter para microssegundos

                                //Imprime o tempo total da execução
                                System.out.println("Tempo total da leitura do txt de 20000 números: " + tempoTotal + " microssegundos");
                            }catch (Exception erro){
                                System.out.println("Erro ao ler o arquivo TXT com 20000 números!");
                            }
                            break;
                        default:
                            System.out.println("Escolha uma opção válida!");
                            break;
                    }
                    break;
                default:
                    System.out.println("Escolha uma opção válida!");
                    break;
            }
        }
    }
}
