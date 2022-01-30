package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Selenide;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selectors.byText;
import static org.assertj.core.api.Assertions.assertThat;


public class FileParsingTest {

    private ClassLoader cl = File.class.getClassLoader();

    //скачивание PDF
    @Test
    void parsePdfTest() throws Exception {
        Selenide.open("https://junit.org/junit5/docs/current/user-guide/");
        File pdfDownload = Selenide.$(byText("PDF download")).download();
        PDF parsed = new PDF(pdfDownload);
        assertThat(parsed.author).contains("Marc Philipp");
    }

     //Xls
//    @Test
//    void parseXlsTest() throws Exception {
//        try (InputStream stream = cl.getResourceAsStream("files/sample-xlsx-file.xlsx")) {
//            XLS parsed = new XLS(stream); //stream нужно закрыть потом!!! с помощью try
//            assertThat(parsed.excel.getSheetAt(0).getRow(1).getCell(2).getStringCellValue()
//                    .isEqualTo("Abril");
//        }
//    }


    @Test
    void parseCsvFile() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("files/example.csv")) {
            CSVReader reader = new CSVReader(new InputStreamReader(stream)); //взяли csv
            List<String[]> list = reader.readAll();  //возвращает лист массива строк
            assertThat(list)
                    .hasSize(3) //провери, что там 3 строки
                    .contains(new String[]{"Author", "Book"}, //первая строка такая <-
                            new String[]{"Blok", "Apteka"},
                            new String[]{"Esenin", "Cherniy Chelovek"}
                    );
        }
    }

    //zip
    @Test
    void zipTest() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("files/sample-zip-file.zip");
             ZipInputStream zis = new ZipInputStream(stream)) {
            ZipEntry zipEntry; //упакованные в zip архив папки, объявили переменную
            while ((zipEntry = zis.getNextEntry()) != null) { //пока в стриме есть zip мы можем её получать
                assertThat(zipEntry.getName()).isEqualTo("sample.txt");
            }
        }
        //прочитать содержимое
        ZipFile zf = new ZipFile(new File(cl.getResource("files/sample-zip-file.zip").toURI()));
    }
}