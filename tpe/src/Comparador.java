
import java.util.Comparator;

public abstract class Comparador implements Comparator<Maquina> {

    @Override
    public abstract int compare(Maquina m1, Maquina m2);
    
}
