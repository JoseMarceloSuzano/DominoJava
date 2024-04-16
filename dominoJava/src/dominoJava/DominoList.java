package dominoJava;

import java.util.LinkedList;

public class DominoList extends LinkedList<DominoPiece> {
    public DominoPiece findPiece(int number1, int number2) {
        for (DominoPiece piece : this) {
            if ((piece.getNumber1() == number1 && piece.getNumber2() == number2) ||
                (piece.getNumber1() == number2 && piece.getNumber2() == number1)) {
                return piece;
            }
        }
        return null;
    }

    public boolean containsPiece(int number1, int number2) {
        for (DominoPiece piece : this) {
            if ((piece.getNumber1() == number1 && piece.getNumber2() == number2) ||
                (piece.getNumber1() == number2 && piece.getNumber2() == number1)) {
                return true;
            }
        }
        return false;
    }
}

