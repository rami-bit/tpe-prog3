import java.util.HashMap;
import java.util.List;

public class Fabrica {
    private List<Maquina> maquinas;
    private int totalPiezasAproducir;
    private int llamadas;
    private int cantCandidatosSeleccionados;

    public Fabrica(List<Maquina> m, int totalPiezasAproducir) {
        this.maquinas = m;
        this.totalPiezasAproducir = totalPiezasAproducir;
        this.llamadas = 0;
        this.cantCandidatosSeleccionados = 0;
    }

    public void incrementarLLamadas() {
        this.llamadas++;
    }

    public void incrementarCandidatos() {
        cantCandidatosSeleccionados++;
    }

    public int getTotalPiezasAproducir() {
        return totalPiezasAproducir;
    }

    public int getLlamadas() {
        return llamadas;
    }

    public int getCantCandidatosSeleccionados() {
        return cantCandidatosSeleccionados;
    }

    /*
     * <Backtracking>
     * Mi Estrategia para llegar a la solución usando backtracking:
     * 
     * Itero sobre cada máquina y la agrego a una solución parcial, sin repetir
     * máquinas.
     * Para cada máquina, calculo cuántas veces puede entrar sin pasarse del total
     * de piezas a producir,
     * y la agrego con esa cantidad al mapa de solución parcial.
     * 
     * Si la suma parcial de piezas alcanza exactamente el total requerido
     * (piezasTotales),
     * verifico si la solución parcial actual es mejor que la mejor solución
     * encontrada hasta el momento.
     * En este caso, considero "mejor" a la que usa menor cantidad total de
     * maquinas.
     * Si lo es, reemplazo la solución guardada.
     * 
     * Podas:
     * - Si la suma parcial se pasa del objetivo, no tiene sentido seguir agregando
     * máquinas (corto la rama).
     * - Si ya tengo una solución válida y la solución parcial actual usa más
     * máquinas que esa, corto también,
     * porque aunque llegue a una solución válida, no será mejor que la ya
     * encontrada.
     * 
     * Reentrega: la verificación que hacia antes en el principio del método de
     * verificar si me paso de piezas a producir era inefciente ya que esta generaba 
     * llamados innecesarios cuando yo podría haber podado antes.
     * Ahora como voy construyendo la solucion es asi : Recorro cada maquina y por
     * cada maquina, recorro la maxima cantidad de veces que puedo usar la misma.En lugar de como antes que
     * agregaba directamente la cantidad posible de una maquina, dentro de este bucle recorro todas las posibilidades
     * desde 1 hasta ese maximo (llamando recursivamente al metodo). 
     * Si la suma de piezas que puedo producir se pasa de las que tengo que producir dentro de este bucle 
     * corto la rama sin tener que generar un salto mas de recursion.
     * Y por ultimo ordeno las maquinas de mayor a menor cantidad de piezas que
     * pueden producir antes de entrar al backtracking ya que en general
     * provee una mejor eficiencia que no tenerlo, mas en casos donde la respuesta
     * se encuentra en las maquinas que pueden producir muchas piezas.
     * 
     * 
     * 
     */
    public HashMap<String, Integer> solucionBackracking() {
        if (getTotalPiezasAproducir() <= 0 || maquinas.isEmpty()) {
            return null;
        }
        HashMap<String, Integer> sol = new HashMap<>();
        maquinas.sort(new ComparadorInt());
        backtracking(sol, 0, new HashMap<>(), 0);
        return sol;
    }

    public int cuantasVecesEntra(int piezasDisp, int piezasMaquina) {
        return piezasDisp / piezasMaquina;
    }

