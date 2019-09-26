package JiraKanban.JiraService;

import java.util.ArrayList;
import java.util.HashMap;

public class JiraMetadata {
    private static final long openStatusID = 1;
    private static final long reopenStatusID = 4;
    private static final long inProgressStatusID = 3;
    private static final long analysisStatusID = 10005;
    private static final long infoRequiredStatusID = 10300;
    private static final long developStatusID = 10001;
    private static final long solvedStatusID = 5;
    private static final long closeStatusID = 6;

    private static final String openSection = "openSection";
    private static final String infoRequiredSection = "infoRequiredSection";
    private static final String inProgressSection = "inWorkSection";
    private static final String analysisSection = "analysisSection";
    private static final String doneSection = "doneSection";

    private static final String openStatusValue = "open";
    private static final String reopenStatusValue = "reopen";
    private static final String inProgressStatusValue = "inProgress";
    private static final String analysisStatusValue = "analysis";
    private static final String infoRequiredStatusValue = "infoRequired";
    private static final String developStatusValue = "develop";
    private static final String solvedStatusValue = "solved";
    private static final String closeStatusValue = "closed";

    private static final long blockerPriorityID = 1;
    private static final long criticalPriorityID = 2;
    private static final long highPriorityID = 3;
    private static final long minorPriorityID = 4;
    private static final long trivialPriorityID = 5;

    private static final String blockerPriorityPath = "images/PriorityImg/blockerPriorityImg.png";
    private static final String criticalPriorityPath = "images/PriorityImg/criticalPriorityImg.png";
    private static final String highPriorityPath = "images/PriorityImg/highPriorityImg.png";
    private static final String minorPriorityPath = "images/PriorityImg/minorPriorityImg.png";
    private static final String trivialPriorityPath = "images/PriorityImg/trivialPriorityImg.png";

    private static final String blockerPriorityValue = "blocker";
    private static final String criticalPriorityValue = "critical";
    private static final String highPriorityValue = "major";
    private static final String minorPriorityValue = "minor";
    private static final String trivialPriorityValue = "trivial";

    private static final long requestTypeID = 10504;
    private static final long changeRequestTypeID = 10505;
    private static final long bugIssueTypeID = 1;
    private static final long modificationTypeID = 4;
    private static final long componentDevTypeID = 10101;
    private static final long requirementTypeID = 10506;

    private static final String requestTypePath = "images/IssueTypeImg/requestIssueImg.png";
    private static final String changeRequestTypePath = "images/IssueTypeImg/changeRequestIssue.png";
    private static final String bugIssueTypePath = "images/IssueTypeImg/bugIssueImg.png";
    private static final String modificationTypePath = "images/IssueTypeImg/modificationIssueImg.png";
    private static final String componentDevTypePath = "images/IssueTypeImg/componentDevIssue.png";

    private static final String requestTypeValue = "request";
    private static final String changeRequestTypeValue = "changeRequest";
    private static final String bugIssueTypeValue = "bugIssue";
    private static final String modificationTypeValue = "modification";
    private static final String componentDevTypeValue = "componentDev";
    private static final String requirementTypeValue = "requirement";

    private static HashMap<Long, String> priorityMap;
    private static HashMap<Long, String> issueTypeMap;

    public static String getPathByPriority(long priority){
        initPriorityMap();
        return priorityMap.get(priority);
    }

    public static HashMap initPriorityMap(){
        priorityMap = new HashMap<>();

        priorityMap.put(blockerPriorityID, blockerPriorityPath);
        priorityMap.put(criticalPriorityID, criticalPriorityPath);
        priorityMap.put(highPriorityID, highPriorityPath);
        priorityMap.put(minorPriorityID, minorPriorityPath);
        priorityMap.put(trivialPriorityID, trivialPriorityPath);

        return priorityMap;
    }

    public static String getPathByIssueType(long type){
        initIssueTypeMap();
        return issueTypeMap.get(type);
    }

    public static HashMap initIssueTypeMap(){
        issueTypeMap = new HashMap<>();

        issueTypeMap.put(requestTypeID, requestTypePath);
        issueTypeMap.put(changeRequestTypeID, changeRequestTypePath);
        issueTypeMap.put(bugIssueTypeID, bugIssueTypePath);
        issueTypeMap.put(modificationTypeID, modificationTypePath);
        issueTypeMap.put(componentDevTypeID, componentDevTypePath);

        return issueTypeMap;
    }

    public static HashMap getStatusesBySection(){
        HashMap<String, ArrayList<Long>> statusesMap = new HashMap<>();

        statusesMap.put(openSection, getOpenStatuses());
        statusesMap.put(infoRequiredSection, getInfoRequiredStatuses());
        statusesMap.put(inProgressSection, getInProgressStatuses());
        statusesMap.put(analysisSection, getAnalysisStatuses());
        statusesMap.put(doneSection, getDoneStatuses());

        return statusesMap;
    }

