<h2>Модуль микро-сервиса по продажи подписки на сервис для пользователей</h2>
<h3>Описание работы по задаче 58 "Добавить MapStruct для маппинга ДТО."</h3>
**Вступление.**
Поскольку микросервисы и распределенные приложения быстро захватывают мир разработки, целостность и безопасность данных важны как никогда. Безопасный канал связи и ограниченная передача данных между этими слабосвязанными системами имеют первостепенное значение. В большинстве случаев конечному пользователю или службе требуется доступ не ко всем данным модели, а только к некоторым конкретным частям.

_Объекты передачи данных (DTO)_ регулярно применяются в этих приложениях. DTO - это просто объекты, которые содержат запрошенную информацию о другом объекте. Обычно информация ограничена по объему. Поскольку DTO являются отражением исходных объектов, сопоставители между этими классами играют ключевую роль в процессе преобразования.

**MapStruct** - это генератор кода на основе Java с открытым исходным кодом, который создает код для реализации сопоставления.

Он использует обработку аннотаций для создания реализаций классов сопоставления во время компиляции и значительно сокращает объем стандартного кода, который обычно должен быть написан вручную.

Маппер в широком смысле — подсистема для преобразования и передачи данных. В случае с Mapstruct разработчик обычно пишет аннотацию маппера — короткий блок кода.
Он описывает, какую информацию и в какую сущность нужно передать.
А библиотека на основе этой аннотации генерирует код, который и занимается непосредственной передачей данных.
Поэтому Mapstruct часто называют генератором кода, который базируется на аннотациях.

Благодаря Mapstruct маппинг можно автоматизировать: не понадобится вручную писать методы, которые обрабатывают данные и передают их в другую сущность, в другой слой программы.

**Зависимости MapStruct**
Поскольку в нашем проекте используется Maven, то добавим зависимость:
 <dependencies> 
        <dependency>
            <groupId>org.mapstruct</groupId>
            <artifactId>mapstruct</artifactId>
            <version>1.5.3.Final</version>
        </dependency>
 </dependencies> 

Эта зависимость импортирует основные аннотации MapStruct.
Поскольку MapStruct работает во время компиляции и прикреплен к сборщику Maven, нам также придется добавить плагин в <build> :
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.5.1</version>
                <configuration>
                    <source>11</source> <!-- depending on your project -->
                    <target>11</target> <!-- depending on your project -->
                    <annotationProcessorPaths>
                        <path>
                            <groupId>org.mapstruct</groupId>
                            <artifactId>mapstruct-processor</artifactId>
                            <version>1.5.3.Final</version>
                        </path>
                    </annotationProcessorPaths>
                </configuration>
            </plugin>

**Основные сопоставления.**
Начнем с базового картирования. У нас будет модель Subscription SubscriptionDTO. Для нашего удобства их поля будут иметь одинаковые имена.
Теперь, чтобы создать сопоставитель между моделью и ДТО, мы создадим интерфейс SubscriptionMapper @Mapper. C помощью @Mapper, MapStruct знает, что это сопоставитель между нашими двумя классами:

@Mapper
public interface SubscriptionMapper {
SubscriptionMapper INSTANCE = Mappers.getMapper(SubscriptionMapper.class);
SubscriptionDTO subscriptionToDTO(Subscription subscription);

У нас есть INSTANCE типа SubscriptionMapper.
Это будет наша «точка входа» в экземпляр после того, как мы сгенерируем реализацию.

Мы определили subscriptionToDTO() в интерфейсе, который принимает Subscription и возвращает экземпляр SubscriptionDTO. Этого достаточно, чтобы MapStruct знал, что мы хотим сопоставить экземпляр Subscription SubscriptionDTO.

После компиляции в папке проекта появляются файлы. Один из них - реализация маппера...
Класс, которые сгенерировал Mapstruct располагается в generated-sources: ${projectDir}/target/generated-sources/annotations/
public class SubscriptionMapperImpl implements SubscriptionMapper {

