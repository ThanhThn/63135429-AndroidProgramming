package ntu.MSSV63135429.intromysefl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText txtUsername, txtPassword;
    String username, password;
    Intent intent;

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
            intent = new Intent(LoginActivity.this, MainActivity.class);

            startActivity(intent);
        }else {
            Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
        }
    }
}