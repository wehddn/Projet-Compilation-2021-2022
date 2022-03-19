package zoot.arbre;

import zoot.exceptions.AnalyseSemantiqueException;

import java.util.ArrayList;

/**
 * Description
 *
 * @author Elhadji Moussa FAYE
 * @version 1.8.0
 * @since 1.8.0
 * created on 13/03/2022
 */
public class BlocDeFonctions extends ArbreAbstrait {
    public ArrayList<Fonction> fonctions ;

    public BlocDeFonctions(int n, int m) {
        super(n, m);
        fonctions = new ArrayList<>() ;
    }

    @Override
    public void verifier() throws AnalyseSemantiqueException {
        for (Fonction f: fonctions) {
            f.verifier();
        }
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        for (Fonction f : fonctions) {
            sb.append(f.toMIPS()).append('\n');
        }
        return sb.toString();
    }

    public void ajouter(Fonction f) {
        fonctions.add(f);
    }
}