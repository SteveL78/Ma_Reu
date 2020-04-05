package lamzone.com.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import lamzone.com.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //    Menus de la Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.all_meetings:
                Toast.makeText(this, "Menu Toutes les réunions sélectionné", Toast.LENGTH_LONG).show();
                return true;


            case R.id.select_menu:
                Toast.makeText(this, "Menu Trier sélectionné", Toast.LENGTH_LONG).show();
                return true;
            case R.id.select_by_room:
                Toast.makeText(this, "Menu Trier par salle sélectionné", Toast.LENGTH_LONG).show();
                return true;
            case R.id.select_by_ascending_date:
                Toast.makeText(this, "Menu Trier par ordre croissant sélectionné", Toast.LENGTH_LONG).show();
                return true;
            case R.id.select_by_increasing_date:
                Toast.makeText(this, "Menu Trier par ordre décroissant sélectionné", Toast.LENGTH_LONG).show();
                return true;


            case R.id.filter_menu:
                Toast.makeText(this, "Menu Filtrer sélectionné", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.filter_by_date:
                Toast.makeText(this, "Menu Filtrer par date sélectionné", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.filter_by_room:
                Toast.makeText(this, "Menu Filtrer par salle sélectionné", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Mario:
                Toast.makeText(this, "Salle Mario sélectionnée", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);


        }


    }
}