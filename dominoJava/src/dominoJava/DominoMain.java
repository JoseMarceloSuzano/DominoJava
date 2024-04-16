package dominoJava;

public class DominoMain {
	public static void main(String[] args) {
        DominoGame game = new DominoGame();
        game.startGame();

        while (!game.isGameOver()) {
            DominoMenu.printMenu();

            int choice = DominoInput.getIntInput("Escolha uma opção: ");

            switch (choice) {
                case 1:
                    playPiece(game);
                    break;
                case 2:
                    passTurn(game);
                    break;
                case 3:
                    System.out.println("Saindo do jogo...");
                    return; 
                default:
                    System.out.println("Opção inválida. Escolha uma opção válida.");
                    break;
            }
        }
    }

    private static void playPiece(DominoGame game) {
        DominoOutput.printPieceList("Suas peças:", game.getHumanPieces());

        int choice = DominoInput.getIntInput("Escolha a peça que deseja jogar (ou -1 para passar a vez): ");

        if (choice == -1) {
            passTurn(game);
            return;
        }

        DominoPiece piece = game.getHumanPieces().get(choice);
        game.playPiece(piece, game.getHumanPieces());
    }


    private static void passTurn(DominoGame game) {
        if (!game.passTurn(game.getHumanPieces())) {
            System.out.println("Não é possível passar a vez.");
        }
    }
}
