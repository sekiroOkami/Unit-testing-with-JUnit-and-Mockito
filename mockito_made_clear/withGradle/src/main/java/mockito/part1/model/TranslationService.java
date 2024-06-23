package mockito.part1.model;

public interface TranslationService {
   default String translate(String text, String sourceLang, String targetLang) {
       return text;
   }
    String translate(String text);
}
