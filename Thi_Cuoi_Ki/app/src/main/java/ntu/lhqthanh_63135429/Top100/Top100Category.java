package ntu.lhqthanh_63135429.Top100;

import java.util.List;

public class Top100Category {
    String title;
    List<Top100> top100List;

    public Top100Category(String title, List<Top100> top100List) {
        this.title = title;
        this.top100List = top100List;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Top100> getTop100List() {
        return top100List;
    }

    public void setTop100List(List<Top100> top100List) {
        this.top100List = top100List;
    }
}
