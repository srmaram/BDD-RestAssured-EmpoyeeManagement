package report;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Report {

    public static void main(String[] args) {

        String projectpath = System.getProperty("user.dir");
        String sapareter =  System.getProperty("file.separator");
        File reportOutputDirectory = new File(projectpath+sapareter+"target");
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add(projectpath+sapareter+"target"+sapareter+"cucumber-json"+sapareter+"1.json");

        String projectName = "cucumberHTMLReport";

        Configuration configuration = new Configuration(reportOutputDirectory, projectName);


        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        Reportable result = reportBuilder.generateReports();
// and here validate 'result' to decide what to do if report has failed
    }
}
