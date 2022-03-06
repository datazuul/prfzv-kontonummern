package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 27
 *
 * <p>
 * FIXME: das Iterationsverfahren ist noch nicht eingebaut!</p>
 *
 * <p>
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1, 2, 1, 2 (modifiziert)</p>
 *
 * <p>
 * Die Berechnung erfolgt wie bei Verfahren 00, jedoch nur für die Kontonummern von 1 bis 999 999 999. Ab Konto 1 000 000 000 kommt das Prüfziffernverfahren M10H (iterierte Transformation) zum
 * Einsatz.</p>
 *
 * <p>
 * Es folgt die Beschreibung der iterierten Transformation:</p>
 *
 * <p>
 * Die Position der einzelnen Ziffern von rechts nach links innerhalb der Kontonummer gibt die Zeile 1 bis 4 der Transformationstabelle noch an. Aus ihr sind die Übersetzungswerte zu summieren. Die
 * Einerstelle wird von 10 subtrahiert. Die Differenz stellt die Prüfziffer dar.</p>
 *
 * <pre>
 * Beispiel:
 * Kontonummer
 * 2 8 4 7 1 6 9 4 8 P (P = Prüfziffer)
 * 1 4 3 2 1 4 3 2 1   (Transf.-Zeile)
 *
 * Transformationstabelle:
 * Ziffer:  0 1 2 3 4 5 6 7 8 9
 * Zeile 1: 0 1 5 9 3 7 4 8 2 6
 * Zeile 2: 0 1 7 6 9 8 3 2 5 4
 * Zeile 3: 0 1 8 4 6 2 9 5 7 3
 * Zeile 4: 0 1 2 3 4 5 6 7 8 9
 *
 * Von rechts nach links:
 * Ziffer 8 wird 2 aus Transformationszeile 1
 * Ziffer 4 wird 9 aus Zeile 2
 * Ziffer 9 wird 3 aus Zeile 3
 * Ziffer 6 wird 6 aus Zeile 4
 * Ziffer 1 wird 1 aus Zeile 1
 * Ziffer 7 wird 2 aus Zeile 2
 * Ziffer 4 wird 6 aus Zeile 3
 * Ziffer 8 wird 8 aus Zeile 4
 * Ziffer 2 wird 5 aus Zeile 1
 *
 *       ___
 * Summe  42
 *       ===
 * </pre>
 *
 * <p>
 * Die Einerstelle wird vom Wert 10 subtrahiert. Das Ergebnis ist die Prüfziffer, in unserem Beispiel also 10 – 2 = Prüfziffer 8, die Kontonummer lautet somit 2847169488. </p>
 */
public class PV27 extends PRFZV {

  public PV27() {
    // vorgegebene mGewichtung
    mGewichtungswerte = new int[]{2, 1};

    // vorgegebener mModulo-Wert
    mModulo = 10;

    // Index der Prüfzifferstelle (ab 0 beginnend)
    mPruefzifferstelle = 9;
  }

  @Override
  int getVergleichswert() {
    if (mKontonummer.length() > 9) {
      mErgebnis = 999;
    } else {
      // mKontonummernummer zehnstellig machen
      while (mKontonummer.length() < 10) {
        mKontonummer = "0" + mKontonummer;
      }

      // Array mGewichtung füllen
      mGewichtung = new int[9];
      for (int i = mGewichtung.length - 1; i >= 0; i--) {
        mGewichtung[i] = mGewichtungswerte[mCounter];
        mCounter++;
        if (mCounter > mGewichtungswerte.length - 1) {
          mCounter = 0;
        }
      }
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

    }
    return mErgebnis;
  }
}
