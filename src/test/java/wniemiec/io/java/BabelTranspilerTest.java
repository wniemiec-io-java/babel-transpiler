package wniemiec.io.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class BabelTranspilerTest {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static final Path RESOURCES;
    private BabelTranspiler babelTranspiler;
    private List<String> errors;


    //-------------------------------------------------------------------------
    //		Initialization block
    //-------------------------------------------------------------------------
    static {
        RESOURCES = Path.of("src", "test", "resources").toAbsolutePath();
    }


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUpMocks() {
        errors = new ArrayList<>();
        babelTranspiler = new BabelTranspiler(error -> errors.add(error));
    }
    

    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testFromCode() throws IOException {
        List<String> code = List.of(
            "const getMessage = () => \"Hello World\";"
        );
        List<String> expectedCode = List.of(
            "\"use strict\";",
            "",
            "var getMessage = function getMessage() {",
            "  return \"Hello World\";",
            "};"
        );
        List<String> obtainedCode = babelTranspiler.fromCode(code);

        Assertions.assertTrue(errors.isEmpty());
        Assertions.assertEquals(expectedCode, obtainedCode);
    }

    @Test
    void testFromFile() throws IOException {
        Path file = RESOURCES.resolve("simpleJsCode.js");
        Path expectedFile = RESOURCES.resolve("expected").resolve("simpleJsCode.js");
        
        List<String> expectedCode = Files.readAllLines(expectedFile);
        List<String> obtainedCode = babelTranspiler.fromFile(file);

        Assertions.assertTrue(errors.isEmpty());
        Assertions.assertEquals(expectedCode, obtainedCode);
    }

    @Test
    void testFromNullCode() throws IOException {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            babelTranspiler.fromCode(null);
        });
    }

    @Test
    void testFromNullFile() throws IOException {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            babelTranspiler.fromFile(null);
        });
    }
}
