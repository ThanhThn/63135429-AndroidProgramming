package Trigonometric;

public class ContextTrigonometric {
    ITrigonometric trigonometric;

    public void setTrigonometric(ITrigonometric trigonometric){
        this.trigonometric = trigonometric;
    }
    public double Tinh(String str){
        return trigonometric.Tinh(str);
    }
}
