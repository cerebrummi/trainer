package vokabeltrainer.cmd;

public interface TextHelper {

    static String cleanText(String text) {
        return text
            .strip()
            .replace('\t', ' ')
            .replace('\n', ' ')
            .replace('\r', ' ');
    }

    static String cleanTextSanitizeForCsv(String text) {
        return text
            .replace(',', ' ')
            .strip()
            .replace('\t', ' ')
            .replace('\n', ' ')
            .replace('\r', ' ');
    }
}
