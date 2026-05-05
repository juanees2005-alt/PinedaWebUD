package asp.bean;

// Autor: Juan Esteban Pineda Gonzalez
// Bean JSF que actúa como controlador del simulador de CDT.
// ViewScoped: mantiene el estado durante las interacciones de la misma vista.

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import asp.modelo.CDT;
import asp.modelo.CDTServicio;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named("cdtBean")
@SessionScoped
public class CDTBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private CDT dto       = new CDT();
    private CDT resultado = null;
    private boolean calculado = false;

    // Historial de simulaciones en la sesión
    private List<CDT> historial = new ArrayList<>();

    // ──────────────────────────────────────────────
    //  Acción principal: calcular CDT
    // ──────────────────────────────────────────────
    public String calcular() {
        FacesContext ctx = FacesContext.getCurrentInstance();

        // Validación: capital mínimo
        if (dto.getCapital() < 100_000) {
            ctx.addMessage("cdtForm:capital",
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Capital inválido", "El capital mínimo es $100.000 COP."));
            calculado = false;
            return null;
        }

        // Validación: tasa entre 0.1% y 30%
        if (dto.getTasaAnual() < 0.1 || dto.getTasaAnual() > 30) {
            ctx.addMessage("cdtForm:tasa",
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Tasa inválida", "La tasa anual debe estar entre 0,1% y 30%."));
            calculado = false;
            return null;
        }

        // Validación: plazo entre 30 y 1800 días
        if (dto.getPlazo() < 30 || dto.getPlazo() > 1800) {
            ctx.addMessage("cdtForm:plazo",
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Plazo inválido", "El plazo debe estar entre 30 y 1800 días."));
            calculado = false;
            return null;
        }

        // Calcular
        resultado = CDTServicio.calcular(new CDT(dto.getCapital(), dto.getTasaAnual(), dto.getPlazo()));
        historial.add(0, resultado);  // Insertar al inicio del historial
        calculado = true;
        return null;
    }

    // Limpiar formulario
    public String limpiar() {
        dto      = new CDT();
        resultado = null;
        calculado = false;
        return null;
    }

    // ──────────────────────────────────────────────
    //  Getters y Setters
    // ──────────────────────────────────────────────

    public CDT getDto()                  { return dto; }
    public void setDto(CDT dto)          { this.dto = dto; }

    public CDT getResultado()            { return resultado; }
    public boolean isCalculado()         { return calculado; }

    public List<CDT> getHistorial()      { return historial; }
}
