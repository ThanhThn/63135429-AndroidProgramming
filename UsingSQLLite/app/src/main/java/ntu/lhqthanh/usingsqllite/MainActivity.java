package ntu.lhqthanh.usingsqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    List<Books> arrayBooks = new ArrayList<>();
    ListView books;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        books = findViewById(R.id.list);
        db = openOrCreateDatabase("QLSach.db", MODE_PRIVATE, null);
        createDB();
        String sqlSelect = "SELECT BooksID,\n" +
                "       BookName,\n" +
                "       Page,\n" +
                "       Price,\n" +
                "       Description\n" +
                "  FROM BOOKS;";
        Cursor cs = db.rawQuery(sqlSelect, null);
        cs.moveToFirst();
        do {
            int id = cs.getInt(0);
            String name = cs.getString(1);
            int page = cs.getInt(2);
            float price = cs.getFloat(3);
            String description = cs.getString(4);
            arrayBooks.add(new Books(id, page, name, description, price));
        }while (cs.moveToNext());
        cs.close();
        BookAdapter adapter = new BookAdapter(arrayBooks,this);
        books.setAdapter(adapter);
    }
    public void createDB(){
        String sqlDropTableExit = "DROP TABLE IF EXISTS Books;\n";
        String sqlCreateTable = "CREATE TABLE BOOKS(BooksID integer PRIMARY KEY, " + "BookName text," + "Page integer, " + "Price Float," + "Description text)";
        String sqlThem1 = "INSERT INTO BOOKS VALUES(1, 'Java', 100, 9.99, 'sách về java');";
        String sqlThem2 = "INSERT INTO BOOKS VALUES(2, 'Android', 320, 19.00, 'Android cơ bản');";
        String sqlThem3 = "INSERT INTO BOOKS VALUES(3, 'Học làm giàu', 120, 0.99, 'sách đọc cho vui');";
        String sqlThem4 = "INSERT INTO BOOKS VALUES(4, 'Tử điển Anh-Việt', 1000, 29.50, 'Từ điển 100.000 từ');";
        String sqlThem5 = "INSERT INTO BOOKS VALUES(5, 'CNXH', 1, 1, 'chuyện cổ tích');";
        db.execSQL(sqlDropTableExit);
        db.execSQL(sqlCreateTable);
        db.execSQL(sqlThem1);
        db.execSQL(sqlThem2);
        db.execSQL(sqlThem3);
        db.execSQL(sqlThem4);
        db.execSQL(sqlThem5);

    }
}