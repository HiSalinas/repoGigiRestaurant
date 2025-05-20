🍽️ GeoRestaurantsApp
Una aplicación móvil nativa para Android que muestra restaurantes cercanos basados en la ubicación del usuario, permitiendo guardar favoritos para acceso rápido.

🏗️ Arquitectura:
La aplicación está desarrollada siguiendo los principios de Clean Architecture y el patrón MVVM.

- Presentation (UI):
Implementada con Jetpack Compose
ViewModels para gestionar el estado y la lógica de presentación
Diferentes pantallas: lista de restaurantes, detalles y favoritos


- Domain:
Casos de uso que representan las acciones que puede realizar el usuario
Modelos de dominio independientes de la implementación externa
Interfaces de repositorios


- Data:
Repositorios (implementaciones de las interfaces definidas en Domain)
Fuentes de datos: API REST y base de datos local
Mappers para convertir entre diferentes modelos de datos


📋 Tecnologías principales:
- Kotlin como lenguaje principal
- Jetpack Compose para la UI
- Coroutines y Flow para operaciones asíncronas
- Dagger Hilt para inyección de dependencias
- Room para persistencia local (favoritos)
- Retrofit para consumo de API REST
- Navigation Component para la navegación entre pantallas


🚀 Instrucciones de ejecución:
1. Clona el repositorio.
2. Abre el proyecto en Android Studio
3. Ejecuta la aplicación en un emulador o dispositivo físico con Android 6.0 (API 23) o superior.
4. La aplicación solicitará permisos de ubicación al iniciar. Es necesario concederlos y tener el GPS activado para ver los restaurantes cercanos.
