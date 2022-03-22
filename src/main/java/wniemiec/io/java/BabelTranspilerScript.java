package wniemiec.io.java;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;


/**
 * Bridge between Babel transpiler JavaScript file and Java.
 */
class BabelTranspilerScript {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private Path location;

    
    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public Path getLocation() throws IOException {
        if (location == null) {
            location = initializeLocation();
        }

        return location;        
    }

    private Path initializeLocation() throws IOException {
        Path baseDir = buildBaseDir();

        return baseDir
            .resolve("javascript")
            .resolve("babel");
    }

    private static Path buildBaseDir() throws IOException {
        if (!isJarFile()) {
            return getAppRootPath();
        }

        Path tmpDir = Path.of(System.getProperty("java.io.tmpdir"));
        JarFileManager jarManager = new JarFileManager(getAppRootPath());

        return jarManager.extractTo(tmpDir);
    }

    private static Path getAppRootPath() {
        Path binRootPath = getBinRootPath();

        if (isDevelopmentEnvironment(binRootPath)) {
            return binRootPath
                .getParent()
                .getParent()
                .resolve("src")
                .resolve("main");
        }
        
        return Path.of(binRootPath.toString().split("file:")[1]);
    }

    private static Path getBinRootPath() {
        return getAppBinPath()
            .normalize()
            .toAbsolutePath()
            .getParent()
            .getParent()
            .getParent()
            .getParent();
    }

    private static Path getAppBinPath() {
		return urlToPath(BabelTranspilerScript.class.getResource("BabelTranspilerScript.class"));
	}
	
	private static Path urlToPath(URL url) {
		return new File(url.getPath()).toPath();
	}

    private static boolean isDevelopmentEnvironment(Path binRootPath) {
        return binRootPath
            .getFileName()
            .toString()
            .equals("classes");
    }

    private static boolean isJarFile() {
        return JarFileManager.isJarFile(getAppRootPath());
    }
}
