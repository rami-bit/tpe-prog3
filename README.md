# Programación 3: Trabajo Practico Especial

El presente trabajo fue realizado como requisito de aprobacion del curso de [Programación 3](https://moodle.exa.unicen.edu.ar/course/view.php?id=1247).


## Contexto
En una fábrica de construcción de autopartes se dispone de un conjunto de máquinas capaces de producir un número determinado de piezas. Cada máquina produce un número establecido de piezas una vez que se pone en funcionamiento, esta cantidad de piezas se conoce de antemano y la máquina no puede detener su funcionamiento hasta completar esa cantidad. 
Por una cuestión de ahorro energético sólo una máquina puede estar funcionando a la vez. Por otro lado, no hay restricciones respecto a volver a poner en funcionamiento una máquina que ya ha estado en funcionamiento anteriormente. 

El objetivo del mismo consistió en resolver un problema, dentro de este contexto, mediante dos técnicas de programación: Backtracking y Greedy. 

## Enunciado

Dado un número determinado de piezas a producir se desea encontrar, de existir, la secuencia de máquinas que se deben usar para minimizar la cantidad de puestas en funcionamiento totales requeridas para completar la producción.

Por ejemplo, suponiendo las siguientes máquinas con la cantidad de piezas que produce cada una:

(M1, 7), (M2, 3), (M3, 4), (M4, 1) 

Si necesitamos producir 12 piezas una configuración óptima posible sería [M1 - M3 - M4]. También se podría haber propuesto una secuencia [M3 - M3 - M3]

## Estructura del Projecto

```
📂 Proyecto
│  
├── 📁 src                  # Código fuente principal
│   ├── 📄 Comparador.java   # Abstract Class para comparar Maquinas usando la interfaz Comparator
│   ├── 📄 ComparadorInt.java # Clase que implementa Comparador
│   ├── 📄 Fabrica.java      # Clase principal donde se resuelve el problema con Backtracking y Greedy
│   ├── 📄 Main.java         # Levanta el archivo de prueba y ejecuta el programa
│   ├── 📄 Maquina.java      # Clase que modela una maquina
│  
├── 📁 data                 # Archivos de datos
│   ├── 📄 prueba.txt       # Archivo de prueba para la ejecución del programa
```

### Resultado para el ejemplo adjuntado (prueba.txt)

![image](https://github.com/user-attachments/assets/4a89ec13-8bc3-4297-9206-f88198dc940b)
