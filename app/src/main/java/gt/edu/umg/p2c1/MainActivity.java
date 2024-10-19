package gt.edu.umg.p2c1;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import gt.edu.umg.p2c1.BaseDatos.DbHelper;

public class MainActivity extends AppCompatActivity {
    Button bntSaludo,btnCrearDb, btnGuardar;
    TextView tvSaludo, txtNombre, txtTelefono, txtEmail;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        bntSaludo = findViewById(R.id.btnSaludo);
        tvSaludo = findViewById(R.id.tvSaludo);
        btnCrearDb = findViewById(R.id.btnCrearDb);

        bntSaludo.setOnClickListener(view -> {
            Toast.makeText(this, "Aviso Bryan", Toast.LENGTH_SHORT).show();
            tvSaludo.setText("Hola Bryan");
        });
        //CREAR DB
        btnCrearDb.setOnClickListener(view -> {
            //crear la basse de datos
            DbHelper dbHelper = new DbHelper( this);
            dbHelper.getWritableDatabase();
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            if (db!=null) {
                Toast.makeText(this, "Base de datos creada", Toast.LENGTH_SHORT).show();
                tvSaludo.setText("base de datos creada");

            }else {
                Toast.makeText(this, "Error al crear la base de datos", Toast.LENGTH_SHORT).show();
                tvSaludo.setText("Error al crear la base de datos");
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}