    @Override
    public SubscriptionDTO subscriptionToDTO(Subscription subscription) {
        if ( subscription == null ) {
            return null;
        }

        SubscriptionDTO subscriptionDTO = new SubscriptionDTO();

        subscriptionDTO.setId( subscription.getId() );
        subscriptionDTO.setTariff( subscription.getTariff() );
        subscriptionDTO.setCreatedAt( subscription.getCreatedAt() );
        subscriptionDTO.setUpdatedAt( subscription.getUpdatedAt() );
        subscriptionDTO.setStartAt( subscription.getStartAt() );
        subscriptionDTO.setExpiresAt( subscription.getExpiresAt() );
        subscriptionDTO.setStatus( subscription.getStatus() );
        subscriptionDTO.setInterval( subscription.getInterval() );
        subscriptionDTO.setIntervalCount( subscription.getIntervalCount() );
        subscriptionDTO.setPrice( subscription.getPrice() );
        subscriptionDTO.setSendSMS( subscription.getSendSMS() );

        return subscriptionDTO;
    }

Класс SubscriptionMapperImpl теперь содержит subscriptionToDTO() , который сопоставляет наши поля Subscription SubscriptionDTO.



<h3>Описание задачи 79 "добавить API создания подписки пользователем"</h3>
Добавить в подписки возможные статусы

“awaiting_transaction” “active” “inactive” “payment_error” "“awaiting_deactivation” “deactivation_error” “awaiting_start_date” “archived”

После создания подписка должна быть в статусе awaiting_transaction

тело запроса

метод /subscriptions post

В запросе будет приходить еще @RequestHeader - x-request-id

пример запроса
![img.png](img.png)
пример успешного ответа , в рамках задачи нужно еще добавить подписке поле is_promo boolean + добавить модель пропоподписки PromoSubscription с полями id promocode(string) type (enum - PROCENT or SUMM )

{

“id”: “54254824”,

“starts_at”: “2021-04-02T13:23:15+00:00”,

“expires_at”: “2022-04-02T13:23:15+00:00”,

“payment_id”: “только после оплаты , пока оплаты не было не возвращать на фронт”,

“email”: “test@rt.ru”,

“pricing_plan”: {

“id”: “123”,

“title”: “Подписка на 1 месяц”,

“period”: {},

“ext_id”: “alpina_1_month”,

“description”: “Каталог электронных и аудиокниг, саммари, видеолекций и подборок по разным темам: личная эффективность и карьерный рост, экономика и бизнес, политика и история, психология и отношения”,

“version”: 1,

“type”: “permanent”

},

“product”: {

“id”: “1”,

“title”: “Описание продукта”

},

“status”: {

“id”: “текущий статус”,

“status_title”: “Описание текущего статуса”

},

“price”: {

“full_price”: 49900,

“price”: 29900,

“promo_price”: 19900

},

“is_promo”: false,

“created_datetime”: “2020-02-01T13:23:15+00:00”,

“updated_datetime”: “2021-02-01T13:34:00+00:00”,

“promocode_info”: {

“is_promocode_applied”: true,

“promocode”: “GAMING2021”

}

}

пример не успешного ответа -

422

{

“title”: “Тариф не существует”,

“message”: “По заданным параметрам не удалось найти тариф для создания подписки”,

“code”: “tariff_not_found”,

“request_id”: “7e055372-15da-4951-8540-a6267555a4f1” -из запроса

}

422

{

“title”: “Не кдалось создать подписку”,

“message”: “По заданным параметрам не удалось создать подписку”,

“code”: “subscription_not_saved”,

“request_id”: “7e055372-15da-4951-8540-a6267555a4f1” -из запроса

}

500

{

“title”: “Не предвиденная ошибка сервера”,

“message”: “Пожалуйста попробуйте позже”,

“code”: “server_error”,

“request_id”: “7e055372-15da-4951-8540-a6267555a4f1” -из запроса

}

Для решение задачи нужно создать новую модель и связи , создать ДТО на входящий запрос + универсальное ДТО для неуспешных ответов на фронт которое собирать в зависимости от результата.

Для тестов добавить в ликвебейз скрипты на заполнение тарифов - сделать 3 тарифа и 1 продукт