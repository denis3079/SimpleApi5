# SimpleApi(Java, Spring Boot, Maven, Postgresql)
 Лабораторная работы №1: Создание микросервиса на Spring Boot с базой данных Postgresql
------------------------------------------------------------------------------------------------------------------------------------

### Дисциплина: "Технология разработки программного обеспечения"

### Выполнил: Арбатов Денис Кириллович (МБД2131)

Цель лабораторной работы: Проектирование многослойной архитектуры Web-API (веб-приложений, микро-сервисов). Создание API микросервиса с учётом современных архитектурных паттернов, CRUD-приложение
написанное на JAVA.
------------------------------------------------------------------------------------------------------------------------------------
Инструкция по сборке и запуску:

Для работы потребуется установленная среда разработки IntelliJ IDEA 2021.3 (Ultimate Edition). А также Docker — проект для автоматизации развертывания приложений в виде переносимых автономных контейнеров, выполняемых в облаке или локальной среде. Используется операционная система Windows 10 и кроссплатформенная оболочка PowerShell.
1. Необходимо написать код на языке JAVA используя среду разработки IntelliJ IDEA 2021.3.
2. Запустим контейнер с базой данных Postgresql для теста приложения с помощью команды: 
```html 
docker run -e POSTGRES_PASSWORD=root -p 5432:5432 postgres 
```
4. Запустим файлы schema.sql, data.sql (src/main/resources/...) для создания таблиц и добавления данных в эти таблицы.
5. С помощью сборщика проектов MAVEN необходимо собрать образ для Docker. Во вкладке Maven/SimpleApi1/Lifecycle/package по двойному клику будет создан *.jar архив.
6. Далее необходимо произвести сборку образа: 
```html
docker build . -t $IMAGE_NAME:latest
```
7. Запустим созданный образ командой: 
```html
docker run -p 8080:8080 $IMAGE_NAME:latest
```

После запуска приложения его тестируем с помощью следующих команд:
1. Получим hostname: 
```html
curl http://localhost:8080/api/v1/status
```
2. Получим список всех строк в таблице:
```html
curl http://localhost:8080/api/v1/drivers
```
3. Получим определённую строку из таблицы по его id: 
```html
curl http://localhost:8080/api/v1/drivers/{id} 
```
4. Сохранение позиции в таблице:
```html
curl -x POST http://localhost:8080/api/v1/drivers/	
   {
   "last_name": "Кудрявцев",
   "first_name": "Анатолий",
   "mid_name": "Иванович",
   "phone": "8-926-000-00-06",
   "rating": 8
   }
```
5. Удаление позиции в таблице по id: 
```html
curl -x DELETE http://localhost:8080/api/v1/drivers/{id} 
```
![Alt Text](https://github.com/denis3079/SimpleApi/blob/master/Api_docker.gif)
-------------------------------------------------------------------------------------------------------------------------------------
 Лабораторная работы №2: Создание кластера Kubernetes и деплой приложения SimpleApi
-------------------------------------------------------------------------------------------------------------------------------------

Цель лабораторной работы: знакомство с кластерной архитектурой на примере Kubernetes, а также деплоем приложения в кластер.
В данной лабораторной работе необходимо развернуть кластер Kubernetes на локальной рабочей станции посредством MiniKube.

--------------------------------------------------------------------------------------------------------------------------------------
Инструкция:
1. Развернём локально кластер на Kubernetes с использованием MiniKube.

Установим MiniKube по инструкции https://minikube.sigs.k8s.io/docs/start/
Установим kubectl https://kubernetes.io/ru/docs/tasks/tools/install-kubectl/
Убедимся, что kubectl работает и произведём осмотр кластера с помощью команд:
```yaml
kubectl get node
kubectl get po
kubectl get po -A
kubectl get svc
```
2. Установим графический интерфейс Dashboard https://kubernetes.io/docs/tasks/access-application-cluster/web-ui-dashboard/ - необходимо выполнить шаги Deploying the Dashboard UI и Accessing the Dashboard UI. 
В последнем не забудьте кликнуть по ссылке creating a sample user и выполнить там инструкции.

3. Произведём деплой приложения в кластер.
Для того, чтобы MiniKube видел образы docker, необходимо создавать docker-образы внутри MiniKube. Для обеспечения этого, выполним команду в PowerShell:
```html
minikube -p minikube docker-env | Invoke-Expression 
```
Перейдём в папку с API в PowerShell:
```html
cd C:\Users\denis\IdeaProjects\SimpleApi 
```
Пересоберём докер образ в PowerShell:
```html
minikube -p minikube docker-env | Invoke-Expression 
```
Решеним проблему с обращением из MiniKube к приложению, либо БД на localhost. Определим локальный адрес нашей машины, который будет вписан в манифест deployment.yaml (https://minikube.sigs.k8s.io/docs/handbook/host-access/) - IP of localhost from MiniKube используя команду в PowerShell:
```html
ping host.minikube.internal
```

Создадим манифест Deployment и сохранить в файл, например deployment.yaml:

```yaml
    apiVersion: apps/v1
    kind: Deployment
    metadata:
      name: my-deployment
    spec:
      replicas: 10
      selector:
        matchLabels:
          app: my-app
      strategy:
        rollingUpdate:
          maxSurge: 1
          maxUnavailable: 1
        type: RollingUpdate
      template:
        metadata:
          labels:
            app: my-app
        spec:
          containers:
            - image: myapi:latest
              # https://medium.com/bb-tutorials-and-thoughts/how-to-use-own-local-doker-images-with-minikube-2c1ed0b0968
              # указыаает на то, что образы нужно брать только из локального registry. В продакшене никогда не использовать
              imagePullPolicy: Never 
              name: myapi
              ports:
                - containerPort: 8080
          hostAliases:
          - ip: "192.168.65.2" # The IP of localhost from MiniKube
            hostnames:
            - postgres.local
```
Выполним данный код командой в PowerShell: 
```yaml
kubectl apply -f deployment.yaml
```

Создадим манифест Service и сохранить в файл, например service.yaml
```yaml
    apiVersion: v1
    kind: Service
    metadata:
      name: my-service
    spec:
      type: NodePort
      ports:
        - nodePort: 31317
          port: 8080
          protocol: TCP
          targetPort: 8080
      selector:
        app: my-app
```
Выполним данный код командой в PowerShell: 
```yaml
kubectl apply -f service.yaml
```
Для того, чтобы проверить доступность localhost изнутри Minikube, надо зайти внутрь Minikube путем выполнения команды в PowerShell:
```yaml
minikube ssh
```
Далее выполним в PowerShell curl к какому-нибудь нашему сервису на 192.168.65.2:
```html
curl http://192.168.65.2:8080/api/v1/status 
```
Если запрос выпоолнился, успешно, то адрес верный!

![Alt Text](https://github.com/denis3079/SimpleApi/blob/master/Minikube1.bmp)

4. Увеличим количество реплик до 10 и проверим отображение hostname.

В манифесте deployment.yaml изменим количество реплик до 10:
```html
spec.replicas: 10
```
Применим изменения:
```html
kubectl apply -f deployment.yaml
```
![Alt Text](https://github.com/denis3079/SimpleApi/blob/master/deployment1.bmp)

5. Произведём осмотр подов в графическом интерфейсе, предварительно выполнив пункт №2 данной инструкции и далее перейдём по ссылке http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/:

![Alt Text](https://github.com/denis3079/SimpleApi/blob/master/Dashboard.gif)

