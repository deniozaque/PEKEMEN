# Juego de Pekemen

Juego de rol y combates por turnos para consola desarrollado en Java. El jugador gestiona un equipo de criaturas (Pekemens), aprovecha ventajas elementales, combate contra entrenadores y líderes de gimnasio, captura criaturas salvajes y evoluciona su estrategia a lo largo de la historia.

---

## Modos de juego

1. **Modo historia**:
   - **Elección inicial**: Elige entre los tres iniciales elementales: *Peplet* (Agua), *Terteg* (Planta) o *Chemchemp* (Fuego).
   - **Primer combate**: Enfrentamiento introductorio contra tu rival.
   - **Mundo y entrenamiento**: Entre combates puedes elegir entre:
     - **Pekemens salvajes**: Puedes combatirlos para ganar experiencia y subir de nivel, o intentar **capturarlos** para incorporarlos a tu equipo (máximo 6 Pekemens).
     - **Líderes de gimnasio**: Desafía en orden a los tres líderes elementales:
       1. **Líder Agua**: Tepexes, Peplet y Mereene.
       2. **Líder Fuego**: Menferme, Slegme y Megcerge.
       3. **Líder Planta**: Terterra, Cecnee y Cecterne.
   - **Combate final contra el rival**: Tras superar a los tres líderes, te enfrentarás al rival en una batalla decisiva contra su equipo completo de 6 Pekemens (incluyendo a su inicial).
2. **Combate singular**: Un combate rápido e individual 1 vs 1 contra la máquina.
3. **Salir**: Cierra el juego.

---

## Opciones durante el combate

En cada turno puedes seleccionar una de las 7 acciones disponibles:

- **1. Atacar**: Ataque estándar basado en la fuerza del Pekemen y las ventajas de tipo, con un 10% de probabilidad de asestar un golpe crítico ($\times 1.1$).
- **2. Curarte**: Restaura 20 puntos de vida usando una poción (1 disponible por combate).
- **3. Explotar**: Sacrifica la mitad de tu vida actual para debilitar instantáneamente al Pekemen rival.
- **4. Atacar fuerte**: Ejecuta un golpe con el doble de potencia, pero con un 60% de probabilidad de fallar.
- **5. Protegerte**: Te proteges durante el turno, bloqueando el daño del rival.
- **6. Efecto especial**: Aplica un estado alterado sobre el rival según el tipo elemental de tu Pekemen:
  - **Fuego (Quemar)**: El rival pierde 15 de vida en cada turno y su ataque se reduce en 10 puntos.
  - **Planta (Envenenar)**: El rival pierde 25 de vida en cada turno.
  - **Agua (Ahogar)**: Contador de 3 turnos tras los cuales el rival queda debilitado automáticamente.
- **7. Cambiar pekemen**: Permite cambiar tu Pekemen activo por otro miembro con vida de tu equipo durante el combate, recalculando ventajas elementales y reiniciando estados alterados.

---

## Mecánica de debilitamiento y cambios

- **Debilitamiento del jugador**: Si tu Pekemen activo cae a 0 puntos de vida, el juego comprueba si quedan otros miembros con vida en tu equipo. De ser así, podrás elegir cuál enviar al combate. Solo serás derrotado si todo tu equipo es debilitado.
- **Debilitamiento del rival / líderes**: Cuando derrotas al Pekemen del rival o de un líder, este enviará automáticamente a su siguiente criatura disponible hasta agotar su equipo.

---

## Sistema de tipos y ventajas

Las relaciones elementales multiplican el daño recibido o infligido:

|  | Fuego | Agua | Planta | Normal |
| :--- | :---: | :---: | :---: | :---: |
| **Fuego** | 1.0 | 0.75 | 1.25 | 1.0 |
| **Agua** | 1.25 | 1.0 | 0.75 | 1.0 |
| **Planta** | 0.75 | 1.25 | 1.0 | 1.0 |
| **Normal** | 1.0 | 1.0 | 1.0 | 1.0 |

---

## Lista de Pekemens (Pekedex)

| Nº | Nombre | Tipo | Vida Base | Fuerza Máxima |
| :-: | :--- | :---: | :-: | :-: |
| 1 | Peplet | Agua | 40 | 2 |
| 2 | Prenplet | Agua | 70 | 4 |
| 3 | Enpeleen | Agua | 100 | 6 |
| 4 | Terteg | Planta | 40 | 2 |
| 5 | Tercel | Planta | 70 | 4 |
| 6 | Terterra | Planta | 100 | 6 |
| 7 | Chemchemp | Fuego | 40 | 2 |
| 8 | Menferme | Fuego | 70 | 4 |
| 9 | Enfermet | Fuego | 100 | 6 |
| 10 | Bedef | Normal | 30 | 2 |
| 11 | Shex | Normal | 30 | 2 |
| 12 | Lexe | Normal | 65 | 4 |
| 13 | Lexrrey | Normal | 85 | 6 |
| 14 | Tepexes | Agua | 35 | 3 |
| 15 | Mereene | Agua | 70 | 5 |
| 16 | Slegme | Fuego | 40 | 4 |
| 17 | Megcerge | Fuego | 75 | 5 |
| 18 | Cecnee | Planta | 45 | 5 |
| 19 | Cecterne | Planta | 100 | 6 |

---

## Estructura del proyecto

```text
codigo/
├── src/
│   └── App.java      # Código fuente principal del juego
├── bin/              # Archivos compilados (.class)
├── .vscode/          # Configuración de entorno para VS Code
└── README.md         # Documentación del proyecto
```

---

## Compilación y ejecución

### Compilar
```powershell
javac -d bin src/App.java
```

### Ejecutar
```powershell
java -cp bin App
```

---

## Autor

- **Jorge** (Curso 2026/27)
