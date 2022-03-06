package com.datazuul.prfzv.kontonummern;

/**
 * Abstrakte Basisklasse fuer alle verschiedenen Pruefverfahren
 */
public abstract class PRFZV {

  protected int mCounter = 0;
  protected int mErgebnis;
  protected int[] mGewichtung;
  protected int[] mGewichtungswerte;
  protected boolean mHasMoreChecks = false;
  protected String mKontonummer;
  public short mModulo;
  protected int mPruefzifferstelle;
  protected int mRest;
  protected int mVergleichswert;

  public boolean check(final String pKontonummer)
          throws PRFZVException {
    mKontonummer = pKontonummer;
    boolean checked = false;
    try {
      while (!(checked = ((mVergleichswert = getVergleichswert()) == getPruefziffer()))) {
        //System.out.println("Checked: " + checked + "\n" + "HasMore: " + mHasMoreChecks);
        if (!(mHasMoreChecks) || (mVergleichswert == -1)) {
          return false;
        }
      }
      // Rückgabe von -1 z.B. in 02 verwendet (Kontonummer nicht verwendbar)
    } catch (NumberFormatException nfe) {
      throw new PRFZVException("Die eingegebene Kontonummer enthaelt nichtnumerische Wert!");
    } catch (Exception e) {
      throw new PRFZVException(e.getMessage());
    }

    // Ergebnisrueckgabe
    //System.out.println("Vergleichswert :" + mVergleichswert + " , Pruefziffer: " + getPruefziffer());
    return checked;
  }

  protected int getPruefziffer() {
    return Integer.parseInt(mKontonummer.substring(mPruefzifferstelle, mPruefzifferstelle + 1));
  }

  abstract int getVergleichswert();
}
