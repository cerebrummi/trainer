package vokabeltrainer.cmd;

public interface TextHelper {

    static String cleanText(String text) {
        return text.replaceAll("\\t", " ").replaceAll("\\n", " ")
            .replaceAll("\\r", " ").strip();
    }

    static String cleanTextSanitizeForCsv(String text) {
        return text.replaceAll("\\t", " ").replaceAll("\\n", " ")
            .replaceAll("\\r", " ").replaceAll(",", " ").strip();
    }
}
