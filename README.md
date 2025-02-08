# ExamenPC_AlexParejaMateos

EN EL PAQUETE Ejercicio1 SE ENCUENTRA LA SOLUCIÓN COMPLETA DE LA PARTE PRÁCTICA DEL EXAMEN. LA PARTE TEÓRICA SE DESARROLLA A CONTINUACIÓN: 

Parte teórica. Responda razonadamente a las siguientes preguntas.

1.	Describa dos modelos de concurrencia comunes en la programación paralela y explique las diferencias principales entre ellos:
Dos modelos comunes son el de memoria compartida y el de paso de mensajes. En la memoria compartida, varios hilos acceden a un espacio común (requeriendo sincronización), mientras que en el paso de mensajes los procesos se comunican explícitamente mediante el envío y recepción de mensajes.

2.	Explique qué es un mutex y cómo se utiliza para resolver problemas de sincronización en la programación paralela:
Un mutex (exclusión mutua) es un mecanismo que garantiza que solo un hilo acceda a una sección crítica a la vez. Se utiliza bloqueando el mutex al entrar y liberándolo al salir, evitando condiciones de carrera en recursos compartidos.

3.	Describa dos algoritmos de planificación de procesos y explique cómo afectan el rendimiento y la equidad en un sistema operativo multitarea:
Por ejemplo, Round Robin asigna un tiempo fijo a cada proceso, promoviendo la equidad, y la planificación por prioridades asigna CPU según la importancia del proceso, lo que puede favorecer el rendimiento de tareas críticas, pero arriesga la inanición de las de baja prioridad.

4.	Explique la importancia del balanceo de carga en sistemas de computación paralela y describa una estrategia para lograrlo:
Es fundamental para distribuir equitativamente las tareas entre recursos y evitar cuellos de botella. Una estrategia es el work stealing, donde hilos o nodos con menor carga toman tareas de aquellos saturados, optimizando el rendimiento global.

5.	Compare y contraste paralelización de datos y paralelización de tareas, proporcionando ejemplos de cuándo sería apropiado usar cada una:
La paralelización de datos divide grandes conjuntos en partes que se procesan de forma idéntica (por ejemplo, procesamiento de imágenes), mientras que la paralelización de tareas ejecuta funciones o módulos diferentes en paralelo (como en un servidor web). La elección depende de si el problema se segmenta naturalmente en datos o en funciones independientes.

6.	Describa las diferencias entre sistemas de memoria compartida y sistemas de memoria distribuida en el contexto de la programación paralela:
En sistemas de memoria compartida todos los hilos acceden a un mismo espacio, facilitando la comunicación, pero requiriendo sincronización. En memoria distribuida cada nodo posee su propia memoria y se comunican mediante mensajes, lo que favorece la escalabilidad a costa de mayor complejidad.

7.	Explique qué es la tolerancia a fallos en sistemas paralelos y describa un método para implementarla:
Es la capacidad de un sistema paralelo para continuar operando a pesar de fallas en algunos componentes. Un método para implementarla es la replicación de procesos y el uso de técnicas de detección y recuperación, de modo que si un nodo falla, otro pueda asumir sus tareas.

8.	Explique qué es la computación GPGPU y discuta sus ventajas y desafíos en comparación con la computación tradicional en CPU:
La computación GPGPU aprovecha la alta paralelización de las GPUs para realizar cálculos generales, ofreciendo un gran rendimiento en tareas masivamente paralelizables. Sin embargo, plantea desafíos en programación, optimización de memoria y no siempre se adapta a problemas no paralelizables.
	
9.	Describa el problema del productor-consumidor en programación paralela y explique cómo se puede resolver utilizando semáforos:
Consiste en coordinar la inserción y extracción de datos en un buffer compartido sin causar conflictos. Utilizando semáforos, se controla el acceso: uno cuenta los elementos disponibles y otro gestiona el espacio libre, sincronizando productores y consumidores.

10.	Explique qué métricas se utilizan comúnmente para evaluar el rendimiento de un sistema de computación paralela y cómo se interpretan:
Se utilizan indicadores como el speedup (aceleración frente a la ejecución secuencial), la eficiencia (uso óptimo de los recursos) y la escalabilidad (mejora al aumentar recursos). Estas métricas permiten evaluar tanto la ganancia en tiempo de ejecución como la efectividad en la distribución del trabajo.
