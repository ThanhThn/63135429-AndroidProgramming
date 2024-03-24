package ntu.MSSV63135429.intromysefl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txtUsername, txtPassword;
    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        txtPassword = findViewById(R.id.txtPassword);
        txtUsername = findViewById(R.id.txtUserName);

    }

    public void login(View view){
        username = txtUsername.getText().toString();
        password = txtPassword.getText().toString();
        if(username.equals("root") && password.equals("123456789")){

        }else {
            Toast.makeText(MainActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
        }
    }
}