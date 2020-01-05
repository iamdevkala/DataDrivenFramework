package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CsvUtils {
    public Iterator<Object[]> readFile(String file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        List<Object[]> logindata = new ArrayList<Object[]>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] data = line.split(",");

            logindata.add(data);
        }

        return logindata.iterator();
    }
}
