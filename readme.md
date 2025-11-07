# Nutriplan

Nutriplan es un proyecto desarrollado en **Angular** y **Spring Boot**. Analiza tus datos y crea un plan balanceado para ti en segundos.

Este repositorio corresponde al **backend**.

## 🔗 Repositorios del Proyecto

| Parte    | Repositorio                                                                                    |
| -------- | ---------------------------------------------------------------------------------------------- |
| Backend  | [https://github.com/sxntiBG/nutriplan-backend](https://github.com/sxntiBG/nutriplan-backend)   |
| Frontend | [https://github.com/sxntiBG/nutriplan-frontend](https://github.com/sxntiBG/nutriplan-frontend) |

---

## ✅ Requisitos previos

Asegúrate de tener instalado en tu equipo:

| Herramienta           | Descripción                                       |
| --------------------- | ------------------------------------------------- |
| **Git**               | Para clonar el repositorio                        |
| **JDK 17 o superior** | Necesario para ejecutar la aplicación Spring Boot |
| **Maven**             | Para la gestión de dependencias                   |
| **MySQL**             | Base de datos donde se almacenará la información  |
| **Editor de código**  | Recomendado: IntelliJ IDEA, VS Code o Eclipse     |

---

## 📥 Clonar el proyecto

```bash
git clone https://github.com/sxntiBG/nutriplan-backend.git
```

Entrar a la carpeta del proyecto:

```bash
cd nutriplan
```

---

## 🔧 Instalación y ejecución

Maven se encargará de instalar automáticamente las dependencias necesarias.

Para ejecutar la aplicación:

```bash
mvn spring-boot:run
```

O desde tu IDE ejecutando la clase principal `NutriplanApplication`.

---

## 🔐 Configuración del archivo `.env`

Este proyecto utiliza variables de entorno para proteger credenciales.

Debes crear un archivo llamado `.env` en la raíz del proyecto con el siguiente contenido:

```
APP_NAME=nutriplan

DB_URL=jdbc:mysql://localhost:3306/nutriplan_db?serverTimezone=UTC
DB_USERNAME=<TU_USUARIO>
DB_PASSWORD=<TU_CONTRASEÑA>

JPA_DDL_AUTO=update
JPA_SHOW_SQL=true
```

Puedes usar el archivo `.env.example` como referencia para saber qué variables son necesarias.

> **Importante:** El archivo `.env` **no se debe subir al repositorio**.

---

## 🗄️ Base de Datos

Dentro de la carpeta `DB/` encontrarás los siguientes scripts:

| Archivo           | Descripción                                         |
| ----------------- | --------------------------------------------------- |
| **DB.sql**        | Crea la base de datos y todas las tablas necesarias |
| **Alimentos.sql** | Inserta los registros iniciales de alimentos        |

### 📌 Orden correcto de ejecución

1. Ejecuta primero **DB.sql** para generar la estructura de la base de datos.
2. Luego ejecuta **Alimentos.sql** para añadir la información inicial.

---

## 🎉 Listo

Con esto deberías poder ejecutar el proyecto correctamente.
Si necesitas ayuda o deseas extender el proyecto, abre un issue o pregunta dentro del equipo.
