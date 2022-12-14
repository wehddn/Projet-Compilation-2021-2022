package zoot.arbre.expressions;

import zoot.exceptions.AnalyseSemantiqueException;
import zoot.exceptions.GestionnaireErreursSemantiques;
import zoot.exceptions.TypeNonConcordantException;
import zoot.mips.SnippetsMIPS;
import zoot.tds.Type;

/**
 * Classe qui représente un comparaison a < b dans l'arbre abstrait
 *
 * @author Elhadji Moussa FAYE
 * @version 3.9.0
 * @since 3.2.0
 * created on 05/04/2022
 */
public class Inferieur extends Binaire {
    /**
     *
     * @param gauche l'opérande gauche
     * @param droite l'opérande droite
     * @param ligne la ligne de déclaration de l'expression
     * @param colonne la colonne de déclaration de l'expression
     */
    public Inferieur(Expression gauche, Expression droite, int ligne, int colonne) {
        super(gauche, droite, ligne, colonne);
    }

    /**
     * @see zoot.arbre.ArbreAbstrait
     * @throws AnalyseSemantiqueException lorsque les opérandes ne sont pas du même type ou que l'une des
     * opérandes n'est pas de type entier
     */
    @Override
    public void verifier() throws AnalyseSemantiqueException {
        gauche.verifier();
        droite.verifier();
        Type gt = gauche.getType();
        Type dt = droite.getType();
        if (!gt.equals(Type.ENTIER) || !gt.equals(dt)) {
            GestionnaireErreursSemantiques.getInstance().ajouterErreurSemantique(
             new TypeNonConcordantException(ligne, colonne,  gt + " < " + dt));
        }
    }

    /**
     * @see zoot.arbre.expressions.Expression
     */
    @Override
    public Type getType() {
        return Type.BOOLEEN;
    }

    /**
     * @see zoot.arbre.ArbreAbstrait
     */
    @Override
    public String toMIPS() {
        return """
                # évaluation de la partie gauche
                %s
                # empilement du résultat
                %s
                # évaluation la partie droite
                %s
                # dépile le resultat et le stock dans t0
                %s
                # comparaison v0 et t0 et mettre dans v0
                \tslt $v0, $t0, $v0""".formatted(gauche.toMIPS(),
                SnippetsMIPS.empilerRegistre("$v0"),
                droite.toMIPS(),
                SnippetsMIPS.depilerVersRegistre("$t0"));
    }

    /**
     * @see zoot.arbre.expressions.Expression
     */
    @Override
    public String getCommentaire() {
        return gauche.getCommentaire() + " < " + droite.getCommentaire();
    }
}
