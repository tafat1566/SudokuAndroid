package com.example.projetsudoko;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Interpolator;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextPaint;
import android.text.style.BackgroundColorSpan;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Guideline;
import androidx.core.app.ComponentActivity;

import java.text.ParseException;


public class Vue_Jeux extends View implements GestureDetector.OnGestureListener{


    public static int scoor=0;
    private Chronometer chronometer;
    public static boolean yes=true;
    public static int fin_j =0;
    private GestureDetector gestureDetector;
    private Paint paint = new Paint( Paint.ANTI_ALIAS_FLAG ); //c'est un stylo qui va nous permettre de dissiner la grille
    int xx=niveau_d.getX();
   static Niveau_Diff nv=niveau_d.getY();
    //choisir le niveau de la grille
   public static PlateauJeux plteau = PlateauJeux.getPlateauJeux(nv);

    public class MyActivity extends AppCompatActivity implements View.OnClickListener {

        @Override

        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.niveau_d);


            Toast.makeText(this,niveau_d.getX(),Toast.LENGTH_LONG).show();
        }

        @Override
        public void onClick(View view) {

            int id = view.getId();

            switch (id) {

                case R.id.Facile:
                break;

                case R.id.Moyen:
                    break;

            }

        }

    }



    //ATTRIBUTS POUR CHARGER LES IMAGES ET CALCULER LA TALLE DE LA GRILLE
    private float largeur;
    private float SeparGri;
    private float largeCel;
    private float boutonLarge;
    private float buttonRayon;
    private float buttonMarge;
    public  static   int g=1;
    public static int ov=0;
    private Bitmap gommeBitmap;

    public Vue_Jeux(Context context) {
        super(context);
        this.init();
    }

    public Vue_Jeux(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    private void init() {
        //activer le detecteur de gestes
        gestureDetector = new GestureDetector( getContext(),  this );

    }



    @Override
    //dessiner la taille des bouton et les redemontionner
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // nous redemensionnons cetains tailles
        SeparGri = (w / 9f) / 20f; //taille de séparateur au niveau de la grille

        largeur = w;                                  // La taille de grille  (c la larger de lecan et la hauteur c'est la mm )
        largeCel = largeur / 9f;                     // La taille de la celulle// (c'est un carre aussi)
        boutonLarge = w / 7f;                           // La taille de button
        buttonRayon = boutonLarge / 10f;               // Taille de coin  pour butoon
        buttonMarge = (w - 6*boutonLarge) / 7f ;        // Marge entre deux boutons

        // nous redemensionnons  pour cette ecran a l'image
        gommeBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gomme); //charger les images
        gommeBitmap = Bitmap.createScaledBitmap(gommeBitmap,
                (int) (boutonLarge*0.8f), (int) (boutonLarge*0.8f), false);


    }

    //cette methode va verifier a chaque fois qu'on va remplire une case dans le plateau
    public static int  verifier() {


        int c=Color.YELLOW;

        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                if (plteau.cells[i][j].Vlasuppose != plteau.cells[i][j].valeurReel) {
                    g = 0;

                }
                else
                {
                    scoor++;
                }
            }
        }
        return g;
    }


        @Override
    //cette methode sera invoque a chaque fois qu'on va dessiner
    protected void onDraw(Canvas canvas) {

        // --- Dessine des cellules ---

        paint.setTextAlign( Paint.Align.CENTER );
        int a=verifier();

        int c=Color.WHITE;


        for( int y=0; y<9; y++ ) {
            for( int x=0; x<9; x++ ) {
                int backgroundColor = Color.WHITE;


                if ( plteau.celulleX != -1 && plteau.celulleY != -1 ) {
                    if ( (x / 3 == plteau.celulleX / 3 && y / 3 == plteau.celulleY / 3) ||
                            (x == plteau.celulleX && y != plteau.celulleY) ||
                            (x != plteau.celulleX && y == plteau.celulleY)  ) {
                        backgroundColor = 0xFF_FF_F0_F0;//0xFF_FF_F0_F0 //Coleur de
                    }
                }                // Vérifiez si la cellule est initialement proposée: dans ce cas, le fond est gris
                if ( plteau.cells[y][x].debut ) {
                    if ( backgroundColor == 0xFF_FF_F0_F0 ) {
                        backgroundColor = 0xFF_F4_F0_F0;
                    } else {
                        backgroundColor = 0xFF_F0_F0_F0;
                    }
                }
                // Afficher les erreurs en rouge: une erreur apparaît si une valeur est présente
                // au moins deux fois dans la même ligne, colonne ou bloc.
                if (verifier()==1 )
                {

                  backgroundColor = Color.BLUE;
                  yes=false;

                }


                if ( plteau.cells[y][x].Vlasuppose > 0 ) {
                    for( int tx=0; tx<9; tx++ ) {
                        if ( tx != x &&
                                plteau.cells[y][tx].Vlasuppose == plteau.cells[y][x].Vlasuppose ) {
                            backgroundColor = Color.RED;
                            break;
                        }

                    }
                    if ( backgroundColor != 0xFF_FF_00_00 ) {
                        for (int ty = 0; ty < 9; ty++) {
                            if ( ty != y &&
                                    plteau.cells[ty][x].Vlasuppose == plteau.cells[y][x].Vlasuppose ) {
                                backgroundColor = 0xFF_FF_00_00;
                                break;
                            }
                        }
                    }

                    if ( backgroundColor != 0xFF_FF_00_00 ) {
                        int bx = x / 3;
                        int by = y / 3;
                        for (int dy = 0; dy < 3; dy++) {
                            for (int dx = 0; dx < 3; dx++) {
                                int tx = bx * 3 + dx;
                                int ty = by * 3 + dy;
                                if ( tx != x && ty != y &&
                                        plteau.cells[ty][tx].Vlasuppose == plteau.cells[y][x].Vlasuppose ) {
                                    backgroundColor = 0xFF_FF_00_00;
                                    break;
                                }
                            }
                        }
                    }
                }

                // Changer la couleur de la valeur actuellement sélectionnée
                if ( plteau.getValuer_S() > 0 &&
                        plteau.cells[y][x].Vlasuppose == plteau.getValuer_S() ) {
                    backgroundColor = 0xFF_C7_DA_F8;//0xFF_C7_DA_F8
                }



                // Dessine l'arrière-plan de la cellule actuelle
                paint.setColor( backgroundColor );
                canvas.drawRect(x * largeCel,
                        y * largeCel ,
                        (x+1) * largeCel,
                        (y+1) * largeCel,
                        paint);

                if (plteau.cells[y][x].Vlasuppose != 0) {

                    // Dessinez la valeur supposée pour la cellule.
                    paint.setColor(Color.GREEN);
                    paint.setTextSize( largeCel*0.9f );
                    canvas.drawText("" + plteau.cells[y][x].Vlasuppose,
                            x * largeCel + largeCel / 2,
                            y * largeCel + largeCel * 0.75f, paint);

                } else {


                }
            }
        }

        //--- Tracez les lignes de la grille ---
        paint.setColor( Color.BLACK );
        paint.setStrokeWidth( SeparGri/2 );
        for( int i=0; i<=9; i++ ) {
            canvas.drawLine( i*largeCel+2, 0, i*largeCel+2, largeCel*9+5, paint );
            canvas.drawLine( 0,i*largeCel+2, largeCel*9+2, i*largeCel+5, paint );
        }
        paint.setColor( 0xff474200 );
        paint.setStrokeWidth( SeparGri );
        //grande ligne de separation
        for( int i=0; i<=3; i++ ) {
            canvas.drawLine( i*(largeCel*3), 0, i*(largeCel*3), largeCel*9, paint ); //desessiner la ligne
            canvas.drawLine( 0,i*(largeCel*3), largeCel*9, i*(largeCel*3), paint );
        }
        //canvas c'est la zone de tracer

        // --- Dessine une bordure pour la cellule actuellement sélectionnée ---
        if ( plteau.celulleX != -1 && plteau.celulleY != -1 ) {
            paint.setColor( Color.DKGRAY ); //entoure la case selectioner
            paint.setStrokeWidth( SeparGri * 1.8f ); //epiceur de tracer
            paint.setStyle( Paint.Style.STROKE );
            canvas.drawRect( plteau.celulleX * largeCel,
                    plteau.celulleY * largeCel,
                    (plteau.celulleX+1) * largeCel,
                    (plteau.celulleY+1) * largeCel,
                    paint);
            paint.setStyle( Paint.Style.FILL );
            paint.setStrokeWidth( 3 );
        }

        // --- Barre de boutons ---
        float buttonsTop = 9*largeCel + SeparGri/2; //ceparrer les la griller et les boutons

        paint.setColor(Color.LTGRAY);
        canvas.drawRect(0, buttonsTop, largeur, getHeight(), paint);
        float boutonGauche = buttonMarge;
        float boutonHaut = buttonsTop + buttonMarge;

        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(boutonLarge * 0.7f);

        for (int i = 1; i <= 9; i++) {
            paint.setColor( 0xff444444 );
            RectF rectF = new RectF(boutonGauche, boutonHaut,
                    boutonGauche + boutonLarge, boutonHaut + boutonLarge);
            canvas.drawRoundRect(rectF, buttonRayon, buttonRayon, paint);

            paint.setColor( Color.BLACK );
            canvas.drawText("" + i, rectF.centerX(), rectF.top + rectF.height() * 0.75f, paint); //images des numéros en bas
            //je vais tracer mes  boutoms
            if (i != 6) { //
                boutonGauche += boutonLarge + buttonMarge;
            } else  {
                boutonGauche = buttonMarge;
                boutonHaut += boutonLarge + buttonMarge;
            }
        }

        int imageMargin = (int) (boutonLarge * 0.1f); //image de gomme
        int timer = (int) (boutonLarge * 0.1f);



        // --- gomme ---
        paint.setColor(Color.BLACK);
        RectF rectF = new RectF( boutonGauche, boutonHaut,
                boutonGauche + boutonLarge, boutonHaut + boutonLarge );
        canvas.drawRoundRect( rectF, buttonRayon, buttonRayon, paint );
        canvas.drawBitmap( gommeBitmap,
                boutonGauche + imageMargin, boutonHaut + imageMargin, paint );
        boutonGauche += boutonLarge +buttonMarge;


    }
    // --- Gestionnaires d'événements ---
    // Remplacer la vue
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return gestureDetector.onTouchEvent(event);

    }

    // Remplacer par OnGestureDectector
    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        RectF rectF;

        // --- Vérifier le clic de cellule de la grille ---
        if ( e.getY() < largeur ) {
            int cellX = (int)( e.getX() / largeCel );
            int cellY = (int)( e.getY() / largeCel );

            plteau.celulleX = cellX;
            plteau.celulleY = cellY;
            postInvalidate();
            return true;
        }

        float boutonGauche = buttonMarge;
        float boutonHaut = 9 * largeCel + SeparGri / 2;

        int c=Color.YELLOW;

        rectF = new RectF(boutonGauche, boutonHaut, boutonGauche + boutonLarge, boutonHaut + boutonLarge);
        int kk;
        if ( plteau.celulleX != -1 && plteau.celulleY != -1 ) {

            // --- Vérifiez les touches numériques --- et la fin de partie !
            for (int i = 1; i <= 9; i++) {
                rectF = new RectF(boutonGauche, boutonHaut, boutonGauche + boutonLarge, boutonHaut + boutonLarge);
                if (rectF.contains(e.getX(), e.getY()) &&PlateauJeux.veriffication(plteau)==0 ) {
                    plteau.InsererVal(i);
                    for(int h=1;h<9;h++)
                    {
                     for(int j=1;j<9;j++)
                         {
                             if ( plteau.cells[h][j].Vlasuppose!=plteau.cells[h][j].valeurReel){
                                     g=0;
                                     break;

                         }
                             else{
                                 g=1;
                                 scoor++;
                             }
                         }
                     }

                    postInvalidate();

                        return true;

                }

                if (i != 6) {
                    boutonGauche += boutonLarge + buttonMarge;
                } else {
                    boutonGauche = buttonMarge;
                    boutonHaut += boutonLarge + buttonMarge;
                }

            }

            // --- bouton gomme ---
            rectF = new RectF(boutonGauche, boutonHaut, boutonGauche + boutonLarge, boutonHaut + boutonLarge);
            if (rectF.contains(e.getX(), e.getY())) {
                plteau.EffacerCel();
                postInvalidate();
                return true;

            }
            boutonGauche += boutonLarge + buttonMarge;
        }

        //--- bouton crayon ---
        rectF = new RectF( boutonGauche, boutonHaut, boutonGauche+boutonLarge, boutonHaut+boutonLarge );
        if ( rectF.contains( e.getX(), e.getY() ) ) {
            plteau.G_Nombre = ! plteau.G_Nombre;
            postInvalidate();

            return true;
        }

        return true;

    }


     public static int getG()
     {
         return g;
     }



    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {



    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }


}
