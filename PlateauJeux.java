package com.example.projetsudoko;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//cette classe permit de definir une grille
public class PlateauJeux {


    /**
     * Cette classe represente une celulle et ces info.
     */

    public static class CelJeux {
        public int valeurReel; //c'est la veuleur reel qui definit pour notre grille donc c'est la valeur qu'on dois devoir trouver
        public int Vlasuppose;//c'est la valeur qu'on va inserer soit elle va corespende a la valeur reel ou non
        public boolean debut = false; //si la valuer est indiquée dès le debut de


        public CelJeux( int Valeur_R ) {
            this.valeurReel = Valeur_R;
        }

        public CelJeux( int Valeur_R, int debut ) {
            this.valeurReel = Valeur_R;
            this.debut = debut == 1;
            if ( this.debut ) this.Vlasuppose = Valeur_R;
        }
    }


    public Niveau_Diff niveau;
    public boolean G_Nombre = true; //cest pour le petit et le grand stylo
    public int celulleX = -1; //correspends a la celleule courante selectionner
    public int celulleY = -1;

    public CelJeux [][] cells; // c'est le  tableu qui contient les infos de chaque celleule

    //constructeur *******
    private PlateauJeux( Niveau_Diff niveau, CelJeux [][] cells ) {
        this.niveau = niveau;
        this.cells = cells;
        //this.cells[0][1].marques = new boolean[] { true, true, true, true, true, true, true, true, true };
    }

    /**
     * Renvoie la valeur actuellement sélectionnée. Une cellule doit être sélectionnée, sinon 0 est retourné.
     */
    //elle nous renvois la valeur qu'on a  (selectionnner)
    public int getValuer_S() {
        // Nous devons connaître la cellule actuelle  celulleX
        if ( this.celulleX == -1 ) return 0;
        if ( this.celulleY == -1 ) return 0;

        CelJeux CellCourante = this.cells[ this.celulleY ][ this.celulleX ];
        return CellCourante.Vlasuppose;
    }


    //inserer la valeur selectionner
    public void InsererVal( int value ) {
        // Nous devons connaître la cellule actuelle
        if ( this.celulleX == -1 ) return;
        if ( this.celulleY == -1 ) return;

        CelJeux CellCourante = this.cells[ this.celulleY ][ this.celulleX ];
        // Nous ne pouvons pas mettre à jour une cellule initiale
        if ( CellCourante.debut ) return;

        if ( this.G_Nombre ) {
            // Modifier la valeur supposée
            CellCourante.Vlasuppose = value;
        }
    }

    /**
     * Effacer toutes les informations pour la cellule sélectionnée.
           * Nous ne pouvons pas changer l'état d'un état initial.
     */


    public void EffacerCel() {
        // Nous devons connaître la cellule actuelle
        if ( this.celulleX == -1 ) return;
        if ( this.celulleY == -1 ) return;

        CelJeux CellCourante = this.cells[ this.celulleY ][ this.celulleX ];

        // Nous ne pouvons pas mettre à jour une cellule initiale
        if ( CellCourante.debut )
        {
            return;
        }
        //et si la celleule est courante on va :
        else {
            CellCourante.Vlasuppose = 0; //on metra toutes les valuer a 0 (c'est la cellule courante)
        }
    }

/***************************/
    public static int veriffication(PlateauJeux p)
    {
        int ga=0;
        for(int i=1;i<9;i++)
        {
            for(int j=1;j<9;j++)
            {
                if ( p.cells[i][j].Vlasuppose!=p.cells[i][j].valeurReel){
                    ga=1;

                }
                else
                {
                    ga=0;
                }
            }
        }
        return ga;
    }
   //Générer une nouvelle grille a chaque appel à la fonction getPlateau

