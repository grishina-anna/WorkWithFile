package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.assertj.core.api.Assertions.assertThat;

public class HomeWorkTest {

    private ClassLoader cl = HomeWorkTest.class.getClassLoader();

    @Test
    void zipTest() throws Exception {
        ZipFile zipFile = new ZipFile("src/test/resources/files/filess.zip");

        //Проверка pdf
        ZipEntry pdfEntry = zipFile.getEntry("Akt.pdf");
        try (InputStream stream = zipFile.getInputStream(pdfEntry)) {
            PDF parsed = new PDF(stream);
            assertThat(parsed.text).contains("Акт");
        }

        // Проверка xls
        ZipEntry XlsEntry = zipFile.getEntry("Roles.xlsx");
        try (InputStream stream = zipFile.getInputStream(XlsEntry)) {
            XLS parsed = new XLS(stream);
            assertThat(parsed.excel.getSheetAt(0).getRow(1).getCell(0).getStringCellValue()).isEqualTo("MQ");
        }

        // Проверка csv
        ZipEntry csvEntry = zipFile.getEntry("example.csv");
        try (InputStream stream = zipFile.getInputStream(csvEntry)) {
            CSVReader reader = new CSVReader(new InputStreamReader(stream));
            List<String[]> list = reader.readAll();
            assertThat(list)
                    .hasSize(3)
                    .contains(
                            new String[]{"Author", "Book"},
                            new String[]{"Blok", "Apteka"},
                            new String[]{"Esenin", "Cherniy Chelovek"});
        }
    }
}