    private void backtracking(HashMap<String, Integer> sol, int sumaParcial, HashMap<String, Integer> solparcial,
            int indice) {
        this.incrementarLLamadas();
        /*
         * Aca lo siguiente que hago es porque antes al Comparar el tamaño del HashMap
         * no era lo correcto, porque solo dice cuántos tipos distintos de máquinas uso,
         * no cuántas veces en total las uso. Por
         * ejemplo,
         * una sola máquina usada 13 veces da .size() = 1, pero en realidad son 13
         * máquinas.
         * En cambio, otra solución con 2 tipos de máquinas puede usar solo 8 en total.
         * Entonces, investigando encontré una forma de sumar los valores del HashMap
         * para saber cuántas máquinas en total se están usando, y así comparar
         * correctamente.
         */
        int totalParcial = solparcial.values().stream().mapToInt(Integer::intValue).sum();
        int totalSol = sol.values().stream().mapToInt(Integer::intValue).sum();
        /*
         * Estas dos líneas recorren todos los valores de los HashMap para sumar cuántas
         * veces se usa cada máquina. El costo de cada línea es O(n)
         * El uso de Hashmap para el acceso y añadido de cada maquina a la solucion
         * compensa este gasto.
         */

        if (!sol.isEmpty() && totalParcial >= totalSol) {
            return;
        }

        if (sumaParcial == getTotalPiezasAproducir()) {
            if (sol.isEmpty() || totalParcial < totalSol) {
                sol.clear();
                sol.putAll(solparcial);
            }
        } else {
            for (int i = indice; i < maquinas.size(); i++) {
                Maquina m = maquinas.get(i);
                int maxVeces = cuantasVecesEntra(getTotalPiezasAproducir() - sumaParcial, m.getPiezas());
                for (int veces = 1; veces <= maxVeces; veces++) {
                    int nuevaSuma = sumaParcial + veces * m.getPiezas();
                    if (nuevaSuma > getTotalPiezasAproducir()) {
                        return; // podo si me paso
                    }
                    solparcial.put(m.getnMaquina(), veces);
                    backtracking(sol, nuevaSuma, solparcial, i + 1);
                    solparcial.remove(m.getnMaquina());
                }
            }
        }
    }
    // </Backtracking>//////////////

    /*
     * <Greedy>
     * Mi estrategia para llegar a la solución Greedy:
     * 
     * Mientras no llegue a producir todas las piezas necesarias, voy eligiendo la
     * mejor
     * máquina disponible para sumar a la solución.
     * 
     * ¿Cómo elijo la mejor?
     * - Como las máquinas están ordenadas de mayor a menor cantidad de piezas que
     * pueden procesar,
     * recorro esa lista y devuelvo la primera máquina que todavía puede entrar (es
     * decir,
     * que no se pase del total de piezas restantes por producir).
     * 
     * Luego:
     * - Si encuentro una máquina válida, calculo cuántas veces entra y la agrego a
     * la solución.
     * - Si no hay ninguna máquina que pueda seguir entrando, corto el ciclo y
     * retorno null (no hay solución para greedy).
     * 
     * 
     * 
     * Reentrega:
     * Ahora para no volver a recorrer por las mismas maquinas borro la maquina que ya eleji
     * por que se que no la voy a elegir en un futuro, ademas de la estrategía que tengo de añadir todas
     * las maquinas que entran en la solución.
     *
     */

    public Maquina mejorMaquina(int piezasDisp) {
        Maquina mejor;
        for (Maquina maquina : maquinas) {
            if (maquina.getPiezas() <= piezasDisp) {
                mejor = maquina;
                maquinas.remove(maquina); // la saco de la lista para no volver a usarla
                return mejor; // está ordenado de mayor a menor, así que esta es la mejor posible
            }
        }
        return null; // ninguna entra
    }

    public HashMap<String, Integer> solucionGreedy() {
        HashMap<String, Integer> sol = new HashMap<>();
        maquinas.sort(new ComparadorInt());
        if (getTotalPiezasAproducir() <= 0 || maquinas.isEmpty()) {
            return sol;
        }
        int piezasProducidas = 0;

        while (piezasProducidas < getTotalPiezasAproducir()) {
            incrementarCandidatos();
            Maquina m = mejorMaquina(getTotalPiezasAproducir() - piezasProducidas);
            if (m != null) {
                int cuantasVecesEntra = cuantasVecesEntra(getTotalPiezasAproducir() - piezasProducidas, m.getPiezas());
                sol.put(m.getnMaquina(), cuantasVecesEntra);
                piezasProducidas += (m.getPiezas() * cuantasVecesEntra);
            } else {
                return null;
            }
        }
        return sol;
    }
    // </Greedy>/////////////////
}