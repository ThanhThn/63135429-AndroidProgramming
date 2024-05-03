package ntu.lhqthanh.usingsqllite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class BookAdapter extends BaseAdapter {
    List<Books> list;
    Context mContext;
    LayoutInflater inflater;

    public BookAdapter(List<Books> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BookHolder bookHolder = new BookHolder();
        if(convertView == null){
            convertView = inflater.inflate(R.layout.book_item, null);
            bookHolder.name = convertView.findViewById(R.id.name);
            bookHolder.page = convertView.findViewById(R.id.page);
            bookHolder.description = convertView.findViewById(R.id.description);
            bookHolder.price = convertView.findViewById(R.id.price);
            convertView.setTag(bookHolder);
        }else {
            bookHolder = (BookHolder)convertView.getTag();
        }
        Books book = list.get(position);
        bookHolder.name.setText(book.getBookName());
        bookHolder.price.setText(String.valueOf((float) book.getPrice()));
        bookHolder.description.setText(book.getDescription());
        bookHolder.page.setText(String.valueOf((int) book.getPage()));
        return convertView;
    }
    private class BookHolder{
        TextView name, page, price, description;
    }
}
