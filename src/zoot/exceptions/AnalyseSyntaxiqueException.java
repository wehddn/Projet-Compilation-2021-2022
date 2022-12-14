package zoot.exceptions;

public class AnalyseSyntaxiqueException extends AnalyseException {
 
    public AnalyseSyntaxiqueException(String m) {
        super("ERREUR SYNTAXIQUE :\n\t" + m) ;
    }

    public AnalyseSyntaxiqueException(int ligne, int colonne, String m) {
        super("ERREUR SYNTAXIQUE : ligne " + ligne + " : " + m);
    }

}
