package de.budgetfreak.budgetfreakapplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ThisHasIssues {

    public final static void ohoh() throws Exception {
        File file = new File("foo.txt");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
