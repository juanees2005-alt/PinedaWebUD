package asp.modelo;

// Autor: Juan Esteban Pineda Gonzalez
// Clase de servicio con la lógica de negocio del simulador CDT.
// Aplica la fórmula de interés simple y retención en la fuente colombiana.

public class CDTServicio {

    // Retención en la fuente: 7% sobre intereses (Colombia, rendimientos financieros)
    private static final double TASA_RETENCION = 0.07;

    /**
     * Calcula todos los valores del CDT y los almacena en el objeto.
     *
     * Fórmula interés simple:
     *   InterésBruto = Capital × (TasaAnual / 100) × (Plazo / 365)
     *
     * Retención en la fuente:
     *   Se aplica 7% si el interés bruto supera $1.000.000 COP
     */
    public static CDT calcular(CDT cdt) {
        double capital    = cdt.getCapital();
        double tasaAnual  = cdt.getTasaAnual();
        int    plazo      = cdt.getPlazo();

        // Interés bruto
        double interesBruto = capital * (tasaAnual / 100.0) * (plazo / 365.0);

        // Retención en la fuente (solo si supera el umbral)
        double retencion = 0;
        if (interesBruto > 1_000_000) {
            retencion = interesBruto * TASA_RETENCION;
        }

        double interesNeto   = interesBruto - retencion;
        double totalRecibido = capital + interesNeto;

        cdt.setInteresBruto(redondear(interesBruto));
        cdt.setRetencion(redondear(retencion));
        cdt.setInteresNeto(redondear(interesNeto));
        cdt.setTotalRecibido(redondear(totalRecibido));

        return cdt;
    }

    /** Redondea a 2 decimales */
    private static double redondear(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }
}
