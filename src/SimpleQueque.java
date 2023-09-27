import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SimpleQueque {
    //map doctor name, list of patient, with value waiting time
    private Map<String,Doctor> listDoctor = new HashMap<String, Doctor>();
    private int counter = 0;
    private int MAX_VAL = Integer.MAX_VALUE;

    public void addDoctor(String doctorName, Integer estimateCheckup){
        Doctor newDoc = new Doctor(estimateCheckup);
        listDoctor.put(doctorName, newDoc);
    }

    public String getCurrentQueueNumber() {
        counter+=1;
        String doctorName = "";

        boolean sameWaiting = true;
        //doing process to put queue patient number into one of list doctor
        for (Map.Entry<String, Doctor> set :
                listDoctor.entrySet()) {
            if (set.getValue().getListPatient().size() < MAX_VAL){
                set.getValue().getListPatient().add(counter);
                MAX_VAL = set.getValue().getListPatient().size();
                sameWaiting = false;
                doctorName = set.getKey();
                break;
            }
        }

        //if all the list of doctor have same number of the patient then put patient in first doctor,
        // and add into first list doctor, and setup max val to queue count of that doctor
        if(sameWaiting){
            String key = listDoctor.keySet().stream().findFirst().get();
            Doctor doctorSelected = listDoctor.get(key);
            doctorSelected.getListPatient().add(counter);
            listDoctor.put(key, doctorSelected);
            doctorName = key;
            MAX_VAL = doctorSelected.getListPatient().size();
        }

        return "Handle by Doctor : "+doctorName+" with queue number : "+counter;
    }

    public void estimatePatientCall(String doctorName, int quequeNumber){
        int currentPatient = 0;
        int doctorETACheckupPatient = 0;
        int currentListWait =  0;
        for (Map.Entry<String, Doctor> set :
                listDoctor.entrySet()) {
            if(set.getKey().equals(doctorName)){
                currentListWait = set.getValue().getListPatient().size();
                doctorETACheckupPatient = set.getValue().getEstimateCheckTime();
                for(Integer currentIdx : set.getValue().getListPatient()){
                    currentPatient+=1;
                    if(currentIdx ==quequeNumber){
                        break;
                    }
                }
                break;
            }
        }
        System.out.println("Current list patient waiting : "+currentListWait);
        System.out.println("ETA patient call : "+currentPatient * doctorETACheckupPatient+" minutes ");
    }

    public void doctorHandlePatient(String doctorName){
        if(listDoctor.containsKey(doctorName)){
            if(listDoctor.get(doctorName).getListPatient().size()>0) {
                int curentUser = listDoctor.get(doctorName).getListPatient().get(0);
                listDoctor.get(doctorName).getListPatient().remove(0);
                System.out.println("user with queue "+curentUser+" is handled by doctor");
            }else{
                System.out.println("No patient with doctor "+doctorName);
            }
        }else{
            System.out.println("No doctor with name "+ doctorName);
        }
    }

    public SimpleQueque (){
        Scanner sc = new Scanner(System.in);
        int optionSelected = -1;
        do{
            display();
            optionSelected = sc.nextInt();

            switch (optionSelected){
                case 1:
                    System.out.print("Name of doctor : ");
                    String nameDoctor = sc.next();
                    System.out.print("Estimate time checkup each patient : ");
                    int etaCheckup = sc.nextInt();
                    addDoctor(nameDoctor, etaCheckup);
                    break;
                case 2:
                    System.out.println(getCurrentQueueNumber());
                    break;
                case 3:
                    System.out.print("Name of doctor : ");
                    nameDoctor = sc.next();
                    System.out.print("Your current Queue : ");
                    int yourQueue = sc.nextInt();
                    estimatePatientCall(nameDoctor, yourQueue);
                    break;
                case 4:
                    System.out.print("Doctor name : ");
                    String doctorName = sc.next();
                    doctorHandlePatient(doctorName);
                    break;
            }

        }while (optionSelected!=5);
    }

    public void display(){
        System.out.println("=============================");
        System.out.println("=== QUEUING DOCTOR SYSTEM ===");
        System.out.println("==============================");
        System.out.println("1. Add Doctor");
        System.out.println("2. Get current Queue");
        System.out.println("3. Get estimate time patient call");
        System.out.println("4. Doctor handle patient");
        System.out.println("5. Exit");
        System.out.println("==============================");
        System.out.print("Your choise : ");
    }

    public static void main(String[] args) {
        new SimpleQueque();
    }
}
