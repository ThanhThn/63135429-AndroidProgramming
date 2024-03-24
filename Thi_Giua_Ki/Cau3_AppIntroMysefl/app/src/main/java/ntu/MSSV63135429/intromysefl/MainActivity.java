package ntu.MSSV63135429.intromysefl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txtUsername, txtPassword;
    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtPassword = findViewById(R.id.txtPassword);
        txtUsername = findViewById(R.id.txtUserName);
    }

    public void login(){
        username = txtUsername.getText().toString();
        password = txtPassword.getText().toString();
        if(username.equals("root") && password.equals("123456789")){

        }else {
            Toast.makeText(MainActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
        }
    }
}