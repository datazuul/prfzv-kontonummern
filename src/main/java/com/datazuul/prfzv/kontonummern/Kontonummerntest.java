package com.datazuul.prfzv.kontonummern;

public class Kontonummerntest {

  public static void main(String[] args) {
    // Nichtvorhandenes Verfahren
    try {
      System.out.println("Prüfung des Verfahrens: " + args[0] + " für Kontonummer " + args[1] + ".");
      System.out.println("And the result is " + PRFZVFactory.createInstance(args[0]).check(args[1]));
      /*
	    //System.out.println("Falsche Verfahrenskennung: " +  PRFZVFactory.createInstance("00").check("0290545005") + "\n");
            // Nichtnumerisches Zeichen in der Kontonummer
            //System.out.println("Kontonummer mit Char: "  + PRFZVFactory.createInstance("00").check("0A90545005"));
            // Verfahren 00
            System.out.println("\n*** Verfahren 00 ***");
            System.out.println("Richtige Kontonummer: "+ PRFZVFactory.createInstance("00").check("9290701"));
            System.out.println("Andere richtige Kontonummer: " + PRFZVFactory.createInstance("00").check("539290858"));
            System.out.println("Falsche Kontonummer: " + PRFZVFactory.createInstance("00").check("555"));
          
            // Verfahren 06
            System.out.println("\n*** Verfahren 06 ***");
            System.out.println("Richtige Kontonummer: "+ PRFZVFactory.createInstance("06").check("94012341"));
            System.out.println("Andere richtige Kontonummer: " + PRFZVFactory.createInstance("06").check("5073321010"));
            System.out.println("Falsche Kontonummer: " + PRFZVFactory.createInstance("06").check("94012342"));
            // Verfahren 09
            System.out.println("\n*** Verfahren 09 ***");
            System.out.println("Derzeit erfolgt laut Vorgabe keine Pruefung");
            // Verfahren 10
            System.out.println("\n*** Verfahren 10 ***");
            System.out.println("Richtige Kontonummer: " + PRFZVFactory.createInstance("10").check("12345008"));
            System.out.println("Andere richtige Kontonummer: " + PRFZVFactory.createInstance("10").check("87654008"));
            System.out.println("Falsche Kontonummer: " + PRFZVFactory.createInstance("10").check("87654009"));
            // Verfahren 11
            System.out.println("\n*** Verfahren 11 ***");
            System.out.println("Keine Testkontonummer vorhanden");
            // Verfahren 14
            System.out.println("\n*** Verfahren 14 ***");
            System.out.println("Selbst erstellte richtige Kontonummer: " + PRFZVFactory.createInstance("14").check("1982358239"));
            System.out.println("Falsche Kontonummer: " + PRFZVFactory.createInstance("14").check("1982358238"));
            // Verfahren 28
            System.out.println("\n*** Verfahren 28 ***");
            System.out.println("Richtige Kontonummer: " + PRFZVFactory.createInstance("28").check("19999000"));
            System.out.println("Andere richtige Kontonummer: " + PRFZVFactory.createInstance("28").check("9130000201"));
            System.out.println("Falsche Kontonummer: " + PRFZVFactory.createInstance("28").check("913000200"));
            
            // Verfahren 32
            System.out.println("\n*** Verfahren 32 ***");
            System.out.println("Test Kontonummer: 320960 - " + PRFZVFactory.createInstance("32").check("320960"));
            System.out.println("Richtige Kontonummer: " + PRFZVFactory.createInstance("32").check("9141405"));
            System.out.println("Andere richtige Kontonummer: " + PRFZVFactory.createInstance("32").check("1709107983"));
            System.out.println("Falsche Kontonummer: " + PRFZVFactory.createInstance("32").check("87654009"));
            
            // Verfahren 33
            System.out.println("\n*** Verfahren 33 ***");
            System.out.println("Richtige Kontonummer: " + PRFZVFactory.createInstance("33").check("48658"));
            System.out.println("Andere richtige Kontonummer: " + PRFZVFactory.createInstance("33").check("84956"));
            System.out.println("Falsche Kontonummer: " + PRFZVFactory.createInstance("33").check("84957"));
            // Verfahren 34
            System.out.println("\n*** Verfahren 34 ***");
            System.out.println("Richtige Kontonummer: " + PRFZVFactory.createInstance("34").check("9913000700"));
            System.out.println("Andere richtige Kontonummer: " + PRFZVFactory.createInstance("34").check("991400100"));
            System.out.println("Falsche Kontonummer: " + PRFZVFactory.createInstance("34").check("84957"));
            // Verfahren 37
            System.out.println("\n*** Verfahren 37 ***");
            System.out.println("Richtige Kontonummer: " + PRFZVFactory.createInstance("37").check("624315"));
            System.out.println("Andere richtige Kontonummer: " + PRFZVFactory.createInstance("37").check("632500"));
            System.out.println("Falsche Kontonummer: " + PRFZVFactory.createInstance("37").check("84957"));
            // Verfahren 38
            System.out.println("\n*** Verfahren 38 ***");
            System.out.println("Richtige Kontonummer: " + PRFZVFactory.createInstance("38").check("191919"));
            System.out.println("Andere richtige Kontonummer: " + PRFZVFactory.createInstance("38").check("1100660"));
            System.out.println("Falsche Kontonummer: " + PRFZVFactory.createInstance("38").check("84957"));
            // Verfahren 40
            System.out.println("\n*** Verfahren 40 ***");
            System.out.println("Richtige Kontonummer: " + PRFZVFactory.createInstance("40").check("1258345"));
            System.out.println("Andere richtige Kontonummer: " + PRFZVFactory.createInstance("40").check("3231963"));
            System.out.println("Falsche Kontonummer: " + PRFZVFactory.createInstance("40").check("84957"));
            // Verfahren 42
            System.out.println("\n*** Verfahren 42 ***");
            System.out.println("Richtige Kontonummer: " + PRFZVFactory.createInstance("42").check("59498"));
            System.out.println("Andere richtige Kontonummer: " + PRFZVFactory.createInstance("42").check("59510"));
            System.out.println("Falsche Kontonummer: " + PRFZVFactory.createInstance("42").check("84957"));
            // Verfahren 43
            System.out.println("\n*** Verfahren 43 ***");
            System.out.println("Richtige Kontonummer: " + PRFZVFactory.createInstance("43").check("6135244"));
            System.out.println("Andere richtige Kontonummer: " + PRFZVFactory.createInstance("43").check("9516893476"));
            System.out.println("Falsche Kontonummer: " + PRFZVFactory.createInstance("43").check("84957"));
            // Verfahren 44
            System.out.println("\n*** Verfahren 44 ***");
            System.out.println("Richtige Kontonummer: " + PRFZVFactory.createInstance("44").check("889006"));
            System.out.println("Andere richtige Kontonummer: " + PRFZVFactory.createInstance("44").check("2618040504"));
            System.out.println("Falsche Kontonummer: " + PRFZVFactory.createInstance("44").check("84957"));
            // Verfahren 46
            System.out.println("\n*** Verfahren 46 ***");
            System.out.println("Richtige Kontonummer: " + PRFZVFactory.createInstance("46").check("0235468612"));
            System.out.println("Andere richtige Kontonummer: " + PRFZVFactory.createInstance("46").check("0837890901"));
            System.out.println("Falsche Kontonummer: " + PRFZVFactory.createInstance("46").check("84957"));
            // Verfahren 47
            System.out.println("\n*** Verfahren 47 ***");
            System.out.println("Richtige Kontonummer: " + PRFZVFactory.createInstance("47").check("1018000"));
            System.out.println("Andere richtige Kontonummer: " + PRFZVFactory.createInstance("47").check("1003554450"));
            System.out.println("Falsche Kontonummer: " + PRFZVFactory.createInstance("47").check("84957"));
            // Verfahren 48
            System.out.println("\n*** Verfahren 48 ***");
            System.out.println("Selbst erstellte richtige Kontonummer: " + PRFZVFactory.createInstance("48").check("123436"));
            System.out.println("Falsche Kontonummer: " + PRFZVFactory.createInstance("48").check("84957"));
            // Verfahren 50
            System.out.println("\n*** Verfahren 50 ***");
            System.out.println("Richtige Kontonummer: " + PRFZVFactory.createInstance("50").check("4000005001"));
            System.out.println("Andere richtige Kontonummer: " + PRFZVFactory.createInstance("50").check("4444442001"));
            System.out.println("Falsche Kontonummer: " + PRFZVFactory.createInstance("50").check("00849574"));
            // Verfahren 64
            System.out.println("\n*** Verfahren 64 ***");
            System.out.println("Richtige Kontonummer: " + PRFZVFactory.createInstance("64").check("1206473010"));
            System.out.println("Andere richtige Kontonummer: " + PRFZVFactory.createInstance("64").check("5016511020"));
            System.out.println("Falsche Kontonummer: " + PRFZVFactory.createInstance("64").check("00849574"));
            // Verfahren 73
            System.out.println("\n*** Verfahren 73 ***");
            System.out.println("Richtige Kontonummer: " + PRFZVFactory.createInstance("73").check("7899100003"));
            System.out.println("Andere richtige Kontonummer: " + PRFZVFactory.createInstance("73").check("3503398"));
            System.out.println("Falsche Kontonummer: " + PRFZVFactory.createInstance("73").check("00849574"));
            // Verfahren 81
            System.out.println("\n*** Verfahren 81 ***");
            System.out.println("Richtige Kontonummer: " + PRFZVFactory.createInstance("81").check("0646440"));
            System.out.println("Andere richtige Kontonummer: " + PRFZVFactory.createInstance("81").check("3199500501"));
            System.out.println("Falsche Kontonummer: " + PRFZVFactory.createInstance("81").check("3199500502"));
            // Verfahren 82
            System.out.println("\n*** Verfahren 82 ***");
            System.out.println("Richtige Kontonummer: " + PRFZVFactory.createInstance("82").check("123897"));
            System.out.println("Andere richtige Kontonummer: " + PRFZVFactory.createInstance("82").check("3199500501"));
            System.out.println("Falsche Kontonummer: " + PRFZVFactory.createInstance("82").check("3199500502"));
            // Verfahren 88
            System.out.println("\n*** Verfahren 88 ***");
            System.out.println("Richtige Kontonummer: " + PRFZVFactory.createInstance("88").check("2525259"));
            System.out.println("Andere richtige Kontonummer: " + PRFZVFactory.createInstance("88").check("99913003"));
            System.out.println("Falsche Kontonummer: " + PRFZVFactory.createInstance("88").check("3199500502"));
            // Verfahren 91
            System.out.println("\n*** Verfahren 91 ***");
            System.out.println("A:Richtige Kontonummer: " + PRFZVFactory.createInstance("91").check("2974118000"));
            System.out.println("A:Andere richtige Kontonummer: " + PRFZVFactory.createInstance("91").check("5281741000"));
            System.out.println("A:Andere richtige Kontonummer: " + PRFZVFactory.createInstance("91").check("9952810000"));
            System.out.println("B:Richtige Kontonummer: " + PRFZVFactory.createInstance("91").check("2974117000"));
            System.out.println("B:Andere richtige Kontonummer: " + PRFZVFactory.createInstance("91").check("5281770000"));
            System.out.println("B:Andere richtige Kontonummer: " + PRFZVFactory.createInstance("91").check("9952812000"));     
            System.out.println("Falsche Kontonummer: " + PRFZVFactory.createInstance("91").check("3199500502"));
       */
    } catch (ArrayIndexOutOfBoundsException aie) {
      System.out.println("Usage: java Kontonummerntest <Verfahren> <Kontonummer>");
    } catch (Exception e) {
      System.err.println(e.getMessage());
      e.printStackTrace();
    }
  }
}
