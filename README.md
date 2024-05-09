# Pasalista Trompikallo

Proyecto fin de DAM

Aplicación de escritorio escrita en Java pensada para profesores que quieran automatizar el control de la asistencia de sus alumnos

## Manual de instalación

Debido a que este programa está dirigido a usuarios finales, se supone que el servidor de
la base de datos ya estaría configurado previamente por parte del administrador, por lo que
el único paso para ejecutar la aplicación sería la descarga y ejecución del archivo ejecutable
(si el servidor ya está en ejecución)
En el caso concreta de este proyecto, con el servidor en local, será necesario configurar
y tener iniciado un servidor de base de datos MySQL a la vez que se ejecuta la aplicación
(localhost o 127.0.0.1 y puerto 3306). Se recomienda usar XAMPP para configurar este
servicio, ya que te configura automáticamente el servidor de MySQL con phpMyAdmin. El
usuario configurado para poder acceder al servidor desde la app es “root” y la contraseña una
cadena vacía “”. Una vez creado y abierto el servidor, al ejecutar la aplicación por primera vez
ya se creará automáticamente la base de datos para el año escolar actual y al pulsar el botón
demo se creará la base de datos para la demo.

Nota para administradores:

Después de la primera ejecución, para que la aplicación sea 100% funcional, el
administrador deberá añadir manualmente, por lo menos, las asignaturas y las clases del año
escolar, y un gestor. Con estos datos, el gestor ya podría completar desde la interfaz gráfica
resto de datos para el año académico (añadir profesores, gestores, alumnos, asignar PACs,
etc…
