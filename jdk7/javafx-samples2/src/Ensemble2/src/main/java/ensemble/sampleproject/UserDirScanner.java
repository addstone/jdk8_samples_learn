package ensemble.sampleproject;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

/**
 * Description:  Netbeans UserDirScanner
 * Author: LuDaShi
 * Date: 2021-01-05-15:24
 * UpdateDate: 2021-01-05-15:24
 * FileName: UserDirScanner
 * Version: 0.0.0.1
 * Since: 0.0.0.1
 */
public class UserDirScanner {

    public static NBInstallation[] suitableNBInstallations(File homeDir, String minVersion, Comparator<NBInstallation> comp) {
        File nbUserHome = new File(homeDir, ".netbeans");
        List<NBInstallation> list = allNBInstallations(nbUserHome);
//        System.out.println("All found NetBeans installations: " + list);

        NBInstallation devNbi = null;
        // find dev NBInstallation
        for (NBInstallation nbi : list) {
            // 1.0 version means no version number exists
            if ("1.0".equals(nbi.numVersion()) &&
                "dev".equals(nbi.releaseType()) &&
                "".equals(nbi.releaseVersion())) {
                devNbi = nbi;
            }
        }
        if ("dev".equals(minVersion)) {
            if (devNbi != null) {
                return new NBInstallation[]{devNbi};
            }
            return new NBInstallation[]{};
        }

        Collections.sort(list, comp);
        for (ListIterator listIter = list.listIterator(); listIter.hasNext(); ) {
            NBInstallation nbi = (NBInstallation) listIter.next();
            // in case we don't want dev builds -> || nbi.releaseType().equals("dev")) {
            if (NBInstallation.compareVersions(minVersion, nbi.numVersion()) > 0) {
                listIter.remove();
            }
        }
        Collections.reverse(list);
        // add dev to the end of the list here
        if (devNbi != null) {
            list.add(devNbi);
        }
        return list.toArray(new NBInstallation[list.size()]);
    }

    // returns all valid installations of NB found in ${HOME}/.netbeans
    private static List<NBInstallation> allNBInstallations(File nbUserHome) {
        File files[] = nbUserHome.listFiles(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory();
            }
        });
        List<NBInstallation> list = new ArrayList<NBInstallation>();
        // files might be null here, e.g. if there is no .netbeans folder
        if (files != null) {
            for (File file : files) {
                // creating NB installation is based on userdir
                NBInstallation nbi = new NBInstallation(file);
                if (nbi.isValid()) {
                    list.add(nbi);
                }
            }
        }
        return list;
    }

}
