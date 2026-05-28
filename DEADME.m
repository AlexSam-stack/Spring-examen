Datos del alumno (o pareja) y curso: Samaniego Inga Alex Piero 


◦ Listado de todos los endpoints con su método y ruta.
tengo 3 controllers :info  un empoint , reserva  7 empoints  y sala  5 empoints

InfoController
GET /api/info — Devuelve los metadatos generales de la API (nombre, versión y autor).

SalaController
GET /api/salas — Lista todas las salas registradas en el sistema.

GET /api/salas/{id} — Obtiene los detalles de una sala específica mediante su ID.

POST /api/salas — Crea y habilita una nueva sala de reuniones en el edificio.

PUT /api/salas/{id} — Actualiza todos los datos modificables de una sala existente.

DELETE /api/salas/{id} — Elimina una sala y sus reservas asociadas en cascada.

ReservaController
POST /api/reservas — Registra una nueva reserva forzando su estado inicial a "PENDIENTE".

GET /api/reservas/{id} — Devuelve una reserva específica buscando por su ID único.

GET /api/reservas — Filtra el listado de reservas por la intersección de estado, fecha y sala.

GET /api/reservas/sala/{salaId} — Recupera de forma exclusiva todas las reservas de una sala.

PUT /api/reservas/{id}/estado — Cambia el estado actual validando que sea PENDIENTE, CONFIRMADA o CANCELADA.

DELETE /api/reservas/{id} — Remueve por completo una reserva del sistema en memoria.

POST /api/reservas/{id}/comprobante — Procesa la subida de un archivo PDF y retorna sus metadatos básicos.

◦ Explicación breve (3-5 líneas) de la responsabilidad de cada capa.

la capa de modelo sirve para mapear la base de datos, reposiory sirve para acceder a la base datos, dtos sirven para decidir que mostrar al usuario final , mapper es el traductor universial entre dtos, service , la capa de negocios, y controller la exposicion de los empoints hacia los usuarios
