# Descripción
Esta es una aplicación Android que consta de una pantalla principal donde el usuario puede visualizar una
lista de películas populares.
Al seleccionar una película de la lista, se navega a una segunda pantalla donde se
puede visualizar más información sobre la misma.

# API
La información de las películas es provista por [The Movie Database (TMBD)](https://www.themoviedb.org/), la cual cuenta con un gran
catálogo de películas accesible mediante una API

# Tecnologías y patrones utilizados

- La aplicación está estructurada usando patrones de diseño ampliamente establecidos (**MVVM**, **Repository Pattern**, etc.)
- Para la parte visual se hace uso de **Jetpack Compose**
- **Retrofit** como gestor de consultas HTTP
- **Hilt** para inyección de dependencias
- Las imagenes son cargadas utilizando la librería **Coil**
- **Material Design** para el diseño de la app

# Características extra

✔️ La aplicación cuenta con un **buscador de películas**\
✔️ **Paginado**: Al llegar al fondo de la lista de películas, se recargan otras automáticamente\
✔️ Está configurada de acuerdo al **idioma del dispositivo**\
✔️ Modo oscuro y claro\
✔️ Manejo de errores (película no encontrada, detalles inexistentes. errores de conexión)\

# Videos

https://github.com/user-attachments/assets/ef035387-bef5-4a8e-a82f-3a955b75575b



https://github.com/user-attachments/assets/e5b90de0-c58f-4acd-b507-754a6463ed64



https://github.com/user-attachments/assets/7aeb0e4b-2545-48e4-a5d9-3942cac351cf

