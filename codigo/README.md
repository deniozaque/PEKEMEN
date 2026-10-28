# Juego de Pekemen

Juego de consola hecho en Java donde juegas batallas usando tres tipos: Fuego, Agua y Planta.

---

## Como funciona el juego

En este juego eliges un elemento y te enfrentas a la maquina por turnos. 

Dependiendo del elemento que elijas tendras ventaja o desventaja contra el enemigo. Durante tu turno puedes decidir entre varias opciones.

---

## Modos de juego

1. **Torneo**: Juegas tres combates seguidos. Si ganas los tres, ganas el torneo.
2. **Combate singular**: Un combate rapido de una sola ronda contra la maquina.
3. **Salir**: Cierra el programa.

---

## Opciones en cada turno

Cuando te toque jugar puedes elegir:
- **1. Atacar**: Hace daño al rival teniendo en cuenta la ventaja de tipos.
- **2. Curarse**: Recuperas 20 puntos de vida usando una pocion (solo tienes una por partida).
- **3. explotar**: Te quitas la mitad de la vida actual para matar de un golpe al enemigo.
- **4. Atacar fuerte**: Quitas el doble de vida pero hay un 60% de fallar
- **5. Protejer**: Pierdes el turno pero no te quitan daño
- **6. Efectio especial**: Cada tipo tiene un ataque diferente. Fuego, quemar(quita 15 por turno y le baja el ataque 10). Planta, envenenar(quita 25 por turno). Agua, ahogar(en 3 turnos muere)

---

## Estructura de carpetas

```text
trabajo_juego/
├── src/
│   └── App.java      # Codigo principal del juego
├── bin/              # Archivos compilados
└── README.md         # Explicacion del proyecto
```

---

## Autor

- Jorge
