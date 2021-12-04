import java.util.ArrayList;
import java.util.Collections;

/* custom class to add an onChanged listener to ArrayList */
public class PenguinList<T> {
    private ArrayList<T> list;
    private PenguinListListener listener;

    public PenguinList(PenguinListListener listener) {
        list = new ArrayList<>();
        this.listener = listener;
    }

    public void update() {
        listener.onListChanged();
    }

    public T get(int index) {
        return list.get(index);
    }

    public void clear() {
        list.clear();
        listener.onListChanged();
    }

    public void add(T t) {
        list.add(t);
        listener.onListChanged();
    }

    public int size() {
        return list.size();
    }
}
