package gt.edu.umg.p2c1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import gt.edu.umg.p2c1.BaseDatos.DbContactos;
import gt.edu.umg.p2c1.BaseDatos.DbHelper;


public class NuevoActivity extends AppCompatActivity {

    Button btnNuevo;
    EditText txtNombre, txtTelefono, txtEmail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nuevo);

        btnNuevo = findViewById(R.id.btnGuardar);
        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtEmail = findViewById(R.id.txtEmail);

        btnNuevo.setOnClickListener(v -> {
            String nombre = txtNombre.getText().toString();
            String telefono = txtTelefono.getText().toString();
            String email = txtEmail.getText().toString();

            DbContactos dbContactos = new DbContactos(this);
            long id = dbContactos.insertaContacto(txtNombre.getText().toString(), txtTelefono.getText().toString(), txtEmail.getText().toString());
            if (nombre.isEmpty() || telefono.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                DbHelper dbHelper = new DbHelper(this);
                boolean insertado = dbHelper.insertarContacto(nombre, telefono, email);
                if (insertado) {
                    Toast.makeText(this, "Contacto guardado exitosamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Error al guardar el contacto", Toast.LENGTH_SHORT).show();
                }
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}