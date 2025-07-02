import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner();
        // Construimos la empresa a partir del archivo de configuración
        // que contiene las máquinas y la cantidad total de piezas a producir.
        Fabrica e = scanner.construirEmpresa();

        System.out.println("<Backtracking>");
        HashMap<String,Integer> solucionBacktracking = e.solucionBackracking();
        System.out.println("Solución obtenida (del tipo Numero maquina/Cuantas Veces Aparece): " + solucionBacktracking);
        int puestasFuncionamientoBack = solucionBacktracking.values().stream().mapToInt(Integer::intValue).sum();
        System.out.println("Piezas Producidas -> "+ e.getTotalPiezasAproducir() + " || " + "Maquinas puestas en funcionamiento: "+ puestasFuncionamientoBack);
        System.out.println("Cantidad de llamados recursivos: " + e.getLlamadas());
        System.out.println(" ");


        System.out.println("<Greedy>");
        HashMap<String,Integer> solucionGreedy = e.solucionGreedy();
        System.out.println("Solución obtenida (del tipo Numero maquina/Cuantas Veces Aparece): " + solucionGreedy);

        if (solucionGreedy == null) {
            System.out.println("No se pudo encontrar una solución válida con el algoritmo Greedy.");
        }else {
            int puestasFuncionamientoGreedy = solucionGreedy.values().stream().mapToInt(Integer::intValue).sum();
            System.out.println("Piezas Producidas -> "+ e.getTotalPiezasAproducir() + " || " + "Maquinas puestas en funcionamiento: "+ puestasFuncionamientoGreedy);
            System.out.println("Cantidad de candidatos elegidos: " + e.getCantCandidatosSeleccionados());
        }
    }

    
}