    public static ArrayList getOpenStatuses(){
        ArrayList<Long> list = new ArrayList<>();

        list.add(openStatusID);
        list.add(reopenStatusID);

        return list;
    }

    public static ArrayList getInProgressStatuses(){
        ArrayList<Long> list = new ArrayList<>();

        list.add(inProgressStatusID);
        list.add(developStatusID);

        return list;
    }

    public static ArrayList getAnalysisStatuses(){
        ArrayList<Long> list = new ArrayList<>();

        list.add(analysisStatusID);

        return list;
    }

    public static ArrayList getInfoRequiredStatuses(){
        ArrayList<Long> list = new ArrayList<>();

        list.add(infoRequiredStatusID);

        return list;
    }

    public static ArrayList getDoneStatuses(){
        ArrayList<Long> list = new ArrayList<>();

        list.add(solvedStatusID);
        list.add(closeStatusID);

        return list;
    }

    public static ArrayList<String> getStatusValues(){
        ArrayList<String> list = new ArrayList<>();

        list.add(openStatusValue);
        list.add(reopenStatusValue);
        list.add(inProgressStatusValue);
        list.add(analysisStatusValue);
        list.add(infoRequiredStatusValue);
        list.add(developStatusValue);
        list.add(solvedStatusValue);

        return list;
    }

    public static ArrayList<String> getPriorityValues(){
        ArrayList<String> list = new ArrayList<>();

        list.add(blockerPriorityValue);
        list.add(criticalPriorityValue);
        list.add(highPriorityValue);
        list.add(minorPriorityValue);
        list.add(trivialPriorityValue);

        return list;
    }

    public static ArrayList<String> getTypeValues(){
        ArrayList<String> list = new ArrayList<>();

        list.add(requestTypeValue);
        list.add(changeRequestTypeValue);
        list.add(bugIssueTypeValue);
        list.add(modificationTypeValue);
        list.add(componentDevTypeValue);
        list.add(requirementTypeValue);

        return list;
    }

    public static String getOpenSectionName() {
        return openSection;
    }

    public static String getInfoRequiredSectionName() {
        return infoRequiredSection;
    }

    public static String getInProgressSectionName() {
        return inProgressSection;
    }

    public static String getAnalysisSectionName() {
        return analysisSection;
    }

    public static String getDoneSectionName() {
        return doneSection;
    }

    public static long getBlockerPriorityID() {
        return blockerPriorityID;
    }

    public static long getCriticalPriorityID() {
        return criticalPriorityID;
    }

    public static long getHighPriorityID() {
        return highPriorityID;
    }

    public static long getMinorPriorityID() {
        return minorPriorityID;
    }

    public static long getTrivialPriorityID() {
        return trivialPriorityID;
    }

    public static long getRequestTypeID() {
        return requestTypeID;
    }

    public static long getChangeRequestTypeID() {
        return changeRequestTypeID;
    }

    public static long getBugIssueTypeID() {
        return bugIssueTypeID;
    }

    public static long getModificationTypeID() {
        return modificationTypeID;
    }

    public static long getComponentDevTypeID() {
        return componentDevTypeID;
    }

    public static long getRequirementTypeID() {
        return requirementTypeID;
    }

    public static String getOpenStatusValue() {
        return openStatusValue;
    }

    public static String getReopenStatusValue() {
        return reopenStatusValue;
    }

    public static String getInProgressStatusValue() {
        return inProgressStatusValue;
    }

    public static String getAnalysisStatusValue() {
        return analysisStatusValue;
    }

    public static String getInfoRequiredStatusValue() {
        return infoRequiredStatusValue;
    }

    public static String getDevelopStatusValue() {
        return developStatusValue;
    }

    public static String getSolvedStatusValue() {
        return solvedStatusValue;
    }

    public static String getCloseStatusValue() {
        return closeStatusValue;
    }

    public static String getBlockerPriorityValue() {
        return blockerPriorityValue;
    }

    public static String getCriticalPriorityValue() {
        return criticalPriorityValue;
    }

    public static String getHighPriorityValue() {
        return highPriorityValue;
    }

    public static String getTrivialPriorityValue() {
        return trivialPriorityValue;
    }

    public static String getRequestTypeValue() {
        return requestTypeValue;
    }

    public static String getChangeRequestTypeValue() {
        return changeRequestTypeValue;
    }

    public static String getModificationTypeValue() {
        return modificationTypeValue;
    }

    public static String getComponentDevTypeValue() {
        return componentDevTypeValue;
    }

    public static String getMinorPriorityValue() {
        return minorPriorityValue;
    }

    public static String getRequirementTypeValue() {
        return requirementTypeValue;
    }

    public static String getBugIssueTypeValue() {
        return bugIssueTypeValue;
    }
}
