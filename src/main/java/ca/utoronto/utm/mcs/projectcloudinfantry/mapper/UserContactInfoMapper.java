package ca.utoronto.utm.mcs.projectcloudinfantry.mapper;

import ca.utoronto.utm.mcs.projectcloudinfantry.repository.UserContactInfoResult;
import ca.utoronto.utm.mcs.projectcloudinfantry.response.UserContactInfo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserContactInfoMapper {

    public UserContactInfo toUserContactInfo(UserContactInfoResult result) {
        UserContactInfo info = new UserContactInfo();
        info.setEmail(result.getContact().getEmail());
        info.setOidUser(result.getContact().getOidUser());
        info.setUsername(result.getContact().getUsername());
        info.setDescription((result.getContact().getDescription()));
        return info;
    }

    public List<UserContactInfo> toAllUserContactInfo (List<UserContactInfoResult> results) {
        List<UserContactInfo> infoList = new ArrayList<>();
        for (UserContactInfoResult result: results) {
            if (result.getContact() != null) {
                UserContactInfo info = toUserContactInfo(result);
                infoList.add(info);
            }
        }
        return infoList;
    }
}
