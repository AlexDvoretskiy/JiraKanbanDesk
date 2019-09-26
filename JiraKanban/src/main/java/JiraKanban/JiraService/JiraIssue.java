package JiraKanban.JiraService;

import com.atlassian.jira.rest.client.api.domain.Issue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class JiraIssue {
    private String label;
    private String hyperlink;
    private String createInfo;
    private String noteSummary;
    private String assigneeInfo;
    private Long priorityID;
    private String priority;
    private String status;
    private Long statusID;
    private String issueType;
    private Long issueTypeID;

    private final String mainLink = "https://job-jira.otr.ru/browse/";

    public JiraIssue(Issue issue){
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String formatDate = dateFormat.format(issue.getCreationDate().toDate());

        label = issue.getKey();
        hyperlink = mainLink + label;
        createInfo = issue.getReporter().getDisplayName() + "\n" + formatDate;
        noteSummary = issue.getSummary();
        assigneeInfo = issue.getAssignee().getDisplayName();
        priority = issue.getPriority().getName();
        priorityID = issue.getPriority().getId();
        status = issue.getStatus().getName();
        statusID = issue.getStatus().getId();
        issueType = issue.getIssueType().getName();
        issueTypeID = issue.getIssueType().getId();
    }

    public String getLabel() {
        return label;
    }

    public String getHyperlink() {
        return hyperlink;
    }

    public String getCreateInfo() {
        return createInfo;
    }

    public String getNoteSummary() {
        return noteSummary;
    }

    public String getAssigneeInfo() {
        return assigneeInfo;
    }

    public Long getPriorityID() {
        return priorityID;
    }

    public String getPriority() {
        return priority;
    }

    public String getMainLink() {
        return mainLink;
    }

    public String getStatus() {
        return status;
    }

    public Long getStatusID() {
        return statusID;
    }

    public String getIssueType() {
        return issueType;
    }

    public Long getIssueTypeID() {
        return issueTypeID;
    }
}
