package com.company;
import org.graphstream.algorithm.generator.BananaTreeGenerator;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.View;
import scala.xml.dtd.Tokens;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;
import org.graphstream.stream.SourceBase.ElementType;
import scala.Char;

public class scanner {
    public static int c = 64, s = 96, count=0;
    public static int sx=5,sy=15;
    public static char cc, ss;
    public static ArrayList<String> reservedWords = new ArrayList<String>();
    public static ArrayList<String> Specialsym = new ArrayList<String>();
    public static List<Character> CharactersCap = new ArrayList<Character>();
    public static List<Character> CharactersSm = new ArrayList<Character>();
    public static Parser parser;
    // public static Parsetree parsetree = new Parsetree();


    private static void init() {
        reservedWords.add("if");
        reservedWords.add("then");
        reservedWords.add("read");
        reservedWords.add("else");
        reservedWords.add("end");
        reservedWords.add("repeat");
        reservedWords.add("until");
        reservedWords.add("write");
        Specialsym.add("+");
        Specialsym.add("-");
        Specialsym.add("*");
        Specialsym.add("/");
        Specialsym.add("=");
        Specialsym.add("<");
        Specialsym.add("(");
        Specialsym.add(")");
        Specialsym.add(";");
        Specialsym.add(":=");
        Specialsym.add(":");
        for (int k = 0; k < 27; k++) {
            c += 1;
            s += 1;
            cc = (char) c;
            ss = (char) s;
            CharactersCap.add(cc);
            CharactersSm.add(ss);
        }
    }

    public Parser getParser() {
        return parser;
    }

