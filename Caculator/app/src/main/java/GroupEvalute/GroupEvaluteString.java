package GroupEvalute;

import Evaluate.EvaluateString;

public class GroupEvaluteString {
    String expresion;
    char[] tokens;

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
                continue;
            }
            if (tokens[i] == ')'){
                newExpersion.append(EvaluateString.evaluate(expersionGroup.toString()));
                check = false;
                continue;
            }
            if(check){
                expersionGroup.append(tokens[i]);
            }else {
                newExpersion.append(tokens[i]);
            }
        }
        return EvaluateString.evaluate(newExpersion.toString());
    }
}
