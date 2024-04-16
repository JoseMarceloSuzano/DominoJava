package dominoJava;

public class DominoOutput {
    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printPieceList(String header, DominoList pieces) {
        System.out.println(header);
        for (int i = 0; i < pieces.size(); i++) {
            DominoPiece piece = pieces.get(i);
            System.out.println("[" + i + "] " + piece.toString());
        }
    }

    public static void printBoardPieces(String header, DominoList boardPieces) {
        System.out.println(header);
        for (DominoPiece piece : boardPieces) {
            System.out.print(piece.toString() + " ");
        }
        System.out.println();
    }
}

