package core.basesyntax;

import static org.junit.Assert.assertEquals;

import core.basesyntax.service.FileReaderService;
import core.basesyntax.service.impl.FileReadServiceImpl;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.BeforeClass;
import org.junit.Test;

public class FileReaderServiceTest {
    private static FileReaderService fileReaderService;
    private static final String VALID_PATH_FILE = "src/test/resources/date.csv";

    @BeforeClass
    public static void setUp() {
        fileReaderService = new FileReadServiceImpl();
    }

    @Test(expected = NullPointerException.class)
    public void redFromFile_nullFilePath_notOk() {
        fileReaderService.readFromFile(null);
    }

    @Test(expected = RuntimeException.class)
    public void redFromFile_invalidPath_notOk() {
        fileReaderService.readFromFile(Path.of("not_valid_path"));
    }

    @Test
    public void redFromFile_validFilePath_ok() throws IOException {
        Path path = Path.of(VALID_PATH_FILE);
        String actual = fileReaderService.readFromFile(path);
        String expected = Files.readString(path);
        assertEquals("File not read", expected, actual);
    }
}