package com.example.myapplication2;


import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MainActivity  extends Activity  implements OnTouchListener  {

    TextView tv;
    int x;
    int y;
    int sDownX;
    int sDownY;
    int sMoveX;
    int sMoveY;
    String message = "-";
    private static Socket socket;
    private static BufferedReader in;
    private static BufferedWriter out;
    int serverPort = 4004; // здесь обязательно нужно указать порт к которому привязывается сервер.
    String address = "192.168.43.149";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv = new TextView(this);
        connect();
        tv.setOnTouchListener(this);

        setContentView(tv);

    }

    public boolean onTouch(View v, MotionEvent event) {
        x = (int)event.getX();
        y = (int)event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // нажатие
                sDownX = x ;sDownY = y;
                break;

            case MotionEvent.ACTION_MOVE: // движение
                sMoveX = x - sDownX ; sMoveY = y - sDownY;
                break;
        }
        while(true) {
            send();
            return true;
        }

    }

    void connect() {
            Thread myThread = new Thread (new Runnable() {
            public void run() {

                try {
                        socket = new Socket(address, serverPort);
                } catch (IOException e) {
                    e.printStackTrace();
                    tv.setText("Error");
                }
            }
        });
     myThread.start();
    }

    public void send(){
            try {
                out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                out.write(sMoveX + ":" + sMoveY +"\n");
                out.flush();
            }catch (IOException e){
                close();
                tv.setText("Error");
            }
    }
    public void close(){
        try {
            socket.close();
        }catch (IOException e){
            tv.setText("Error");
        }
    }

}
