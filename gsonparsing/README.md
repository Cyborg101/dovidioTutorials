# Esempio di deserializzazione Json con la libreria Gson.

Con questa piccola app facciamo una ricerca di repositories Github avvalendoci della loro Api.
Per eseguire l'app:

- Scarica la repository
- Esegui un ``` mvn clean install ```
- Esegui l'app tramite il comando ``` mvn exec:java -Dexec.mainClass="gsonparsing.GithubSearch" -Dexec.args="ricerca",
dove al posto di ricerca puoi immettere il nome della repository da cercare ```

Se utilizzi un'ide puoi eseguire ti basterà fare un maven import ed eseguire la classe fornendo un parametro di ricerca
