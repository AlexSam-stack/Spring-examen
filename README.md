# Spring-examen
Documentación del Proyecto
Datos del Alumno y Curso
Alumno(s): Alex Samaniego Inga 

Cómo Ejecutar el Proyecto
Para iniciar el servidor de desarrollo local, asegúrate de tener las dependencias instaladas y ejecuta el siguiente comando en la raíz del proyecto:

Bash
mvn spring-boot:run
Listado de Endpoints
GET /api/v1/recurso - Obtiene el listado completo de elementos.

GET /api/v1/recurso/{id} - Obtiene los detalles de un elemento específico por su ID.

POST /api/v1/recurso - Crea un nuevo registro con los datos enviados en el cuerpo (body).

PUT /api/v1/recurso/{id} - Actualiza por completo un registro existente.

DELETE /api/v1/recurso/{id} - Elimina un registro por su ID.

Arquitectura y Responsabilidad de Capas
Capa de Controlador (Controller): Es la puerta de entrada de la aplicación; se encarga de recibir las peticiones HTTP, validar que los datos entrantes cumplan con el formato esperado y retornar la respuesta final al cliente. Actúa como intermediario directo entre el usuario externo y la lógica del sistema.

Capa de Servicio (Service): Contiene la lógica central de la aplicación y las reglas de negocio que definen el comportamiento del sistema. Se encarga de procesar la información, coordinar los flujos de datos y aplicar las validaciones necesarias antes de interactuar con el almacenamiento.

Capa de Acceso a Datos (Repository/DAO): Se encarga exclusivamente de la comunicación directa con la base de datos. Su única responsabilidad es ejecutar las consultas, inserciones y actualizaciones requeridas, abstrayendo al resto de la aplicación de la tecnología de persistencia utilizada.
