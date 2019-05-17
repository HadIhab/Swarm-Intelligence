package models;

public class Clause {
	private int Literal1, Literal2, Literal3;

    public Clause(int literal1, int literal2, int literal3) {
        super();
        Literal1 = literal1;
        Literal2 = literal2;
        Literal3 = literal3;
    }

    public int getLiteral1() {
        return Literal1;
    }

    public int getLiteral2() {
        return Literal2;
    }

    public int getLiteral3() {
        return Literal3;
    }

    public int getLiteral(int i) {
        switch (i) {
            case 1:
                return Literal1;
            case 2:
                return Literal2;
            case 3:
                return Literal3;
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        return Literal1 + " " + Literal2 + " " + Literal3;
    }
}
