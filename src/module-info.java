import java.util.ArrayList;
import java.util.List;

// Clase abstracta Pieza
abstract class Pieza {
// Posición inicial (X,Y ó fila, columna)
    protected int fila;
    protected int columna;
    protected int valor; // Nueva propiedad valor

    public Pieza(int fila, int columna, int valor) {
        this.fila = fila;
        this.columna = columna;
        this.valor = valor;
    }

    public abstract List<String> movimientosPosibles();

    public int getValor() {
        return valor;
    }
}

// Clase Peon (hereda de Pieza)
class Peon extends Pieza {

    public Peon(int fila, int columna) {
        super(fila, columna, 1); // Valor de Peón es 1
    }

    @Override
    public List<String> movimientosPosibles() {
        List<String> movimientos = new ArrayList<>();
        // Movimiento hacia adelante
        if (fila < 8) {
            movimientos.add((fila + 1) + "," + columna);
        }
        // Movimiento doble desde la fila inicial
        if (fila == 2) {
            movimientos.add((fila + 2) + "," + columna);
        }
        return movimientos;
    }
}

// Clase Alfil (hereda de Pieza)
class Alfil extends Pieza {

    public Alfil(int fila, int columna) {
        super(fila, columna, 5); // Valor del Alfil es 5
    }

    @Override
    public List<String> movimientosPosibles() {
        List<String> movimientos = new ArrayList<>();
        // Movimientos diagonales (En el mismo color de la baldosa de arranque de la pieza)
        for (int i = 1; i < 8; i++) {
            // Diagonal superior derecha
            if (fila + i <= 8 && columna + i <= 8) {
                movimientos.add((fila + i) + "," + (columna + i));
            }
            // Diagonal superior izquierda
            if (fila + i <= 8 && columna - i >= 1) {
                movimientos.add((fila + i) + "," + (columna - i));
            }
            // Diagonal inferior derecha
            if (fila - i >= 1 && columna + i <= 8) {
                movimientos.add((fila - i) + "," + (columna + i));
            }
            // Diagonal inferior izquierda
            if (fila - i >= 1 && columna - i >= 1) {
                movimientos.add((fila - i) + "," + (columna - i));
            }
        }
        return movimientos;
    }
}

// Clase Caballo (hereda de Pieza)
class Caballo extends Pieza {

    public Caballo(int fila, int columna) {
        super(fila, columna, 4); // Valor del Caballo es 4
    }

    @Override
    public List<String> movimientosPosibles() {
        List<String> movimientos = new ArrayList<>();
        // Movimientos en "L" (De tener una pieza enfrente, aliada o no, esta la puede saltar)
        int[][] posiblesMovimientos =  {{2, 1}, {2, -1}, {-2, 1}, {-2, -1},
        								{1, 2}, {1, -2}, {-1, 2}, {-1, -2}};
        for (int[] mov : posiblesMovimientos) {
            int nuevaFila = fila + mov[0];
            int nuevaColumna = columna + mov[1];
            if (nuevaFila >= 1 && nuevaFila <= 8 && nuevaColumna >= 1 && nuevaColumna <= 8) {
                movimientos.add(nuevaFila + "," + nuevaColumna);
            }
        }
        return movimientos;
    }
}

// Clase principal para probar los resultados
public class Principal {

    public static void main(String[] args) {
        // Crear instancias de cada pieza
        Peon peon = new Peon(2, 4); // Peón en fila 2, columna 4
        Alfil alfil = new Alfil(4, 4); // Alfil en fila 4, columna 4
        Caballo caballo = new Caballo(3, 3); // Caballo en fila 3, columna 3

        // Mostrar movimientos posibles y valores para cada pieza
        System.out.println("Movimientos del Peón: " + peon.movimientosPosibles());
        System.out.println("Valor del Peón: " + peon.getValor());

        System.out.println("Movimientos del Alfil: " + alfil.movimientosPosibles());
        System.out.println("Valor del Alfil: " + alfil.getValor());

        System.out.println("Movimientos del Caballo: " + caballo.movimientosPosibles());
        System.out.println("Valor del Caballo: " + caballo.getValor());
    }
}
