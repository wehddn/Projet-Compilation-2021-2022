package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;

/**
 * Instruction pour écrire une expression
 * @version 1.3.1
 */
public class Ecrire extends Instruction {

    /**
     * L'instruction à écrire
     */
    protected Expression exp ;

    /**
     * @param e l'expression à écrire
     * @param n le numéro de ligne dans le code zoot de l'instruction écrire
     */
    public Ecrire (Expression e, int n, int m) {
        super(n, m) ;
        exp = e ;
    }

    /**
     * @see zoot.arbre.ArbreAbstrait
     */
    @Override
    public void verifier() {
        exp.verifier();
    }

    /**
     * @see zoot.arbre.ArbreAbstrait
     */
    @Override
    public String toMIPS() {
        return exp.toMIPS().indent(4) +
                "# Ecriture\n" +
                "\tmove $a0, $v0\n" +
                "\tli $v0, 1\n" +
                "\tsyscall\n" +
                "# Saut de ligne\n" +
                "\tli $v0, 11\n" +
                "\tli $a0, 10\n" +
                "\tsyscall";
    }

    @Override
    public String toString() {
        return "ecrire : "  + exp;
    }
}
