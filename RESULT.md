# **Resultado Desafío de código Semi-Senior**

A continuación detallo la informacion relevante sobre el resultado de la prueba

## **🚀 Ejecución de los servicios **
Para levantar los servicios, ejecuta el siguiente script en la raíz del repositorio:  

```sh
./run.sh
```

Este script se encarga de:  
✅ Compilar los cuatro microservicios.  
✅ Levantar los servicios de infraestructura (Kafka, Zookeeper, PostgreSQL, Kafdrop).  
✅ Iniciar los microservicios (transaction-service, auth-service, mail-service, notification-service) contanerizados con Docker.  

---

## **📌 Visualización de eventos **
Para monitorear los eventos simulados, agregué Kafdrop, que corre en el puerto 9000.

📍 **URL de acceso a Kafdrop:**  
🔗 [http://localhost:9000](http://localhost:9000)  

### **📂 Topics Disponibles **
1️⃣ **transaction-events** → Simulaciones de transacciones.  
🔗 [Ver mensajes](http://localhost:9000/topic/transaction-events/allmessages)  

2️⃣ **login-events** → Simulaciones de inicio de sesión..  
🔗 [Ver mensajes](http://localhost:9000/topic/login-events/allmessages)  

---

## **🛠️ Servicios Implementados**
Los microservicios se dividen en dos tipos:  

🔹 **Producers**  
   - `transaction-service` (Spring Boot)  
   - `auth-service` (Spring Boot)  

🔹 **Consumers**  
   - `mail-service` (NestJS)  
   - `notification-service` (NestJS)  

---

## **🐄️ Base de Datos **
Utilicé una base de datos PostgreSQL para almacenar los eventos simulados:.  

| Evento                 | Tabla en la BD          |
|------------------------|------------------------|
| Eventos de transacciones | `transactions` |
| Eventos de inicio de sesión | `audit_login` |

---

## **📢 Detalle de los Servicios**

1. **transaction-service**: Creé una tarea programada que genera simulaciones de transacciones, las inserta en la tabla `transactions` y las envía al servidor de streaming.

2. **auth-service**: Implementé una tarea programada que genera simulaciones de inicios de sesión, las inserta en la tabla `audit_login` y las envía al servidor de streaming.

3. **mail-service**: Desarrollé un consumidor que se conecta al topic `transaction-events`, procesa los eventos y envía correos utilizando plantillas de Handlebars.

4. **notification-service**: Implementé un consumidor que se conecta al topic `login-events`, procesa los eventos y genera una estructura dummy para el envío de SMS.

## Configuración Adicional para el Envío de Correos

Para que el servicio `mail-service` funcione correctamente con el envío de correos, es necesario configurar las credenciales del servidor SMTP en el environment del servicio `email-service` del `docker-compose`.

---



