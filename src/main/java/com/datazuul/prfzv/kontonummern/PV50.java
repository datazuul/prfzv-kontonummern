package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 50
 *
 * <p>
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7</p>
 *
 * <p>
 * Die für die Berechnung relevante Grundnummer befindet sich in den Stellen 1 bis 6, die Prüfziffer in Stelle 7 (von links nach rechts gezählt). Die Stellen 1 bis 6 werden mit den Ziffern 7, 6, 5, 4,
 * 3, 2 multipliziert. Die restliche Berechnung und Ergebnisse entsprechen dem Verfahren 06. Die dreistellige Unternummer (Stellen 8 bis 10) darf nicht in das Prüfzifferberechnungsverfahren einbezogen
 * werden. Ist die Unternummer »000«, so kommt es vor, dass diese nicht angegeben ist. Ergibt die erste Berechnung einen Prüfzifferfehler, wird empfohlen, die Prüfzifferberechnung ein zweites Mal
 * durchzuführen und dabei die »gedachte« Unternummer 000 an die Stellen 8 bis 10 zu setzen und die vorhandene Kontonummer vorher um drei Stellen nach links zu verschieben.</p>
 *
 * <pre>
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)
 * Kontonr.:   x x x x x x P x x x
 * Gewichtung: 7 6 5 4 3 2
 * </pre>
 *
 * <p>
 * Testkontonummern: 4000005001, 4444442001</p>
 */
public class PV50 extends PRFZV {

  private boolean testedTwice = false;

  public PV50() {
    // vorgegebene mGewichtung
    mGewichtungswerte = new int[]{7, 6, 5, 4, 3, 2};

    // vorgegebener mModulo-Wert
    mModulo = 11;

    // Index der Prüfzifferstelle (ab 0 beginnend)
    mPruefzifferstelle = 6;
  }

  @Override
  public int getVergleichswert() {
    if (mKontonummer.length() > 7) {
      testedTwice = true;
    }

    // mKontonummer zehnstellig machen
    while (mKontonummer.length() < 10) {
      mKontonummer = "0" + mKontonummer;
    }
    // Array mGewichtung füllen
    mGewichtung = new int[6];
    for (int i = 0; i <= 5; i++) {
      mGewichtung[i] = mGewichtungswerte[i];
    }

    Integer zwischenmErgebnis;
    int quersumme = 0;
    for (int i = 0; i <= 5; i++) {
      // Multiplikation der mGewichtung mit mKontonummer
      String stelle = mKontonummer.substring(i, i + 1);
      int gewi = mGewichtung[i];
      zwischenmErgebnis = (Integer.parseInt(stelle) * gewi);
      // Multiplikationsergebnis:
      quersumme = zwischenmErgebnis;
      //	    System.out.println( "" + stelle + "*" + gewi + " = " + quersumme );
      // zu mErgebnis addieren
      mErgebnis += quersumme;
    }

    // Vergleichswert bestimmen
    mRest = mErgebnis % mModulo;
    if (mRest == 1 || mRest == 0) {
      mVergleichswert = 0;
    } else {
      mVergleichswert = mModulo - mRest;
    }

    // 2. Prüfung, falls erste fehlschlug (s. Doku):
    if ((mVergleichswert != getPruefziffer()) && !testedTwice) {
      //	    System.out.println( "2. Pr�fung");
      testedTwice = true;
      mErgebnis = 0;
      mCounter = 0;
      mKontonummer += "000";
      mKontonummer = mKontonummer.substring(3);
      mVergleichswert = getVergleichswert();
    }

    return mVergleichswert;
  }
}
