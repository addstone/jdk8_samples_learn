package ensemble.model;

import ensemble.Pages;
import ensemble.SampleHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description:  Contains all the information we need about a Ensemble sample
 * Description:  包含我们需要的有关样本的所有信息
 * Author: LuDaShi
 * Date: 2021-01-05-13:59
 * UpdateDate: 2021-01-05-13:59
 * FileName: SampleInfo
 * Version: 0.0.0.1
 * Since: 0.0.0.1
 */
public class SampleInfo {

    private static final Pattern findPackage = Pattern.compile("[ \\t]*package[ \\t]*([^;]*);\\s*");
    private static final Pattern findJavaDocComment = Pattern.compile("\\/\\*\\*(.*?)\\*\\/\\s*", Pattern.DOTALL);
    private final String name;
    private final String sourceFileUrl;
    private final String ensemblePath;
    private final String packageName;
    private final String className;
    private final String description;
    private final String[] apiClasspaths;
    private final String[] relatesSamplePaths;
    private final String[] resourceUrls;

    public SampleInfo(String sourceFileUrl, String unqualifiedClassName, String fileContent) {
        this.sourceFileUrl = sourceFileUrl;
        this.name = SampleHelper.formatName(unqualifiedClassName);
        // extract package
        Matcher matcher = findPackage.matcher(fileContent);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Failed to find package statement in sample file [" + sourceFileUrl + "]");
        }
        packageName = matcher.group(1);
        className = packageName + "." + unqualifiedClassName;
        // build ensemble path
        String[] parts = packageName.substring("ensemble.samples.".length()).split("\\.");
        String path = "";
        for (String part : parts) {
            path = path + '/' + SampleHelper.formatName(part);
        }
        ensemblePath = Pages.SAMPLES + path + "/" + name;
        // parse the src file comment
        matcher = findJavaDocComment.matcher(fileContent);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Failed to find java doc comment in sample file [" + sourceFileUrl + "]");
        }
        String javaDocComment = matcher.group(1);
//            System.out.println("javaDocComment = " + javaDocComment);
        String[] lines = javaDocComment.split("\\n");
//            for (String jdocline:lines) {
//                System.out.println("jdocline = " + jdocline);
//            }
        String[] lines2 = javaDocComment.split("([ \\t]*\\n[ \\t]*\\*[ \\t]*)+");
        StringBuilder descBuilder = new StringBuilder();
        List<String> relatedList = new ArrayList<String>();
        List<String> seeList = new ArrayList<String>();
        List<String> resourceList = new ArrayList<String>();
        for (String jdocline : lines2) {
//                System.out.println("jdocline2 = " + jdocline);
            String trimedLine = jdocline.trim();
            if (trimedLine.length() != 0) {
                if (trimedLine.startsWith("@related")) {
                    relatedList.add(trimedLine.substring(8).trim());
                } else if (trimedLine.startsWith("@see")) {
                    seeList.add(trimedLine.substring(4).trim());
                } else if (trimedLine.startsWith("@resource")) {
                    // todo resolve to a URL
                    resourceList.add(trimedLine.substring(9).trim());
                } else {
                    descBuilder.append(trimedLine);
                    descBuilder.append(' ');
                }
            }
        }
        description = descBuilder.toString();
        relatesSamplePaths = relatedList.toArray(new String[relatedList.size()]);
        apiClasspaths = seeList.toArray(new String[seeList.size()]);
        resourceUrls = resourceList.toArray(new String[resourceList.size()]);
    }

    public String getName() {
        return name;
    }

    public String getSourceFileUrl() {
        return sourceFileUrl;
    }

    public String getEnsemblePath() {
        return ensemblePath;
    }

    public String getClassName() {
        return className;
    }

    public String getDescription() {
        return description;
    }

    public String[] getApiClasspaths() {
        return apiClasspaths;
    }

    public String[] getRelatesSamplePaths() {
        return relatesSamplePaths;
    }

    public String[] getResourceUrls() {
        return resourceUrls;
    }

    @Override
    public String toString() {
        return "SampleInfo{" +
            "\n     sourceFileUrl='" + sourceFileUrl + '\'' +
            "\n     name='" + name + '\'' +
            "\n     packageName='" + packageName + '\'' +
            "\n     ensemblePath='" + ensemblePath + '\'' +
            "\n     className='" + className + '\'' +
            "\n     description='" + description + '\'' +
            "\n     apiClasspaths=" + (apiClasspaths == null ? null : Arrays.asList(apiClasspaths)) +
            "\n     relatesSamplePaths=" + (relatesSamplePaths == null ? null : Arrays.asList(relatesSamplePaths)) +
            "\n     resourceUrls=" + (resourceUrls == null ? null : Arrays.asList(resourceUrls)) +
            "\n}";
    }

}
