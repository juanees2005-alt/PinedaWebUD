package asp.modelo;

// Autor: Juan Esteban Pineda Gonzalez
// Clase DTO que representa un CDT (Certificado de Depósito a Término).
// Contiene los datos de entrada y los resultados del cálculo financiero.

import java.io.Serializable;

public class CDT implements Serializable {

    private static final long serialVersionUID = 1L;

    private double capital;       // Monto invertido
    private double tasaAnual;     // Tasa de interés anual en %
    private int    plazo;         // Plazo en días

    // Resultados calculados
    private double interesBruto;
    private double retencion;     // Retención en la fuente (4% si interés > 1.000.000)
    private double interesNeto;
    private double totalRecibido;

    public CDT() {}

    public CDT(double capital, double tasaAnual, int plazo) {
        this.capital   = capital;
        this.tasaAnual = tasaAnual;
        this.plazo     = plazo;
    }

    // ── Getters y Setters ──────────────────────────────────────

    public double getCapital()              { return capital; }
    public void   setCapital(double v)      { this.capital = v; }

    public double getTasaAnual()            { return tasaAnual; }
    public void   setTasaAnual(double v)    { this.tasaAnual = v; }

    public int    getPlazo()                { return plazo; }
    public void   setPlazo(int v)           { this.plazo = v; }

    public double getInteresBruto()         { return interesBruto; }
    public void   setInteresBruto(double v) { this.interesBruto = v; }

    public double getRetencion()            { return retencion; }
    public void   setRetencion(double v)    { this.retencion = v; }

    public double getInteresNeto()          { return interesNeto; }
    public void   setInteresNeto(double v)  { this.interesNeto = v; }

    public double getTotalRecibido()        { return totalRecibido; }
    public void   setTotalRecibido(double v){ this.totalRecibido = v; }

    @Override
    public String toString() {
        return "CDT [capital=" + capital + ", tasa=" + tasaAnual
             + "%, plazo=" + plazo + " días, interesNeto=" + interesNeto + "]";
    }
}
