package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 00
 *
 * <p>
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1, 2, 1, 2</p>
 *
 * <p>
 * Die Stellen der Kontonummer sind von rechts nach links mit den Ziffern 2, 1, 2, 1, 2 usw. zu multiplizieren. Die jeweiligen Produkte werden addiert, nachdem jeweils aus den zweistelligen Produkten
 * die Quersumme gebildet wurde (z. B. Produkt 16 = Quersumme 7). Nach der Addition bleiben außer der Einerstelle alle anderen Stellen unberücksichtigt. Die Einerstelle wird von dem Wert 10
 * subtrahiert. Das Ergebnis ist die Prüfziffer (10. Stelle der Kontonummer). Ergibt sich nach der Subtraktion der Rest 10, ist die Prüfziffer 0.</p>
 *
 * <p>
 * Testkontonummern: 9290701, 539290858<br>
 * 1501824, 1501832 </p>
 */
public class PV00 extends PRFZV {

  public PV00() {
    // vorgegebene mGewichtung
    mGewichtungswerte = new int[]{2, 1};

    // vorgegebener mModulo-Wert
    mModulo = 10;

    // Index der Prüfzifferstelle (ab 0 beginnend)
    mPruefzifferstelle = 9;
  }

  @Override
  int getVergleichswert() {
    // mKontonummer zehnstellig machen
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
    int quersumme = 0;
    for (int i = 0; i < 9; i++) {
      // Multiplikation der mGewichtung mit mKontonummer
      zwischenmErgebnis = (Integer.parseInt(mKontonummer.substring(i, i + 1)) * mGewichtung[i]);
      // Quersumme der MultiplikationsmErgebnisse bilden
      zwischenmErgebnisstring = zwischenmErgebnis.toString();
      if (zwischenmErgebnisstring.length() == 2) {
        quersumme = (Integer.parseInt(zwischenmErgebnisstring.substring(0, 1)) + Integer.parseInt(zwischenmErgebnisstring.substring(1)));
      } else {
        quersumme = zwischenmErgebnis;
      }
      // Quersummen addieren
      mErgebnis += quersumme;
    }
    // Nur Einserstelle berücksichtigen und diese von zehn subtrahieren
    String mErgebnisstring = Integer.toString(mErgebnis);
    String einerstelle = mErgebnisstring.substring(mErgebnisstring.length() - 1);
    mErgebnis = 10 - Integer.parseInt(einerstelle);
    if (mErgebnis == mModulo) {
      mErgebnis = 0;
    }
    return mErgebnis;
  }
}
