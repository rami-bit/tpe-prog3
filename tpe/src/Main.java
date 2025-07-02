import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public static Fabrica construirEmpresa() {
        int piezasTotales = 0;
        List<Maquina> maquinas = new ArrayList<>();
        boolean esPrimerLinea = true;
        System.out.println(new File(".").getAbsolutePath());

        try (BufferedReader reader = new BufferedReader(new FileReader(".\\tpe\\\\data\\prueba.txt"))){
            String line;
            while ((line = reader.readLine()) != null) {
                if (esPrimerLinea) {
                    piezasTotales = Integer.parseInt(line);
                } else {
                    String[] configMaquina = line.split(",");
                    Maquina m = new Maquina(configMaquina[0], Integer.parseInt(configMaquina[1]));
                    maquinas.add(m);
                }
                esPrimerLinea = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Fabrica(maquinas, piezasTotales);
    }
}
