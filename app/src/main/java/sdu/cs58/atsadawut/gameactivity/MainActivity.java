package sdu.cs58.atsadawut.gameactivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    //Explicit
    Button btn1,btn2,btn3, btn4;
    ImageView questionImageView;
    MediaPlayer mediaPlayer; //เล่นไฟล์เสียง
    ImageButton volumnImageButton;
    int questionCount = 1; //ตัวแปรเก็บจำนวนข้อคำถาม
    ArrayList<Integer> qID = new ArrayList<Integer>(); //ตัวแปรสุ่มคำถาม เป็นชนิดarray
    String answer;//เก็บคำตอบที่userตอบ
    int score = 0;//ค่าเริ่มต้นที่0<รวมคะแนน>
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ผูก element บน java
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        questionImageView = findViewById(R.id.imvQuestion);
        volumnImageButton = findViewById(R.id.imbVolumn);

        //วนนับจำนวนคำถามว่าครบจำนวนข้อที่ตั้งไว้หรือยัง
        for (int i = 1; i <= questionCount; i++) {
            qID.add(i);
        }
        Collections.shuffle(qID);//กำหนดให้ random คำถาม
        setQuestion(qID.remove(0));
    }//end onCreate

    private void setQuestion(Integer qID) {//ใช้สำหรับกำหนดข้อคำถามและเฉลยในแต่ละข้อ
        if (qID == 1) {
            answer = "นก";
            questionImageView.setImageResource(R.drawable.bird);
            mediaPlayer = MediaPlayer.create(this, R.raw.bird);

            ArrayList<String> choice = new ArrayList<String>();//กำหนดการrandom choice
            choice.add("นก");
            choice.add("ช้าง");
            choice.add("แมว");
            choice.add("ยุง");
            Collections.shuffle(choice);//random choice
            btn1.setText(choice.remove(0));
            btn2.setText(choice.remove(0));
            btn3.setText(choice.remove(0));
            btn4.setText(choice.remove(0));
        }
    }//end setQuestion
    public void choiceAns(View view) {//ตรวจคำตอบ
        Button button = (Button) view;
        String buttonstring = button.getText().toString(); //รับข้อความบนปุ่มไปไว้ในตัวแปร button string ตามที่ user กด
        if (buttonstring.equals(answer)){
            score++;
        }
        if (qID.isEmpty()){//ถ้าทำครบทุกข้อ qID จะเป็นค่าว่าง ให้แสดงคะแนนออกมา
            dialogboxscore(); //method คะแนนรวม

        }else {
            setQuestion(qID.remove(0));
        }
    }//method choiceAns
    private void dialogboxscore() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("สรุปคะแนน");
        builder.setMessage(" ได้คะแนน " + score + " คะแนน ")
            .setCancelable(false)
            .setPositiveButton("ออกจากเกม", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }//จบโปรแกรม
            })
                  .setNegativeButton("เล่นอีกครั้ง", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                         Intent intent = getIntent();
                         finish();
                         startActivity(intent);

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
    }//end dialogboxscore

    public void playSound(View view) { //playSound method
        mediaPlayer.start();

    }//end method playsound
}