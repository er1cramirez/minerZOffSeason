# minerZOffSeason
2024 MinerZ OffSeason code


## Configuracion de git en windows

Descarga el instalador(windows)

https://git-scm.com/download/win](https://github.com/git-for-windows/git/releases/download/v2.47.0.windows.2/Git-2.47.0.2-64-bit.exe)

Abre el archivo descargado y sigue el asistente de instalación.
### Select Components: 
Selecciona las opciones por defecto, pero si quieres un acceso directo en el escritorio, marca la casilla correspondiente.
### Adjusting your PATH environment: 
Elige "Git from the command line and also from 3rd-party software" para usar Git en la terminal de Windows o en otros programas.
### Choosing the SSH executable: 
Puedes seleccionar la opción por defecto "Use bundled OpenSSH" para una instalación básica.
### Configuring the line ending conversions: 
Si colaboras con usuarios de Windows y Unix, selecciona "Checkout Windows-style, commit Unix-style line endings".
### Verificar la instalación
Abre una terminal (cmd, PowerShell o Git Bash) y escribe el siguiente comando para verificar que Git está instalado correctamente:
```
git --version
```
Esto debería mostrar la versión de Git instalada.
## Configurar Git
Ahora vamos a configurar algunas opciones básicas como tu nombre de usuario y correo electrónico.

Abre la terminal y configura tu nombre:
```
git config --global user.name "Tu Nombre"
```
Luego, configura tu correo electrónico (el mismo que usarás en GitHub u otros servicios):
```
git config --global user.email "tucorreo@ejemplo.com"
```
Configurar el editor predeterminado (opcional)
```
git config --global core.editor "code --wait"
```

## clonar el repo

```
cd C:/ruta/donde/quieres/guardar/el/repo
```
```
git clone https://github.com/usuario/repo.git
```
### Mantener el repositorio actualizado
Una vez que tengas el repositorio clonado, es importante mantenerlo actualizado con los últimos cambios hechos por otros colaboradores o por ti mismo en el repositorio remoto.

Pasos:
Verificar cambios locales: Antes de actualizar el repositorio con los últimos cambios, es buena práctica verificar si tienes cambios locales pendientes que aún no se han guardado:
```
git status
```
Esto te mostrará si hay archivos modificados o pendientes de ser enviados al repositorio remoto (commits no realizados).

Actualizar el repositorio (git pull): Para obtener los últimos cambios del repositorio remoto, usa:
```
git pull
```




## Librerias de terceros por instalar

### CTRE Libs
https://maven.ctr-electronics.com/release/com/ctre/phoenix6/latest/Phoenix6-frc2024-latest.json

https://maven.ctr-electronics.com/release/com/ctre/phoenix/Phoenix5-frc2024-latest.json

### Rev Robotics Lib
https://software-metadata.revrobotics.com/REVLib-2024.json

### Path Planner Lib: https://pathplanner.dev/pplib-getting-started.html
https://3015rangerrobotics.github.io/pathplannerlib/PathplannerLib.json

###Studica (NavX)

https://dev.studica.com/releases/2024/NavX.json

