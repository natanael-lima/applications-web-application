# Sistema de Autogestión de Postulaciones Laborales

Este proyecto es un sistema web diseñado para gestionar postulaciones a ofertas laborales en una empresa. El sistema permite la publicación de ofertas laborales asociadas a diferentes sectores, y brinda a los usuarios visitantes la posibilidad de registrarse y postularse a las ofertas disponibles. Los postulantes pueden cargar sus datos personales y la documentación digital requerida, y hacer seguimiento al estado de sus postulaciones. Además, cuenta con un módulo administrativo donde el personal de reclutamiento puede gestionar las postulaciones recibidas.

## Características Principales

- **Publicación de Ofertas Laborales:** Las empresas pueden publicar y gestionar ofertas laborales, categorizadas por diferentes sectores.
- **Registro de Usuarios:** Los visitantes pueden registrarse para crear un perfil, lo que les permite postularse a las ofertas laborales disponibles.
- **Postulación y Seguimiento:** Los postulantes pueden cargar sus datos personales y la documentación requerida para cada oferta laboral. Además, pueden hacer un seguimiento del estado de sus postulaciones.
- **Gestión Administrativa:** El personal de reclutamiento tiene acceso a un módulo administrativo donde pueden visualizar, aceptar o rechazar postulaciones.

## Tecnologías Utilizadas

- **Backend:**
  - [Java Spring Boot](https://spring.io/projects/spring-boot): Marco de trabajo para el desarrollo de aplicaciones Java.
  - [Hibernate](https://hibernate.org/): Framework de mapeo objeto-relacional para la persistencia de datos en la base de datos.

- **Frontend:**
  - [Thymeleaf](https://www.thymeleaf.org/): Motor de plantillas de Java para renderizar vistas HTML.
  - [HTML](https://developer.mozilla.org/en-US/docs/Web/HTML): Lenguaje de marcado para estructurar las páginas web.
  - [Bootstrap](https://getbootstrap.com/): Framework de CSS para el diseño de interfaces responsivas.

- **Base de Datos:**
  - [MySQL](https://www.mysql.com/): Sistema de gestión de bases de datos relacional utilizado para almacenar la información de las ofertas, usuarios y postulaciones.

## Instalación y Configuración

1. **Clonar el Repositorio:**

   ```bash
   git clone https://github.com/natanael-lima/applications-web-application.git
   cd applications-web-application
2. **Configurar la Base de Datos:
   - Asegúrate de tener MySQL instalado y en ejecución.
   - Crea una base de datos en MySQL para la aplicación:
   - Configura las credenciales de la base de datos en el archivo application.properties de Spring Boot
3. ** Compilar y Ejecutar la Aplicación o Desde Spring Tool Suit:
   ```bash
   mvn clean install
   mvn spring-boot:run
5. **Acceder a la Aplicación:
   ```bash
   http://localhost:8080
## Contribuciones

Las contribuciones son bienvenidas. Si deseas contribuir a este proyecto, por favor realiza un fork del repositorio, crea una rama con tus cambios y envía un pull request.

## Contacto

Si tienes alguna pregunta o sugerencia, no dudes en ponerte en contacto.

- **Autor:** Natanael Lima
- **Repositorio:** [GitHub](https://github.com/natanael-lima/applications-web-application)

---

Este README ofrece una guía clara y estructurada para comprender, instalar y contribuir al proyecto, además de proporcionar información sobre las tecnologías utilizadas y cómo ponerse en contacto para cualquier consulta.

