package model;

public class OccultationService {
    public static String occultation(String text,String caracOcult){
        return text.replaceAll("[a-zA-Z0-9]", caracOcult);  // on occulte le texte dans une autre variable
    }


}
