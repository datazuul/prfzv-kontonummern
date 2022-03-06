package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 80 (geändert zum 06.09.2004)
 *
 * <p>
 * FIXME: untere Berechnung veraltet</p>
 *
 * <p>
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2</p>
 *
 * <p>
 * Methode A</p>
 *
 * <p>
 * Die Kontonummer ist durch linksbündige Nullenauffüllung 10- stellig darzustellen. Die Berechnung und die möglichen Ergebnisse entsprechen dem Verfahren 00; es ist jedoch zu beachten, dass nur die
 * Stellen 5 bis 9 in das Prüfzifferberechnungsverfahren einbezogen werden. Die Stelle 10 der Kontonummer ist die Prüfziffer.</p>
 *
 * <pre>
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)
 * Kontonr.:   x x x x x x x x x P
 * Gewichtung:         2 1 2 1 2
 * </pre>
 *
 * <p>
 * Testkontonummer: 340968</p>
 *
 * <p>
 * Führt die Berechnung nach Methode A zu einem Prüfzifferfehler, ist die Berechnung nach Methode B vorzunehmen.</p>
 *
 * <p>
 * Methode B</p>
 *
 * <p>
 * Modulus 7, Gewichtung 2, 1, 2, 1, 2</p>
 *
 * <p>
 * Das Berechnungsverfahren entspricht Methode A. Die Summe der Produkt-Quersummen ist jedoch durch 7 zu dividieren. Der verbleibende Rest wird vom Divisor (7) subtrahiert. Das Ergebnis ist die
 * Prüfziffer. Verbleibt nach der Division kein Rest, ist die Prüfziffer = 0</p>
 *
 * <p>
 * Testkontonummer: 340966</p>
 *
 * <p>
 * Ausnahme:</p>
 *
 * <p>
 * Ist nach linksbündiger Auffüllung mit Nullen auf 10 Stellen die 3. Stelle der Kontonummer = 9 (Sachkonten), so erfolgt die Berechnung gemäß der Ausnahme in Methode 51 mit den gleichen Ergebnissen
 * und Testkontonummern. </p>
 */
public class PV80 extends PRFZV {

  public PV80() {
    // vorgegebene mGewichtung
    mGewichtungswerte = new int[]{2, 1, 2, 1, 2};

    // vorgegebener mModulo-Wert (Variante 1, Variante 2 unten definiert)
    mModulo = 10;

    // Index der Prüfzifferstelle (ab 0 beginnend)
    mPruefzifferstelle = 9;
  }

  @Override
  public int getVergleichswert() {
    // mKontonummernummer zehnstellig machen
    while (mKontonummer.length() < 10) {
      mKontonummer = "0" + mKontonummer;
    }

    // Array mGewichtung füllen
    mGewichtung = new int[9];
    for (int i = 8; i >= 4; i--) {
      mGewichtung[i] = mGewichtungswerte[mCounter];
      mCounter++;
    }
    // Variante 1
    Integer zwischenmErgebnis;
    String zwischenmErgebnisstring;
    int quersumme;
    for (int i = 0; i < 9; i++) {
      // Multiplikation der mGewichtung mit mKontonummer
      zwischenmErgebnis = (Integer.parseInt(mKontonummer.substring(i, i + 1)) * mGewichtung[i]);
      // Quersumme der MultiplikationsmErgebnisse bilden
      zwischenmErgebnisstring = zwischenmErgebnis.toString();
      if (zwischenmErgebnisstring.length() == 2) {
        quersumme = (Integer.parseInt(zwischenmErgebnisstring.substring(0, 1))
                + Integer.parseInt(zwischenmErgebnisstring.substring(1)));
      } else {
        quersumme = zwischenmErgebnis;
      }
      // Quersummen addieren
      mErgebnis += quersumme;
    }
    // Nur Einserstelle berücksichtigen und diese von zehn subtrahieren
    if (mErgebnis > 9) {
      String mErgebnisstring = Integer.toString(mErgebnis);
      mErgebnis = 10 - (Integer.parseInt(mErgebnisstring.substring(mErgebnisstring.length() - 1)));
    }
    // Vergleichswert bestimmen
    mVergleichswert = mErgebnis;

    // Variante 2
    if (mVergleichswert != getPruefziffer()) {
      // Hier noch implementieren. mModulo ist im Konstruktor zu prüfen
    }
    return mVergleichswert;
  }
}
