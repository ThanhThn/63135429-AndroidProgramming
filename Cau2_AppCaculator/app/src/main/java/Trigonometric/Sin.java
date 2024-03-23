package Trigonometric;


public class Sin implements ITrigonometric{
    @Override
    public double Tinh(String str) {
        return Math.sin(Double.parseDouble(str));
    }
}
