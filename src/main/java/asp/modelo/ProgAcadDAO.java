package asp.modelo;

// Autor: Juan Esteban Pineda Gonzalez
// Clase DAO que contiene la lista estática de programas académicos ofertados.
// Incluye los 6 programas requeridos según el enunciado de la actividad.

import java.util.ArrayList;

public class ProgAcadDAO {

    public static ArrayList<ProgAcad> lista_P = new ArrayList<ProgAcad>();

    public static void cargaDatos() {
        if (lista_P.isEmpty()) {
            lista_P.add(new ProgAcad(10, "Ingeniería de Sistemas"));
            lista_P.add(new ProgAcad(20, "Ingeniería Industrial"));
            lista_P.add(new ProgAcad(30, "Matemática"));
            lista_P.add(new ProgAcad(40, "Administración de Empresas"));
            lista_P.add(new ProgAcad(50, "Derecho"));
            lista_P.add(new ProgAcad(60, "Ingeniería Telemática"));
        }
    }
}
