package JiraKanban.Utils.Statistics;

import JiraKanban.JiraService.JiraIssue;
import JiraKanban.JiraService.JiraService;

import java.util.ArrayList;
import java.util.HashMap;

public class StatisticCounter {
    public enum Mode {PRIORITY, STATUS}

    public static HashMap<String, Integer> countIssueQuantityByParameters(Mode parameter){
        HashMap<String, Integer> resultMap = new HashMap<>();
        ArrayList<JiraIssue> issueList = JiraService.getIssueList();

        for(JiraIssue issue: issueList){
            String value;

            if(parameter == Mode.PRIORITY){
                value = issue.getPriority();
            } else if(parameter == Mode.STATUS){
                value = issue.getStatus();
            } else {
                throw new RuntimeException("Illegal enum parameter");
            }

            if(resultMap.containsKey(value)){
                int quantity = resultMap.get(value) + 1;
                resultMap.remove(value);
                resultMap.put(value, quantity);
            }else{
                resultMap.put(value, 1);
            }

        }
        return resultMap;
    }
}
