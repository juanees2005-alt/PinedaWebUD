# PinedaWebUD 🎓

**Autor:** Juan Esteban Pineda González  
**Asignatura:** Programación Web — Grupo 1  
**Universidad:** Universidad Distrital Francisco José de Caldas  
**Año:** 2026

---

## Descripción

Aplicación web desarrollada con **Java EE / Jakarta Faces (JSF)** y **Maven**, que permite el registro y consulta de aspirantes universitarios. El sistema incluye validaciones de formulario, arquitectura en capas MVC y un simulador de CDT (Certificado de Depósito a Término).

---

## Tecnologías

| Tecnología | Versión |
|---|---|
| Java | 21 |
| Jakarta EE | 10.0.0 |
| Jakarta Faces (JSF) | 4.0.1 |
| CDI (Weld) | 4.0.2 |
| Maven | 3.x |

---

## Estructura del proyecto (MVC)

```
PinedaWebUD/
├── src/main/java/asp/
│   ├── modelo/          ← Modelo (DTOs y DAOs)
│   │   ├── Persona.java
│   │   ├── Aspirante.java
│   │   ├── AspiranteDAO.java
│   │   ├── ProgAcad.java
│   │   └── ProgAcadDAO.java
│   └── bean/            ← Controlador (Managed Beans JSF)
│       └── AspiranteBean.java
└── src/main/webapp/     ← Vista (XHTML / JSF)
    ├── index.xhtml
    ├── registro.xhtml
    ├── admin.xhtml
    ├── cdt.xhtml
    └── resources/css/
        └── estilos.css
```

---

## Funcionalidades

- ✅ Registro de aspirantes con validaciones completas
- ✅ Listado de aspirantes registrados (vista administrador)
- ✅ Selección de programa académico
- ✅ Simulador de CDT con lógica de negocio
- ✅ Arquitectura MVC con capas bien definidas
- ✅ Interfaz responsiva con estilos propios

---

## Cómo ejecutar

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/TU_USUARIO/PinedaWebUD.git
   ```
2. Importar como proyecto Maven en Eclipse o IntelliJ.
3. Desplegar en un servidor compatible con Jakarta EE 10 (ej. WildFly, Payara, TomEE).
4. Acceder desde el navegador a `http://localhost:8080/PinedaWebUD/`

---

## Commits del proyecto

| Commit | Descripción |
|---|---|
| `origen` | Subida inicial del proyecto base (Registro Aspirante) |
| `jp_logica_negocio` | Diagrama de clases — lógica de negocio del simulador CDT |
| `jp_mvc` | Diseño en capas MVC del simulador CDT |
| `jp_frontend` | Front-End con validaciones y presentación final |

---

## Simulador CDT

El simulador calcula el rendimiento de un Certificado de Depósito a Término con base en:
- Capital inicial
- Tasa de interés anual (%)
- Plazo en días

**Fórmula:** `Interés = Capital × (Tasa / 100) × (Días / 365)`
