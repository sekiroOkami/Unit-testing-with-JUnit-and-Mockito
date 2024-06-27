package mockito.part4.model.service;

public interface TranslationService {
   default String translate(String text, String sourceLang, String targetLang) {
       return text;
   }
    String translate(String text);
}
