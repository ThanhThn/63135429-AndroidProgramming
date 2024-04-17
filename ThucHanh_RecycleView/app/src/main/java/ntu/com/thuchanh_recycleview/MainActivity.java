package ntu.com.thuchanh_recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ArrayList<Exam> list;
    ExamAdapter adapter;
    RecyclerView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = setListExam();

        view = findViewById(R.id.examView);

        adapter = new ExamAdapter(this, list);

        RecyclerView.LayoutManager linear = new LinearLayoutManager(this);
        view.setLayoutManager(linear);
        view.setAdapter(adapter);
    }

    ArrayList<Exam> setListExam(){
        ArrayList <Exam> list1 = new ArrayList<>();
        Exam exam1 = new Exam("First Exam", "Best Of Luck", 2015, 5, 23);
        Exam exam2 = new Exam("Second Exam", "b of I", 2015, 6, 9);
        Exam exam3 = new Exam("My Test Exam", "This is testing exam...",2017, 8, 27);
        list1.add(exam1);
        list1.add(exam2);
        list1.add(exam3);
        return list1;

    }
}