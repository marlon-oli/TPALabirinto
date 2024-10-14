

public class Application {
    public static void main(String[] args) {
        Labirinto labirinto = new Labirinto();

        labirinto.mostrarLabirinto();

        labirinto.encontrarSolucao();

        System.out.println("---------------------");
        labirinto.mostrarCaminhoSolucao();
        labirinto.mostrarLabirinto();
    }
}
