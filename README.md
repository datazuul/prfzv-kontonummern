# Prüfzifferberechnung für Kontonummern

Dokumentation siehe <https://www.bundesbank.de/de/aufgaben/unbarer-zahlungsverkehr/serviceangebot/pruefzifferberechnung/pruefzifferberechnung-fuer-kontonummern-603282>:

> "Die Prüfziffersicherung von Kontonummern fördert eine reibungslose und automatisierte Abwicklung des Zahlungsverkehrs. Daher ist ein Zahlungsdienstleister verpflichtet, die Prüfzifferberechnungsmethode für die Kontonummern der von ihnen geführten Zahlungskonten bekanntzugeben und im Zahlungsverkehr ausschließlich Kontonummern zu verwenden, die von der gemeldeten Prüfzifferberechnungsmethode erfasst sind.
> 
> Den Zahlungsdienstleistern ist die Wahl der Prüfzifferberechnungsmethode freigestellt. Die Deutsche Bundesbank nimmt entsprechend einer Vereinbarung mit den Spitzenverbänden des Kreditgewerbes die Vergabe von Kennzeichen für Prüfzifferberechnungsmethoden vor und führt eine Übersicht der im Kreditgewerbe angewandten Prüfzifferberechnungsmethoden."

Dokumentation: 201806-pruefzifferberechnungsmethoden-data.pdf

## Usage

Beispiel: Überprüfung einer Kontonummer nach Prüfzifferverfahren Nummer "37":

```java
boolean isKontonummerKorrekt = PRFZVFactory.createInstance("37").check("624315");
```
