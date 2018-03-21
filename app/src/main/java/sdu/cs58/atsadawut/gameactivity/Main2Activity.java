package sdu.cs58.atsadawut.gameactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    EditText nameEditText ; //ชนิดตัวแปร และ ประกาศตัวแปร
    Button StartButton ;
    String nameString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // intial view
        nameEditText = findViewById(R.id.edtName);
        StartButton = findViewById(R.id.btnbutton);

        StartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameString = nameEditText.getText().toString().trim();  //ข้อความที่พิมพ์ใน edittext มากี่ตัวแปรใน namestring

                //check
                if (nameString.length() == 0){
                    Toast.makeText(getApplicationContext(), " กรุณากรอกชื่อด้วย " , Toast.LENGTH_SHORT).show();
                }else {
                    //เปิดหน้า game
                    Intent Startintent = new Intent(Main2Activity.this,MainActivity.class);
                    Startintent.putExtra("Name" , nameString);
                    startActivity(Startintent);
                }
            }
        });
    }//end method
}//end class
