package spinat.plsqlparser;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;

public class Util {

    public static String loadFile(String filename) throws IOException {
        return IOUtils.toString(FileUtils.openInputStream(new File(filename)));
    }
}
