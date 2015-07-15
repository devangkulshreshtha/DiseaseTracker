package devang.developer.com.diseasetracker;

import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by devang kulshreshtha on 4/10/2015.
 */
public class values {
    public static int height=0;
    public static int disease_info[][] = {{1,13},{3,5,6,7,13,12},{4,14,15,16,17},{4,6,5,15,18,19},{39,4,17,13,20,21},{4,5,13,7,22,29},{4,24,25,15,18,26},{5,27,28,29},{30,4,5,31,12},{15,4,23,16},{4,14,13,19,39},{4,5,19,9,32},{19,33,20,4,34,35,23},{3,5,10,15,6,25},{4,5,9,1,20,17,13,22},{14,36,37,3},{10,38,17,13,12}};
    public static double[][] symptom_score = {{.5,.5},{.28571,.142857,.142857,.142857,.142857,.142857},{.2,.2,.2,.2,.2},{.16667,.16667,.16667,.16667,.16666,.16666},{.125,.125,.125,.125,.25,.25},{.1111,.1111,.1111,.2222,.1111,.3333},{.1,.2,.3,.1,.1,.2},{.25,.25,.25,.25},{.3333,.1111,.1111,.2222,.2222},{.28571,.142587,.28571,.28571},{.16667,.16667,.33332,.16667,.16667},{.1,.1,.2,.2,.4},{.1,.1,.2,.1,.3,.1,.1},{.25,.125,.25,.125,.125,.125},{.125,.125,.125,.125,.125,.125,.125,.125},{.1111,.3333,.3333,.2222},{0.3,0.3,0.1,0.1,0.2}};
    public static double disease_score[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    public static Button button;
    public static String riskFactors[] = {"I have high cholestrol", "I have hypertension", "I have diabetes", "I have a history of cancer"};
    public static int selected_risk_factors[] = {0, 0, 0, 0};
    public static String finalDiseases[];
    public static int finalScores[];
    public static String problems[] = {"Eye Problems", "Common Problems", "Egestion Problems", "Pain", "Digestion Problems", "Respiration Problems","Skin Problems", "Other"};
    public static String[] diseases = {"Cholera","Dengue","Diptheria","Influenza","Hepatitis","Malaria","Measles","Meningitis","Mumps","Pnemonia","Poliomelitus","SmallPox","Tubercolosis","Typhoid","Yellow fever","Tonsilitis","Peptic Ulcers"};
    public static int x = 0;
    public static String symptoms_of_respective_problems[][] = {{"Swollen eye","Conjuctivitis"},{"High fever","Mild fever","Headache","Fatigue","Sleepiness","Dry mouth"},{"Diarrhoea","Dark urine"},{"Muscle pain","Neck pain","Joint pain","Back pain","Stomach pain","Leg pain","Abdominal pain","Neck stiffness","Painful swelling of paratoid glands","Heartburn","Body pain"},{"Vomiting","Coughing blood or sputum","Difficulty in swallowing food/liquid"},{"Breathing difficulty","Nausea","Cough","Sore throat","Runny nose"},{"Jaundice", "Rashes", "White spots inside mouth", "Red spots on body", "White spots on tonsils"},{"Loss of apetite","Chills","Intolerance to sound & brightness","Weight loss","Night sweats"}};
    public static int symptoms_of_problems[][] = {{2,24},{3,4,5,19,29,31},{1,21},{6,7,8,9,10,11,12,27,30,38,39},{13,34,36},{16,17,15,14,18},{22,25,26,0,32,37},{20,23,28,33,35}};
    public static int toBeAskedSymptoms[];
    public static String questions[] = {"", "Do you have diarrhea", "Have you got a swollen eye?", "Are you having high fever?", "Are you having mild fever?", "Do you frequently suffer from a headache?", "Did you recently had a muscle cramp/are you having constant muscle pain?", "Do you often feel neck pain?", "Do you feel pain in your joints?", "Do you suffer from a back ache?", "Do you often have pain and irritation in your stomach?", "Do you have internal leg pain?", "Do you feel abdominal pain that lasts for 1-2 weeks?", "Do you sometimes vomit?", "Do you experience a sore throat?", "Do you often cough?", "Are you breathing faster?(breathing difficulty)", "Are you suffering from nausea", "Is your nose stuff/runny?", "Do you feel tired all the time?", "Have you noticed a loss of apetite?", "Do you produce dark urine over the course of the day?", "Do you have jaundice?", "Do you feel chills?", "Do you have conjuctivitis?", "Do you have rashes over your body?", "Do you have white spots inside your mouth?", "Do you constantly feel pain/stiffness in your neck?", "Are you not able to tolerate sound and brightness?", "Do you always feel sleepiness?", "Have you noticed swelling in your paratoid glands?", "Do you feel increased thirst- is your mouth always dry?", "Have you noticed red spots on your body?", "Have you lost weight without any change in your apetite?", "Do you often cough blood or sputum?", "Do you experience nightsweats?", "Do you experience difficulty in swallowing food, especially liquid?", "Do you have white spots ovr your tonsils?", "Does your heart feel like burning at sometimes?", "Do you suffer from overall body pain?"};
    public static int index=0;
    public static int direct = 0;
    public static int counter=0;
    public static int selectesSymptoms2[] = {0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0,};
    public static String[] symptoms={"None","Diarrhoea","Swollen Eye","High Fever","Mild Fever","Headache","Muscle Pain","Neck Pain","Joint Pain","Back pain","Stomach Pain","Leg Pain","Abdominal Pain","Vomiting","Sore Throat","Cough","Breathing Difficulty","Nausea","Runny nose","Fatigue","Loss of apetite","Dark Urine","Jaundice","Chills","Conjuctivitis","Rashes","White spots inside mouth","Neck stiffness","Intolerance to sound & brightness","Sleepiness","Painful swelling of paratoid glands","Dry mouth","Red spots on body","Weight Loss","Coughing Blood or Sputum","Night Sweats","Difficulty in swallowing Food/liquid","White spots on Tonsils","Heartburn","Body Pain"};
    public static int[] selectedsymptoms={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
}
