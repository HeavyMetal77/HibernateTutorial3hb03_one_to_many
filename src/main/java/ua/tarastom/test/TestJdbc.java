package ua.tarastom.test;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {
    public static void main(String[] args) {
        String db1 = "hb-05-many-to-many"; //схема БД + не забудь поменять название в hibernate.cfg.xml
        String jdbcUrl = "jdbc:mysql://localhost:3306/" + db1 + "?useSSL=false&serverTimezone=UTC";
        String user = "hbstudent";
        String pass = "hbstudent";

        try {
            System.out.println("Connecting to database: " + jdbcUrl);
            Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
            System.out.println("Connection successful!!!");
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
/*
если IDEA подчеркивает красным аннотированный класс (from Student) ("Cannot resolve simbol")
- https://ru.stackoverflow.com/questions/763151/%D0%A0%D0%B0%D0%B1%D0%BE%D1%82%D0%B0-%D1%81-hql-hibernate

Скорее всего у вас не включена интеграции идеи и БД. Идея подчеркивает потому,
что ничего не знает про схему вашей базы.
В левом столбце снизу (левее дерева файлов) у вас должны быть вкладка Persistence.
Откройте её, там должно быть имя ваше БД. ПКМ на ней и "Assign Data Sources..."
В открывшемся окне слева надо указать коннект к БД и сохранить.
Если вкладки Persistence нет, тогда :
Ctrl + Shift + Alt + S
Facets
добавить jpa и внизу выбрать Default JPA provider - Hibernate
По факту даже не делая этого у вас все должно работать, если все сделали правильно.
Т.к. это ошибка интеграции Intellij IDEA а не проекта
 */

/*
если hibernate не подключен:
- добавить поддержку плагинов jpa и hibernate
- в структуре проекта, в modules - jpa - дефолт jpa провайдер установить - hibernate
- на вкладке слева внизу persistence - ПКМ - assign data sources
- если @Column(name = "id") - id красный - alt+enter - inject language or reference
или refresh...
Убедитесь, что плагин IntelliLang включен, прежде чем начать работу с инъекциями языка.
 */