package JiraKanban.JiraService;

import JiraKanban.Utils.Configurations.ConfigParameters;
import JiraKanban.Utils.Configurations.UserConfiguration;
import JiraKanban.Utils.GUI.JiraAlert;
import JiraKanban.Utils.GUI.ProgressInfoPane;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.SearchRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.util.concurrent.Promise;

import java.util.ArrayList;

public class JiraService {
    private static JiraClient jiraClient;
    private static JiraRestClient restClient;

    private static String login = "";
    private static String password = "";
    private static String jiraUrl = "";

    private static boolean authSuccess = false;
    private static boolean isConnected = false;

    private static String searchQuery;

    private static ArrayList<JiraIssue> issueList;

    private final static String DEFAULT_QUERY = "issuetype in (Improvement, \"Change request\", Request, \"Component Improvement\", Ошибка, Requirement) " +
            "AND status in (Open, \"In Progress\", Reopened, Resolved, \"In Development\", Analysis, \"Information Required\") " +
            "AND assignee in (currentUser())";

    public static void connect() {
        ProgressInfoPane.add("Подключение к Jira");
        jiraClient = new JiraClient(login, password, jiraUrl);
        issueList = new ArrayList<>();

        updateIssueList();

        isConnected = true;
    }

    public static void updateIssueList(){
        issueList.clear();

        try {
            initSearchQuery();

            restClient = jiraClient.getRestClient();
            SearchRestClient searchRestClient = restClient.getSearchClient();
            Promise<SearchResult> searchResult = searchRestClient.searchJql(searchQuery, 500, 0,null);
            ProgressInfoPane.add("Отправлен запрос к Jira");
            ProgressInfoPane.add("Ожидание ответа...");

            for (Issue issue : searchResult.claim().getIssues()) {
                JiraIssue jiraIssue = new JiraIssue(issue);
                issueList.add(jiraIssue);
            }
            authSuccess = true;
            ProgressInfoPane.add("Подключено успешно");
            ProgressInfoPane.add("Получено записей " + issueList.size());
        } catch (Exception e) {
            if (isConnected){
                ProgressInfoPane.add("Пожалуйста проверьте соединение");
            } else {
                e.printStackTrace();
                ProgressInfoPane.add("Ошибка при подключении к Jira \n" + "Пожалуйста проверьте корректность логина и пароля");
            }
        }
    }

    private static void initSearchQuery() {
        String issueType = UserConfiguration.getQueryParameters(JiraMetadata.getTypeValues(), ConfigParameters.TYPE_CLASSPATH);
        String issueStatus = UserConfiguration.getQueryParameters(JiraMetadata.getStatusValues(), ConfigParameters.STATUS_CLASSPATH);
        String issuePriority = UserConfiguration.getQueryParameters(JiraMetadata.getPriorityValues(), ConfigParameters.PRIORITY_CLASSPATH);

        searchQuery = "issuetype in (" + issueType + ") AND status in (" + issueStatus + ") AND priority in (" +
                      issuePriority + ") AND assignee in (currentUser())";

        if(issueType == null || issueStatus == null || issuePriority == null){
            new JiraAlert("Получение данных", "Поле запроса не может быть пустым, измените настройку фильтров");
        }
    }

    public static ArrayList<JiraIssue> getIssueList() {
        return issueList;
    }

    public static void setLogin(String login) {
        JiraService.login = login;
    }

    public static void setPassword(String password) {
        JiraService.password = password;
    }

    public static void setJiraUrl(String jiraUrl) {
        JiraService.jiraUrl = jiraUrl;
    }

    public static boolean isAuthSuccess() {
        return authSuccess;
    }

    public static String getLogin() {
        return login;
    }

    public static String getPassword() {
        return password;
    }

    public static boolean isConnected() {
        return isConnected;
    }
}
