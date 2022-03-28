package org.mskcc.cbio.oncokb.model;

import org.apache.commons.lang3.StringUtils;
import org.mskcc.cbio.oncokb.apiModels.InfoLevel;
import org.mskcc.cbio.oncokb.util.*;

import java.io.Serializable;
import java.util.List;

import static org.mskcc.cbio.oncokb.Constants.IS_PUBLIC_INSTANCE;
import static org.mskcc.cbio.oncokb.Constants.PUBLIC_API_VERSION;

/**
 * Created by Hongxin Zhang on 7/13/18.
 */
public class OncoKBInfo implements Serializable {
    String oncoTreeVersion;
    String ncitVersion;
    List<InfoLevel> levels;
    Version dataVersion;
    SemVer appVersion;
    SemVer apiVersion;
    Boolean publicInstance;

    public OncoKBInfo() {
        Info info = CacheUtils.getInfo();

        this.oncoTreeVersion = info.getOncoTreeVersion();
        this.ncitVersion = info.getNcitVersion();
        this.levels = LevelUtils.getInfoLevels();

        Version dataVersion = new Version();
        dataVersion.setVersion(MainUtils.getDataVersion());
        dataVersion.setDate(MainUtils.getDataVersionDate());

        this.dataVersion = dataVersion;
        this.apiVersion = new SemVer(PUBLIC_API_VERSION);

        String appVersion = this.getClass().getPackage().getImplementationVersion();

        if (StringUtils.isNotEmpty(appVersion)) {
            this.appVersion = new SemVer(appVersion);
        }
        String isPublicInstance = PropertiesUtils.getProperties(IS_PUBLIC_INSTANCE);

        if (isPublicInstance != null && Boolean.valueOf(isPublicInstance)) {
            this.publicInstance = true;
        } else {
            this.publicInstance = false;
        }
    }

    public String getOncoTreeVersion() {
        return oncoTreeVersion;
    }

    public String getNcitVersion() {
        return ncitVersion;
    }

    public List<InfoLevel> getLevels() {
        return levels;
    }

    public Version getDataVersion() {
        return dataVersion;
    }

    public SemVer getAppVersion() {
        return appVersion;
    }

    public Boolean getPublicInstance() {
        return publicInstance;
    }

    public SemVer getApiVersion() {
        return apiVersion;
    }
}
