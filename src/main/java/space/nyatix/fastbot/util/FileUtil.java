package space.nyatix.fastbot.util;

import space.nyatix.fastbot.FastBOT;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author Nyatix
 * @created 28.08.2021 - 21:13
 */
public class FileUtil {

    public static void createFiles() {
        if (FastBOT.getInstance().getBotFiles().mkdir())
            System.out.println("» Bot directory files was created!");

        for (String fileName : Arrays.asList(
                "config.json"
        )) {
            try {
                if (new File(fileName).createNewFile()) {
                    System.out.printf("» The %s file has been created!%n", fileName);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
