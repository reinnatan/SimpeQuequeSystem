import java.util.ArrayList;
import java.util.List;

public class Doctor {
    private List<Integer> listPatient;
    private int estimateCheckTime;

    public Doctor(int estimateCheckTime){
        this.estimateCheckTime = estimateCheckTime;
        listPatient = new ArrayList<>();
    }

    public List<Integer> getListPatient() {
        return listPatient;
    }

    public int getEstimateCheckTime() {
        return estimateCheckTime;
    }
}
