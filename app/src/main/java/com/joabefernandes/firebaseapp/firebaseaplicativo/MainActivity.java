package com.joabefernandes.firebaseapp.firebaseaplicativo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    //crianção de nós
    private DatabaseReference firebaseReference = FirebaseDatabase.getInstance().getReference();//referencia a raiz
    private DatabaseReference usuarioReference = firebaseReference.child("usuario").child("001");//vira filho do no raiz
    private DatabaseReference produtoReference = firebaseReference.child("produto");
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /***gravando dados no firebase*/
        Usuario usuario = new Usuario();
        usuario.setNome("Ana");
        usuario.setSobrenome("Fernandes");
        usuario.setSexo("M");
        usuario.setIdade(21);

        usuarioReference.child("001").setValue(usuario);//cria um nó do nó, em cascata, e depois atribui um valor para o ultimo elemento

        Produto produto1 = new Produto();
        produto1.setCor("vermelho");
        produto1.setDescricao("destinado a criancas");
        produto1.setFabricante("Apple");

        Produto produto2 = new Produto();
        produto2.setCor("Azul");
        produto2.setDescricao("destinado a idosos");
        produto2.setFabricante("Google");

        produtoReference.child("001").setValue(produto1);
        produtoReference.child("002").setValue(produto2);

       

        produtoReference.addValueEventListener(new ValueEventListener() { //dependendo da referencia ele atualiza tudo , se houver filhos. pode-se atualizar os filhos sem atualizar tudo.
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("produtos", dataSnapshot.getValue().toString());

		/* for (DataSnapshot dados : dataSnapshot.getChildren()) { // retriving objeto por objeto.
                       
			Produto produto = new Produto();
			produto = dados.getValue(Produto.class);


                 } */
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
