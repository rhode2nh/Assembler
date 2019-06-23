import java.util.Objects;

/**
 * Created by Nick on 1/14/2017.
 */
public class Code {

    public String comp(String cComp){
        if(Objects.equals(cComp, "0")){
            return "0101010";
        }
        else if(cComp.equals("1")){
            return "0111111";
        }
        else if(Objects.equals(cComp, "-1")){
            return "0111010";
        }
        else if(Objects.equals(cComp, "D")){
            return "0001100";
        }
        else if(Objects.equals(cComp, "A")){
            return "0110000";
        }
        else if(Objects.equals(cComp, "!D")){
            return "0001101";
        }
        else if(Objects.equals(cComp, "!A")){
            return "0110001";
        }
        else if(Objects.equals(cComp, "-D")){
            return "0001111";
        }
        else if(Objects.equals(cComp, "-A")){
            return "0110011";
        }
        else if(Objects.equals(cComp, "D+1")){
            return "0011111";
        }
        else if(Objects.equals(cComp, "A+1")){
            return "0110111";
        }
        else if(Objects.equals(cComp, "D-1")){
            return "0001110";
        }
        else if(Objects.equals(cComp, "A-1")){
            return "0110010";
        }
        else if(Objects.equals(cComp, "D+A")){
            return "0000010";
        }
        else if(Objects.equals(cComp, "D-A")){
            return "0010011";
        }
        else if(Objects.equals(cComp, "A-D")){
            return "0010011";
        }
        else if(Objects.equals(cComp, "D|M")){
            return "1010101";
        }
        else if(Objects.equals(cComp, "D|A")){
            return "0010101";
        }
        else if(Objects.equals(cComp, "M")){
            return "1110000";
        }
        else if(Objects.equals(cComp, "!M")){
            return "1110001";
        }
        else if(Objects.equals(cComp, "-M")){
            return "1110011";
        }
        else if(Objects.equals(cComp, "M+1")){
            return "1110111";
        }
        else if(Objects.equals(cComp, "M-1")){
            return "1110010";
        }
        else if(Objects.equals(cComp, "D+M")){
            return "1000010";
        }
        else if(Objects.equals(cComp, "D-M")){
            return "1010011";
        }
        else if(Objects.equals(cComp, "M-D")){
            return "1000111";
        }
        else if(Objects.equals(cComp, "D&M")){
            return "1000000";
        }
        else return "0000000";


    }

    public String dest(String dDest){
        if(Objects.equals(dDest, "M")){
            return "001";
        }
        else if(Objects.equals(dDest, "D")){
            return "010";
        }
        else if(Objects.equals(dDest, "MD")){
            return "011";
        }
        else if(Objects.equals(dDest, "A")){
            return "100";
        }
        else if(Objects.equals(dDest, "AM")){
            return "101";
        }
        else if(Objects.equals(dDest, "AD")){
            return "110";
        }
        else if(Objects.equals(dDest, "AMD")){
            return "111";
        }
        else return "000";
    }

    public String jump(String jJump){
        if(jJump.equals("JGT")){
            return "001";
        }
        else if(jJump.equals("JEQ")){
            return "010";
        }
        else if(jJump.equals("JGE")){
            return "011";
        }
        else if(jJump.equals("JLT")){
            return "100";
        }
        else if(jJump.equals("JNE")){
            return "101";
        }
        else if(jJump.equals("JLE")){
            return "110";
        }
        else if(jJump.equals("JMP")){
            return "111";
        }
        else return "000";
    }

}
