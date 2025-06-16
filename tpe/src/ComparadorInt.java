
public class ComparadorInt extends Comparador {

    @Override
    public int compare(Maquina m1, Maquina m2) {
        return m2.getPiezas() - m1.getPiezas();
    }
    
}
