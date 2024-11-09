# Convertidor de Monedas - CurrencyConverterApp

La aplicación `CurrencyConverterApp` es una aplicación de consola desarrollada en Java 17, diseñada para realizar conversiones de moneda precisas y en tiempo real utilizando datos actualizados de tasas de cambio de una API externa. Su diseño intuitivo y su interfaz de usuario basada en texto permiten a los usuarios realizar conversiones de manera rápida y precisa, accediendo a una variedad de monedas y tasas de cambio desde una única fuente de datos confiable.

## Descripción del Proyecto

`CurrencyConverterApp` proporciona conversiones de moneda de manera eficiente mediante el uso de un menú interactivo. Esta aplicación está diseñada para ejecutarse en cualquier máquina con Java 17 instalado y conexión a Internet, haciendo solicitudes a una API de tasas de cambio en tiempo real. La aplicación permite realizar las siguientes operaciones:

- **Conversión Personalizada entre Monedas**: Selección entre varios pares de monedas comunes para conversiones rápidas.
- **Conversión General entre Monedas**: Selección flexible entre cualquier par de monedas admitidas por la API.
- **Visualización de Tasas de Cambio**: Permite consultar rápidamente las tasas de cambio actuales de algunas de las monedas más utilizadas.

## Beneficios de esta Aplicación

- **Actualización en Tiempo Real**: Obtiene tasas de cambio actualizadas al instante de la API.
- **Fácil Usabilidad**: Interfaz basada en menú para una interacción sencilla.
- **Adaptabilidad**: Capacidad de ampliar o ajustar las opciones de conversión según las necesidades del usuario.
- **Fiabilidad**: Fuente de datos segura y precisa con el respaldo de ExchangeRate-API.

## Tecnologías Utilizadas

- **Java 17**: Lenguaje de programación principal para la aplicación.
- **Java.net.HttpClient**: Librería utilizada para realizar solicitudes HTTP a la API de tasas de cambio.
- **Google Gson**: Librería para la conversión y manipulación de datos JSON de manera estructurada.
- **Logger**: Herramienta para registrar mensajes de información y errores.
- **ExchangeRate-API**: API de terceros utilizada como fuente de datos para las tasas de cambio.

## Prerrequisitos para la Ejecución

Antes de iniciar la aplicación, asegúrate de cumplir con los siguientes requisitos:

- **Java 17**: La aplicación está desarrollada en Java 17, por lo que necesitas tener esta versión del JDK instalada.
- **Conexión a Internet**: Es necesaria para realizar solicitudes a la API de tasas de cambio.
- **Clave de API de ExchangeRate-API**: Regístrate en [ExchangeRate-API](https://www.exchangerate-api.com/) para obtener una clave de API y habilitar el acceso a los datos de tasas de cambio.

## Configuración Inicial

Para poner en funcionamiento esta aplicación en tu entorno local, sigue estos pasos:

1. **Clona el repositorio**:

   ```bash
   git clone https://github.com/tu-usuario/currency-converter-app.git

2. **Accede al directorio del proyecto**:

   ```bash
   cd currency-converter-app

3. **Agrega tu clave de API**: Abre el archivo `CurrencyConverterApp.java` y reemplaza la línea:

   ```java
   private static final String API_KEY = "tu_clave_api";

**Localiza la línea: private static final String API_KEY = "tu_clave_api";**

**Y reemplázala con tu clave API personal obtenida de ExchangeRate-API**.

## Instrucciones para Ejecutar la Aplicación en un IDE:

1. **Importar el Proyecto**
2. **Abre tu IDE favorito (IntelliJ IDEA, Eclipse, etc.).**
3. **Importa el proyecto como un proyecto Java estándar.**
4. **Ejecutar la Aplicación**
5. **Dentro de tu IDE, localiza la clase principal CurrencyConverterApp.java.**
6. **Haz clic derecho sobre la clase y selecciona "Run" para ejecutar el programa.**

## Funcionamiento de la Aplicación

Al iniciar la aplicación, verás un menú de opciones. A continuación, se detallan las opciones principales que se presentan:

**Opciones del Menú Principal**

1. **Conversión Personalizada entre Monedas: Permite convertir entre varios pares de divisas predefinidos, ideales para conversiones rápidas entre monedas populares como USD/EUR, USD/JPY, entre otras**.

2. **Conversión General entre Monedas: Esta opción permite al usuario ingresar cualquier par de monedas soportadas por la API. Ofrece flexibilidad para realizar conversiones entre monedas menos comunes o personalizadas.**

3. **Salir: Finaliza la aplicación.**

## Ejemplo de Conversión

En la sección de Conversión Personalizada, el usuario puede seleccionar un par de monedas y luego ingresar la cantidad a convertir. La aplicación consultará la API, mostrará la tasa de cambio actual y presentará el valor convertido en la moneda de destino.

1. **Ingrese la cantidad a convertir: 150**
2. **Ingrese la moneda de origen (ej. USD): USD**
3. **Ingrese la moneda de destino (ej. EUR): EUR**
4. **Resultado: 150 USD equivale a 137.45 EUR**
5. **Personalización de la Aplicación**
6. **Para ajustar los pares de divisas disponibles en la opción de conversión personalizada, puedes modificar el método realizarConversionPersonalizada() dentro de la clase principal CurrencyConverterApp. Puedes añadir o remover pares según lo necesites.**

## Estructura del Proyecto

1. **El proyecto está organizado de la siguiente manera:**

   ```bash
   currency-converter-app/ 
   │
   ├── src/
   │   ├── com.currencyconverter/
   │   │   ├── CurrencyConverterApp.java           # Clase principal que ejecuta la aplicación
   │   │   ├── ExchangeRatesResponse.java          # Clase de modelo que mapea la respuesta JSON de la API
   │   │   └── JsonProcessor.java                  # Clase para procesar y extraer datos de tasas de cambio del JSON
   │
   ├── README.md                                   # Archivo de documentación del proyecto


2. **Cada archivo cumple un rol específico, garantizando la modularidad y mantenibilidad del código:**

- **CurrencyConverterApp.java: Gestiona la lógica principal y la interacción con el usuario.**
- **ExchangeRatesResponse.java: Define la estructura de datos para almacenar y manipular la respuesta JSON.**
- **JsonProcessor.java: Procesa y transforma los datos JSON en objetos utilizables dentro de la aplicación.**

3. **Seguridad y Consideraciones**

- **Protección de la Clave API: La clave de API es un recurso sensible. Evita compartirla en repositorios públicos o entornos inseguros.**
- **Variables de Entorno: Para una mayor seguridad, considera almacenar la clave de API en una variable de entorno en lugar de incluirla directamente en el código fuente.**
- **Planificación para Mejoras Futuras**
- **Interfaz Gráfica de Usuario (GUI): Migrar a una interfaz gráfica para una experiencia más intuitiva.**
- **Historial de Conversiones: Implementación de un sistema para registrar y consultar conversiones previas.**
- **Gestión de Errores Mejorada: Ampliar la captura y el tratamiento de errores para mejorar la robustez de la aplicación.**

## Contribución

Este proyecto está abierto a contribuciones y mejoras. Si deseas colaborar, sigue estos pasos:

## Haz un fork de este repositorio.

Crea una rama con la nueva funcionalidad o mejora (git checkout -b feature/nueva-funcionalidad).
Realiza un pull request con una descripción detallada de los cambios.

## Licencia
Este proyecto está disponible bajo la licencia MIT. Consulta el archivo LICENSE para conocer los términos completos.

## © 2024 Albx.Dev - Aplicación de Convertidor de Monedas
