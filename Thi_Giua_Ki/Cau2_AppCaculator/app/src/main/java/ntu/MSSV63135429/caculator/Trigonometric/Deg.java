package ntu.MSSV63135429.caculator.Trigonometric;

public class Deg implements ITrigonometric{
    @Override
    public double Tinh(String str) {
        return Double.parseDouble(str) * Math.PI / 180;
    }
}
