package CodeViz;

import java.io.BufferedReader;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileAnalyzer {

    private int lineCount;
    private int complexCount;
    private String face;

    public void analyze(File file) {

        int lines = 0;
        int keywords = 0;
        boolean hasAuthor = false;
        boolean hasVersion = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines++;
                keywords += countKeywords(line);

                if (line.contains("@author")) hasAuthor = true;
                if (line.contains("@version")) hasVersion = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        lineCount = lines;
        complexCount = keywords;

        if (hasAuthor && hasVersion) {
            face = "Happy";
        }
        else if (hasAuthor || hasVersion) {
            face = "Neutral";
        }
        else {
            face = "Sad";
        }
    }

    public int countKeywords (String line) {
        int count = 0;
        String[] keywords = {"if", "for", "while", "switch"};
        for (String keyword : keywords) {
            Pattern pattern = Pattern.compile("\\b" + keyword + "\\b", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                count++;
            }
        }
        return count;
    }

    public int getLineCount()   {return lineCount;};
    public int getComplexCount() {return complexCount;};
    public String getOverallCount() {return face;};
}
