package com.taras.tests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import javax.swing.table.DefaultTableModel;
import static com.taras.main.Evaluate.evaluate;
import static com.taras.main.Evaluate.transform;



class EvTest {
    @org.junit.jupiter.api.Test
    public static void unitTest1(){
        String test = "1+-10divnmin(4,-4,8)";
        System.out.println(test);
        test = String.valueOf(evaluate(test));
        String answer = "3.0";
        System.out.println("Real answer: " + test + " Expected answer: " + answer);
        Assertions.assertEquals(answer, test);
    }
    @org.junit.jupiter.api.Test
    public static void unitTest2(){

        DefaultTableModel dm = new DefaultTableModel(5, 5);
        dm.setValueAt(50.0, 2, 2);
        try {
            System.out.println("nmax(5,7,20,9,#C3)");
           String test = transform("nmax(5,7,20,9,#C3)", dm);
            test = String.valueOf(evaluate(test));

            String answer = "50.0";
            System.out.println("Real answer: " + test + " Expected answer: " + answer);
            Assertions.assertEquals(answer, test);
        }
        catch (Exception ex){
            Assertions.assertEquals("1", "0");
        }
    }
    @org.junit.jupiter.api.Test
    public static void unitTest3(){
        String test = "(4+abc)*8";
        System.out.println(test);
        try {
            test = String.valueOf(evaluate(test));
        }
        catch (Exception ex){
            test = "ERROR";
        }
        String answer = "ERROR";
        System.out.println("Real answer: " + test + " Expected answer: " + answer);
        Assertions.assertEquals(answer, test);
    }
    @org.junit.jupiter.api.Test
    public static void unitTest4(){
        String test = "1--16divnmax(4,-4,8)";
        System.out.println(test);
        test = String.valueOf(evaluate(test));
        String answer = "3.0";
        System.out.println("Real answer: " + test + " Expected answer: " + answer);
        Assertions.assertEquals(answer, test);
    }
    @org.junit.jupiter.api.Test
    public static void unitTest5(){
        String test = "1 - - (32 mod nmin(-4,25,0))";
        System.out.println(test);
        test = String.valueOf(evaluate(test));
        String answer = "1.0";
        System.out.println("Real answer: " + test + " Expected answer: " + answer);
        Assertions.assertEquals(answer, test);
    }
    public static void unitTest6(){

        DefaultTableModel dm = new DefaultTableModel(5, 5);
        dm.setValueAt(0.0, 2, 3);
        dm.setValueAt(51.0, 1, 1);
        dm.setValueAt(-8, 4, 4);

        try {
            String test = transform("nmax(#D3,#B2,#E5)+nmin(#D3,#B2,#E5)div(-#E5)", dm);
            System.out.println(test);
            test = String.valueOf(evaluate(test));
            String answer = "48.0";
            System.out.println("Real answer: " + test + " Expected answer: " + answer);
            Assertions.assertEquals(answer, test);
        }
        catch (Exception ex){
            Assertions.assertEquals("1", "0");
        }
    }
    public static void main(String[] args) {
        unitTest1();
        unitTest2();
        unitTest3();
        unitTest4();
        unitTest5();
        unitTest6();
        System.out.println("Tests completed successfully");
    }
}