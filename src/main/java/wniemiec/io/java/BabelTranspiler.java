/**
 * Copyright (c) William Niemiec.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package wniemiec.io.java;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Consumer;


/**
 * 
 */
public class BabelTranspiler {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final BabelTranspilerScript babelScript;
    private Path tempFileInput;
    private Path tempFileOutput;
    private Path babelLocation;
    private Terminal terminal;
    private Consumer<String> outputErrorHandler;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    /**
     * 
     * 
     * @param       outputErrorHandler Function to handle with error messages
     */
    public BabelTranspiler(Consumer<String> outputErrorHandler) {
        this.outputErrorHandler = outputErrorHandler;
        babelScript = new BabelTranspilerScript();
    }

    public BabelTranspiler() {
        this(null);
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    /**
     * Generates JavaScript ECMA 5 from a JavaScript ECMA 6 code.
     * 
     * @param       code JavaScript ECMA 6 code
     * 
     * @return      JavaScript code
     * 
     * @throws      IOException If code cannot be generated
     * @throws      IllegalArgumentException If code is null
     */
    public List<String> fromCode(List<String> code) throws IOException {
        if (code == null) {
            throw new IllegalArgumentException("Code cannot be null");
        }
        
        setUpTerminal();
        setUpBabelLocation();
        setUpTempInputFile(code);
        setUpTempOutputFile();
        runBabel(tempFileInput);

        return parsedJavaScriptCode();
    }

    private void setUpTerminal() {
        terminal = StandardTerminalBuilder
            .getInstance()
            .outputHandler(null)
            .outputErrorHandler(outputErrorHandler)
            .build();
    }

    private void setUpTempInputFile(List<String> code) throws IOException {
        tempFileInput = createTempInFileWith(code);
    }

    private Path createTempInFileWith(List<String> code) throws IOException {
        Path tmpDir = createTempJsFile("inputJsCode");
        
        Files.write(tmpDir, code, StandardCharsets.UTF_8);

        return tmpDir;
    }

    private Path createTempJsFile(String name) throws IOException {
        Path file = babelLocation.resolve(name + ".js");

        Files.deleteIfExists(file);
        
        return Files.createFile(file);
    }

    private void setUpTempOutputFile() throws IOException  {
        tempFileOutput = createTempOutFile();
    }

    private Path createTempOutFile() throws IOException {
        return createTempJsFile("outputJsCode");
    }

    private void setUpBabelLocation() throws IOException {
        babelLocation = babelScript.getLocation();
    }

    private void runBabel(Path jsFile) throws IOException {
        terminal.clean();
        terminal.exec(
            "npm",
            "exec",
            "--prefix",
            babelLocation.toAbsolutePath().toString(),
            "--",
            "babel",
            jsFile.toAbsolutePath().toString(),
            "-o",
            tempFileOutput.toAbsolutePath().toString()
        );
    }

    private List<String> parsedJavaScriptCode() throws IOException {
        List<String> code = Files.readAllLines(tempFileOutput);

        Files.deleteIfExists(tempFileOutput);
        Files.deleteIfExists(tempFileInput);

        return code;
    }

    /**
     * Generates JavaScript ECMA 5 from a JavaScript ECMA 6 code.
     * 
     * @param       file JavaScript file
     * 
     * @return      JavaScript code
     * 
     * @throws      IOException If code cannot be generated
     * @throws      IllegalArgumentException If file is null
     */
    public List<String> fromFile(Path file) throws IOException {
        if (file == null) {
            throw new IllegalArgumentException("File cannot be null");
        }

        return fromCode(Files.readAllLines(file));
    }


    //-------------------------------------------------------------------------
    //		Setters
    //-------------------------------------------------------------------------
    public void setOutputErrorHandler(Consumer<String> outputErrorHandler) {
        this.outputErrorHandler = outputErrorHandler;
    }
}
