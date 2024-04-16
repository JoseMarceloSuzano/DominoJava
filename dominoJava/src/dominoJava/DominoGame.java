package dominoJava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DominoGame {
    private DominoList computerPieces;
    private DominoList humanPieces;
    private DominoList boardPieces;

    public DominoGame() {
        this.computerPieces = new DominoList();
        this.humanPieces = new DominoList();
        this.boardPieces = new DominoList();
    }

    public void startGame() {
        List<DominoPiece> allPieces = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            for (int j = i; j <= 6; j++) {
                DominoPiece piece = new DominoPiece(i, j);
                allPieces.add(piece);
            }
        }

        Collections.shuffle(allPieces);

        for (int i = 0; i < 28; i++) {
            DominoPiece piece = allPieces.get(i);
            if (i % 2 == 0) {
                computerPieces.add(piece);
            } else {
                humanPieces.add(piece);
            }
        }
    }
    
    public void playGame() {
        while (!isGameOver()) {
            DominoOutput.printBoardPieces("Peças na mesa:", boardPieces);

            playHumanTurn();

            if (!isGameOver()) {
                playComputerTurn();
            }
        }

        DominoOutput.printBoardPieces("Peças na mesa:", boardPieces);
        if (humanPieces.isEmpty()) {
            System.out.println("Parabéns! Você venceu o jogo!");
        } else {
            System.out.println("Você perdeu! O computador venceu o jogo!");
        }
    }

    private void playHumanTurn() {
        DominoOutput.printPieceList("Suas peças:", humanPieces);
        int choice = DominoInput.getIntInput("Escolha a peça que deseja jogar (ou -1 para passar a vez): ");

        if (choice == -1) {
            System.out.println("Você passou a vez.");
        } else {
            DominoPiece piece = humanPieces.get(choice);
            playPiece(piece, humanPieces);
        }
    }

    private void playComputerTurn() {
        DominoPiece computerPiece = chooseComputerPiece();
        playPiece(computerPiece, computerPieces);
    }

    public void playPiece(DominoPiece piece, DominoList playerPieces) {
        
        if (!playerPieces.contains(piece)) {
            System.out.println("Peça inválida. Escolha uma peça válida.");
            return;
        }

        if (boardPieces.isEmpty()) {
            boardPieces.add(piece);
        } else {
            DominoPiece firstPiece = boardPieces.getFirst();
            DominoPiece lastPiece = boardPieces.getLast();
            if (!(piece.getNumber1() == firstPiece.getNumber1() || piece.getNumber2() == firstPiece.getNumber1()
                    || piece.getNumber1() == lastPiece.getNumber2() || piece.getNumber2() == lastPiece.getNumber2())) {
                System.out.println("Peça inválida. Escolha uma peça que possa ser jogada nos extremos.");
                return;
            }

            if (piece.getNumber1() == lastPiece.getNumber2()) {
                boardPieces.addLast(piece);
            } else if (piece.getNumber2() == lastPiece.getNumber2()) {
                piece.flip();
                boardPieces.addLast(piece);
            } else if (piece.getNumber2() == firstPiece.getNumber1()) {
                boardPieces.addFirst(piece);
            } else if (piece.getNumber1() == firstPiece.getNumber1()) {
                piece.flip();
                boardPieces.addFirst(piece);
            } else {
                System.out.println("Peça inválida. Escolha uma peça que possa ser jogada nos extremos.");
                return;
            }
        }

        playerPieces.remove(piece);

        DominoOutput.printBoardPieces("Peças na mesa:", boardPieces);

        if (checkWinner(playerPieces)) {
            System.out.println("Parabéns! Você venceu o jogo!");
            return;
        }

        if (isGameOver()) {
            System.out.println("O jogo acabou. Você venceu!");
            return;
        }

        System.out.println("Vez do computador jogar...");
        DominoPiece computerPiece = chooseComputerPiece();
        System.out.println("O computador jogou a peça: " + computerPiece);
        playComputerPiece(computerPiece, computerPieces);

        if (isGameOver()) {
            System.out.println("O jogo acabou. O computador venceu!");
            return;
        }
    }

    public void playComputerPiece(DominoPiece piece, DominoList computerPieces) {
        if (piece == null) {
            System.out.println("O computador não tem peças para jogar. Passando a vez...");
            passTurn(computerPieces);
            return;
        }

        if (boardPieces.isEmpty()) {
            boardPieces.add(piece);
        } else {
            DominoPiece firstPiece = boardPieces.getFirst();
            DominoPiece lastPiece = boardPieces.getLast();
            if (piece.getNumber1() == lastPiece.getNumber2()) {
                boardPieces.addLast(piece);
            } else if (piece.getNumber1() == firstPiece.getNumber1()) {
                piece.flip();
                boardPieces.addFirst(piece);
            } else if (piece.getNumber2() == firstPiece.getNumber1()) {
                boardPieces.addFirst(piece);
            } else if (piece.getNumber2() == lastPiece.getNumber2()) {
                piece.flip();
                boardPieces.addLast(piece);
            } else {
                System.out.println("Peça inválida. Escolha uma peça que possa ser jogada nos extremos.");
                return;
            }
        }

        computerPieces.remove(piece);

        DominoOutput.printBoardPieces("Peças na mesa:", boardPieces);
    }


    private DominoPiece chooseComputerPiece() {
        if (boardPieces.isEmpty()) {
            return computerPieces.getFirst();
        }

        DominoPiece firstPiece = boardPieces.getFirst();
        DominoPiece lastPiece = boardPieces.getLast();
        int firstValue = firstPiece.getNumber1();
        int lastValue = lastPiece.getNumber2();

        for (DominoPiece piece : computerPieces) {
            if (piece.getNumber1() == firstValue || piece.getNumber2() == firstValue) {
                if (piece.getNumber1() != firstValue) {
                    piece.flip();
                }
                return piece;
            } else if (piece.getNumber1() == lastValue || piece.getNumber2() == lastValue) {
                if (piece.getNumber2() != lastValue) {
                    piece.flip();
                }
                return piece;
            }
        }

        return null;
    }

    public boolean passTurn(DominoList playerPieces) {
        if (!playerPieces.isEmpty()) {
            System.out.println("Você ainda tem peças disponíveis para jogar. Não pode passar a vez.");
            return false;
        }
        return true;
    }

    private boolean checkWinner(DominoList playerPieces) {
        return playerPieces.isEmpty();
    }

    public DominoList getHumanPieces() {
        return humanPieces;
    }

    public DominoList getComputerPieces() {
        return computerPieces;
    }

    public DominoList getBoardPieces() {
        return boardPieces;
    }

    public boolean isGameOver() {
        return computerPieces.isEmpty() || humanPieces.isEmpty();
    }
}