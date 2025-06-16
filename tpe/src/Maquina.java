

public class Maquina {
    private String nMaquina;
    private int piezas;

    public Maquina(String nMaquina, int piezas) {
        this.nMaquina = nMaquina;
        this.piezas = piezas;
    }

    @Override
    public String toString() {
        return "Maquina [nMaquina=" + nMaquina + ", piezas=" + piezas + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Maquina otra = (Maquina) obj;
        return this.nMaquina == otra.nMaquina && this.piezas == otra.piezas;
    }

    public String getnMaquina() {
        return nMaquina;
    }

    public int getPiezas() {
        return piezas;
    }

    

}