    public static void main(String[] args) throws IOException {
        init();
        ArrayList<String> Tokens = new ArrayList<String>();
        File file = new File("tiny.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        BufferedWriter bw = new BufferedWriter(new FileWriter("in.txt"));
        BufferedWriter bwp = new BufferedWriter(new FileWriter("Parser_output.txt"));
        StringBuilder Comment = new StringBuilder();
        StringBuilder num = new StringBuilder();
        StringBuilder st = new StringBuilder();
        String x;
        char[] y;
        int i = 0;
        while ((x = bufferedReader.readLine()) != null) {
            y = x.toCharArray();
            while (i < y.length) {
                //System.out.println('T');
                switch (y[i]) {
                    case '{':
                        Comment.delete(0, Comment.length());
                        while (y[i] != '}') {
                            if (y[i] == '{') {
                                i += 1;
                                continue;
                            }
                            Comment.append(y[i]);
                            i += 1;
                        }
                        i += 1;
                        bw.write("Comment:" + Comment);
                        bw.newLine();
                        break;
                    case ' ':
                        //System.out.println("Delimiter !!");
                        i += 1;
                        break;
                    case '\t':
                        i += 1;
                        break;
                    case '+':
                        bw.write("Special Symbol:" + y[i]);
                        bw.newLine();
                        Tokens.add(Character.toString(y[i]));
                        i += 1;
                        break;
                    case '-':
                        bw.write("Special Symbol:" + y[i]);
                        bw.newLine();
                        Tokens.add(Character.toString(y[i]));
                        i += 1;
                        break;
                    case '*':
                        bw.write("Special Symbol:" + y[i]);
                        bw.newLine();
                        Tokens.add(Character.toString(y[i]));
                        i += 1;
                        break;
                    case '/':
                        bw.write("Special Symbol:" + y[i]);
                        bw.newLine();
                        Tokens.add(Character.toString(y[i]));
                        i += 1;
                        break;
                    case '=':
                        bw.write("Special Symbol:" + y[i]);
                        bw.newLine();
                        Tokens.add(Character.toString(y[i]));
                        i += 1;
                        break;
                    case '<':
                        bw.write("Special Symbol:" + y[i]);
                        bw.newLine();
                        Tokens.add(Character.toString(y[i]));
                        i += 1;
                        break;
                    case '(':
                        bw.write("Special Symbol:" + y[i]);
                        bw.newLine();
                        Tokens.add(Character.toString(y[i]));
                        i += 1;
                        break;
                    case ')':
                        bw.write("Special Symbol:" + y[i]);
                        bw.newLine();
                        Tokens.add(Character.toString(y[i]));
                        i += 1;
                        break;
                    case ';':
                        bw.write("Special Symbol:" + y[i]);
                        bw.newLine();
                        Tokens.add(Character.toString(y[i]));
                        i += 1;
                        break;
                    case ':':
                        bw.write("Special Symbol:" + y[i]);
                        bw.newLine();
                        Tokens.add(":=");
                        i += 2;
                        break;
                    case '0':
                        num.delete(0, num.length());
                        while (y[i] != ' ' && i < y.length && !Specialsym.contains(String.valueOf(y[i])) && !CharactersCap.contains(y[i]) && !CharactersSm.contains(y[i])) {
                            num.append(y[i]);
                            i += 1;
                            if ((i - 1) == (y.length - 1))
                                break;
                        }
                        bw.write("Number:" + num);
                        bw.newLine();
                        Tokens.add(num.toString());
                        break;
                    case '1':
                        num.delete(0, num.length());
                        while (y[i] != ' ' && i < y.length && !Specialsym.contains(String.valueOf(y[i])) && !CharactersCap.contains(y[i]) && !CharactersSm.contains(y[i])) {
                            num.append(y[i]);
                            i += 1;
                            if ((i - 1) == (y.length - 1))
                                break;
                        }
                        bw.write("Number:" + num);
                        bw.newLine();
                        Tokens.add(num.toString());
                        break;
                    case '2':
                        num.delete(0, num.length());
                        while (y[i] != ' ' && i < y.length && !Specialsym.contains(String.valueOf(y[i])) && !CharactersCap.contains(y[i]) && !CharactersSm.contains(y[i])) {
                            num.append(y[i]);
                            i += 1;
                            if ((i - 1) == (y.length - 1))
                                break;
                        }
                        bw.write("Number:" + num);
                        bw.newLine();
                        Tokens.add(num.toString());
                        break;
                    case '3':
                        num.delete(0, num.length());
                        while (y[i] != ' ' && i < y.length && !Specialsym.contains(String.valueOf(y[i])) && !CharactersCap.contains(y[i]) && !CharactersSm.contains(y[i])) {
                            num.append(y[i]);
                            i += 1;
                            if ((i - 1) == (y.length - 1))
                                break;
                        }
                        bw.write("Number:" + num);
                        bw.newLine();
                        Tokens.add(num.toString());
                        break;
                    case '4':
                        num.delete(0, num.length());
                        while (y[i] != ' ' && i < y.length && !Specialsym.contains(String.valueOf(y[i])) && !CharactersCap.contains(y[i]) && !CharactersSm.contains(y[i])) {
                            num.append(y[i]);
                            i += 1;
                            if ((i - 1) == (y.length - 1))
                                break;
                        }
                        bw.write("Number:" + num);
                        bw.newLine();
                        Tokens.add(num.toString());
                        break;
                    case '5':
                        num.delete(0, num.length());
                        while (y[i] != ' ' && i < y.length && !Specialsym.contains(String.valueOf(y[i])) && !CharactersCap.contains(y[i]) && !CharactersSm.contains(y[i])) {
                            num.append(y[i]);
                            i += 1;
                            if ((i - 1) == (y.length - 1))
                                break;
                        }
                        bw.write("Number:" + num);
                        bw.newLine();
                        Tokens.add(num.toString());
                        break;
                    case '6':
                        num.delete(0, num.length());
                        while (y[i] != ' ' && i < y.length && !Specialsym.contains(String.valueOf(y[i])) && !CharactersCap.contains(y[i]) && !CharactersSm.contains(y[i])) {
                            num.append(y[i]);
                            i += 1;
                            if ((i - 1) == (y.length - 1))
                                break;
                        }
                        bw.write("Number:" + num);
                        bw.newLine();
                        Tokens.add(num.toString());
                        break;
                    case '7':
                        num.delete(0, num.length());
                        while (y[i] != ' ' && i < y.length && !Specialsym.contains(String.valueOf(y[i])) && !CharactersCap.contains(y[i]) && !CharactersSm.contains(y[i])) {
                            num.append(y[i]);
                            i += 1;
                            if ((i - 1) == (y.length - 1))
                                break;
                        }
                        bw.write("Number:" + num);
                        bw.newLine();
                        Tokens.add(num.toString());
                        break;
                    case '8':
                        num.delete(0, num.length());
                        while (y[i] != ' ' && i < y.length && !Specialsym.contains(String.valueOf(y[i])) && !CharactersCap.contains(y[i]) && !CharactersSm.contains(y[i])) {
                            num.append(y[i]);
                            i += 1;
                            if ((i - 1) == (y.length - 1))
                                break;
                        }
                        bw.write("Number:" + num);
                        bw.newLine();
                        Tokens.add(num.toString());
                        break;
                    case '9':
                        num.delete(0, num.length());
                        while (y[i] != ' ' && i < y.length && !Specialsym.contains(String.valueOf(y[i])) && !CharactersCap.contains(y[i]) && !CharactersSm.contains(y[i])) {
                            num.append(y[i]);
                            i += 1;
                            if ((i - 1) == (y.length - 1))
                                break;
                        }
                        bw.write("Number:" + num);
                        bw.newLine();
                        Tokens.add(num.toString());
                        break;
                    default:
                        st.delete(0, st.length());
                        /*--Letters Section--*/
                        while (y[i] != ' ' && i < y.length && !Specialsym.contains(String.valueOf(y[i]))) {
                            //System.out.println("in");
                            st.append(y[i]);
                            //System.out.println(y[i]);
                            i += 1;
                            if ((i - 1) == (y.length - 1))
                                break;
                        }
                        if (reservedWords.contains(String.valueOf(st))) {
                            bw.write("ReservedWord:" + st);
                            bw.newLine();
                            Tokens.add(st.toString());
                            break;
                        } else {
                            bw.write("Identifier:" + st);
                            bw.newLine();
                            Tokens.add(st.toString());
                            break;
                        }
                }
            }
            i = 0;
        }
        bw.flush();
        bw.close();
        parser = new Parser(Tokens);
        parser.program();
        for (String s : parser.getCons()) {
            bwp.write(s);
            bwp.newLine();
        }
        bwp.flush();
        bwp.close();
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        Graph graph = new SingleGraph("ParseTree", false, true);
        graph.setAutoCreate(true);
        graph.setStrict(false);
        Node A =null,B = null;
        Viewer viewer = graph.display(false);
        viewer.disableAutoLayout();
        View view = viewer.getDefaultView();
        ((ViewPanel) view).resizeFrame(1920,1080);
        view.getCamera().setViewCenter(440000,2503000,0);
        view.getCamera().setViewPercent(0.25);
        graph.setAttribute("ui.stylesheet", "node {\n" +
                "\n" +
                "fill-color: #EEE; stroke-mode: plain; stroke-color: #333;" +
                "        size: 100px, 50px;\n" +
                "  text-size: 15;  }");
        while (!parser.getCons().isEmpty()) {
            if (reservedWords.contains(parser.getCons().get(0)) || Specialsym.contains(parser.getCons().get(0)) || parser.getCons().get(0).equalsIgnoreCase("Number") || parser.getCons().get(0).equalsIgnoreCase("ID")) {
                graph.addNode(Integer.toString(++count)+" "+parser.getCons().get(0));
                B = graph.addNode(Integer.toString(++count)+" "+parser.getCons().get(0));
                B.addAttribute("ui.style", "shape: circle; fill-color: white; stroke-mode: plain; stroke-color: #333;");
                graph.addEdge(A.getId()+B.getId(),A,B,true);
                parser.getCons().remove(0);
            } else {
                A = graph.addNode(Integer.toString(++count)+" "+parser.getCons().get(0));
                A.addAttribute("ui.style", "shape: box; fill-color: red; stroke-mode: plain; stroke-color: #333;");
                parser.getCons().remove(0); }
        }
        for (Node node : graph) {
            node.addAttribute("ui.label", node.getId()); // en label el node hwa el ana katbu fel instances.
        }
        graph.display();
    }
}
