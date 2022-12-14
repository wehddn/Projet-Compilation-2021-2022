package zoot.tds;

import zoot.exceptions.DeclencheurDException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Description
 *
 * @author Elhadji Moussa FAYE
 * @version 2.6.3
 * @since 1.5.4
 * created on 06/03/2022
 */
public class EntreeFct extends Entree{
    private ArrayList<Type> typeParametres;

    public EntreeFct(String nom) {
        super(nom);
        this.typeParametres = new ArrayList<>();
    }

    public EntreeFct(String nom, String... typeParametres) {
        super(nom);
        setTypeParametres(typeParametres);
    }

    public void setTypeParametres(Type... typeParametres) {
        this.typeParametres = new ArrayList<>(typeParametres.length);
        Collections.addAll(this.typeParametres, typeParametres);
    }

    public void setTypeParametres(String... typeParametres) {
        this.typeParametres = new ArrayList<>(typeParametres.length);
        for (String t : typeParametres) {
            this.typeParametres.add(Type.valueOf(t.toUpperCase()));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        // Une fonction est égale à une autre fonction s'ils ont le même nom et les mêmes paramètres
        if (o instanceof EntreeFct entreeFct) {
            return nom.equals(entreeFct.nom) && this.typeParametres.equals(entreeFct.typeParametres);
        }

        return false;
    }

    @Override
    public void declencherException(DeclencheurDException d, String message) {
        d.entreeFct(this, message);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < typeParametres.size(); i++) {
            sb.append(typeParametres.get(i));
            if (i < (typeParametres.size()-1))
                sb.append(", ");
        }
        return nom+ "( " + sb + " )";
    }

    // TODO Enlever cette fonction
    @Override
    public String getNom() {
        return this.nom;
    }
}
