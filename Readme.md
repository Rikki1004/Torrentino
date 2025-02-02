# Torrentino

Приложение для поиска и просмотра просто всех фильмов, которые можно найти на [кинопоиске](https://www.kinopoisk.ru/) (У них есть неофициальный API на котором работает их сайт)

## Как оно работает?

Все просто. Это самостоятельное приложение, функционал которого может быть расширен с помощью двух модулей. Изначально вы можете просматривать фильмы по категориям или искать их через поиск, для каждого фильма доступно описание, треллер и другая информация. Если подключить [TorrServer](https://github.com/YouROK/TorrServer/releases) (Просто скачайте по ссылке и запустите, он доступен для всех возможных платформ), то вы сможете сразу посмотреть любой понравившийся фильм и да, его не нужно будет полностью скачивать! TorrServer работает через торренты, но он загружает только ту часть фильма, которую сейчас смотрят, так что загрузка происходит мгновенно. Третий модуль - [RemotePlayer](https://github.com/Rikki1004/RemotePlayer) нужен для того, чтобы смотреть фильмы на телевизоре, управляя просмотром с телефона.

### Как пользоваться?
1. Загружаете [приложение](https://github.com/Rikki1004/Torrentino/releases/download/Torrentino/Torrentino.apk) и устанавливаете
2. Если планируете смотреть фильмы, то скачиваете [TorrServer](https://github.com/YouROK/TorrServer/releases). Есть версии для разных платформ, в т.ч. и для Android. Не обязательно чтобы TorrServer работал на том же устройстве, где вы планируете смотреть кино, главное быть с ним в одной локальной сети.
3. [RemotePlayer](https://github.com/Rikki1004/RemotePlayer) Был написан под Raspberry PI, но может быть запущен примерно везде т.к. он на Python'е. Предполагаемое использование: Плата подключается к телевизору по hdmi, у неё на автозапуске стоят TorrServer (да, он и на малину есть) и RemotePlayer, вы с телефона выбираете фильм и запускаете, управляя просмотром так же с телефона.
 
 ### Как выглядит?
<img src="https://user-images.githubusercontent.com/81743483/226067835-af64caed-d065-4be4-95d7-f4a63609fad9.png" width="30%">
<img src="https://user-images.githubusercontent.com/81743483/226067916-7640073e-ae6c-4895-ac63-940f1edefc3d.png" width="30%">
<img src="https://user-images.githubusercontent.com/81743483/226067972-f43e5cfd-464e-4ca7-8a0a-7cc16bb76946.png" width="30%">

