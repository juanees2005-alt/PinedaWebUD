package asp.bean;

// Autor: Juan Esteban Pineda Gonzalez
// Bean JSF que gestiona el registro de aspirantes universitarios.
// Utiliza SessionScoped para mantener la lista de aspirantes activa durante toda la sesión.

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import asp.modelo.Aspirante;
import asp.modelo.AspiranteDAO;
import asp.modelo.ProgAcad;
import asp.modelo.ProgAcadDAO;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named("asp")
@SessionScoped
public class AspiranteBean implements Serializable {

    private static final long serialVersionUID = 1L;

    // DTO que recibe los datos del formulario
    private Aspirante dto = new Aspirante();

    // Referencia a las listas estáticas del DAO
    private ArrayList<Aspirante> listaAs = AspiranteDAO.lista_A;
    private ArrayList<ProgAcad> listaPa = ProgAcadDAO.lista_P;

    // Índice del programa seleccionado en el selectOneMenu
    private int idxPrograma = -1;

    public AspiranteBean() {
        // Carga los programas académicos al iniciar el bean
        ProgAcadDAO.cargaDatos();
    }

    // ──────────────────────────────────────────────
    //  Acción principal: registrar un aspirante
    // ──────────────────────────────────────────────
    public String registrar() {
        FacesContext ctx = FacesContext.getCurrentInstance();

        // Validación: identificación no puede ser 0 ni negativa
        if (dto.getId_p() <= 0) {
            ctx.addMessage("regForm:id_p",
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Identificación inválida", "Debe ser un número mayor a 0."));
            return null;
        }

        // Validación: número de identificación no repetido
        for (Aspirante a : listaAs) {
            if (a.getId_p() == dto.getId_p()) {
                ctx.addMessage("regForm:id_p",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Identificación duplicada", "Ya existe un aspirante con ese número."));
                return null;
            }
        }

        // Validación: programa seleccionado
        if (idxPrograma < 0 || idxPrograma >= listaPa.size()) {
            ctx.addMessage("regForm:pro_aca",
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Programa requerido", "Seleccione un programa académico."));
            return null;
        }

        // Asignar programa y fecha de registro
        dto.setPro_acad(listaPa.get(idxPrograma));
        dto.setFecha_reg(LocalDate.now());

        // Guardar en la lista estática
        listaAs.add(dto);
        System.out.println("Aspirante registrado: " + dto.toString());

        // Limpiar formulario para nuevo registro
        dto = new Aspirante();
        idxPrograma = -1;

        // Redirigir a la vista administrador
        return "admin?faces-redirect=true";
    }

    // Limpiar el formulario sin registrar
    public String limpiar() {
        dto = new Aspirante();
        idxPrograma = -1;
        return null;
    }

    // ──────────────────────────────────────────────
    //  Getters y Setters
    // ──────────────────────────────────────────────

    public Aspirante getDto() { return dto; }
    public void setDto(Aspirante dto) { this.dto = dto; }

    public ArrayList<Aspirante> getListaAs() { return listaAs; }
    public void setListaAs(ArrayList<Aspirante> listaAs) { this.listaAs = listaAs; }

    public ArrayList<ProgAcad> getListaPa() { return listaPa; }
    public void setListaPa(ArrayList<ProgAcad> listaPa) { this.listaPa = listaPa; }

    public int getIdxPrograma() { return idxPrograma; }
    public void setIdxPrograma(int idxPrograma) { this.idxPrograma = idxPrograma; }
}
