package Trigonometric;

public class Cos implements ITrigonometric{
    @Override
    public double Tinh(String str) {
        return Math.cos(Double.parseDouble(str));
    }
}
