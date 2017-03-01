package com.example.kromanidis.translator;

import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final String ENGLISH = "English";
    public static final String GREEK = "Greek";
    public static final String GERMAN = "German";
    public static final String ITALIAN = "Italian";
    public static final String SPANISH = "Spanish";
    public static final String FRENCH = "French";
    public static final String JAPANESE = "Japanese";

    private String[] lang = new String[] {ENGLISH, GREEK, GERMAN, ITALIAN, SPANISH, FRENCH, JAPANESE};

    private Random random = new Random();
    private List<Words> words;
    private Words currentWord;
    private String language;
    private TextView original;
    private TextView translation;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        original = (TextView) findViewById(R.id.english_wrd);
        translation = (TextView) findViewById(R.id.translation);
        image = (ImageView) findViewById(R.id.image_src);

        buildData();
        language = ENGLISH;
        shuffle();
        refresh();

        final Button buttonShuffle = (Button) findViewById(R.id.btn_shuffle);

        assert buttonShuffle != null;
        buttonShuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shuffle();
                refresh();
            }
        });

        final Spinner languageSelect = (Spinner) findViewById(R.id.language_spinner);
        ArrayAdapter<String> adapter =  new ArrayAdapter<>(MainActivity.this,
                                                android.R.layout.simple_selectable_list_item, lang);
        assert languageSelect != null;
        languageSelect.setAdapter(adapter);

        languageSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                language = languageSelect.getSelectedItem().toString();
                refresh();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Button play = (Button) findViewById(R.id.btn_play);
        assert play != null;
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentWord == null) {
                    return;
                }
                final MediaPlayer mp = MediaPlayer.create(MainActivity.this,
                                        currentWord.getTranslationMap().get(language).getAudioID());
                mp.start();
            }
        });
    }

    private void shuffle() {
        currentWord = words.get(random.nextInt(words.size()));
    }

    private void refresh() {
        if (currentWord == null) {
            return;
        }
        if (language != null) {
            image.setBackground(ContextCompat.getDrawable(MainActivity.this,
                    currentWord.getTranslationMap().get(ENGLISH).getPhotoID()));
            original.setText(currentWord.getTranslationMap().get(ENGLISH).getText());
            translation.setText(currentWord.getTranslationMap().get(language).getText());
        }
    }

    public void buildData() {
        Words w = new Words();
        w.getTranslationMap().put(ENGLISH, new Translation("Bread", R.raw.en_bread, R.drawable.bread));
        w.getTranslationMap().put(GREEK, new Translation("Ψωμί", R.raw.gr_bread, R.drawable.bread));
        w.getTranslationMap().put(GERMAN, new Translation("Brot", R.raw.ger_bread, R.drawable.bread));
        w.getTranslationMap().put(ITALIAN, new Translation("Pane", R.raw.it_bread, R.drawable.bread));
        w.getTranslationMap().put(SPANISH, new Translation("Pan de molde", R.raw.sp_bread, R.drawable.bread));
        w.getTranslationMap().put(FRENCH, new Translation("Pain", R.raw.fr_bread, R.drawable.bread));
        w.getTranslationMap().put(JAPANESE, new Translation("ブレッド", R.raw.jp_bread, R.drawable.bread));

        Words w1 = new Words();
        w1.getTranslationMap().put(ENGLISH, new Translation("Milk", R.raw.en_milk, R.drawable.milk));
        w1.getTranslationMap().put(GREEK, new Translation("Γάλα", R.raw.gr_milk, R.drawable.milk));
        w1.getTranslationMap().put(GERMAN, new Translation("Milch", R.raw.ger_milk, R.drawable.milk));
        w1.getTranslationMap().put(ITALIAN, new Translation("Latte", R.raw.it_milk, R.drawable.milk));
        w1.getTranslationMap().put(SPANISH, new Translation("Leche", R.raw.sp_milk, R.drawable.milk));
        w1.getTranslationMap().put(FRENCH, new Translation("Lait", R.raw.fr_milk, R.drawable.milk));
        w1.getTranslationMap().put(JAPANESE, new Translation("ミルク", R.raw.jp_milk, R.drawable.milk));

        Words w2 = new Words();
        w2.getTranslationMap().put(ENGLISH, new Translation("Meat",R.raw.en_meat, R.drawable.meat));
        w2.getTranslationMap().put(GREEK, new Translation("Κρέας", R.raw.gr_meat, R.drawable.meat));
        w2.getTranslationMap().put(GERMAN, new Translation("Fleisch", R.raw.ger_meat, R.drawable.meat));
        w2.getTranslationMap().put(ITALIAN, new Translation("la Carne", R.raw.it_meat, R.drawable.meat));
        w2.getTranslationMap().put(SPANISH, new Translation("Carne", R.raw.sp_meat, R.drawable.meat));
        w2.getTranslationMap().put(FRENCH, new Translation("Moi à", R.raw.fr_meat, R.drawable.meat));
        w2.getTranslationMap().put(JAPANESE, new Translation("お肉", R.raw.jp_meat, R.drawable.meat));

        Words w3 = new Words();
        w3.getTranslationMap().put(ENGLISH, new Translation("Salad", R.raw.en_salad, R.drawable.salad));
        w3.getTranslationMap().put(GREEK, new Translation("Σαλάτα", R.raw.gr_salad, R.drawable.salad));
        w3.getTranslationMap().put(GERMAN, new Translation("Salat", R.raw.ger_salad, R.drawable.salad));
        w3.getTranslationMap().put(ITALIAN, new Translation("Insalate", R.raw.it_salad, R.drawable.salad));
        w3.getTranslationMap().put(SPANISH, new Translation("ensalada", R.raw.sp_salad, R.drawable.salad));
        w3.getTranslationMap().put(FRENCH, new Translation("Salade", R.raw.fr_salad, R.drawable.salad));
        w3.getTranslationMap().put(JAPANESE, new Translation("サラダ", R.raw.jp_salad, R.drawable.salad ));

        Words w4 = new Words();
        w4.getTranslationMap().put(ENGLISH, new Translation("Dessert", R.raw.en_dessert, R.drawable.dessert));
        w4.getTranslationMap().put(GREEK, new Translation("Επιδόρπιο", R.raw.gr_dessert, R.drawable.dessert));
        w4.getTranslationMap().put(GERMAN, new Translation("Dessert", R.raw.ger_dessert, R.drawable.dessert));
        w4.getTranslationMap().put(ITALIAN, new Translation("Dolce", R.raw.it_dessert, R.drawable.dessert));
        w4.getTranslationMap().put(SPANISH, new Translation("Postre", R.raw.sp_dessert, R.drawable.dessert));
        w4.getTranslationMap().put(FRENCH, new Translation("Dessert", R.raw.fr_dessert, R.drawable.dessert));
        w4.getTranslationMap().put(JAPANESE, new Translation("デザート", R.raw.jp_dessert, R.drawable.dessert));

        Words w5 = new Words();
        w5.getTranslationMap().put(ENGLISH, new Translation("Cheese", R.raw.en_cheese, R.drawable.cheese));
        w5.getTranslationMap().put(GREEK, new Translation("Τυρί", R.raw.gr_cheese, R.drawable.cheese));
        w5.getTranslationMap().put(GERMAN, new Translation("Käse", R.raw.ger_cheese, R.drawable.cheese));
        w5.getTranslationMap().put(ITALIAN, new Translation("Formaggio", R.raw.it_cheese, R.drawable.cheese));
        w5.getTranslationMap().put(SPANISH, new Translation("Qqueso", R.raw.sp_cheese, R.drawable.cheese));
        w5.getTranslationMap().put(FRENCH, new Translation("Fromage", R.raw.fr_cheese, R.drawable.cheese));
        w5.getTranslationMap().put(JAPANESE, new Translation("乾酪", R.raw.jp_cheese, R.drawable.cheese));

        Words w6 = new Words();
        w6.getTranslationMap().put(ENGLISH, new Translation("French Fries", R.raw.en_french_fries, R.drawable.fried_potatoes));
        w6.getTranslationMap().put(GREEK, new Translation("Τηγανιτές πατάτες", R.raw.gr_french_fries, R.drawable.fried_potatoes));
        w6.getTranslationMap().put(GERMAN, new Translation("Pommes Frittes", R.raw.ger_french_fries, R.drawable.fried_potatoes));
        w6.getTranslationMap().put(ITALIAN, new Translation("Patatine Fritte", R.raw.it_french_fries, R.drawable.fried_potatoes));
        w6.getTranslationMap().put(SPANISH, new Translation("Papas Fritas", R.raw.sp_french_fries, R.drawable.fried_potatoes));
        w6.getTranslationMap().put(FRENCH, new Translation("Frites", R.raw.fr_french_fries, R.drawable.fried_potatoes));
        w6.getTranslationMap().put(JAPANESE, new Translation("フライドポテト", R.raw.jp_french_fries, R.drawable.fried_potatoes));

        Words w7 = new Words();
        w7.getTranslationMap().put(ENGLISH, new Translation("Beer", R.raw.en_beer, R.drawable.beer));
        w7.getTranslationMap().put(GREEK, new Translation("Μπύρα", R.raw.gr_beer, R.drawable.beer));
        w7.getTranslationMap().put(GERMAN, new Translation("Bier", R.raw.ger_beer, R.drawable.beer));
        w7.getTranslationMap().put(ITALIAN, new Translation("Birra", R.raw.it_beer, R.drawable.beer));
        w7.getTranslationMap().put(SPANISH, new Translation("Cerveza", R.raw.sp_beer, R.drawable.beer));
        w7.getTranslationMap().put(FRENCH, new Translation("Bière", R.raw.fr_beer, R.drawable.beer));
        w7.getTranslationMap().put(JAPANESE, new Translation("麦酒", R.raw.jp_beer, R.drawable.beer));

        Words w8 = new Words();
        w8.getTranslationMap().put(ENGLISH, new Translation("Coffee", R.raw.en_coffee, R.drawable.coffee));
        w8.getTranslationMap().put(GREEK, new Translation("Καφές", R.raw.gr_coffee, R.drawable.coffee));
        w8.getTranslationMap().put(GERMAN, new Translation("Kaffee", R.raw.ger_coffee, R.drawable.coffee));
        w8.getTranslationMap().put(ITALIAN, new Translation("Caffè", R.raw.it_coffee, R.drawable.coffee));
        w8.getTranslationMap().put(SPANISH, new Translation("Café", R.raw.sp_coffee, R.drawable.coffee));
        w8.getTranslationMap().put(FRENCH, new Translation("Café", R.raw.fr_coffee, R.drawable.coffee));
        w8.getTranslationMap().put(JAPANESE, new Translation("珈琲", R.raw.jp_coffee, R.drawable.coffee));

        Words w9 = new Words();
        w9.getTranslationMap().put(ENGLISH, new Translation("Wine", R.raw.en_wine, R.drawable.wine));
        w9.getTranslationMap().put(GREEK, new Translation("Κρασί", R.raw.gr_wine, R.drawable.wine));
        w9.getTranslationMap().put(GERMAN, new Translation("Wein", R.raw.ger_wine, R.drawable.wine));
        w9.getTranslationMap().put(ITALIAN, new Translation("Vino", R.raw.it_wine, R.drawable.wine));
        w9.getTranslationMap().put(SPANISH, new Translation("Vino", R.raw.sp_wine, R.drawable.wine));
        w9.getTranslationMap().put(FRENCH, new Translation("du Vin", R.raw.fr_wine, R.drawable.wine));
        w9.getTranslationMap().put(JAPANESE, new Translation("葡萄酒", R.raw.jp_wine, R.drawable.wine));

        Words w10 = new Words();
        w10.getTranslationMap().put(ENGLISH, new Translation("Apple", R.raw.en_apple, R.drawable.apple));
        w10.getTranslationMap().put(GREEK, new Translation("Μήλο", R.raw.gr_apple, R.drawable.apple));
        w10.getTranslationMap().put(GERMAN, new Translation("Apfel", R.raw.ger_apple, R.drawable.apple));
        w10.getTranslationMap().put(ITALIAN, new Translation("Mela", R.raw.it_apple, R.drawable.apple));
        w10.getTranslationMap().put(SPANISH, new Translation("Manzana", R.raw.sp_apple,R.drawable.apple));
        w10.getTranslationMap().put(FRENCH, new Translation("Pomme", R.raw.fr_apple, R.drawable.apple));
        w10.getTranslationMap().put(JAPANESE, new Translation("アップル", R.raw.jp_apple, R.drawable.apple ));

        Words w11 = new Words();
        w11.getTranslationMap().put(ENGLISH, new Translation("Orange juice", R.raw.en_orange_juice, R.drawable.orange_juice));
        w11.getTranslationMap().put(GREEK, new Translation("Χυμός Πορτοκαλιού", R.raw.gr_orange_juice, R.drawable.orange_juice));
        w11.getTranslationMap().put(GERMAN, new Translation("Orangensaft", R.raw.ger_orange_juice, R.drawable.orange_juice));
        w11.getTranslationMap().put(ITALIAN, new Translation("Succo d'arancia", R.raw.it_orange_juice, R.drawable.orange_juice));
        w11.getTranslationMap().put(SPANISH, new Translation("Zumo de naranja", R.raw.sp_orange_juice, R.drawable.orange_juice));
        w11.getTranslationMap().put(FRENCH, new Translation("du jus d'orange", R.raw.fr_orange_juice, R.drawable.orange_juice));
        w11.getTranslationMap().put(JAPANESE, new Translation("オレンジジュース", R.raw.jp_orange_juice, R.drawable.orange_juice));

        words = new ArrayList<>();
        words.add(w);
        words.add(w1);
        words.add(w2);
        words.add(w3);
        words.add(w4);
        words.add(w5);
        words.add(w6);
        words.add(w7);
        words.add(w8);
        words.add(w9);
        words.add(w10);
        words.add(w11);
    }
}
