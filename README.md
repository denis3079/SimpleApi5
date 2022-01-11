# SimpleApi(Java, Spring Boot, Maven, Postgresql)
 Лабораторная работы №1: Создание микросервиса на Spring Boot с базой данных Postgresql
------------------------------------------------------------------------------------------------------------------------------------

### Дисциплина: "Технология разработки программного обеспечения"

### Выполнил: Арбатов Денис Кириллович (МБД2131)

Цель лабораторной работы: Проектирование многослойной архитектуры Web-API (веб-приложений, микро-сервисов). Создание API микросервиса с учётом современных архитектурных паттернов, CRUD-приложение
написанное на JAVA.
------------------------------------------------------------------------------------------------------------------------------------
Инструкция по сборке и запуску:

Для работы потребуется установленная среда разработки IntelliJ IDEA 2021.3 (Ultimate Edition). А также Docker — проект для автоматизации развертывания приложений в виде переносимых автономных контейнеров, 
выполняемых в облаке или локальной среде.
1. Необходимо написать код на языке JAVA используя среду разработки IntelliJ IDEA 2021.3.
2. Запустить контейнер с базой данных Postgresql для теста приложения с помощью команды: 
```html 
docker run -e POSTGRES_PASSWORD=root -p 5432:5432 postgres 
```
4. Запустить файлы schema.sql, data.sql (src/main/resources/...) для создания таблиц и добавления данных в эти таблицы.
5. С помощью сборщика проектов MAVEN необходимо собрать образ для Docker. Во вкладке Maven/SimpleApi1/Lifecycle/package по двойному клику будет создан *.jar архив.
6. Далее необходимо произвести сборку образа: 
```html
docker build . -t $IMAGE_NAME:latest
```
6. Запустить созданный образ командой: 
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
1. Развернуть локально кластер на Kubernetes с использованием MiniKube.

Установить MiniKube по инструкции https://minikube.sigs.k8s.io/docs/start/
Установить kubectl https://kubernetes.io/ru/docs/tasks/tools/install-kubectl/
Убедитесь, что kubectl работает и произведите осмотрите кластера с помощью команд:
```yaml
kubectl get node
kubectl get po
kubectl get po -A
kubectl get svc
```
Установите графический интерфейс Dashboard https://kubernetes.io/docs/tasks/access-application-cluster/web-ui-dashboard/ - необходимо выполнить шаги Deploying the Dashboard UI и Accessing the Dashboard UI. 
В последнем не забудьте кликнуть по ссылке creating a sample user и выполнить там инструкции.

3. Произвести деплой приложения в кластер. У приложения должны быть ендпоинты, соответствующие его предметной области, а также ендпоинт, 
где будет отображаться

Создать манифест Deployment и сохранить в файл, например deployment.yaml:

```yaml
    apiVersion: apps/v1
    kind: Deployment
    metadata:
      name: my-deployment
    spec:
      replicas: 2
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
              imagePullPolicy: Never 
              name: myapi
              ports:
                - containerPort: 8080
          hostAliases:
          - ip: "127.0.0.1" # The IP of localhost from MiniKube
            hostnames:
            - postgres.local
```
Выполнить данный код командой: 
```yaml
kubectl apply -f deployment.yaml
```

Создать манифест Service и сохранить в файл, например service.yaml
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
Выполнить данный код командой: 
```yaml
kubectl apply -f service.yaml
```
Для того, чтобы проверить доступность localhost изнутри Minikube, надо зайти внутрь Minikube путем выполнения в консоли:
```yaml
minikube start
minikube ssh
```
![Alt Text](https://github.com/denis3079/SimpleApi/blob/master/Minikube.bmp)
4. Увеличение количества реплик до 10 и проверка отображение hostname.

В манифесте deployment.yaml изменить количество реплик до 10:
```html
spec.replicas: 10
```
Применить изменения:
```html
kubectl apply -f deployment.yaml
```
![Alt Text](https://github.com/denis3079/SimpleApi/blob/master/deployment.bmp)

