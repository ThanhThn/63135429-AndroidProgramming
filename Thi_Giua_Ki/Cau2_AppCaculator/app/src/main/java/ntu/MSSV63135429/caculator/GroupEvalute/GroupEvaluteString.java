package ntu.MSSV63135429.caculator.GroupEvalute;


import ntu.MSSV63135429.caculator.Evaluate.EvaluateString;
import ntu.MSSV63135429.caculator.Trigonometric.ContextTrigonometric;
import ntu.MSSV63135429.caculator.Trigonometric.Cos;
import ntu.MSSV63135429.caculator.Trigonometric.Deg;
import ntu.MSSV63135429.caculator.Trigonometric.Sin;

public class GroupEvaluteString {
    String expresion;
    char[] tokens;
    Double exper;
    ContextTrigonometric context = new ContextTrigonometric();
    boolean sin = false, cos = false, deg = false;
    public GroupEvaluteString(String expresion) {
        this.expresion = expresion;
        tokens = expresion.toCharArray();
    }
    public Double evaluate(){
        StringBuilder newExpersion = new StringBuilder();
        boolean check = false;
        StringBuffer expersionGroup = new StringBuffer();
        for(int i  = 0; i < tokens.length; i++){
            if(tokens[i] == '('){
                expersionGroup.setLength(0);
                check = true;
                if(i > 0 && tokens[i - 1] == 's'){
                    cos = true;
                }else if (i > 0 && tokens[i - 1] == 'n'){
                    sin = true;
                }
                continue;
            }
            if (tokens[i] == ')'){
                exper = EvaluateString.evaluate(expersionGroup.toString());
                if(i < tokens.length - 1 && tokens[i + 1] == 'd'){ deg = true;}
                if (sin){
                    context.setTrigonometric(new Sin());
                    exper = context.Tinh(exper.toString());
                    sin = false;
                }
                if (cos){
                    context.setTrigonometric(new Cos());
                    exper = context.Tinh(exper.toString());
                    cos = false;
                }
                if (deg){
                    context.setTrigonometric(new Deg());
                    exper = context.Tinh(exper.toString());
                    deg = false;
                }
                newExpersion.append(exper);
                check = false;
                continue;
            }
            if(check){
                expersionGroup.append(tokens[i]);
            }else{
                switch (tokens[i]){
                    case 's':
                    case 'c':
                    case 'd':
                        i += 2;
                        break;
                    default: newExpersion.append(tokens[i]);
                }
            }
        }
        return EvaluateString.evaluate(newExpersion.toString());
    }
}
