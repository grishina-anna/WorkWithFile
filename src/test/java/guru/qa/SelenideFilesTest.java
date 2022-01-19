package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;
import static io.netty.util.internal.SystemPropertyUtil.contains;

//public class SelenideFilesTest {

    //privatе ClassLoader cl= SelenideFilesTest.class.getClassLoader(); //означает - дай мне тот класс лодер, котрорый был загружен во время программы


// //   @Test
//    void downloadTest() throws Exception {
//        Selenide.open("https://github.com/junit-team/junit5/blob/main/LICENSE.md");
//        File downloadedFile = Selenide.$("#raw-url").download(); //из скаченного файла получаем путь к нему в build, этот путь в объекте класса File
////        System.out.println();
//        //необходимо выбрать первую строку - добавить exception к сигнатуре метода - перекладываем ответственность, перестает гореть красным
//
//// 2 вариант - плохой
//// try {
////            File downloadedFile = Selenide.$("#raw-url").download(); //правкой кнопкой и выбрать Introduce local variable - кладем результат в переменную с типом файл
////        } catch (FileNotFoundException e) {
////            e.printStackTrace(); //будет написано, как мы пришли в этому исключению
////        //    e.getMessage()  - сообщение об ошибке, которое завернуто в это исключение
////            // e.getCause - используется когда исключение завернуто в дургое исключение. с помощью этого метода его можно достать
////            }
//        //подчеркнуто красным, потому что может выбросить исключение
//        //чтобы не было исключения можно либо обернуть в try catch (контекстное меню) - не используется
//        //try {} это код, при выполнении которого мы ожидаем исключение если файла нет (например)
//        // в catch написано какой файл будет обрабатываться (e), если нужного там нет
//
////        Stream.of("Дима", "Петя")  //подход для последовательной обработки одинаковых переменных, Stream API. input stream
////                .map(String::toUpperCase) //преобразовать в верхний регистр
////                .forEach(System.out::println);
//
////в скобках создается input stream, который необходимо автомат. закрыть, когда он будет не нужен
//        try (InputStream is = new FileInputStream(downloadedFile)) { //из этого файла создали inputStream
//            assertThat(new String(is.readAllBytes(), StandardCharsets.UTF_8)) //прочитали все байты из inputStream
//                    .contains("Eclipse License - v 2.0"); //проверяем, что он содержит строку в скобках
//        }
//
//        //иногда у кнопки скачивания нет href (ссылки) и тогда то, что описано выше не сработает. Ниже решение, указывается до части с try ()...
//
////        Configuration.proxyEnabled = true; //пускают трафик между тестом и драйвером через прокси сервер. это уменьшает стабильность тестов
////        Configuration.fileDownload = FileDownloadMode.PROXY;
//
//    }
//
//}
// //  @Test
//    void uploadFile()    {
//        Selenide.open("https://the-internet.herokuapp.com/upload");
//        Selenide.$("input[type='file']").uploadFromClasspath("upload.txt");  //стандартный поиск кнопки загрузки файла на страницу
//        Selenide.$("#file-submit").click();
//        Selenide.$("#upload-files").shouldHave(Condition.text("upload.txt"));
//    }
//}