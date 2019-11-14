package ca.utoronto.utm.mcs.projectcloudinfantry.service;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.content.TextContent;
import org.springframework.stereotype.Service;


@Service
public interface TextPostService {
    TextContent getTextPost(Long oidTextPost);
    TextContent addTextPost(TextContent textPost);
}
