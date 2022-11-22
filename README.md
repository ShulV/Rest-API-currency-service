<h1 align="center">Client-server application for a currency tracking</h1>

## Description

Репозиторий включает в себя бэкэнд, фронэнд и мобильное приложение. Подпроекты расположены в папках "service", "service-frontend", "cliend-android" соотвественно.

## Installation

### Backend
Можно пойти трудным путём и заняться сборкой, компиляцией, другими низкоуровневыми вещами через консоль:
https://javarush.ru/groups/posts/2318-kompiljacija-v-java

Можно пойти простым путём и доверить это IDE (например, установить Intellij IDEA)
Структура maven проекта:

<img width="150px" alt="maven" src="https://github.com/ShulV/sber-practice/blob/main/readme-images/maven_structure.jpeg">

Сборка и запуск проекта на 8080 порту:

<img width="900px" alt="backend" src="https://github.com/ShulV/sber-practice/blob/main/readme-images/backend_console_execute_project.png">

### Frontend
В cmd вводим:
проверка наличия установленного Node.js:
```
$ npm --version
```
установка Node.js:
```cmd
$ npm install
```
установка пакетов для работы React:
```
$ npm install react-scripts
```
открыть проект и запустить его коммандой:
```
$ npm start
```
Запуск фронтенда на 3000 порту:

<img width="250px" alt="frontend-console" src="https://github.com/ShulV/sber-practice/blob/main/readme-images/frontend_console_execute_project.png">

Результат:

<img width="350px" alt="frontend" src="https://github.com/ShulV/sber-practice/blob/main/readme-images/frontend.png">

### Android Application
<img width="250px" alt="android" src="https://github.com/ShulV/sber-practice/blob/main/readme-images/mobile.jpg">
Для установки Android-приложения нужно перейти в директорию:
```
client-android\app\build\outputs\apk\debug
```
и установить на свой мобильный телефон apk-файл ```app-debug.apk```

Также для корректной настройки работы Android-приложения потребуется запущенное Spring-приложение,
код которого находится в директории ```service```. При этом и Spring-приложение, и Android-приложение должны находиться в одной сети.

Для того, чтобы Android-приложение могло подключиться к Spring-приложению, нужно верно указать URL сервера.
URL сервера можно настроить во вкладке "Настройки" Android-приложения.

<img width="250px" alt="android" src="https://github.com/ShulV/sber-practice/blob/main/readme-images/android.png">

Данный URL-адрес представляет собой строку:
```
http://"IPv4-адрес":8080
```
Из этой строки требуется поменять только IPv4-адрес.
Для того, чтобы узнать правильный адрес, проделайте на компъютере со Spring-приложением следующее:
1. Нажать на клавиатуре комбинацию клавиш ```Win+R``` и ввести ```cmd```
2. В терминале ввести команду ```ipconfig```
3. Найти среди IP-адресов адрес компъютера в той же сети, к которой подключен телефон с Android-приложением
4. Ввести данный адрес в настройках URL сервера в Android-приложении

Если после всех проделанных шагов проблемы с подключением к серверу всё еще возникают, это может быть связано с тем,
что порт ```8080``` уже используется в компъютере для других целей или антивирус блокирует обращение по этому порту.
Для исправления данной проблемы нужно либо освободить порт ```8080```, либо разрешить доступ к нему в антивирусе.
