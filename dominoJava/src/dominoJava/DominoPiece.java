package dominoJava;

	public class DominoPiece {
    private int number1;
    private int number2;

    public DominoPiece(int number1, int number2) {
        this.number1 = number1;
        this.number2 = number2;
    }

    public int getNumber1() {
        return number1;
    }

    public int getNumber2() {
        return number2;
    }

    public void flip() {
        int temp = number1;
        number1 = number2;
        number2 = temp;
    }

    @Override
    public String toString() {
        return "[" + number1 + "|" + number2 + "]";
    }
}

