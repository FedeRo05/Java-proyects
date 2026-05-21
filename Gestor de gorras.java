import java.util.*;
import java.io.*;

class Gorra {
    private String marca;
    private String equipo;
    private boolean esPlana;

    public Gorra(String marca, String equipo, boolean esPlana) {
        this.marca = marca;
        this.equipo = equipo;
        this.esPlana = esPlana;
    }

    public String darMarca()  { return marca; }
    public String darEquipo() { return equipo; }
    public boolean darEsPlana() { return esPlana; }

    public void mostrarGorra() {
        System.out.println("Marca: " + marca + " | Equipo: " + equipo);
        System.out.println("Forma: " + (esPlana ? "Plana" : "Curva"));
    }
}

public class Gestor de gorras {

    static void guardarEnArchivo(List<Gorra> lista) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("coleccion.txt"));
        for (Gorra g : lista) {
            writer.write(g.darMarca() + " " + g.darEquipo() + " " + (g.darEsPlana() ? "1" : "0"));
            writer.newLine();
        }
        writer.close();
    }

    static void cargarDesdeArchivo(List<Gorra> lista) throws IOException {
        File archivo = new File("coleccion.txt");
        if (!archivo.exists()) return;  // Si no existe, no hay nada que cargar

        BufferedReader reader = new BufferedReader(new FileReader(archivo));
        String linea;
        while ((linea = reader.readLine()) != null) {
            String[] partes = linea.split(" ");
            if (partes.length == 3) {
                String m = partes[0];
                String e = partes[1];
                boolean p = partes[2].equals("1");
                lista.add(new Gorra(m, e, p));
            }
        }
        reader.close();
    }

    public static void main(String[] args) throws IOException {
        List<Gorra> gorras = new ArrayList<>();
        cargarDesdeArchivo(gorras);

        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("GESTOR DE GORRAS");
            System.out.println("1. Agregar gorra");
            System.out.println("2. Ver coleccion");
            System.out.println("3. Salir");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            if (opcion == 1) {
                System.out.print("Marca: ");
                String n = scanner.nextLine();
                System.out.print("Equipo: ");
                String e = scanner.nextLine();
                System.out.print("Es plana? (1=si / 0=no): ");
                int i = scanner.nextInt();
                gorras.add(new Gorra(n, e, i == 1));

            } else if (opcion == 2) {
                System.out.println("Gorras guardadas:");
                for (Gorra g : gorras) g.mostrarGorra();
            }

        } while (opcion != 3);

        guardarEnArchivo(gorras);
        System.out.println("Coleccion guardada en coleccion.txt");
    }
}
