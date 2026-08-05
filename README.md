# 🕹️ Chaos Pong Arcade (Java Swing)

Un reinvento moderno, funcional y divertido del clásico Pong de Atari, construido con **Java Swing** aplicando arquitectura **MVC (Modelo-Vista-Controlador)** y persistencia de datos binaria.

---

## 🌟 Características e Innovaciones

* **Power-Ups Dinámicos**: Captura bonificadores flotantes en el mapa (*Velocidad*, *Escudo Defensivo*, *Congelamiento Rival*).
* **Físicas de Aceleración Posicional**: La trayectoria de la pelota depende del punto exacto del impacto en la raqueta.
* **Persistencia Binaria**: Registro automático del *Top 10 High Scores* persistido localmente en un archivo binario `.dat`.
* **Soporte Arquitectónico MVC**: Separación estricta de responsabilidades entre renderizado, cálculo de físicas e I/O.

---

## 🛠️ Requisitos del Sistema

* **JDK**: Java Development Kit 11 o superior.
* **Entorno**: Compatible con Windows, macOS y Linux.

---

## ⚙️ Compilación y Ejecución desde Terminal

### 1. Clonar el repositorio
```bash
git clone [https://github.com/TU_USUARIO/ChaosPong.git](https://github.com/TU_USUARIO/ChaosPong.git)
cd ChaosPong
```

### 2. Compilar el código fuente
Crea la carpeta de salida `bin` y compila los archivos `.java`:

* En **Linux / macOS**:
  ```bash
  mkdir -p bin
  javac -d bin $(find src -name "*.java")
  ```

* En **Windows (PowerShell)**:
  ```powershell
  mkdir bin
  Get-ChildItem -Path src -Filter *.java -Recurse | ForEach-Object { $_.FullName } | Out-File -FilePath sources.txt -Encoding ascii
  javac -d bin @sources.txt
  Remove-Item sources.txt
  ```

### 3. Ejecutar la aplicación
```bash
java -cp bin com.chaospong.Main
```

---

## 🎮 Controles de Juego

* **Jugador 1 (Izquierda)**: 
  * `W` - Mover Arriba
  * `S` - Mover Abajo
* **Jugador 2 (Derecha)**: 
  * `Tecla Arriba (▲)` - Mover Arriba
  * `Tecla Abajo (▼)` - Mover Abajo
* **General**:
  * `P` - Pausa
  * `ESC` - Volver al Menú Principal

---

## 💾 Persistencia de Datos
Las puntuaciones se almacenan en la ruta `data/highscores.dat` utilizando serialización de objetos en un **archivo binario no legible en texto plano**, garantizando el cumplimiento de requisitos de almacenamiento en disco mediante `ObjectOutputStream` e `ObjectInputStream`.