    public static PlateauJeux getPlateauJeux( Niveau_Diff niveau ) {

        if (niveau == Niveau_Diff.FACILE) return new PlateauJeux(niveau, new CelJeux[][]{
                {new CelJeux(9, 0), new CelJeux(2, 1), new CelJeux(8, 1),
                        new CelJeux(7, 1), new CelJeux(5, 1), new CelJeux(4, 1),
                        new CelJeux(1, 0), new CelJeux(3, 1), new CelJeux(6, 0)},
                {new CelJeux(6, 1), new CelJeux(7, 1), new CelJeux(1, 1),
                        new CelJeux(8, 1), new CelJeux(2, 1), new CelJeux(3, 1),
                        new CelJeux(5, 0), new CelJeux(4, 0), new CelJeux(9, 0)},
                {new CelJeux(3, 0), new CelJeux(5, 0), new CelJeux(4, 1),
                        new CelJeux(9, 1), new CelJeux(1, 1), new CelJeux(6, 1),
                        new CelJeux(2, 0), new CelJeux(7, 0), new CelJeux(8, 0)},

                {new CelJeux(4, 1), new CelJeux(9, 1), new CelJeux(6, 0),
                        new CelJeux(2, 1), new CelJeux(3, 1), new CelJeux(7, 1),
                        new CelJeux(8, 0), new CelJeux(5, 0), new CelJeux(1, 0)},
                {new CelJeux(8, 0), new CelJeux(1, 1), new CelJeux(5, 0),
                        new CelJeux(4, 0), new CelJeux(6, 1), new CelJeux(9, 0),
                        new CelJeux(7, 0), new CelJeux(2, 1), new CelJeux(3, 0)},
                {new CelJeux(7, 0), new CelJeux(3, 0), new CelJeux(2, 1),
                        new CelJeux(5, 0), new CelJeux(8, 0), new CelJeux(1, 1),
                        new CelJeux(9, 0), new CelJeux(6, 1), new CelJeux(4, 1)},

                {new CelJeux(5, 0), new CelJeux(4, 1), new CelJeux(3, 0),
                        new CelJeux(1, 1), new CelJeux(9, 0), new CelJeux(2, 0),
                        new CelJeux(6, 0), new CelJeux(8, 1), new CelJeux(7, 0)},
                {new CelJeux(2, 1), new CelJeux(6, 0), new CelJeux(9, 1),
                        new CelJeux(3, 0), new CelJeux(7, 1), new CelJeux(8, 1),
                        new CelJeux(4, 0), new CelJeux(1, 1), new CelJeux(5, 0)},
                {new CelJeux(1, 1), new CelJeux(8, 0), new CelJeux(7, 1),
                        new CelJeux(6, 0), new CelJeux(4, 0), new CelJeux(5, 1),
                        new CelJeux(3, 1), new CelJeux(9, 1), new CelJeux(2, 1)}
        });

        else if(niveau == Niveau_Diff.MOYEN){
            return new PlateauJeux(niveau, new CelJeux[][]{
                    {new CelJeux(9, 1), new CelJeux(2, 0), new CelJeux(8, 0),
                            new CelJeux(7, 1), new CelJeux(5, 0), new CelJeux(4, 0),
                            new CelJeux(1, 1), new CelJeux(3, 0), new CelJeux(6, 0)},
                    {new CelJeux(6, 0), new CelJeux(7, 0), new CelJeux(1, 0),
                            new CelJeux(8, 1), new CelJeux(2, 0), new CelJeux(3, 1),
                            new CelJeux(5, 0), new CelJeux(4, 0), new CelJeux(9, 0)},
                    {new CelJeux(3, 0), new CelJeux(5, 1), new CelJeux(4, 1),
                            new CelJeux(9, 0), new CelJeux(1, 1), new CelJeux(6, 0),
                            new CelJeux(2, 0), new CelJeux(7, 1), new CelJeux(8, 0)},

                    {new CelJeux(4, 1), new CelJeux(9, 1), new CelJeux(6, 0),
                            new CelJeux(2, 0), new CelJeux(3, 0), new CelJeux(7, 0),
                            new CelJeux(8, 1), new CelJeux(5, 1), new CelJeux(1, 0)},
                    {new CelJeux(8, 0), new CelJeux(1, 1), new CelJeux(5, 0),
                            new CelJeux(4, 1), new CelJeux(6, 0), new CelJeux(9, 1),
                            new CelJeux(7, 0), new CelJeux(2, 1), new CelJeux(3, 0)},
                    {new CelJeux(7, 0), new CelJeux(3, 1), new CelJeux(2, 1),
                            new CelJeux(5, 0), new CelJeux(8, 0), new CelJeux(1, 0),
                            new CelJeux(9, 0), new CelJeux(6, 1), new CelJeux(4, 1)},

                    {new CelJeux(5, 0), new CelJeux(4, 1), new CelJeux(3, 0),
                            new CelJeux(1, 0), new CelJeux(9, 1), new CelJeux(2, 0),
                            new CelJeux(6, 1), new CelJeux(8, 1), new CelJeux(7, 0)},
                    {new CelJeux(2, 0), new CelJeux(6, 0), new CelJeux(9, 0),
                            new CelJeux(3, 1), new CelJeux(7, 0), new CelJeux(8, 1),
                            new CelJeux(4, 0), new CelJeux(1, 0), new CelJeux(5, 0)},
                    {new CelJeux(1, 0), new CelJeux(8, 0), new CelJeux(7, 1),
                            new CelJeux(6, 0), new CelJeux(4, 0), new CelJeux(5, 1),
                            new CelJeux(3, 0), new CelJeux(9, 0), new CelJeux(2, 1)}
            });

        }
        else if(niveau==Niveau_Diff.DUR){
            return new PlateauJeux(niveau, new CelJeux[][]{
                    {new CelJeux(9, 0), new CelJeux(2, 0), new CelJeux(8, 0),
                            new CelJeux(7, 1), new CelJeux(5, 0), new CelJeux(4, 0),
                            new CelJeux(1, 1), new CelJeux(3, 0), new CelJeux(6, 0)},
                    {new CelJeux(6, 0), new CelJeux(7, 0), new CelJeux(1, 0),
                            new CelJeux(8, 1), new CelJeux(2, 0), new CelJeux(3, 1),
                            new CelJeux(5, 0), new CelJeux(4, 0), new CelJeux(9, 0)},
                    {new CelJeux(3, 0), new CelJeux(5, 1), new CelJeux(4, 1),
                            new CelJeux(9, 0), new CelJeux(1, 1), new CelJeux(6, 0),
                            new CelJeux(2, 0), new CelJeux(7, 1), new CelJeux(8, 0)},

                    {new CelJeux(4, 1), new CelJeux(9, 1), new CelJeux(6, 0),
                            new CelJeux(2, 0), new CelJeux(3, 0), new CelJeux(7, 0),
                            new CelJeux(8, 1), new CelJeux(5, 1), new CelJeux(1, 0)},
                    {new CelJeux(8, 0), new CelJeux(1, 1), new CelJeux(5, 0),
                            new CelJeux(4, 1), new CelJeux(6, 0), new CelJeux(9, 1),
                            new CelJeux(7, 0), new CelJeux(2, 1), new CelJeux(3, 0)},
                    {new CelJeux(7, 0), new CelJeux(3, 1), new CelJeux(2, 1),
                            new CelJeux(5, 0), new CelJeux(8, 0), new CelJeux(1, 0),
                            new CelJeux(9, 0), new CelJeux(6, 1), new CelJeux(4, 1)},

                    {new CelJeux(5, 0), new CelJeux(4, 1), new CelJeux(3, 0),
                            new CelJeux(1, 0), new CelJeux(9, 1), new CelJeux(2, 0),
                            new CelJeux(6, 1), new CelJeux(8, 1), new CelJeux(7, 0)},
                    {new CelJeux(2, 0), new CelJeux(6, 0), new CelJeux(9, 0),
                            new CelJeux(3, 1), new CelJeux(7, 0), new CelJeux(8, 1),
                            new CelJeux(4, 0), new CelJeux(1, 0), new CelJeux(5, 0)},
                    {new CelJeux(1, 0), new CelJeux(8, 0), new CelJeux(7, 1),
                            new CelJeux(6, 0), new CelJeux(4, 0), new CelJeux(5, 1),
                            new CelJeux(3, 0), new CelJeux(9, 0), new CelJeux(2, 1)}
            });

        }
        else {
            return null;

        }



    }



}



