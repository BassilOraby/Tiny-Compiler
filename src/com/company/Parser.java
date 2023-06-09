package com.company;


import java.util.ArrayList;
import static java.lang.Character.isDigit;

public class Parser
{
    ArrayList<String> tok = new ArrayList();
    static int ptr =0;
    static int FoundEndPointer=0;
    private String reserved_words[]={"if","then","else","end","repeat","until","read","write"};
    public static ArrayList<String> cons =new ArrayList();

    public Parser(ArrayList tokens){
        this.tok = tokens;
}

    void equate(String recieved, String string)
    {
        if(string.equals(recieved))
        {

            ++ptr;
            if(recieved.equals("if"))
            {
                if(!(conditionEnd()))
                {
                    System.out.println("end not found");
                    cons.add("end not found");
                }

            }
            else if(recieved.equals("repeat"))
            {
                boolean until_Flag=false;
                until_Flag=Until_Exists();
                if(!(Until_Exists()))
                {
                    System.out.println("repeat not found");
                    cons.add("repeat not found");
                }

            }
            else if(recieved.equals(";"))
            {System.out.println(";");
                cons.add(";");}
            else if (recieved.equals("end"))
            {
                System.out.println("end");
                cons.add("end");
            }
            else if (recieved.equals(")"))
            {
                System.out.println(")");
                cons.add(")");
            }
            else if (recieved.equals(":="))
            {
                System.out.println(":=");
                cons.add(":=");
            }


        }
        else
        {
            if(recieved.equals(";"))
            {
                System.out.println(";");
                cons.add(";");
            }
            else if(recieved.equals(":="))
            {
                System.out.println(":=");
                cons.add(":=");
            }
            else if(recieved.equals("then"))
            {
                System.out.println("then not found");
                cons.add("then not found");
            }
            else if(recieved.equals("end"))
            {
                System.out.println("end not found");
                cons.add("end not found");
            }

        }



    }
    void program()
    {

        System.out.println("program");
        cons.add("program");
        stmt_sequence();

    }
    private void stmt_sequence()
    {

        System.out.println("stmt_sequence");
        cons.add("stmt_sequence");
        statment();
        int i = ptr;
        if(ptr >=tok.size()) return;
        while((this.tok.get(ptr).equals(";") && ptr <tok.size()) || (Trueid(tok.get(ptr -1)) || Truedigit(tok.get(ptr -1)))) // lw kan fe id w mgash b3deeh semi colon yb2a kaml brnamg 3ady w catch el error 3ady gdn,rkz 3al or ly fl nos de
        {
            equate(";",this.tok.get(ptr));
            statment();
            if(ptr <tok.size())
            {
                if(this.tok.get(ptr).equals("until") && !(this.tok.get(ptr -1).equals(";")))
                {
                    System.out.println("; not found");
                    cons.add("; not found");
                    break;
                }
                else if(this.tok.get(ptr).equals("end") && !(this.tok.get(ptr -1).equals(";")))
                {
                    System.out.println("; is not found");
                    cons.add("; is not found");
                    break;
                }
            }
            if(ptr >=tok.size()) break;
            i++;
        }
    }
    private void statment()
    {

        if(ptr < tok.size())
        {
            System.out.println("statment");
            cons.add("statment");
            if(tok.get(ptr).equals("if"))
            {
                System.out.println("if-stmt");
                cons.add("if-stmt");
                if_stmt();

            }
            else if(tok.get(ptr).equals("repeat"))
            {
                System.out.println("repeat-stmt");
                cons.add ("repeat-stmt");
                repeat_stmt();
            }
            else if(tok.get(ptr).equals("read"))
            {
                System.out.println("read-stmt");
                cons.add ("read-stmt");
                read_stmt();

            }
            else if(tok.get(ptr).equals("write"))
            {
                System.out.println("write-stmt");
                cons.add ("write-stmt");
                write_stmt();
            }
            else if(Trueid(this.tok.get(ptr)))
            {
                System.out.println("assign-stmt");
                cons.add ("assign-stmt");
                assign_stmt();
            }
        }
    }
    private void if_stmt()
    {
        equate("if",tok.get(ptr));
        exp();
        if(this.tok.get(ptr).equals("then"))
        {
            System.out.println("then");
            cons.add ("then");
        }

        equate("then",this.tok.get(ptr));
        stmt_sequence();
        if(ptr < tok.size())
        {
            if(this.tok.get(ptr).equals("else"))
            {
                if(this.tok.get(ptr).equals("else"))
                {
                    System.out.println("else");
                    cons.add ("else");
                }
                equate("else",this.tok.get(ptr));
                stmt_sequence();

            }
            equate("end",this.tok.get(ptr));
            if(ptr <tok.size())
                stmt_sequence();
        }
    }
    private void repeat_stmt()
    {
        equate("repeat",tok.get(ptr));
        stmt_sequence();
        if(ptr <this.tok.size())
        {
            if(this.tok.get(ptr).equals("until"))
            {System.out.println("until");
                cons.add ("until");
                equate("until",this.tok.get(ptr));
                exp();
            }
        }

    }
    private void assign_stmt()
    {
        ++ptr;
        System.out.println("ID");
        cons.add ("ID");
        equate(":=",this.tok.get(ptr));
        exp();
    }
    private void read_stmt()
    {
        equate("read",tok.get(ptr));
        if(Trueid(this.tok.get(ptr)))
        {
            ++ptr;
            System.out.println("ID");
            cons.add ("ID");
        }
        else
        {
            System.out.println("Missing Identifier");
            cons.add ("ID not found");
        }

    }
    private void write_stmt()
    {
        equate("write",tok.get(ptr));
        exp();
    }
    private void exp()
    {
        System.out.println("exp");
        cons.add ("exp");
        simpleExpression();
        if(ptr < tok.size())
        {
            if(this.tok.get(ptr).equals("<") || this.tok.get(ptr).equals("=") || this.tok.get(ptr).equals(">") )
            {
                comparisonOperation();
                System.out.println("comparison cons");
                cons.add ("comparison cons");
                simpleExpression();
            }
        }
    }
    private void simpleExpression()
    {

        System.out.println("simple exp");
        cons.add ("simple exp");
        term();

        if(ptr < tok.size())
        {
            while( tok.get(ptr).equals("+") || tok.get(ptr).equals("-"))
            {

                System.out.println("add-cons");
                cons.add ("add-cons");
                addop();
                term();
                // new line
                if(ptr ==tok.size())
                    break;
            }
        }
    }
    private void comparisonOperation()
    {
        if(this.tok.get(ptr).equals("<") || this.tok.get(ptr).equals("=") || this.tok.get(ptr).equals(">"))
        {
            ++ptr;
        }

    }
    private void term()
    {

        System.out.println("term");
        cons.add ("term");
        factor();
        if(ptr < tok.size())
        {
            while(  tok.get(ptr).equals("*") || tok.get(ptr).equals("/"))
            {
                System.out.println("mulop");
                cons.add ("mulop");
                mulop();
                factor();

            }
        }

    }
    private void addop()
    {
        if(this.tok.get(ptr).equals("+") || this.tok.get(ptr).equals("-"))
        {
            ++ptr;
        }

    }
    private void factor()
    {

        System.out.println("factor");
        cons.add ("factor");
        if(this.tok.get(ptr).equals("("))
        {
            System.out.println("(");
            cons.add ("(");
            equate("(",this.tok.get(ptr));
            exp();
            equate(")",this.tok.get(ptr));
        }
        else if(Truedigit(tok.get(ptr)))
        {
            ptr++;
            System.out.println("Number");
            cons.add ("Number");
        }
        else if(Trueid(this.tok.get(ptr)))
        {
            ++ptr;
            System.out.println("ID");
            cons.add ("ID");
        }
        else
        {
            System.out.println("error in factor");
            cons.add ("Error in factor where it wasnt an (exp) nor ID nor Number");
        }

    }
    private void mulop()
    {

        if(this.tok.get(ptr).equals("*") || this.tok.get(ptr).equals("/"))
        {
            ++ptr;
        }

    }
    public boolean Truedigit(String get)
    {
        if(isDigit(get.charAt(0)))
        {
            return true;
        }
        return false;
    }
    public boolean Trueid(String get)
    {
        if(isReserved(get) || Truedigit(get) || isSymbol(get))
        {
            return false;
        }
        else
        {

            return true;
        }
    }
    private boolean isReserved(String get)
    {
        for(int i=0;i<this.reserved_words.length;i++)
        {
            if(get.equals(this.reserved_words[i]))
                return true;
        }
        return false;
    }
    private boolean isSymbol(String get)
    {
        if( "*".equals(get) || "=".equals(get) || ";".equals(get) || "+".equals(get) || "-".equals(get) || " ".equals(get) || "/".equals(get) || "(".equals(get) || ")".equals(get) || ":=".equals(get)|| "<".equals(get)|| "{".equals(get) || "}".equals(get) )
            return true;
        return false;
    }
    private void Finish()
    {    int stop=0;
        while(true)
        {
            stop++;
        }

    }
    private boolean conditionEnd()
    {
        for(int i = ptr; i<this.tok.size(); i++)
        {
            if (this.tok.get(i).equals("end")) // lw l2yt end w de awl mra tl2eha then khodha gher kda la2
            {
                FoundEndPointer = i;
                return true;
            }
        }
        return false;
    }
    private boolean Until_Exists() {
        for(int i = ptr; i<this.tok.size(); i++)
        {
            if (this.tok.get(i).equals("until"))
                return true;
        }
        return false;
    }

    public ArrayList<String> getCons() {
        return cons;
    }

}








