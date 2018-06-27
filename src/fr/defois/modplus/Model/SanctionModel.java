package fr.defois.modplus.Model;

public class SanctionModel {

    private String _reasonId;
    private String _reasonTitle;
    private int _number;

    public SanctionModel(String reasonId, String reasonTitle, int number) {
        _reasonId = reasonId;
        _reasonTitle = reasonTitle;
        _number = number;
    }

    public String getTitle() {
        return _reasonTitle;
    }

    public int getNb() {
        return _number;
    }
}
