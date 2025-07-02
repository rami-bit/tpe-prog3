import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Scanner {
    
    public Fabrica construirEmpresa() {
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
