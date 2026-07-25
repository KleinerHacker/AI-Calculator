# Bedienung der Oberfläche

Starten Sie die Anwendung über `./gradlew run`. Das Hauptfenster öffnet sich als
eigenständiges Fenster und zeigt das Anwendungsicon in Titel- und Taskleiste an. Das
Fenster hat eine feste Größe von 300 x 500 Pixel und kann nicht verändert werden.

## Formel- und Ergebnisanzeige

Im oberen Bereich des Fensters befindet sich die Anzeige:

- in der ersten Zeile die aktuelle Formel in kleiner Schrift
- darunter das aktuelle Ergebnis in großer Schrift

Beides wird ständig angezeigt. Solange keine Formel eingegeben wurde, zeigt die Formelzeile als
Hinweis eine Beispielformel (`12+34*2`) und die Ergebniszeile `0`. Eine Formel, die nicht
berechnet werden kann, wird unverändert weiter angezeigt, das Ergebnis fällt dann auf `0`
zurück.

Die Anzeige wächst nie über das Fenster hinaus. Ist eine Formel oder ein Ergebnis für die
verfügbare Breite zu lang, wird der Text am Anfang abgeschnitten und mit Auslassungspunkten
gekennzeichnet; der vollständige Text erscheint dann als Tooltip, wenn die Zeile mit der
Maus überfahren wird.

Das Tastenfeld ist noch nicht mit der Anzeige verbunden, daher zeigt die Anzeige derzeit
immer den leeren Zustand.

## Tastenfeld

In der Mitte des Fensters befindet sich das Tastenfeld. Es ist wie bei einem klassischen
Taschenrechner angeordnet:

| Zeile | Tasten |
| --- | --- |
| 1 | `C` `/` `*` `-` |
| 2 | `7` `8` `9` `+` (doppelte Höhe) |
| 3 | `4` `5` `6` |
| 4 | `1` `2` `3` `=` (doppelte Höhe) |
| 5 | `0` (dreifache Breite) |

Jede Taste ist ausschließlich mit einem Icon beschriftet. Beim Überfahren erscheint ein
Tooltip mit Symbol, Bedeutung und Tastaturkürzel; derselbe Text steht Screenreadern zur
Verfügung.

## Tastaturkürzel

| Taste | Kürzel |
| --- | --- |
| `0` - `9` | Ziffern, auch über den Nummernblock |
| `+` `-` `*` `/` | das jeweilige Zeichen |
| `=` | `Enter` oder `=` |
| `C` | `Esc` oder `Entf` |
