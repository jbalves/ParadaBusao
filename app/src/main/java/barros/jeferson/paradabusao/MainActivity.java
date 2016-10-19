package barros.jeferson.paradabusao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by aluno on 18/10/2016.
 */

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "livro" ;
    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("livro");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    public void SalvarFirebase(View view) {

        Livro livro1 = new Livro();
        livro1.setTitulo("Introducao ao Linux");
        livro1.setAutor("Jeferson");
        livro1.setPaginas(160);

        Livro livro2 = new Livro();
        livro2.setTitulo("Introducao ao Firebase");
        livro2.setAutor("Jeferson");
        livro2.setPaginas(140);


        myRef.setValue(livro1);
        myRef.setValue(livro2);

    }

    public void LerDadosFirebase(View view) {
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Livro livro = dataSnapshot.getValue(Livro.class);
                Log.d(TAG, "Value is: " + livro.getAutor());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}
