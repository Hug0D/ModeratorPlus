package fr.defois.modplus.Model;

public class ReasonModel {

    private String _id;
    private String _title;
    private String _description;
    private String _warning;
    private String[][] _recidivism;

    public ReasonModel(String id, String title, String description, String warning, String recidivism) {
        _id = id;
        _title = title;
        _description = description;
        _warning = warning;

        String[] recidivism_without_time = recidivism.split(" ");
        _recidivism = new String[recidivism_without_time.length][];
        for (int i = 0 ; i < recidivism_without_time.length ; i++) {
            _recidivism[i] = recidivism_without_time[i].split(":");
        }
    }

    public String getId() {
        return _id;
    }

    public String getTitle() {
        return _title;
    }

    public String getDescription() {
        return _description;
    }

    public String getWarning() {
        return _warning;
    }

    public String[][] getRecidivism() {
        return _recidivism;
    }
}
