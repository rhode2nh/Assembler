import java.io.*;
import java.util.Scanner;

/**
 * Created by Nick on 1/13/2017.
 */
public class BetterAssembler {
    public Scanner input;
    public String command;
    public String comment;

    public BetterAssembler() throws Exception{
        input = new Scanner(new File("PongL.asm"));
        comment = input.nextLine();
    }

    public static void main(String[] args) throws Exception{
        BetterAssembler betterAssembler = new BetterAssembler();
        BetterAssembler betterAssembler1 = new BetterAssembler();
        SymbolTable symbolTable = new SymbolTable();
        Code code = new Code();

        StringBuilder cCommand = new StringBuilder();
        File file = new File("Prog.hack");

        String commandType;
        String dest;
        String comp;
        String jump;

        int romAddress = 0;
        int ramAddres = 16;

        //Add predefined symbols
        symbolTable.addEntry("SP", 0);
        symbolTable.addEntry("LCL", 1);
        symbolTable.addEntry("ARG", 2);
        symbolTable.addEntry("THIS", 3);
        symbolTable.addEntry("THAT", 4);
        symbolTable.addEntry("R0", 0);
        symbolTable.addEntry("R1", 1);
        symbolTable.addEntry("R2", 2);
        symbolTable.addEntry("R3", 3);
        symbolTable.addEntry("R4", 4);
        symbolTable.addEntry("R5", 5);
        symbolTable.addEntry("R6", 6);
        symbolTable.addEntry("R7", 7);
        symbolTable.addEntry("R8", 8);
        symbolTable.addEntry("R9", 9);
        symbolTable.addEntry("R10", 10);
        symbolTable.addEntry("R11", 11);
        symbolTable.addEntry("R12", 12);
        symbolTable.addEntry("R13", 13);
        symbolTable.addEntry("R14", 14);
        symbolTable.addEntry("R15", 15);
        symbolTable.addEntry("SCREEN", 16384);
        symbolTable.addEntry("KBD", 24576);

        if(!file.exists()){
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);



        while(betterAssembler1.commentCheck()){
            betterAssembler1.comment = betterAssembler1.input.nextLine();
        }

        while(betterAssembler1.hasMoreCommand()) {
            betterAssembler1.advance();
            commandType = betterAssembler1.commandType();

            //Adds symbol to hashtable
            if (commandType.equals("L_COMMAND")) {
                betterAssembler1.command = betterAssembler1.command.substring
                        (1, betterAssembler1.command
                        .length() - 1);
                symbolTable.addEntry(betterAssembler1.command, romAddress);
            } else if (commandType.equals("A_COMMAND")) {
                ++romAddress;
            } else if (commandType.equals("C_COMMAND")) {
                ++romAddress;
            }

        }

        //Gets rid of empty space and comments
        while(betterAssembler.commentCheck()){
            betterAssembler.comment = betterAssembler.input.nextLine();
        }

        //Prints commands
        while(!betterAssembler.comment.equals("")){
            betterAssembler.advance();
            commandType = betterAssembler.commandType();
            if(commandType.equals("A_COMMAND")) {
                commandType = betterAssembler.symbol();
                if(betterAssembler.isInteger(commandType) == false){
                    if(symbolTable.contains(commandType)){
                        commandType = Integer.toString(symbolTable.getAddress
                                (commandType));
                        commandType = String.format("%16s", Integer
                                .toBinaryString(Integer.parseInt(commandType))).replace(' ', '0');
                        bw.write(commandType);
                        bw.newLine();
                    }
                    else if(symbolTable.contains(commandType) == false){
                        symbolTable.addEntry(commandType, ramAddres);
                        ++ramAddres;
                        commandType = Integer.toString(symbolTable.getAddress
                                (commandType));
                        commandType = String.format("%16s", Integer
                                .toBinaryString(Integer.parseInt(commandType))).replace(' ', '0');
                        bw.write(commandType);
                        bw.newLine();
                    }
                }
                else {
                    commandType = String.format("%16s", Integer
                            .toBinaryString(Integer.parseInt(commandType))).replace(' ', '0');
                    bw.write(commandType);
                    bw.newLine();
                }
            }

            else if(commandType.equals("C_COMMAND")){
                cCommand.append("111");
                dest = betterAssembler.dest();
                comp = betterAssembler.comp();
                jump = betterAssembler.jump();
                cCommand.append(code.comp(comp));
                cCommand.append(code.dest(dest));
                cCommand.append(code.jump(jump));
                bw.write(cCommand.toString());
                bw.newLine();
                cCommand.delete(0, cCommand.length());
            }
        }


        betterAssembler.input.close();
        bw.close();
    }

    //Checks for comments
    public boolean commentCheck() {
        if(comment.contains("//") || comment.length() == 0)
            return true;
        else return false;
    }

    //Checks for command
    public boolean hasMoreCommand(){
        return input.hasNextLine();
    }

    //If there is another command, advance
    public void advance(){
        command = comment;
        try {
            comment = input.nextLine();
        }catch(Exception e){
            command = comment;
            comment = "";
        }
    }

    //Checks if memory address has symbol
    public boolean isInteger(String check){
        try {
            Integer.parseInt(check);
        } catch (NumberFormatException e){
            return false;
        } catch (NullPointerException e){
            return false;
        }
        return true;
    }

    //Determines type of command
    public String commandType(){
        command = command.replaceAll("\\s", "");
        if(command.substring(0, 1).equals("@")){
            return "A_COMMAND";
        }
        else if(command.contains("+") || command.contains("-") || command
                .contains("=") || command.contains(";")){
            return "C_COMMAND";
        }
        else if(command.contains("(")){
            return "L_COMMAND";
        }
        else return null;
    }

    //Returns symbol or decimal
    public String symbol(){
        command = command.replaceAll("\\s", "");
        return command.substring(1);
    }

    public String dest() {
        command = command.replaceAll("\\s", "");
        if(command.contains(";")){
            return "";
        }
        else if(command.contains("MD")){
            return command.substring(0, 2);
        }
        else if(command.contains("AM")){
            return command.substring(0, 2);
        }
        else if(command.contains("AD")){
            return command.substring(0, 2);
        }
        else if(command.contains("AMD")){
            return command.substring(0, 3);
        }
        else return command.substring(0, 1);
    }

    public String comp(){
        command = command.split("/")[0];
        if(command.contains(";"))
            return command.substring(0, 1);
        else if(command.contains("MD")){
            return command.substring(3);
        }
        else if(command.contains("AM")){
            return command.substring(3);
        }
        else if(command.contains("AD")){
            return command.substring(3);
        }
        else if(command.contains("AMD")){
            return command.substring(4);
        }
        else return command.substring(2);
    }

    public String jump(){
        command = command.replaceAll("\\s", "");
        command = command.split("/")[0];
        return command.substring(2);
    }

}
