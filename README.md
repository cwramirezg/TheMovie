# The Movie App

Este proyecto es una aplicación para visualizar las peliculas en Kotlin para Android. La aplicación permite a
los usuarios realizar búsquedas de peliculas, ver su descripción.

Está diseñada siguiendo los principios de Clean Architecture y SOLID, y utiliza varias tecnologías y
bibliotecas importantes para proporcionar una experiencia robusta y escalable.

## Características principales

- Clean Architecture: El proyecto está estructurado siguiendo el patrón de Clean Architecture, lo
  que facilita la separación de responsabilidades y mejora la mantenibilidad del código.
- SOLID: Los principios SOLID (Single Responsibility, Open-Closed, Liskov Substitution, Interface
  Segregation, Dependency Inversion) se aplican en el diseño de la aplicación para promover un
  código limpio, modular y extensible.
- Inyección de Dependencias - Dagger-Hilt: Se utiliza Dagger-Hilt para realizar la inyección de
  dependencias, lo que simplifica la gestión de las dependencias y permite una mejor escalabilidad y
  prueba unitaria del código.
- Jetpack Compose: La interfaz de usuario se desarrolla utilizando Jetpack Compose, el moderno
  toolkit de UI de Android que facilita la creación de interfaces de usuario flexibles y dinámicas.
- Room: Se utiliza Room, la biblioteca de persistencia de Android, para almacenar los datos de las
  recetas en una base de datos local y permitir un acceso rápido y eficiente a ellos.
- Retrofit: Se utiliza Retrofit para realizar las llamadas a una API remota y obtener datos
  relacionados con las recetas, lo que permite una sincronización eficiente y actualizada de la
  información.
- Unit Test: Se incluyen pruebas unitarias para verificar el correcto funcionamiento de los
  componentes clave de la aplicación y garantizar la calidad del código.
- Git: Se uso git para el versionamiento del codigo.

## Adicionales

- Es compatible con JDK 17.
- Es compatible con Android Studio Koala
- En archivo local.properties se agrega una linea para la clave de themoviedb:
  TMDB_API_KEY=############
