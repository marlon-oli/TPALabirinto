import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class Labirinto {
    private ArrayList<String[]> matriz;
    private int[] entrada;
    private int[] saida;
    private Stack<int[]> caminhoSolucao;
    private ArrayList<int[]> caminhoPercorrido;

    public Labirinto(){
        matriz = new ArrayList<>();
        caminhoSolucao = new Stack<>();
        caminhoPercorrido = new ArrayList<>();
        carregarLabirinto();
        entrada = acharEntrada();
        saida = acharSaida();
    }

    public void mostrarCaminhoSolucao(){
        for (int[] coord : caminhoSolucao) {
            matriz.get(coord[0])[coord[1]] = "X";
        }
    }

    public void encontrarSolucao(){
        int lAtual = entrada[0];
        int cAtual = entrada[1];


        caminhoPercorrido.add(entrada);
        caminhoSolucao.push(entrada);


        //jogar tudo while nesse bloco
        while (lAtual != saida[0] && cAtual != saida[1]){
            boolean achou = false;
            int lBusca = lAtual - 1;
            int cBusca = cAtual - 1;

            while(lBusca <= (lAtual+1) && !achou){
                cBusca = cAtual - 1;
                while(cBusca <= (cAtual+1) && !achou){
                    if ((lBusca >= 0 && lBusca < matriz.size()) && (cBusca >= 0 && cBusca < matriz.get(0).length)){
                        if((matriz.get(lBusca)[cBusca].equals("1"))){
                            int[] coordatual = new int[]{lBusca, cBusca};
                            boolean contem = false;
                            for(int[] i: caminhoPercorrido){
                                if(i[0] == lBusca && i[1] == cBusca){
                                    contem = true;
                                }
                            }

                            if(!contem){
                                caminhoSolucao.push(new int[]{lBusca, cBusca});
                                caminhoPercorrido.add(new int[]{lBusca, cBusca});
                                lAtual = lBusca;
                                cAtual = cBusca;

                                achou = true;
                            }
                        }
                    }
                    cBusca++;
                }
                lBusca++;
            }
            if (!achou){
                caminhoSolucao.pop();
                int[] coord = caminhoSolucao.peek();
                lAtual = coord[0];
                cAtual = coord[1];
            }
        }
        caminhoSolucao.push(saida);
    }

    private int[] acharEntrada(){
        for (int i = 0; i < matriz.size(); i++) {
            for (int j = 0; j < matriz.get(0).length; j++) {
                if(matriz.get(i)[j].equals("1")){
                    if (i == 0 || i == matriz.size()-1 || j == 0 || j == matriz.get(0).length-1){
                        return new int[]{i,j};
                    }
                }
            }
        }
        return null;
    }

    private int[] acharSaida(){
        for (int i = 0; i < matriz.size(); i++) {
            for (int j = 0; j < matriz.get(0).length; j++) {
                if(matriz.get(i)[j].equals("1")){
                    if (i == 0 || i == matriz.size()-1 || j == 0 || j == matriz.get(0).length-1){
                        int[] coord = new int[]{i,j};
                        if(!((coord[0] == entrada[0]) && (coord[1] == entrada[1]))){
                            return coord;
                        }
                    }
                }
            }
        }
        return null;
    }

    public void mostrarLabirinto() {
        for (String[] strings : matriz) {
            for (int j = 0; j < strings.length; j++) {
                System.out.print(strings[j] + " ");
            }
            System.out.print("\n");
        }
    }

    private void carregarLabirinto() {
        String arquivo = "C:\\Users\\marlo\\Documents\\GitHub\\intellij\\TPALabirinto\\maze.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String line = br.readLine();
            int i = 0;
            while (line != null) {
                String[] linha = line.split(",");
                matriz.add(linha);
                //matriz.add(line.split(","));
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
